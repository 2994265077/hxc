package com.cetccity.operationcenter.webframework.urbansign.controller.map;

import com.cetccity.operationcenter.webframework.urbansign.api.map.UrbansignTipApi;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanBasicLegalPersonService;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanBuildingBasicService;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanComponentService;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanPopulationBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class UrbansignTipController implements UrbansignTipApi {

    @Autowired
    UrbanPopulationBasicService urbanPopulationBasicService;

    @Autowired
    UrbanBasicLegalPersonService urbanBasicLegalPersonService;

    @Autowired
    UrbanBuildingBasicService urbanBuildingBasicService;

    @Autowired
    UrbanComponentService urbanComponentService;

    public Map populationDensityTip(String id,String tag){
        Map map = urbanPopulationBasicService.populationDensityTip(id,tag);
        return map;
    }

    public Map LegalPersonThermalPowerTip(String id){
        Map map = urbanBasicLegalPersonService.LegalPersonThermalPowerTip(id);
        return map;
    }

    public Map buildNumGradientTip(String id){
        Map map = urbanBuildingBasicService.buildNumGradientTip(id);
        return map;
    }

    public Map houseNumGradientTip(String id){
        Map map = urbanBuildingBasicService.houseNumGradientTip(id);
        return map;
    }

    public Map componentNumGradientTip(String id){
        Map map = urbanComponentService.componentNumGradientTip(id);
        return map;
    }
}
