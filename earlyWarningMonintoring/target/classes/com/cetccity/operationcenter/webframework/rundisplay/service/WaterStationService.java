package com.cetccity.operationcenter.webframework.rundisplay.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.WaterDetailModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface WaterStationService {

    WaterDetailModel getWaterStationDataOfDetails(String id);

    NameDataModel getLineWaterStationDataOfTime(Map<String,String> map_time);

    List<LinkedHashMap> getListWaterStationDataOfTime(Map<String,String> map_time);

    NameDataModel waterStationAverageLineDay();

    NameDataModel alarmWaterStatistics();

}
