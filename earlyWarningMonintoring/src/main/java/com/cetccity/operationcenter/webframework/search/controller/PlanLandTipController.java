package com.cetccity.operationcenter.webframework.search.controller;

import com.cetccity.operationcenter.webframework.search.api.PlanLandTipApi;
import com.cetccity.operationcenter.webframework.search.service.PlanLandTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:27 2019-03-14
 * Updater:     heliangming
 * Update_Date：17:27 2019-03-14
 * 更新描述:    heliangming 补充
 **/
@RestController
public class PlanLandTipController implements PlanLandTipApi{

    @Autowired
    PlanLandTipService planLandTipService;

    public Map addressResolutionTip(String id)throws IOException {
        Map map = planLandTipService.addressResolutionTip(id);
        return map;
    }

    public Map addressWebService_pointTip(String id)throws IOException{
        Map map = planLandTipService.addressWebService_pointTip(id);
        return map;
    }
}
