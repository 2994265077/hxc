package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.hiddendanger.api.BuildWeightSetApi;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE_WEIGHT;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BuildWeightSetService;
import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildWeightSetController implements BuildWeightSetApi {

    @Autowired
    BuildWeightSetService buildWeightSetService;

    public List<BUILD_SCORE_WEIGHT> getBuildWeight(String mainId, String pid){
        List<BUILD_SCORE_WEIGHT> bUILD_SCORE_WEIGHT_list = buildWeightSetService.getBuildWeight(mainId,pid);
        return bUILD_SCORE_WEIGHT_list;
    }

    public Integer updateBuildWeight(String mainId, String weight){
        Integer res = buildWeightSetService.updateBuildWeight(mainId,weight);
        return res;
    }
}
