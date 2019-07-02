/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: BlkChengguanEventTriggerService
 * Author:   YHY
 * Date:     2019/5/9 9:15
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.dao.BlkChengguanEventMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.BlkChengguanEvent;
import com.cetccity.operationcenter.webframework.trigger.utils.CheckData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈
 *      eventSource = 6 && isdelete = 0  &&  (state=0 or 1)  &&  (duration from eventTime and now > timelimit) 报警
 *      eventSource = 6 && isdelete = 0  &&  (state > 1)  &&  (duration from eventTime and now > timelimit) 取消报警
 * 〉
 *
 * @author yhy
 * @create 2019/5/9
 * @since 1.0.0
 */
@Service
@Transactional
public class BlkChengguanEventTriggerService {
    @Autowired
    private BlkChengguanEventMapper blkChengguanEventMapper;

    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;

    public List<BlkChengguanEvent> querySource() {
        return blkChengguanEventMapper.queryAllByCondition();
    }

    public void trigger() {
        List<BlkChengguanEvent> blkChengguanEvents = querySource();
        String jsonString = JSONObject.toJSONString(blkChengguanEvents);
        List<AlarmInformation> informationList = JSONObject.parseArray(jsonString, AlarmInformation.class);
        informationList = informationList.stream()
                .peek(alarmInformation -> {
                    // 填充预警对象
                    // 预警内容= “地址”+ “sjms 事件描述”
                    alarmInformation.setCONTENTS(alarmInformation.getADDRESS() + alarmInformation.getCONTENTS());
                    alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
                    alarmInformation.setRELEASE_PERSON("预警监测平台");           //发布人
                    alarmInformation.setSEND_STATE(1);      //发送状态 发送给综治平台，默认1，已发送
                    //一般需要定制化的指标
                    alarmInformation.setF_OBJECT_ROW_ID(-1d);
                    alarmInformation.setALARM_TYPE_LV1("002");         //事件预警
                    alarmInformation.setALARM_TYPE_LV2("002001");     //应急突发事件预警
                    alarmInformation.setCHANNEL("安监局");
                })
                .filter(alarmInformation -> Objects.nonNull(alarmInformation.getDISPOSAL_STATE()))
                .map(alarmInformation -> {
                    // 查询上次预警对象
                    Optional<AlarmInformation> optional = queryLastAlarm(alarmInformation);
                    // 如果上次预警对象不存在
                    // 如果本次状态为 <= 1 新增预警 state = 0 or 1  此state通过json序列化和DISPOSAL_STATE关联
                    if (!optional.isPresent()) {
                        if (alarmInformation.getDISPOSAL_STATE().intValue() <= 1) {
                            alarmInformation.setALARM_STATE(1);
                            // state = 0 需要发送  state = 1 不再发送
                            alarmInformation.setSEND_STATE(alarmInformation.getDISPOSAL_STATE());
                            return Optional.of(alarmInformation);
                        } else {
                            alarmInformation.setALARM_STATE(0);
                            alarmInformation.setCANCEL_TIME(alarmInformation.getRELEASE_TIME());
                            return Optional.of(alarmInformation);
                        }

                    } else {
                        // 如果上次预警存在
                        // 如果本次状态 > 1 取消预警 state >1 更新
                        if (alarmInformation.getDISPOSAL_STATE().intValue() > 1) {
                            AlarmInformation updateInformation = new AlarmInformation();
                            updateInformation.setOBJECT_ID(optional.get().getOBJECT_ID());
                            updateInformation.setCANCEL_TIME(new Date());
                            updateInformation.setALARM_STATE(0);
                            return Optional.of(updateInformation);
                        }
                    }

                    return Optional.empty();
                })
                .filter(Optional::isPresent)
                .map(optional -> (AlarmInformation) optional.get())
                .filter(CheckData::checkFieldNotNull)
                .collect(Collectors.toList());
        doRelease(informationList);
    }


    public String getOriginTableName() {
        return "BLK_CHENGGUAN_EVENT";
    }

    public void doRelease(List<AlarmInformation> alarmInformationList) {
        List<AlarmInformation> insertAlarms = alarmInformationList.stream()
                .filter(alarmInformation -> Objects.isNull(alarmInformation.getOBJECT_ID()))
                .collect(Collectors.toList());
        List<AlarmInformation> updateAlarms = alarmInformationList.stream()
                .filter(alarmInformation -> Objects.nonNull(alarmInformation.getOBJECT_ID()))
                .collect(Collectors.toList());

        insertAlarms.stream()
                .forEach(alarmInformation ->
                        alarmInformationMapper.insert(alarmInformation)
                );
        if (CollectionUtils.isNotEmpty(updateAlarms)) {
            alarmInformationMapper.updateBatchSelected(updateAlarms);
        }
    }

    public Optional<AlarmInformation> queryLastAlarm(AlarmInformation alarmInformation) {
        AlarmInformation condition = new AlarmInformation();
        condition.setF_ROW_ID(alarmInformation.getF_ROW_ID());
        condition.setORIGIN_TABLE_NAME(getOriginTableName());
        List<AlarmInformation> result = alarmInformationMapper.queryLastAlarmData(condition);
        return CollectionUtils.isEmpty(result) ? Optional.empty() : Optional.ofNullable(result.get(0));
    }

}