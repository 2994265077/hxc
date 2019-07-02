package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.RISK_POINT;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPoint;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPointDangerLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:08 2019-03-29
 * Updater:     heliangming
 * Update_Date：10:08 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface RiskPointMapper {

    /**
     * 风险隐患点详情
     * @param id 主键
     * @return
     */
    List<RISK_POINT> findRISK_POINTDetail(@Param("id") String id);

    /**
     * 风险隐患点下钻
     * @return
     */
    HashMap<String, Object> riskPointDrillDown();

    List<HashMap> urbanSignProductionSafetyBi();

    List<RiskPoint> selectByDangerLevel(@Param("dangerLevels") List<RiskPointDangerLevel> dangerLevels);

    List<RiskPoint> selectByDangerLevelAndMinId(@Param("min_id") String minId, @Param("dangerLevels") List<RiskPointDangerLevel> dangerLevels);
}
