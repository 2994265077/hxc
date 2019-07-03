/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: RiskPointConverter
 * Author:   YHY
 * Date:     2019/5/21 16:50
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter;

import com.cetccity.operationcenter.webframework.trigger.core.converter.AbstractSingleConverter;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPoint;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPointDangerLevel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/21
 * @since 1.0.0
 */
public class RiskPointConverter extends AbstractSingleConverter<RiskPoint> {
    @Override
    protected Optional<AlarmInformation> recentAlarm(RiskPoint riskPoint) {
        // 不管上次是否预警，都新增
        return Optional.empty();
    }

    @Override
    protected String getAlarmLevel(RiskPoint riskPoint) {
        String dangerLevel = riskPoint.getDANGER_LEVEL();
        if (Objects.nonNull(dangerLevel)) {
            switch (dangerLevel) {
                case "红" : return "一级-红";
                case "橙" : return "二级-橙";
                case "黄" : return "三级-黄";
                case "蓝" : return "四级-蓝";
                default: return dangerLevel;
            }
        }
        return "未知等级";
    }

    @Override
    protected AlarmInformation updateAlarmBean(AlarmInformation lastAlarm, RiskPoint riskPoint) {
        AlarmInformation update = new AlarmInformation();
        update.setOBJECT_ID(lastAlarm.getOBJECT_ID());
        update.setSEND_STATE(0);
        return update;
    }

    @Override
    protected AlarmInformation fillAlarmInformation(AlarmInformation alarmInformation) {
        alarmInformation = super.fillAlarmInformation(alarmInformation);
        alarmInformation.setCHANNEL("安监局");
        alarmInformation.setF_OBJECT_ROW_ID(-1d);
        alarmInformation.setALARM_TYPE_LV1("002");
        alarmInformation.setALARM_TYPE_LV2("002002");
        return alarmInformation;
    }

    @Override
    protected String getOriginTableName() {
        return "RISK_POINT";
    }

    @Override
    protected boolean isExceededThreshold(RiskPoint riskPoint) {
        // 目前暂时只有符合条件的数据源才会触发
        return true;
    }

    @Override
    protected boolean shouldUpdate(Optional<AlarmInformation> recentAlarmOption) {
        if (recentAlarmOption.isPresent()) {
            AlarmInformation alarmInformation = recentAlarmOption.get();
            // 查到上条数据，查看是否达到间隔  达到自动推送间隔
            Date releaseTime = alarmInformation.getRELEASE_TIME();
            LocalDateTime releaseDateTime = LocalDateTime.ofInstant(releaseTime.toInstant(), ZoneId.systemDefault());
            RiskPointDangerLevel riskPointDangerLevel = RiskPointDangerLevel.getByValue(alarmInformation.getALARM_LEVEL());
            long between = riskPointDangerLevel.getChronoUnit().between(releaseDateTime, LocalDateTime.now());
            if (between > riskPointDangerLevel.getPushInterval()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSupport(RiskPoint riskPoint) {
        return true;
    }
}