package com.cetccity.operationcenter.webframework.publichealth.dao;

import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_IN_VISITS_DAY_V;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:37 2019-02-27
 * Updater:     heliangming
 * Update_Date：15:37 2019-02-27
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface YJJC_QWJJ_IN_VISITS_DAY_VMapper {

    List<YJJC_QWJJ_IN_VISITS_DAY_V> getYJJC_QWJJ_IN_VISITS_DAY_V(YJJC_QWJJ_IN_VISITS_DAY_V yJJC_QWJJ_IN_VISITS_DAY_V);

    String getLatelyYearnCount(@Param("month") String month);

    String getHospitalTwoYearCount(Map map_time);

    List<HashMap> hospitalizationHospitalDepartment(Map map);
}
