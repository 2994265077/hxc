package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildScoreTool;
import com.cetccity.operationcenter.webframework.hiddendanger.api.BuildScoreApi;
import com.cetccity.operationcenter.webframework.web.dao.RestfulApiMapper;
import com.cetccity.operationcenter.webframework.web.model.CetcCloudResult;
import com.cetccity.operationcenter.webframework.web.model.RestfulApi;
import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreModel;
import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreRestful;
import com.cetccity.operationcenter.webframework.web.util.MD5Encoder;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:16 2019-04-03
 * Updater:     heliangming
 * Update_Date：10:16 2019-04-03
 * 更新描述:    heliangming 补充
 **/
@RestController
@Slf4j
public class BuildScoreController implements BuildScoreApi {

    @Autowired
    BuildScoreTool buildScoreTool;

    @Autowired
    RestfulApiMapper restfulApiMapper;

    public BuildScoreModel buildScore(String buildid){
        BuildScoreModel buildScoreModel = buildScoreTool.getBuildScore(buildid);
        return buildScoreModel;
    }

    public CetcCloudResult<BuildScoreRestful> buildScoreInterfaceService(String API_KEY, String SECRET_KEY) {
        CetcCloudResult<BuildScoreRestful> result = new CetcCloudResult();
        BuildScoreRestful buildScoreRestful = new BuildScoreRestful();
        List<Map.Entry<String, Integer>> map_build_score_list = null;
        RestfulApi restfulApi = new RestfulApi();
        String password = null;
        try {
            password = MD5Encoder.encode(SECRET_KEY);
        } catch (Exception e) {
            log.error(e.toString());
        }
        restfulApi.setName(API_KEY);
        restfulApi.setPassword(password);
        restfulApi.setStatustype("1");
        List<RestfulApi> restfulApi_list = restfulApiMapper.select(restfulApi);
        if(restfulApi_list.size()>0) {
            //map_build_score_list = BuildScoreSortConfig.infoIds_list;
            buildScoreRestful.setTotal(map_build_score_list.size());
            buildScoreRestful.setBuildScore(map_build_score_list);
            result.setCode(200);
            result.setMsg("SUCCESS");
        }else{
            buildScoreRestful.setTotal(0);
            buildScoreRestful.setBuildScore(map_build_score_list);
            result.setCode(201);
            result.setMsg("API_KEY或SECRET_KEY错误或者账号被禁用！");
        }
        result.setData(buildScoreRestful);
        return result;
    }
}
