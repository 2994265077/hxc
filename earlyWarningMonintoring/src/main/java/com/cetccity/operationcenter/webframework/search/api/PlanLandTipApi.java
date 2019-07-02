package com.cetccity.operationcenter.webframework.search.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:24 2019-03-14
 * Updater:     heliangming
 * Update_Date：17:24 2019-03-14
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "搜索引擎--规土委搜索数据弹框")
@RequestMapping("/search")
public interface PlanLandTipApi {

    @ApiOperation(value = "规土委搜索数据弹框", notes = "规土委搜索数据弹框")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--1", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/AddressResolution/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Map addressResolutionTip(String id)throws IOException;

    @ApiOperation(value = "规土委搜索数据弹框", notes = "规土委搜索数据弹框")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--1", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/AddressWebService/point/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Map addressWebService_pointTip(String id)throws IOException;
}
