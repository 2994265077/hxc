/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AbstractIotEventConverter
 * Author:   YHY
 * Date:     2019/5/14 15:56
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter.iot;

import com.cetccity.operationcenter.webframework.trigger.core.converter.AbstractSingleConverter;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEventUnion;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/14
 * @since 1.0.0
 */
public abstract class AbstractIotEventConverter extends AbstractSingleConverter<IotEventUnion> {

    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;

    protected AlarmInformation updateAlarmBean(AlarmInformation lastAlarm, IotEventUnion iotEventUnion) {
        AlarmInformation updateBean = new AlarmInformation();
        updateBean.setOBJECT_ID(lastAlarm.getOBJECT_ID());
        updateBean.setF_ROW_ID(Double.parseDouble(iotEventUnion.getIotEventId()));
        LocalDateTime produceTime = iotEventUnion.getProduceTime();
        if (Objects.nonNull(produceTime)) {
            Instant instant = produceTime.atZone(ZoneId.systemDefault()).toInstant();
            updateBean.setCANCEL_TIME(Date.from(instant));
        } else {
            updateBean.setCANCEL_TIME(new Date());
        }
        updateBean.setALARM_STATE(0);
        return updateBean;
    }

    @Override
    public boolean isSupport(IotEventUnion iotEventUnion) {
        return getIotType().getObjectCodes().contains(iotEventUnion.getDataCode());
    }

    protected Optional<AlarmInformation> recentAlarm(IotEventUnion iotEventUnion) {
        AlarmInformation condition = new AlarmInformation();
        condition.setORIGIN_TABLE_NAME(getOriginTableName());
        condition.setF_OBJECT_ROW_ID(Double.parseDouble(iotEventUnion.getDeviceId()));
        condition.setALARM_TYPE_LV2(getIotType().getAlarmTypeLv2());
        List<AlarmInformation> latelyAlarmState = alarmInformationMapper.findLatelyAlarmState(condition);
        if (CollectionUtils.isNotEmpty(latelyAlarmState)) {
            return Optional.ofNullable(latelyAlarmState.get(0));
        }
        return Optional.empty();
    }


    protected String getOriginTableName() {
        return "IOT_EVENT";
    }


    protected boolean isExceededThreshold(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDataValue().intValue() > 0;
    }

    protected boolean shouldUpdate(Optional<AlarmInformation> recentAlarmOption) {
        return recentAlarmOption.isPresent() && Integer.valueOf(1).equals(recentAlarmOption.get().getALARM_STATE());
    }


    protected AlarmInformation newAlarmBean(IotEventUnion iotEventUnion) {
        AlarmInformation alarmInformation = super.newAlarmBean(iotEventUnion);
        alarmInformation.setCONTENTS(getAlarmContents(iotEventUnion));
        alarmInformation.setALARM_OBJECT(getAlarmObject(iotEventUnion));
        alarmInformation.setALARM_LEVEL(getAlarmLevel(iotEventUnion));
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        alarmInformation.setALARM_TYPE_LV1(getIotType().getAlarmTypeLv1());
        alarmInformation.setALARM_TYPE_LV2(getIotType().getAlarmTypeLv2());
        return alarmInformation;
    }

    @Override
    protected AlarmInformation fillAlarmInformation(AlarmInformation alarmInformation) {
        alarmInformation = super.fillAlarmInformation(alarmInformation);
        alarmInformation.setCHANNEL("物联网平台");
        return alarmInformation;
    }

    protected abstract String getAlarmContents(IotEventUnion iotEventUnion);

    protected abstract String getAlarmLevel(IotEventUnion iotEventUnion);

    protected abstract String getAlarmObject(IotEventUnion iotEventUnion);

    protected abstract Enums.IotTypeEnum getIotType();

}