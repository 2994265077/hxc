package com.cetccity.operationcenter.webframework.urbansign.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UrbanComponentService {

    NameValueModel leftOne(String street);

    NameValueModel leftTwo();

    List<NameValueModel> leftThree(String street);

    NameDataModel rigthOne(String street);

    List<NameValueModel> rigthTwo(String street)throws IOException;

    Map rigthThree(String street);

    HeatMap componentNumGradientLoadMap(String street);

    Map componentNumGradientTip(String id);
}
