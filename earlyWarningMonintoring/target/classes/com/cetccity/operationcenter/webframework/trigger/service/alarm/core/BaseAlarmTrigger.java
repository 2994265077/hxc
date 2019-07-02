package com.cetccity.operationcenter.webframework.trigger.service.alarm.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.utils.CheckData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/17 17:54
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/17 17:54
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/


@Service
public class BaseAlarmTrigger {

    protected static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    protected AlarmInformationV1Mapper alarmInformationMapper;

    /**
     * 查询某一张预警来源表在预警表中,生成的最后一条预警信息的发布时间
     */
    protected Date queryLastAlarmDataReleaseTime(String originTableName) {
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(originTableName);
        List<AlarmInformation> result = alarmInformationMapper.queryLastAlarmDataReleaseTime(alarmInformation);
        return result.size() == 0 ? new Date(0) : result.get(0).getRELEASE_TIME();
    }

    /**
     * 查询该预警对象最后一条预警信息
     * 保证主体唯一性：来源表+objectId
     *
     * 将 RELEASE_TIME 降序 改为 object_id 降序，因为时间可能有重复的，导致获取不到最后一条预警
     */
    protected AlarmInformation queryLastAlarmData(String originTableName, double fObjectRowId) {
        Page<AlarmInformation> page = new Page<AlarmInformation>(1,1);
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(originTableName);
        alarmInformation.setF_OBJECT_ROW_ID(fObjectRowId);
        List<AlarmInformation> result = alarmInformationMapper.queryLastAlarmData(alarmInformation);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }


    /**
     * 取消预警
     * @param systemId  事件编号
     * @param cancelTime  取消发布时间
     * @return
     */
    protected int cancelAlarm(String systemId, Date cancelTime) {
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setALARM_STATE(0);
        alarmInformation.setCANCEL_TIME(cancelTime);
        alarmInformation.setSYSTEM_ID(systemId);
        return alarmInformationMapper.updateState(alarmInformation);
    }

    /**
     * 发布一条新预警
     * 1.需要先存放至数据库，
     * 2.然后推送，修改推送时间。推送是其他模块实现。
     * @param model
     * @return
     */
    protected int releaseAlarm(AlarmInformation model) {
        int object_id = alarmInformationMapper.getSequenceId();
        model.setOBJECT_ID(object_id);
        if (!CheckData.checkFieldNotNull(model)){
            throw new RuntimeException("Alarm is error! Some field is null that should not be null!");
        }
        return alarmInformationMapper.insert(model);
    }
}
