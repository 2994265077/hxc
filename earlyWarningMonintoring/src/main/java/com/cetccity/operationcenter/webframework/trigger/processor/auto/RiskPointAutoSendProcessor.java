/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: RiskPointAutoSendProcessor
 * Author:   YHY
 * Date:     2019/5/22 10:35
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.processor.auto;

import com.cetccity.operationcenter.webframework.trigger.core.processor.AbstractAlarmAutoProcessor;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPointDangerLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/22
 * @since 1.0.0
 */
public class RiskPointAutoSendProcessor extends AbstractAlarmAutoProcessor {

    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;



    @Override
    public Optional<AlarmInformation> createProcessBean(AlarmInformation oldAlarm) {
        AlarmInformation updateBean = new AlarmInformation();
        updateBean.setOBJECT_ID(oldAlarm.getOBJECT_ID());
        updateBean.setSEND_STATE(0);
        return Optional.ofNullable(updateBean);
    }

    @Override
    protected Optional<AlarmInformation> genericProcessAlarmBean(AlarmInformation alarmInformation) {
        String alarmLevel = alarmInformation.getALARM_LEVEL();
        // 上次推送的时间为更新时间  更新时间为空则取创建时间  都为空则不进行推送
        Date preTime = alarmInformation.getYJJC_UPDATE_TIME();
        if (Objects.isNull(preTime)) {
            preTime = alarmInformation.getYJJC_CREATE_TIME();
        }
        if (StringUtils.isNotBlank(alarmLevel) && Objects.nonNull(preTime)) {
            RiskPointDangerLevel riskPointDangerLevel = RiskPointDangerLevel.getByValue(alarmLevel);
            ChronoUnit chronoUnit = riskPointDangerLevel.getChronoUnit();
            Long pushInterval = riskPointDangerLevel.getPushInterval();
            long duration = chronoUnit.between(LocalDateTime.ofInstant(preTime.toInstant(), ZoneId.systemDefault()), LocalDateTime.now());
            if (duration >= pushInterval) {
                return createProcessBean(alarmInformation);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<AlarmInformation> sources() {
        return alarmInformationMapper.findAlarmingByAlarmTypeLv2s(Arrays.asList("002002"), "1");
    }
}