package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.api.UrbanBuildingBasicApi;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanBuildingBasicService;
import com.cetccity.operationcenter.webframework.web.model.citySign.UrbanBasicLegalPersonLeftOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class UrbanBuildingBasicController implements UrbanBuildingBasicApi {

    @Autowired
    UrbanBuildingBasicService urbanBuildingBasicService;

    public NameValueModel leftOne(String street){
        NameValueModel nameValueModel = urbanBuildingBasicService.leftOne(street);
        return nameValueModel;
    }

    public NameValueModel leftTwo(String street){
        NameValueModel nameValueModel = urbanBuildingBasicService.leftTwo(street);
        return nameValueModel;
    }

    public NameValueModel leftThree(String street){
        NameValueModel nameValueModel = urbanBuildingBasicService.leftThree(street);
        return nameValueModel;
    }

    public List<NameValueModel> leftFour(String street){
        List<NameValueModel> nameValueModel_list = urbanBuildingBasicService.leftFour(street);
        return nameValueModel_list;
    }

    public List<NameDataModel> rightOne(String street){
        List<NameDataModel> nameDataModel_list = urbanBuildingBasicService.rightOne(street);
        return nameDataModel_list;
    }

    public List<NameValueModel> rightTwo(String street){
        List<NameValueModel> nameValueModel_list = urbanBuildingBasicService.rightTwo(street);
        return nameValueModel_list;
    }

    public List<NameValueModel> rightThree(String street){
        List<NameValueModel> nameValueModel_list = urbanBuildingBasicService.rightThree(street);
        return nameValueModel_list;
    }

    public List<NameValueModel> rightFour(String street){
        List<NameValueModel> nameValueModel_list = urbanBuildingBasicService.rightFour(street);
        return nameValueModel_list;
    }

}
