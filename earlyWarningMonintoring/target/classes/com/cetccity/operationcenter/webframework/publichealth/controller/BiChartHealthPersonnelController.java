package com.cetccity.operationcenter.webframework.publichealth.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.publichealth.api.BiChartHealthPersonnelApi;
import com.cetccity.operationcenter.webframework.publichealth.api.model.EMPLOYEE_NUM;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartHealthPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:51 2019-03-06
 * Updater:     heliangming
 * Update_Date：9:51 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@RestController
public class BiChartHealthPersonnelController implements BiChartHealthPersonnelApi {

    @Autowired
    BiChartHealthPersonnelService biChartHealthPersonnelService;

    public List<NameValueModel> healthPersonnelNum(){
        List<NameValueModel> nameValueModel_list = biChartHealthPersonnelService.healthPersonnelNum();
        return nameValueModel_list;
    }

    public List<NameValueModel> healthPersonnelAuthorizedNum(){
        List<NameValueModel> aUTHORIZED_NUM_list = biChartHealthPersonnelService.healthPersonnelAuthorizedNum();
        return aUTHORIZED_NUM_list;
    }

    public List<NameValueModel> healthPersonnelDOCTOR_TYPENum(){
        List<NameValueModel> aUTHORIZED_NUM_list = biChartHealthPersonnelService.healthPersonnelDOCTOR_TYPENum();
        return aUTHORIZED_NUM_list;
    }

    public List<NameValueModel> healthPersonnelJOB_LEVEL_CODENum(){
        List<NameValueModel> aUTHORIZED_NUM_list = biChartHealthPersonnelService.healthPersonnelJOB_LEVEL_CODENum();
        return aUTHORIZED_NUM_list;
    }

    public List<NameValueModel> healthPersonnelCSZYLBDMNum(){
        List<NameValueModel> aUTHORIZED_NUM_list = biChartHealthPersonnelService.healthPersonnelCSZYLBDMNum();
        return aUTHORIZED_NUM_list;
    }
}
