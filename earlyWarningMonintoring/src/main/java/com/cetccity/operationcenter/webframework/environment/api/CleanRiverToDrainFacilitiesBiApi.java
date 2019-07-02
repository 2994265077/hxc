package com.cetccity.operationcenter.webframework.environment.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:04 2019-05-29
 * Updater:     heliangming
 * Update_Date：9:04 2019-05-29
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河排水设施BI")
@RequestMapping("/environment")
@CacheConfig(cacheNames = "CleanRiverToDrainFacilitiesBiApi")
public interface CleanRiverToDrainFacilitiesBiApi {

    @ApiOperation(value = "区域排水设施rightOne", notes = "区域排水设施rightOne--QJHH_FACILITY_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "排水户id--SEWERATE_ID", name = "SEWERATE_ID", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/facilities/right/one",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightOne' + #street + '_' + #SEWERATE_ID")
    List<NameDataModel> rightOne(String street, String SEWERATE_ID);

    @ApiOperation(value = "排水设施数量变化", notes = "排水设施数量变化--QJHH_FACILITY_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "排水户id--SEWERATE_ID", name = "SEWERATE_ID", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/facilities/right/two",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightTwo' + #street + '_' + #SEWERATE_ID")
    HttpResponseModel<ChartDetailModel> rightTwo(String street, String SEWERATE_ID);

    @ApiOperation(value = "排水设施类型分布", notes = "排水设施类型分布--QJHH_FACILITY_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/facilities/right/three",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightThree' + #street + '_' + #SEWERATE_ID")
    HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID);

    @ApiOperation(value = "排水设施按街道数量变化", notes = "排水设施按街道数量变化--QJHH_FACILITY_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "排水户id--SEWERATE_ID", name = "SEWERATE_ID", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/facilities/right/four",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightFour' + #SEWERATE_ID")
    HttpResponseModel<ChartDetailModel> rightFour(String SEWERATE_ID);

    @ApiOperation(value = "排水设施各种类型按街道数量变化", notes = "排水设施各种类型按街道数量变化--QJHH_FACILITY_INFO")
    @RequestMapping(value = "/drain/facilities/right/five",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightFive'")
    HttpResponseModel<ChartDetailModel> rightFive();
}
