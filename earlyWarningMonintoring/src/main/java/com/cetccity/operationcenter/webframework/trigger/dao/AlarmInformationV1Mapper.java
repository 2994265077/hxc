package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.backstage.alarm.qo.AlarmQueryObject;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.StrDateRange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@Mapper
public interface AlarmInformationV1Mapper {


    /**
     * 获取序列
     * @return
     */
    @Select("select SEQ_ALARM_INFORMATION.nextval from dual")
    int getSequenceId();

    List<AlarmInformation> selectAlarmInformation(AlarmInformation alarmInformation);

    List<AlarmInformation> selectByAlarmInformationAndDateRange(@Param("alarmInformation") AlarmInformation alarmInformation, @Param("dateRange") StrDateRange dateRange);

    List<AlarmInformation> queryLastAlarmDataReleaseTime(AlarmInformation alarmInformation);

    List<AlarmInformation> queryLastAlarmData(AlarmInformation alarmInformation);

    int updateState(AlarmInformation alarmInformation);

    int updateBatchSelected(List<AlarmInformation> alarmInformations);

    int cancelFFLAlarm(AlarmInformation alarmInformation);

    int insert(AlarmInformation alarmInformation);

    List<AlarmInformation> findLatelyAlarmState(AlarmInformation alarmInformation);

    List<AlarmInformation> query(@Param("alarm_query_vo") AlarmQueryObject alarmQueryVo);

    LocalDateTime selectLastReleaseTime(@Param("alarmInformation") AlarmInformation alarmInformation);

    String selectLastFRowId(@Param("origin_table_name") String originTableName, @Param("alarm_type_lv2s") List<String> alarmTypeLv2s);

    List<AlarmInformation> findAlarmingByAlarmTypeLv2s(@Param("alarm_type_lv2s") List<String> alarmTypeLv2s, @Param("send_state") String sendState);

    List<AlarmInformation> selectAlarmInformationIot(AlarmInformation alarmInformation);
}
