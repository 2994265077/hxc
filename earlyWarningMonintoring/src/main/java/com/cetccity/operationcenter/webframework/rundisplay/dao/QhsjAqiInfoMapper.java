package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.AirLevelCount;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_AQI_INFO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface QhsjAqiInfoMapper {

    List<QHSJ_AQI_INFO> getQHSJ_AQI_INFO(QHSJ_AQI_INFO QHSJ_AQI_INFO);

    List<QHSJ_AQI_INFO> getQHSJ_AQI_INFODataOfTime(Map<String,String> map_time);

    List<AirLevelCount> selectAirLevelDayCount(@Param("min_monitor_time") LocalDateTime minMonitorTime);
}
