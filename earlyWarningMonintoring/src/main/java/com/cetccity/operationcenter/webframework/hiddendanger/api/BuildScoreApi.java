package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.web.model.CetcCloudResult;
import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreModel;
import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreRestful;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:14 2019-04-03
 * Updater:     heliangming
 * Update_Date：10:14 2019-04-03
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "安全隐患一张图--建筑风险--分数查询")
@RequestMapping("/hiddendanger")
public interface BuildScoreApi {

    @ApiOperation(value = "根据建筑id获取建筑评分", notes = "根据建筑id获取建筑评分")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "建筑id--44030400200301T0003", name = "buildid", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/build/score",method = RequestMethod.GET)
    @ResponseBody
    BuildScoreModel buildScore(String buildid);

    @ApiOperation(value = "建筑风险--评分--对外提供服务接口", notes = "建筑风险--评分--对外提供服务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "API_KEY--cetc", name = "API_KEY", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "秘钥SECRET_KEY--123456", name = "SECRET_KEY", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/build/score/api",method = RequestMethod.GET)
    @ResponseBody
    CetcCloudResult<BuildScoreRestful> buildScoreInterfaceService(String API_KEY, String SECRET_KEY);

}
