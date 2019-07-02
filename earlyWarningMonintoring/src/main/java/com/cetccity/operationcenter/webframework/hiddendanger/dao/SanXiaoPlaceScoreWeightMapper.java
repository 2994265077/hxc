package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.SAN_XIAO_PLACE_SCORE_WEIGHT;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao
 * 项目名称:   futian-EarlyWarningMonitoring
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:31 2019-01-15
 * Updater:     heliangming
 * Update_Date：15:31 2019-01-15
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface SanXiaoPlaceScoreWeightMapper {

    List<SAN_XIAO_PLACE_SCORE_WEIGHT> getSAN_XIAO_PLACE_SCORE_WEIGHT(SAN_XIAO_PLACE_SCORE_WEIGHT sAN_XIAO_PLACE_SCORE_WEIGHT);

    int updateWeight(SAN_XIAO_PLACE_SCORE_WEIGHT sAN_XIAO_PLACE_SCORE_WEIGHT);

}
