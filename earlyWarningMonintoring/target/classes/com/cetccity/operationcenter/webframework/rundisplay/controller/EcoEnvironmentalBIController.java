package com.cetccity.operationcenter.webframework.rundisplay.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.EcoEnvironmentalBIApi;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.RIVER_INFO;
import com.cetccity.operationcenter.webframework.rundisplay.service.AirStationService;
import com.cetccity.operationcenter.webframework.rundisplay.service.RIVER_INFOService;
import com.cetccity.operationcenter.webframework.rundisplay.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EcoEnvironmentalBIController implements EcoEnvironmentalBIApi {

    @Autowired
    RIVER_INFOService rIVER_INFOService;

    @Autowired
    WaterStationService waterStationService;

    @Autowired
    AirStationService airStationService;

    public List<RIVER_INFO> riverInfo(){
        List<RIVER_INFO> rIVER_INFO_list = rIVER_INFOService.getRIVER_INFO();
        return rIVER_INFO_list;
    }

    public NameDataModel waterStationAverageLineDay(){
        NameDataModel nameDataModel = waterStationService.waterStationAverageLineDay();
        return nameDataModel;
    }

    public NameDataModel alarmWaterStatistics(){
        NameDataModel nameDataModel = waterStationService.alarmWaterStatistics();
        return nameDataModel;
    }

    public NameDataModel alarmAirStatistics(){
        NameDataModel nameDataModel = airStationService.alarmAirStatistics();
        return nameDataModel;
    }

    public NameDataModel airStationAverageLineDay(){
        NameDataModel nameDataModel = airStationService.airStationAverageLineDay();
        return nameDataModel;
    }

    public NameDataModel airStationAverageAQILineDay(){
        NameDataModel nameDataModel = airStationService.airStationAverageAQILineDay();
        return nameDataModel;
    }
}
