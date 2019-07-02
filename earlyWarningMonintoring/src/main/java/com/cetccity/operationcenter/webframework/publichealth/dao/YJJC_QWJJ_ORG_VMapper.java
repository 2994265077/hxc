package com.cetccity.operationcenter.webframework.publichealth.dao;

import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_ORG_V;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:36 2019-02-27
 * Updater:     heliangming
 * Update_Date：16:36 2019-02-27
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface YJJC_QWJJ_ORG_VMapper {

    List<YJJC_QWJJ_ORG_V> getYJJC_QWJJ_ORG_V(YJJC_QWJJ_ORG_V yJJC_QWJJ_ORG_V);

    List<YJJC_QWJJ_ORG_V> getYJJC_QWJJ_ORG_VRemoval();

    List<YJJC_QWJJ_ORG_V> getYJJC_QWJJ_ORG_VDepartmentRemoval(@Param("ORG_CODE") String ORG_CODE);

    LinkedHashMap<String,String> summaryInfo(@Param("ORG_CODE") String ORG_CODE);
}
