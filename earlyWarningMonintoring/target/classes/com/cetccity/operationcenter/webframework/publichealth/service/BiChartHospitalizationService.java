package com.cetccity.operationcenter.webframework.publichealth.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:10 2019-03-06
 * Updater:     heliangming
 * Update_Date：9:10 2019-03-06
 * 更新描述:    heliangming 补充
 **/
public interface BiChartHospitalizationService {

    List<NameValueModel> hospitalizationLatelyYearn();

    List<NameValueModel> hospitalizationHospital();

    List<NameValueModel> hospitalizationHospitalNearMonth();

    NameDataModel hospitalizationHospitalDepartment();
}
