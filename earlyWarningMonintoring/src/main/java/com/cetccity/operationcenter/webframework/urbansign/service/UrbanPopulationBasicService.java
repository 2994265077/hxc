package com.cetccity.operationcenter.webframework.urbansign.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.XYAxisData;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import com.cetccity.operationcenter.webframework.urbansign.api.model.Tbl_pojo_futianApi;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface UrbanPopulationBasicService {

    List<NameValueUnitModel> getLeftOne(String street, String community);

    XYAxisData getLeftTwo(String street);

    HttpResponseModel<ChartDetailModel> getRightOne(String street);

    NameDataModel getRightTwo(String street, String community);

    NameDataModel getRightThree(String street);

    NameDataModel getRightFour(String street);

    NameDataModel rightFive(String street,String community);

    NameDataModel rightSix(String street);

    LinkedHashMap getRightEight(String street, String community);

    NameDataModel getRightTen(String street);

    NameDataModel getRightEleven(String street,String community);

    HeatMap populationDensity(String street, String tag);

    Map populationDensityTip(String id,String tag);

    List<Tbl_pojo_futianApi> labourPool();

    HttpResponseModel<ChartDetailModel> rightThirteen(String street, String type);

}
