package com.cetccity.operationcenter.webframework.environment.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.environment.api.model.PatrolRecordRightFour;
import com.github.pagehelper.PageInfo;
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
 * Create_Date: 17:26 2019-05-30
 * Updater:     heliangming
 * Update_Date：17:26 2019-05-30
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河排水设施巡查记录BI")
@RequestMapping("/environment")
public interface CleanRiverToDrainFacilitiesPatrolRecordBiApi {

    @ApiOperation(value = "区域巡查--巡查&隐患次数", notes = "区域巡查--巡查&隐患次数--QJHH_PATROL")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "排水户uid--SEWERATE_ID", name = "SEWERATE_ID", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/patrol/record/right/one",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> rightOne(String SEWERATE_ID);

    @ApiOperation(value = "区域巡查--区域巡查数量变化", notes = "区域巡查--区域巡查数量变化--QJHH_PATROL")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "排水户uid--SEWERATE_ID", name = "SEWERATE_ID", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/patrol/record/right/two",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightTwo(String SEWERATE_ID);

    @ApiOperation(value = "区域巡查--巡查区域分布", notes = "区域巡查--巡查区域分布--from QJHH_PATROL where STATUS = '4028801e6a4a0d5b016a4a0edc9f0002'")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "排水户uid--SEWERATE_ID", name = "SEWERATE_ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "隐患--hiddenDanger=1", name = "hiddenDanger", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/patrol/record/right/three",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID, String hiddenDanger);

    @ApiOperation(value = "区域巡查--近三十天的巡查隐患列表", notes = "区域巡查--近三十天的巡查隐患列表--from QJHH_PATROL where STATUS = '正常'")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "排水户uid--SEWERATE_ID", name = "SEWERATE_ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "页码--pageNum", name = "pageNum", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "页行数--pageSize", name = "pageSize", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/patrol/record/right/four",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel rightFour(String street, String SEWERATE_ID, Integer pageNum, Integer pageSize);

}
