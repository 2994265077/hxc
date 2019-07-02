package com.cetccity.operationcenter.webframework.rundisplay.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.WaterDetailModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "运行展现--生态环境详情")
@RequestMapping("/rundisplay")
public interface EcoEnvironmentalDetailApi {

    @ApiOperation(value = "生态环境--空气质量当前详情(最近时间)", notes = "生态环境--空气质量当前详情--QHSJ_AIR_MONITOR_HOUR_AND_DAY_DATA")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000200004", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/air/quality/detail/lately",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel airStationDetailLatelyTime(String id);

    @ApiOperation(value = "生态环境--空气质量时间数据--折线图", notes = "生态环境--空气质量时间数据--QHSJ_AIR_MONITOR_HOUR_AND_DAY_DATA")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000200004", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "类型--type(day、hour)", name = "type", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--2018-11-04 13:19:00", name = "startTime", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "结束时间--2018-11-07 15:19:00", name = "endTime", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/air/quality/line/time",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel airStationLineTime(String id, String type, String startTime, String endTime);

    @ApiOperation(value = "生态环境--空气质量时间数据--列表", notes = "生态环境--空气质量时间数据--QHSJ_AIR_MONITOR_HOUR_AND_DAY_DATA")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000200004", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "类型--type(day、hour)", name = "type", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--2018-11-04 13:19:00", name = "startTime", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "结束时间--2018-11-07 13:19:00", name = "endTime", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/air/quality/list/time",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<Map<String, Object>> airStationListTime(String id, String type, String startTime, String endTime);

    @ApiOperation(value = "生态环境--河口水质量当前详情数据", notes = "生态环境--河口水质量当前详情最近一条数据--QHSJ_ENTERPRISE_BASICINFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000700039", name = "id", dataType = "string", paramType = "query", required = true),
    })
    @RequestMapping(value = "/pollutionSource/quality/details",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    WaterDetailModel pollutionSourceStationDetails(String id);

    @ApiOperation(value = "生态环境--河口水质量时间数据--折线图", notes = "生态环境--河口水质量时间数据--QHSJ_ENTERPRISE_BASICINFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000700039", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "类型--type(day、hour)", name = "type", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--2018-11-04 13:19:00", name = "startTime", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "结束时间--2018-11-07 15:19:00", name = "endTime", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/pollutionSource/quality/line/time",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel pollutionSourceStationLineTime(String id, String type, String startTime, String endTime);


    @ApiOperation(value = "生态环境--河口水质量时间数据", notes = "生态环境--河口水质量时间数据--QHSJ_ENTERPRISE_BASICINFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000700039", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "类型--type(day、hour)", name = "type", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--2018-11-04 13:19:00", name = "startTime", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "结束时间--2018-11-07 13:19:00", name = "endTime", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/pollutionSource/quality/list/time",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LinkedHashMap> pollutionSourceStationListTime(String id,String type,String startTime,String endTime);

    @ApiOperation(value = "生态环境--地表水质量当前详情数据", notes = "生态环境--河口水质量当前详情最近一条数据--QHSJ_SURF_WATER_BASICINFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000100006", name = "id", dataType = "string", paramType = "query", required = true),
    })
    @RequestMapping(value = "/water/quality/details",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    WaterDetailModel waterStationDetails(String id);

    @ApiOperation(value = "生态环境--地表水质量时间数据--折线图", notes = "生态环境--河口水质量时间数据--QHSJ_SURF_WATER_BASICINFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000100006", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "类型--type(day、hour)", name = "type", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--2018-11-07 13:19:00", name = "startTime", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "结束时间--2018-11-09 15:19:00", name = "endTime", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/water/quality/line/time",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel waterStationLineTime(String id, String type, String startTime, String endTime);

    @ApiOperation(value = "生态环境--地表水质量时间数据", notes = "生态环境--河口水质量时间数据--QHSJ_SURF_WATER_BASICINFO、QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--3702810000100006", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "类型--type", name = "type", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--2018-11-07 13:19:00", name = "startTime", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "结束时间--2018-11-09 13:19:00", name = "endTime", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/water/quality/list/time",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LinkedHashMap> waterStationListTime(String id,String type,String startTime,String endTime);

}
