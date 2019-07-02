/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: SafeProduceTriggerServiceImpl
 * Author:   YHY
 * Date:     2019/4/23 10:43
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.RiskPointMapper;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPoint;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPointDangerLevel;
import com.cetccity.operationcenter.webframework.trigger.utils.CheckData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/23
 * @since 1.0.0
 */
@Service
public class SafeProduceTriggerServiceImpl {

    @Autowired
    private RiskPointMapper riskPointMapper;

    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;

    public List<RiskPoint> querySourceData() {
        return riskPointMapper.selectByDangerLevel(Arrays.asList(RiskPointDangerLevel.values()));
    }

    public String getOriginTableName() {
        return "RISK_POINT";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void triggerAlarm() {
        List<RiskPoint> riskPoints = querySourceData();
        List<AlarmInformation> informationList = riskPoints.parallelStream()
                .map(this::row2Alarm)
                .filter(alarmInformation -> alarmInformation.getALARM_STATE() == 1)
                .map(alarmInformation -> {
                    // 上次生成的预警
                    AlarmInformation lastAlarmData = queryLastAlarmData(alarmInformation.getORIGIN_TABLE_NAME(), alarmInformation
                            .getF_ROW_ID());
                    if (Objects.isNull(lastAlarmData) || Objects.isNull(lastAlarmData.getOBJECT_ID())) {
                        // 未查到上条数据 新生成报警
                        return Optional.ofNullable(alarmInformation);
                    } else {
                        // 查到上条数据，查看是否达到间隔
                        Date releaseTime = lastAlarmData.getRELEASE_TIME();
                        LocalDateTime releaseT = LocalDateTime.ofInstant(releaseTime.toInstant(), ZoneId.systemDefault());
                        LocalDateTime minus = LocalDateTime.now()
                                .minus(
                                        RiskPointDangerLevel.getByValue(alarmInformation.getALARM_LEVEL()).getPushInterval(),
                                        RiskPointDangerLevel.getByValue(alarmInformation.getALARM_LEVEL()).getChronoUnit()
                                );
                        if (minus.isAfter(releaseT)) {
                            // 达到间隔 需要重新推送
                            AlarmInformation update = new AlarmInformation();
                            update.setOBJECT_ID(lastAlarmData.getOBJECT_ID());
                            update.setSEND_STATE(0);
                            return Optional.ofNullable(update);
                        } else {
                            // 未达到间隔，不用处理
                            return Optional.empty();
                        }
                    }
                })
                .filter(Optional::isPresent)
                .map(optional -> (AlarmInformation) optional.get())
                .filter(CheckData::checkFieldNotNull)
                .collect(Collectors.toList());

        releaseAll(informationList);
    }

    public AlarmInformation row2Alarm(RiskPoint riskPoint) {
        String jsonString = JSONObject.toJSONString(riskPoint);
        AlarmInformation alarmInformation = JSONObject.parseObject(jsonString, AlarmInformation.class);
        //一般不变的指标
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
        alarmInformation.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));           //事件ID
        alarmInformation.setRELEASE_PERSON("预警监测平台");           //发布人
        alarmInformation.setDISPOSAL_STATE(0);      //处置状态，默认0，未处置
        alarmInformation.setSEND_STATE(0);      //发送状态 发送给综治平台，默认1，已发送
        //一般需要定制化的指标
        alarmInformation.setALARM_STATE(1);              //预警状态，1预警中，0取消预警
        alarmInformation.setF_OBJECT_ROW_ID( -1d);
        alarmInformation.setALARM_TYPE_LV1("002");         //事件预警
        alarmInformation.setALARM_TYPE_LV2("002002");     //应急突发事件预警
        alarmInformation.setCHANNEL("安监局");   //渠道
        return alarmInformation;
    }


    public void releaseAll(List<AlarmInformation> informationList) {
        List<AlarmInformation> insertList = informationList.stream()
                .filter(alarmInformation -> Objects.isNull(alarmInformation.getOBJECT_ID()))
                .collect(Collectors.toList());

        List<AlarmInformation> updateList = informationList.stream()
                .filter(alarmInformation -> Objects.nonNull(alarmInformation.getOBJECT_ID()))
                .collect(Collectors.toList());

        insertList.stream()
                .forEach(alarmInformation ->
                    alarmInformationMapper.insert(alarmInformation)
                );
        if (CollectionUtils.isNotEmpty(updateList)) {
            alarmInformationMapper.updateBatchSelected(updateList);
        }

    }

    public AlarmInformation queryLastAlarmData(String originTableName, double fRowId) {
        Page<AlarmInformation> page = new Page<AlarmInformation>(1,1);
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(originTableName);
        alarmInformation.setF_ROW_ID(fRowId);
        List<AlarmInformation> result = alarmInformationMapper.queryLastAlarmData(alarmInformation);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }

}