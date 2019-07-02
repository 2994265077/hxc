package com.cetccity.operationcenter.webframework.publichealth.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.publichealth.api.BiChartHospitalizationApi;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartHospitalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:09 2019-03-06
 * Updater:     heliangming
 * Update_Date：9:09 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@RestController
public class BiChartHospitalizationController implements BiChartHospitalizationApi {

    @Autowired
    BiChartHospitalizationService biChartHospitalizationService;

    public List<NameValueModel> hospitalizationLatelyYearn(){
        List<NameValueModel> nameValueModel_list = biChartHospitalizationService.hospitalizationLatelyYearn();
        return nameValueModel_list;
    }

    public List<NameValueModel> hospitalizationHospital(){
        List<NameValueModel> nameValueModel_list = biChartHospitalizationService.hospitalizationHospital();
        return nameValueModel_list;
    }

    public List<NameValueModel> hospitalizationHospitalNearMonth(){
        List<NameValueModel> nameValueModel_list = biChartHospitalizationService.hospitalizationHospitalNearMonth();
        return nameValueModel_list;
    }

    public NameDataModel hospitalizationHospitalDepartment(){
        return biChartHospitalizationService.hospitalizationHospitalDepartment();
    }
}
