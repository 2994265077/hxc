package com.cetccity.operationcenter.webframework.publichealth.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.publichealth.api.BiChartSpecialDiseasesApi;
import com.cetccity.operationcenter.webframework.publichealth.api.model.EMPLOYEE_NUM;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartSpecialDiseasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:45 2019-03-06
 * Updater:     heliangming
 * Update_Date：16:45 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@RestController
public class BiChartSpecialDiseasesController implements BiChartSpecialDiseasesApi {

    @Autowired
    BiChartSpecialDiseasesService biChartSpecialDiseasesService;

    public List<NameDataModel> specialDiseasesTrend(){
        List<NameDataModel> nameDataModel_list = biChartSpecialDiseasesService.specialDiseasesTrend();
        return nameDataModel_list;
    }

    public List<NameDataModel> specialDiseasesHospital(){
        List<NameDataModel> nameDataModel_list = biChartSpecialDiseasesService.specialDiseasesHospital();
        return nameDataModel_list;
    }

    public List<NameValueModel> specialDiseasesNum(){
        List<NameValueModel> nameValueModel_list = biChartSpecialDiseasesService.specialDiseasesNum();
        return nameValueModel_list;
    }

    public List<NameValueModel> specialDiseasesProportion(){
        List<NameValueModel> nameValueModel_list = biChartSpecialDiseasesService.specialDiseasesProportion();
        return nameValueModel_list;
    }
}
