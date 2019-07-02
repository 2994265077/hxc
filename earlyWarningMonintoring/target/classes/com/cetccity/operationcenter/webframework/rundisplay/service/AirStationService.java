package com.cetccity.operationcenter.webframework.rundisplay.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface AirStationService {

    NameDataModel getDetailAirStationDataOfLatelyTime(String siteCode);

    NameDataModel getLineAirStationDataOfTime(Map<String,String> map_time);

    List<Map<String, Object>> getListAirStationDataOfTime(Map<String,String> map_time);

    NameDataModel alarmAirStatistics();

    NameDataModel airStationAverageLineDay();

    NameDataModel airStationAverageAQILineDay();
}
