package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.api.UrbanComponentApi;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class UrbanComponentController implements UrbanComponentApi {

    @Autowired
    UrbanComponentService urbanComponentService;

    public NameValueModel leftOne(String street){
        NameValueModel nameValueModel = urbanComponentService.leftOne(street);
        return nameValueModel;
    }

    public NameValueModel leftTwo(){
        NameValueModel nameValueModel = urbanComponentService.leftTwo();
        return nameValueModel;
    }

    public List<NameValueModel> leftThree(String street){
        List<NameValueModel> nameValueModel_list = urbanComponentService.leftThree(street);
        return nameValueModel_list;
    }

    public NameDataModel rightOne(String street){
        NameDataModel nameDataModel = urbanComponentService.rigthOne(street);
        return nameDataModel;
    }

    public List<NameValueModel> rightTwo(String street)throws IOException {
        List<NameValueModel> nameValueModel_list = urbanComponentService.rigthTwo(street);
        return nameValueModel_list;
    }

    public Map rightThree(String street){
        Map map = urbanComponentService.rigthThree(street);
        return map;
    }
}
