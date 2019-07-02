package com.cetccity.operationcenter.webframework.rundisplay.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.WaterDetailModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface PollutionSourceStationService {

    WaterDetailModel getPollutionSourceStationDataOfDetails(Map<String,String> map_detail);

    NameDataModel getLinePollutionSourceStationDataOfTime(Map<String,String> map_time);

    List<LinkedHashMap> getListPollutionSourceStationDataOfTime(Map<String,String> map_time);
}
