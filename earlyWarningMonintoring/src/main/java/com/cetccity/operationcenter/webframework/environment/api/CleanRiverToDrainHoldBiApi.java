package com.cetccity.operationcenter.webframework.environment.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:59 2019-05-27
 * Updater:     heliangming
 * Update_Date：17:59 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河排水户BI")
@RequestMapping("/environment")
public interface CleanRiverToDrainHoldBiApi {

    @ApiOperation(value = "排水户总数&&本月新增", notes = "排水户总数&&本月新增--QJHH_SEWERAGE_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/hold/right/one",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameDataModel> rightOne(String street);

    @ApiOperation(value = "排水户数量变化", notes = "排水户数量变化--QJHH_SEWERAGE_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/hold/right/two",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightTwo(String street);

    @ApiOperation(value = "排水户类型分布", notes = "排水户类型分布--QJHH_SEWERAGE_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/hold/right/three",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightThree(String street);

    @ApiOperation(value = "区域排水户分布", notes = "区域排水户分布--QJHH_SEWERAGE_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/drain/hold/right/four",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightFour(String street);

    @ApiOperation(value = "排水户排水设施数据清洗", notes = "排水户排水设施数据清洗")
    @RequestMapping(value = "/drain/hold/qing/xi",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String qingXing();

}
