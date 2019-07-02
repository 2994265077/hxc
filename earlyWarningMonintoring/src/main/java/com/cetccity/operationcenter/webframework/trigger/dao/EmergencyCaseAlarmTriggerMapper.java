package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.WeeklyEmergencyCase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.cetc.cloud.alarm.trigger.dao
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/17 20:05
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/17 20:05
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface EmergencyCaseAlarmTriggerMapper {

    List<WeeklyEmergencyCase> querySourceData(Map map);

}
