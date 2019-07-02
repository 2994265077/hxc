package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.BuildScoreWeightMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE_WEIGHT;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BuildWeightSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BuildWeightSetServiceImpl implements BuildWeightSetService {

    @Autowired
    BuildScoreWeightMapper bUILD_SCORE_WEIGHTMapper;

    public List<BUILD_SCORE_WEIGHT> getBuildWeight(String mainId, String pid){
        BUILD_SCORE_WEIGHT bUILD_SCORE_WEIGHT = new BUILD_SCORE_WEIGHT();
        bUILD_SCORE_WEIGHT.setMAIN_ID(mainId);
        bUILD_SCORE_WEIGHT.setPID(pid);
        List<BUILD_SCORE_WEIGHT> bUILD_SCORE_WEIGHT_list = bUILD_SCORE_WEIGHTMapper.getBUILD_SCORE_WEIGHT(bUILD_SCORE_WEIGHT);
        return bUILD_SCORE_WEIGHT_list;
    }

    public Integer updateBuildWeight(String mainId, String weight){
        BUILD_SCORE_WEIGHT bUILD_SCORE_WEIGHT = new BUILD_SCORE_WEIGHT();
        bUILD_SCORE_WEIGHT.setMAIN_ID(mainId);
        bUILD_SCORE_WEIGHT.setWEIGHT(weight);
        Integer res = bUILD_SCORE_WEIGHTMapper.updateWeight(bUILD_SCORE_WEIGHT);
        return res;
    }
}
