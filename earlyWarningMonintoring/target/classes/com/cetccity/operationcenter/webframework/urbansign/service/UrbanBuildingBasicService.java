package com.cetccity.operationcenter.webframework.urbansign.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;

import java.util.List;
import java.util.Map;

public interface UrbanBuildingBasicService {

    NameValueModel leftOne(String street);

    NameValueModel leftTwo(String street);

    NameValueModel leftThree(String street);

    List<NameValueModel> leftFour(String street);

    List<NameDataModel> rightOne(String street);

    List<NameValueModel> rightTwo(String street);

    List<NameValueModel> rightThree(String street);

    List<NameValueModel> rightFour(String street);

    HeatMap buildNumGradientLoadMap(String street);

    HeatMap houseNumGradientLoadMap(String street);

    Map buildNumGradientTip(String id);

    Map houseNumGradientTip(String id);

}
