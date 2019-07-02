package com.cetccity.operationcenter.webframework.environment.dao;

import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_SEWERAGE_INFO;
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
 * Create_Date: 18:08 2019-05-24
 * Updater:     heliangming
 * Update_Date：18:08 2019-05-24
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface QJHH_SEWERAGE_INFOMapper {

    List<QJHH_SEWERAGE_INFO> loadMap(Map map);

    List<QJHH_SEWERAGE_INFO> getList(QJHH_SEWERAGE_INFO qJHH_SEWERAGE_INFO);

    List<LinkedHashMap> getTip(@Param("UID") String UID);

    int count(Map map);

    List<HashMap> rightTwo(Map map);

    HashMap rightTwoRemoval(Map map);

    List<HashMap> rightThree(Map map);

    List<HashMap> rightFourNoStreet(Map map);

    List<HashMap> rightFourHasStreet(Map map);
}
