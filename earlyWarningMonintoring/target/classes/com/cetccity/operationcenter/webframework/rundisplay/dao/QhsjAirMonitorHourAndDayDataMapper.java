package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QhsjAirMonitorHourAndDayData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@Mapper
@CacheConfig(cacheNames = "QhsjAirMonitorHourAndDayDataMapper")
public interface QhsjAirMonitorHourAndDayDataMapper {

    @Cacheable(key="#map_time + 'getAirStationDataOfTime'")
    List<QhsjAirMonitorHourAndDayData> getAirStationDataOfTime(Map<String,String> map_time);

    @Cacheable(key="#map_time + 'getAirStationDataOfDay'")
    List<QhsjAirMonitorHourAndDayData> getAirStationValueOfTime(Map<String,String> map_time);

    @Cacheable(key="#map_time + 'getAirStationDataOfDay'")
    List<QhsjAirMonitorHourAndDayData> getAirStationDataOfDay(Map<String,String> map_time);

    @Cacheable(key="#siteCode + 'recentAirValueBySite'")
    List<NameValueUnitModel> recentAirValueBySite(@Param("siteCode") String siteCode);
}
