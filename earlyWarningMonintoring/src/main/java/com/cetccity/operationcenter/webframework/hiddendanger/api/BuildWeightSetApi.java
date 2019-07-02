package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE_WEIGHT;
import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *建筑风险--评分
 */
@Api(tags = "安全隐患一张图--建筑风险--权重")
@RequestMapping("/hiddendanger")
public interface BuildWeightSetApi {

    /*建筑风险--评分 中京消安数据 tableName= t_build_info_v*/
    @ApiOperation(value = "建筑风险--权重", notes = "建筑风险--查看权重")
    @RequestMapping(value = "/find/build/weight",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<BUILD_SCORE_WEIGHT> getBuildWeight(String mainId, String pid);

    /*建筑风险--评分 中京消安数据 tableName= t_build_info_v*/
    @ApiOperation(value = "建筑风险--修改权重", notes = "建筑风险--修改权重")
    @RequestMapping(value = "/update/build/weight",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Integer updateBuildWeight(String mainId, String weight);

}
