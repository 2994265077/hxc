package com.cetccity.operationcenter.webframework.rundisplay.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.EcoEnvironmentalDetailApi;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.WaterDetailModel;
import com.cetccity.operationcenter.webframework.rundisplay.service.AirStationService;
import com.cetccity.operationcenter.webframework.rundisplay.service.PollutionSourceStationService;
import com.cetccity.operationcenter.webframework.rundisplay.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EcoEnvironmentalDetailController implements EcoEnvironmentalDetailApi {

    @Autowired
    AirStationService airStationService;

    @Autowired
    PollutionSourceStationService pollutionSourceStationService;

    @Autowired
    WaterStationService waterStationService;

    public NameDataModel airStationDetailLatelyTime(String id){
        NameDataModel nameDataModel = airStationService.getDetailAirStationDataOfLatelyTime(id);
        return nameDataModel;
    }

    public NameDataModel airStationLineTime(String id,String type,String startTime,String endTime) {
        Map<String,String> map_time = new HashMap();
        map_time.put("id",id);
        map_time.put("type",type);
        map_time.put("startTime",startTime);
        map_time.put("endTime",endTime);
        NameDataModel nameDataModel = airStationService.getLineAirStationDataOfTime(map_time);
        return nameDataModel;
    }

    public List<Map<String, Object>> airStationListTime(String id, String type, String startTime, String endTime) {
        Map<String,String> map_time = new HashMap();
        map_time.put("id",id);
        map_time.put("type",type);
        map_time.put("startTime",startTime);
        map_time.put("endTime",endTime);
        return airStationService.getListAirStationDataOfTime(map_time);
    }

    public WaterDetailModel pollutionSourceStationDetails(String id) {
        Map<String,String> map_detail = new HashMap();
        map_detail.put("id",id);
        WaterDetailModel waterDetailModel= pollutionSourceStationService.getPollutionSourceStationDataOfDetails(map_detail);
        return waterDetailModel;
    }

    public NameDataModel pollutionSourceStationLineTime(String id,String type,String startTime,String endTime) {
        Map<String,String> map_time = new HashMap();
        map_time.put("id",id);
        map_time.put("type",type);
        map_time.put("startTime",startTime);
        map_time.put("endTime",endTime);
        NameDataModel nameDataModel = pollutionSourceStationService.getLinePollutionSourceStationDataOfTime(map_time);
        return nameDataModel;
    }

    public List<LinkedHashMap> pollutionSourceStationListTime(String id,String type,String startTime,String endTime) {
        Map<String,String> map_time = new HashMap();
        map_time.put("id",id);
        map_time.put("type",type);
        map_time.put("startTime",startTime);
        map_time.put("endTime",endTime);
        List<LinkedHashMap> map_list = pollutionSourceStationService.getListPollutionSourceStationDataOfTime(map_time);
        return map_list;
    }

    public WaterDetailModel waterStationDetails(String id) {
       return waterStationService.getWaterStationDataOfDetails(id);
    }

    public NameDataModel waterStationLineTime(String id,String type,String startTime,String endTime) {
        Map<String,String> map_time = new HashMap();
        map_time.put("id",id);
        map_time.put("type",type);
        map_time.put("startTime",startTime);
        map_time.put("endTime",endTime);
        NameDataModel nameDataModel = waterStationService.getLineWaterStationDataOfTime(map_time);
        return nameDataModel;
    }

    public List<LinkedHashMap> waterStationListTime(String id,String type,String startTime,String endTime) {
        Map<String,String> map_time = new HashMap();
        map_time.put("id",id);
        map_time.put("type",type);
        map_time.put("startTime",startTime);
        map_time.put("endTime",endTime);
        List<LinkedHashMap> map_list = waterStationService.getListWaterStationDataOfTime(map_time);
        return map_list;
    }
}
