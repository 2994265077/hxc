package com.cetccity.operationcenter.webframework.urbansign.controller.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.urbansign.api.map.UrbansignLoadMapApi;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import com.cetccity.operationcenter.webframework.urbansign.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrbansignLoadMapController implements UrbansignLoadMapApi {

    @Autowired
    UrbanPopulationBasicService urbanPopulationBasicService;

    @Autowired
    UrbanBasicLegalPersonService urbanBasicLegalPersonService;

    @Autowired
    UrbanBuildingBasicService urbanBuildingBasicService;

    @Autowired
    UrbanComponentService urbanComponentService;

    public HeatMap populationDensity(String street, String tag){
        HeatMap heatMap = urbanPopulationBasicService.populationDensity(street,tag);
        return heatMap;
    }

    public HeatMap LegalPersonThermalPower(String street){
        HeatMap heatMap = urbanBasicLegalPersonService.LegalPersonThermalPower(street);
        return heatMap;
    }

    public HeatMap buildNumGradientLoadMap(String street){
        HeatMap nameDataModel = urbanBuildingBasicService.buildNumGradientLoadMap(street);
        return nameDataModel;
    }

    public HeatMap houseNumGradientLoadMap(String street){
        HeatMap nameDataModel = urbanBuildingBasicService.houseNumGradientLoadMap(street);
        return nameDataModel;
    }

    public HeatMap componentNumGradientLoadMap(String street){
        HeatMap heatMap = urbanComponentService.componentNumGradientLoadMap(street);
        return heatMap;
    }

}
