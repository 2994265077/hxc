package com.cetccity.operationcenter.webframework.environment.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.environment.api.CleanRiverToDrainFacilitiesBiApi;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverToDrainFacilitiesBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:09 2019-05-29
 * Updater:     heliangming
 * Update_Date：9:09 2019-05-29
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CleanRiverToDrainFacilitiesBiController implements CleanRiverToDrainFacilitiesBiApi{

    @Autowired
    CleanRiverToDrainFacilitiesBiService cleanRiverToDrainFacilitiesBiService;

    public List<NameDataModel> rightOne(String street, String SEWERATE_ID){
        return cleanRiverToDrainFacilitiesBiService.rightOne(street, SEWERATE_ID);
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(String street, String SEWERATE_ID){
        return cleanRiverToDrainFacilitiesBiService.rightTwo(street, SEWERATE_ID);
    }

    public HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID){
        return cleanRiverToDrainFacilitiesBiService.rightThree(street, SEWERATE_ID);
    }

    public HttpResponseModel<ChartDetailModel> rightFour(String SEWERATE_ID){
        return cleanRiverToDrainFacilitiesBiService.rightFour(SEWERATE_ID);
    }

    public HttpResponseModel<ChartDetailModel> rightFive(){
        return cleanRiverToDrainFacilitiesBiService.rightFive();
    }
}
