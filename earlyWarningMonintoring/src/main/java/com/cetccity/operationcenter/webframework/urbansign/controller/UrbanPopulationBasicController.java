package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.XYAxisData;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.UrbanPopulationBasicApi;
import com.cetccity.operationcenter.webframework.urbansign.api.model.Tbl_pojo_futianApi;
import com.cetccity.operationcenter.webframework.urbansign.service.BLK_DISABLED_PEOPLEService;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanPopulationBasicService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@Slf4j
public class UrbanPopulationBasicController implements UrbanPopulationBasicApi {

    @Autowired
    UrbanPopulationBasicService urbanPopulationBasicService;

    @Autowired
    BLK_DISABLED_PEOPLEService bLK_DISABLED_PEOPLEService;

    public List<NameValueUnitModel> leftOne(String street) throws IOException {
        List<NameValueUnitModel> nameValueUnitModel_list = urbanPopulationBasicService.getLeftOne(street);
        return nameValueUnitModel_list;
    }

    public XYAxisData leftTwo(String street){
        XYAxisData xY_Axis_Data = urbanPopulationBasicService.getLeftTwo(street);
        return xY_Axis_Data;
    }

    public HttpResponseModel<ChartDetailModel> rightOne(String street){
        return urbanPopulationBasicService.getRightOne(street);
    }

    public NameDataModel rightTwo(String street, String community){
        NameDataModel nameDataModel = urbanPopulationBasicService.getRightTwo(street,community);
        return nameDataModel;
    }

    public NameDataModel rightThree(String street){
        NameDataModel nameDataModel = urbanPopulationBasicService.getRightThree(street);
        return nameDataModel;
    }

    public NameDataModel rightFour(String street){
        NameDataModel nameDataModel = urbanPopulationBasicService.getRightFour(street);
        return nameDataModel;
    }

    public NameDataModel rightFive(String street, String community){
        NameDataModel nameDataModel = urbanPopulationBasicService.rightFive(street,community);
        return nameDataModel;
    }

    public NameDataModel rightSix(String street){
        NameDataModel nameDataModel = urbanPopulationBasicService.rightSix(street);
        return nameDataModel;
    }

    public NameDataModel rightSeven(String street, String community){
        NameDataModel nameDataModel = new NameDataModel();
        //NameDataModel nameDataModel = urbanPopulationBasicService.getRightFour(street,community);
        return nameDataModel;
    }

    public LinkedHashMap rightEight(String street, String community){
        LinkedHashMap map = urbanPopulationBasicService.getRightEight(street,community);
        return map;
    }

    //残疾人群数量及增长率
    public NameDataModel rightNight(String street, String community){
        NameDataModel nameDataModel = bLK_DISABLED_PEOPLEService.getRightNight(street,community);
        return nameDataModel;
    }

    public NameDataModel rightTen(String street){
        NameDataModel nameDataModel = urbanPopulationBasicService.getRightTen(street);
        return nameDataModel;
    }

    public NameDataModel rightEleven(String street, String community){
        NameDataModel nameDataModel = urbanPopulationBasicService.getRightEleven(street,community);
        return nameDataModel;
    }

    public NameDataModel rightTwelve(String street, String community){
        NameDataModel nameDataModel = new NameDataModel();
        //NameDataModel nameDataModel = urbanPopulationBasicService.getRightFour(street,community);
        return nameDataModel;
    }

    public List<Tbl_pojo_futianApi> labourPool(){
        List<Tbl_pojo_futianApi> result =urbanPopulationBasicService.labourPool();
        return result;
    }

    public HttpResponseModel<ChartDetailModel> rightThirteen(String street, String type){
        return urbanPopulationBasicService.rightThirteen(street, type);
    }

    public HttpResponseModel<ChartDetailModel> rightThirteenDrillDown(String street, String name){
        return urbanPopulationBasicService.rightThirteenDrillDown(street, name);
    }

}
