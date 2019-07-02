package com.cetccity.operationcenter.webframework.publichealth.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.publichealth.api.BiChartOfOutpatientApi;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartOfOutpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:17 2019-02-28
 * Updater:     heliangming
 * Update_Date：9:17 2019-02-28
 * 更新描述:    heliangming 补充
 **/
@RestController
public class BiChartOfOutpatientController implements BiChartOfOutpatientApi {

    @Autowired
    BiChartOfOutpatientService biChartOfOutpatientService;

    public List<NameValueModel> outpatientLatelyYearn(){
        List<NameValueModel> nameValueModel_list = biChartOfOutpatientService.outpatientLatelyYearn();
        return nameValueModel_list;
    }

    public List<NameValueModel> outpatientHospital(){
        List<NameValueModel> nameValueModel_list = biChartOfOutpatientService.outpatientHospital();
        return nameValueModel_list;
    }

    public List<NameValueModel> outpatientHospitalNearMonth(){
        List<NameValueModel> nameValueModel_list = biChartOfOutpatientService.outpatientHospitalNearMonth();
        return nameValueModel_list;
    }

    public NameDataModel outpatientHospitalDepartment(){
        return biChartOfOutpatientService.outpatientHospitalDepartment();
    }
}
