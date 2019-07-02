package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.WEATHER_ALARM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WEATHER_ALARMMapper {

    List<WEATHER_ALARM> getWEATHER_ALARMOfToday(@Param("time_weather") String time_weather);
}
