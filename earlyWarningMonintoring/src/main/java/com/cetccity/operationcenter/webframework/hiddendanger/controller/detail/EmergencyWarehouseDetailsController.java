package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.EmergencyWarehouseDetailsApi;
import com.cetccity.operationcenter.webframework.web.service.EMERGENCY_MATERIAL_MEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmergencyWarehouseDetailsController implements EmergencyWarehouseDetailsApi {

    @Autowired
    EMERGENCY_MATERIAL_MEMService eMERGENCY_MATERIAL_MEMService;

    public NameDataModel findEmergencyWarehouseDetails(String name) throws Exception{
        NameDataModel nameDataModel = eMERGENCY_MATERIAL_MEMService.findEmergencyWarehouseDetails(name);
        return nameDataModel;
    }

}
