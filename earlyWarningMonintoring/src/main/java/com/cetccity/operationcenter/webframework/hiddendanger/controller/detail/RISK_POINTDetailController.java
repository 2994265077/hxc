package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.RISK_POINTDetailApi;
import com.cetccity.operationcenter.webframework.hiddendanger.service.RISK_POINTDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller.detail
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:02 2019-03-29
 * Updater:     heliangming
 * Update_Date：10:02 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@RestController
public class RISK_POINTDetailController implements RISK_POINTDetailApi {

    @Autowired
    RISK_POINTDetailService rISK_POINTDetailService;

    public NameDataModel findRISK_POINTDetail(String id){
        NameDataModel nameDataModel = new NameDataModel();
        nameDataModel = rISK_POINTDetailService.findRISK_POINTDetail(id);
        return nameDataModel;
    }

    public HashMap<String,Object> riskPointDrillDown(){
       return rISK_POINTDetailService.riskPointDrillDown();
    }
}
