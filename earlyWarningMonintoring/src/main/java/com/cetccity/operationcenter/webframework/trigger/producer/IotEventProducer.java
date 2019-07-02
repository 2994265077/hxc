/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: IotP    @Autowired
roducer
 * Author:   YHY
 * Date:     2019/5/20 15:45
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.producer;

import com.cetccity.operationcenter.webframework.trigger.core.producer.IdProducer;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.dao.IotAlarmTriggerMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEventUnion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/20
 * @since 1.0.0
 */
public class IotEventProducer implements IdProducer<IotEventUnion> {
    @Autowired
    private IotAlarmTriggerMapper iotAlarmTriggerMapper;
    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;

    private final static String ORIGIN_TABLE_NAME = "IOT_EVENT";

    @Override
    public String previousId() {
        String id = alarmInformationMapper.selectLastFRowId(ORIGIN_TABLE_NAME, null);
        return StringUtils.isBlank(id) ? DEFAULT_ID_VALUE : id;
    }

    @Override
    public Collection<IotEventUnion> produce() {
        List<IotEventUnion> iotEventUnions = iotAlarmTriggerMapper.querySourceByMinId(previousId());
        return iotEventUnions
                .stream()
                .filter(iotEventUnion -> Objects.nonNull(iotEventUnion.getDeviceId()))
                .collect(Collectors.toList());
    }

    public String getOriginTableName() {
        return ORIGIN_TABLE_NAME;
    }

}