package com.cetccity.operationcenter.webframework.environment.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.environment.api.CleanRiverToDrainHoldBiApi;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverToDrainHoldBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:03 2019-05-27
 * Updater:     heliangming
 * Update_Date：18:03 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CleanRiverToDrainHoldBiController implements CleanRiverToDrainHoldBiApi {

    @Autowired
    CleanRiverToDrainHoldBiService cleanRiverToDrainHoldBiService;

    public List<NameDataModel> rightOne(String street){
        return cleanRiverToDrainHoldBiService.rightOne(street);
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(String street){
        return cleanRiverToDrainHoldBiService.rightTwo(street);
    }

    public HttpResponseModel<ChartDetailModel> rightThree(String street){
        return cleanRiverToDrainHoldBiService.rightThree(street);
    }

    public HttpResponseModel<ChartDetailModel> rightFour(String street){
        return cleanRiverToDrainHoldBiService.rightFour(street);
    }
}
