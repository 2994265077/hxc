package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE_WEIGHT;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BuildScoreWeightMapper {

    List<BUILD_SCORE_WEIGHT> getBUILD_SCORE_WEIGHT(BUILD_SCORE_WEIGHT bUILD_SCORE_WEIGHT);

    int updateWeight(BUILD_SCORE_WEIGHT bUILD_SCORE_WEIGHT);
}
