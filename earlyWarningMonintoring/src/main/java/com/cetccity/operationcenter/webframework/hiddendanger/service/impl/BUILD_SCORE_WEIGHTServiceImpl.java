package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.BuildScoreWeightMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE_WEIGHT;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BUILD_SCORE_WEIGHTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BUILD_SCORE_WEIGHTServiceImpl implements BUILD_SCORE_WEIGHTService {

    @Autowired
    BuildScoreWeightMapper bUILD_SCORE_WEIGHTMapper;

    public List<BUILD_SCORE_WEIGHT> getBUILD_SCORE_WEIGHT(BUILD_SCORE_WEIGHT bUILD_SCORE_WEIGHT){
        return bUILD_SCORE_WEIGHTMapper.getBUILD_SCORE_WEIGHT(bUILD_SCORE_WEIGHT);
    }
}
