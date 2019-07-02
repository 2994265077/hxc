package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.api
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/14 19:25
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/14 19:25
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "城市体征KPI-生态环境")
@RequestMapping("/urbansign")
public interface EcologicalEnvironmentApi {

    /**
     * 生态环境-总统计
     * @return
     */
    @ApiOperation(value = "城市体征--生态环境-总统计", notes = "城市体征--生态环境-总统计")
    @RequestMapping(value = "/citySign/ecological/total", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> totalEcological();

    /**
     *  生态环境--河流水质状况-按季度
     * @return
     */
    @ApiOperation(value = "城市体征--生态环境--河流水质状况-按季度", notes = "城市体征--生态环境--河流水质状况-按季度")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "年--year(2018)", name = "year", dataType = "string", paramType = "true"),
            @ApiImplicitParam(value = "季度--quarter(1,2,3,4)", name = "quarter", dataType = "string", paramType = "true")
    })
    @RequestMapping(value = "/citySign/ecological/river/water", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> riverWaterQualityByQuarter(String year, String quarter);

    /**
     *  生态环境--空气质量指数-按月
     * @return
     */
    @ApiOperation(value = "城市体征--生态环境--空气-按月", notes = "城市体征--生态环境--空气-按月")
    @RequestMapping(value = "/citySign/ecological/air/day", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> airAqiByDay();

    @ApiOperation(value = "城市体征--生态环境--监测站超标统计", notes = "城市体征--生态环境--监测站超标统计-按月")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型--type(DATE、MONTH)", name = "type", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "监测站--station(QHSJ_AIR_MONITOR_HOUR_DATA,QHSJ_SFW_MONITOR_HOUR_DATA)", name = "station", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/citySign/ecological/station/excess", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> airExcessByStation(String type, String station);

    @ApiOperation(value = "城市体征--生态环境--监测站分布", notes = "城市体征--生态环境--监测站分布")
    @RequestMapping(value = "/citySign/ecological/station/distribution", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> stationDistribution();



    /*@ApiOperation(value = "城市体征--生态环境-水质-按月", notes = "城市体征--生态环境-水质-按月")
    @RequestMapping(value = "/citySign/ecological/waterQuality/month",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> waterQualityByMonth();


    @ApiOperation(value = "城市体征--生态环境-水质-按月", notes = "城市体征--生态环境-水质-按月")
    @RequestMapping(value = "/citySign/ecological/waterQuality/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> waterQualityByDay();

    @ApiOperation(value = "城市体征--生态环境--空气-按月", notes = "城市体征--生态环境--空气-按月")
    @RequestMapping(value = "/citySign/ecological/air/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> airByDay();

    @ApiOperation(value = "城市体征--生态环境--地表水-按月", notes = "城市体征--生态环境--地表水-按月")
    @RequestMapping(value = "/citySign/ecological/waterOnGround/month",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> waterOnGroundByMonth();

    @ApiOperation(value = "城市体征--生态环境--地表水-按天", notes = "城市体征--生态环境--地表水-按天")
    @RequestMapping(value = "/citySign/ecological/waterOnGround/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> waterOnGroundByDay();*/


}
