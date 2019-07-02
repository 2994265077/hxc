package com.cetccity.operationcenter.webframework.publichealth.dao;

import com.cetccity.operationcenter.webframework.publichealth.api.model.EMPLOYEE_NUM;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_EMPLOYEE_V;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:57 2019-02-27
 * Updater:     heliangming
 * Update_Date：15:57 2019-02-27
 * 更新描述:    heliangming 补充
 **/
public interface YJJC_QWJJ_EMPLOYEE_VMapper {

    List<YJJC_QWJJ_EMPLOYEE_V> getYJJC_QWJJ_EMPLOYEE_V(YJJC_QWJJ_EMPLOYEE_V yJJC_QWJJ_EMPLOYEE_V);

    int getCount(YJJC_QWJJ_EMPLOYEE_V yJJC_QWJJ_EMPLOYEE_V);

    List<EMPLOYEE_NUM> getAUTHORIZED_NUM();

    List<EMPLOYEE_NUM> getDOCTOR_TYPE_NUM();

    List<EMPLOYEE_NUM> getJOB_LEVEL_CODE_NUM();

    List<EMPLOYEE_NUM> getCSZYLBDMNum();

}
