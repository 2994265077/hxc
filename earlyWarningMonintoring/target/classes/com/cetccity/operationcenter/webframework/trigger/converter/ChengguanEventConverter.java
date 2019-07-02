/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ChengguanEventConverter
 * Author:   YHY
 * Date:     2019/5/21 16:03
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.trigger.core.converter.AbstractSingleConverter;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.BlkChengguanEvent;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/21
 * @since 1.0.0
 */
public class ChengguanEventConverter extends AbstractSingleConverter<BlkChengguanEvent> {

    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;

    @Override
    protected Optional<AlarmInformation> recentAlarm(BlkChengguanEvent blkChengguanEvent) {
        AlarmInformation condition = new AlarmInformation();
        condition.setSYSTEM_ID(blkChengguanEvent.getSystemId());
        condition.setORIGIN_TABLE_NAME(getOriginTableName());
        List<AlarmInformation> alarmInformations = alarmInformationMapper.queryLastAlarmData(condition);
        if (CollectionUtils.isNotEmpty(alarmInformations)) {
            return Optional.ofNullable(alarmInformations.get(0));
        }
        return Optional.empty();
    }

    @Override
    protected AlarmInformation updateAlarmBean(AlarmInformation lastAlarm, BlkChengguanEvent blkChengguanEvent) {
        AlarmInformation cancelAlarmBean = new AlarmInformation();
        cancelAlarmBean.setOBJECT_ID(lastAlarm.getOBJECT_ID());
        Date cancelTime = new Date();
        LocalDateTime eventTime = blkChengguanEvent.getEventTime();
        if (Objects.nonNull(eventTime)) {
            Instant instant = eventTime.atZone(ZoneId.systemDefault()).toInstant();
            cancelTime = Date.from(instant);
        }
        cancelAlarmBean.setCANCEL_TIME(cancelTime);
        cancelAlarmBean.setALARM_STATE(0);
        return cancelAlarmBean;
    }

    @Override
    protected AlarmInformation fillAlarmInformation(AlarmInformation alarmInformation) {
        alarmInformation = Objects.nonNull(alarmInformation) ? alarmInformation : new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        alarmInformation.setRELEASE_PERSON("预警监测平台");
        alarmInformation.setALARM_STATE(1);
        // 不需推送
        alarmInformation.setSEND_STATE(1);
        alarmInformation.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));
        alarmInformation.setCONTENTS(alarmInformation.getADDRESS() + alarmInformation.getCONTENTS());
        alarmInformation.setCHANNEL("安监局");
        alarmInformation.setF_OBJECT_ROW_ID(-1d);
        alarmInformation.setALARM_TYPE_LV1("002");         //事件预警
        alarmInformation.setALARM_TYPE_LV2("002001");     //应急突发事件预警
        return alarmInformation;
    }

    @Override
    protected String getOriginTableName() {
        return "BLK_CHENGGUAN_EVENT";
    }

    @Override
    protected boolean isExceededThreshold(BlkChengguanEvent blkChengguanEvent) {
        String state = blkChengguanEvent.getState();
        if (StringUtils.isNotBlank(state)) {
            int stateValue = Integer.parseInt(state);
            return  stateValue == 0 || stateValue == 1;
        }
        throw CetcCommonException.defaultException("预警转换器， 三小巡查state 为空");
    }

    @Override
    protected boolean shouldUpdate(Optional<AlarmInformation> recentAlarmOption) {
        return recentAlarmOption.isPresent() && 1 < recentAlarmOption.get().getALARM_STATE();
    }

    @Override
    public boolean isSupport(BlkChengguanEvent blkChengguanEvent) {
        return true;
    }
}