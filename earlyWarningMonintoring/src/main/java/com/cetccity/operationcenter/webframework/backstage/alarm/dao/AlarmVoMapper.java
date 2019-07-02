package com.cetccity.operationcenter.webframework.backstage.alarm.dao;

import com.cetccity.operationcenter.webframework.backstage.alarm.domain.AlarmVo;
import com.cetccity.operationcenter.webframework.backstage.alarm.qo.AlarmQueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.backstage.alarm.dao
 * @Project: futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/5/17 14:42
 * @Updater: huangzezhou
 * @Update_Date: 2019/5/17 14:42
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface AlarmVoMapper {

    List<AlarmVo> query(@Param("alarm_query_vo") AlarmQueryObject alarmQueryObject);

    AlarmVo queryById(@Param("id") String objectId);

}
