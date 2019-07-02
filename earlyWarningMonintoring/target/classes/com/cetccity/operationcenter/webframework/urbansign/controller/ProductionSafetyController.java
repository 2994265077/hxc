package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.ProductionSafetyApi;
import com.cetccity.operationcenter.webframework.urbansign.service.ProductionSafetyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:13 2019-06-03
 * Updater:     heliangming
 * Update_Date：10:13 2019-06-03
 * 更新描述:    heliangming 补充
 **/
@RestController
public class ProductionSafetyController implements ProductionSafetyApi {

    @Autowired
    ProductionSafetyService productionSafetyService;

    public List<NameValueModel> xiaoSanProject(String type){
        return productionSafetyService.xiaoSanProject(type);
    }

    public HttpResponseModel<ChartDetailModel> sanXiaoPlaceToBar(){
        return productionSafetyService.sanXiaoPlaceToBar();
    }

    public HttpResponseModel<ChartDetailModel> riskPointToBar(){
        return productionSafetyService.riskPointToBar();
    }
}
