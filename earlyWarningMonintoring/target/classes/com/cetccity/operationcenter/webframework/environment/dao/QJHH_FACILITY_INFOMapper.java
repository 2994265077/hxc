package com.cetccity.operationcenter.webframework.environment.dao;

import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_FACILITY_INFO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:16 2019-05-29
 * Updater:     heliangming
 * Update_Date：9:16 2019-05-29
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface QJHH_FACILITY_INFOMapper {

    List<QJHH_FACILITY_INFO> getList(QJHH_FACILITY_INFO qJHH_FACILITY_INFO);

    List<LinkedHashMap> getTip(@Param("UID") String UID);

    List<QJHH_FACILITY_INFO> loadMap(Map map);

    int count(Map map);

    List<HashMap> rightTwo(Map map);

    HashMap rightTwoRemoval(Map map);

    List<HashMap> rightThree(Map map);

    List<HashMap> rightFour(Map map);

    List<HashMap> rightFive();
}
