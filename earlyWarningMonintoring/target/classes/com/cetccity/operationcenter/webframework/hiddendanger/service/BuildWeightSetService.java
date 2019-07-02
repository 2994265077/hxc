package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE_WEIGHT;

import java.util.List;

public interface BuildWeightSetService {

    List<BUILD_SCORE_WEIGHT> getBuildWeight(String mainId, String pid);

    Integer updateBuildWeight(String mainId, String weight);
}
