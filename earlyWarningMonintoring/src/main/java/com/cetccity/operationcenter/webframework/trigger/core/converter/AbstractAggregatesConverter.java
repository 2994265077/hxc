/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AbstractAggregatesConverter
 * Author:   YHY
 * Date:     2019/5/16 17:55
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.converter;


import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractAggregatesConverter<T> implements AggregatesAlarmConverter<T> {

    protected Set<AggregatesAlarmConverter<T>> converterSet = new HashSet<>();

    public void addConverter(AggregatesAlarmConverter<T> aggregatesAlarmConverter) {
        this.converterSet.add(aggregatesAlarmConverter);
    }

    @Override
    public abstract Collection<AlarmInformation> convert(Collection<T> t);


    protected abstract String getOriginTableName();

    protected AlarmInformation defaultAlarmInformation() {
        AlarmInformation alarm = new AlarmInformation();
        //一般不变的指标
        alarm.setF_ROW_ID(-1d);
        alarm.setORIGIN_TABLE_NAME(getOriginTableName());
        alarm.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));
        alarm.setCANCEL_TIME(null);
        alarm.setRELEASE_PERSON("预警监测平台");
        alarm.setDISPOSAL_STATE(0);
        alarm.setSEND_STATE(1);
        alarm.setYJJC_CREATE_TIME(null);
        alarm.setYJJC_UPDATE_TIME(null);
        alarm.setALARM_STATE(1);
        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
        alarm.setALARM_OBJECT("无");    //主体名
        alarm.setF_OBJECT_ROW_ID(-1D);     //主体主键,事件没有主体，默认为-1
        alarm.setJD84("");
        alarm.setWD84("");
        alarm.setADDRESS("");
        alarm.setREGION_CODE("");
        alarm.setSTREET_CODE("");
        alarm.setSTREET_NAME("");
        alarm.setCOMMUNITY_CODE("");
        alarm.setCOMMUNITY_NAME("");
        alarm.setLDDM(null);
        return alarm;
    }

}