package com.cetccity.operationcenter.webframework.publichealth.service;

import com.cetccity.operationcenter.webframework.publichealth.dao.entity.*;
import com.github.pagehelper.PageInfo;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:38 2019-02-27
 * Updater:     heliangming
 * Update_Date：11:38 2019-02-27
 * 更新描述:    heliangming 补充
 **/
public interface HospitalDetailService {

    PageInfo<YJJC_QWJJ_OUT_VISITS_DAY_V> hospitalOutpatientInformation(String ORG_CODE, Integer pageNum, Integer pageSize);

    PageInfo<YJJC_QWJJ_IN_VISITS_DAY_V> hospitalHospitalizationInformation(String ORG_CODE, Integer pageNum, Integer pageSize);

    PageInfo<YJJC_QWJJ_EMPLOYEE_V> hospitalHealthPersonnel(String ORG_CODE, Integer pageNum, Integer pageSize);

    PageInfo<YJJC_QWJJ_ORG_V> hospitalDepartmentInformation(String ORG_CODE, Integer pageNum, Integer pageSize);

    PageInfo<YJJC_QWJJ_SDM_INFO_V> hospitalSpecialDiseases(String ORG_CODE, Integer pageNum, Integer pageSize);
}
