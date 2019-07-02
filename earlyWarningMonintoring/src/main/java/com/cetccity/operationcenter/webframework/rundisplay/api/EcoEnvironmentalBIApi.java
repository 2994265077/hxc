package com.cetccity.operationcenter.webframework.rundisplay.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.RIVER_INFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Api(tags = "运行展现--生态环境BI")
@RequestMapping("/rundisplay")
public interface EcoEnvironmentalBIApi {

    @ApiOperation(value = "生态环境--河流信息", notes = "生态环境--河流信息--RIVER_INFO")
    @RequestMapping(value = "/river/quality/detail",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<RIVER_INFO> riverInfo();

    @ApiOperation(value = "生态环境--最近十天各个地表水监测站按天检测的平均值（总磷(w21011)、总氮(w21001)、溶解氧(w01009)小于2、氨氮(w21003)、化学需氧量(w01018））--折线图", notes = "生态环境--最近十天各个地表水监测站按天检测的平均值--QHSJ_SURF_WATER_BASICINFO")
    @RequestMapping(value = "/avg/water/quality/line/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel waterStationAverageLineDay();

    @ApiOperation(value = "生态环境--最近十天各个地表水监测站报警数量--折线图", notes = "生态环境--最近十天各个地表水监测站报警数量--QHSJ_SURF_WATER_BASICINFO")
    @RequestMapping(value = "/alarm/water/quality/line/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel alarmWaterStatistics();

    @ApiOperation(value = "生态环境--最近十天各个空气监测站报警数量--折线图", notes = "生态环境--最近十天各个空气监测站报警数量--QHSJ_T_AIR_BASICINFO")
    @RequestMapping(value = "/alarm/air/quality/line/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel alarmAirStatistics();

    @ApiOperation(value = "生态环境--最近十天各个空气质量监测站按天检测的平均值（SO2 NO2 PM10 CO O3 PM2.5  +AQI）--折线图", notes = "生态环境--最近十天各个空气质量监测站按天检测的平均值--QHSJ_T_AIR_BASICINFO、QHSJ_AQI_INFO")
    @RequestMapping(value = "/avg/air/quality/line/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel airStationAverageLineDay();

    @ApiOperation(value = "生态环境--最近三十天各个空气质量监测站按天检测的平均值（AQI）--柱状图", notes = "生态环境--最近三十天各个空气质量监测站按天检测的AQI平均值--QHSJ_AQI_INFO")
    @RequestMapping(value = "/avg/air/aqi/line/day",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel airStationAverageAQILineDay();
}
