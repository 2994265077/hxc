package com.cetccity.operationcenter.webframework.alarmcenter.api;

import com.cetccity.operationcenter.webframework.web.model.incident.NameCodeValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import java.util.*;

@Api(tags = "预警中心--功能列表Api")
@RequestMapping("/alarmcenter")
public interface AlarmTrendApi {

    /*今日预警-预警趋势分析*/
    @ApiOperation(value = "今日预警--预警趋势分析--top", notes = "今日预警--预警趋势分析--top")
    @RequestMapping(value = "/trend/lower/top",method = RequestMethod.GET)
    List<NameValueModel> getLowerTop()throws IOException ;

    /*今日预警-预警趋势分析*/
    @ApiOperation(value = "今日预警--预警趋势分析--默认", notes = "今日预警--预警趋势分析--默认")
    @RequestMapping(value = "/trend/lower/one",method = RequestMethod.GET)
    List<NameDataModel> getLowerOne()throws IOException;

    /*今日预警-预警趋势分析*/
    @ApiOperation(value = "今日预警--预警趋势分析--默认二级下钻", notes = "今日预警--预警趋势分析--默认二级下钻")
    @ApiImplicitParam(value = "预警名称--001", name = "alarm_Code", dataType = "string", paramType = "query", required = true)
    @RequestMapping(value = "/trend/lower/one/DrillDown",method = RequestMethod.GET)
    List<NameDataModel> getLowerOneDrillDown(String alarm_Code)throws IOException;

    /*今日预警-预警趋势分析*/
    @ApiOperation(value = "今日预警--预警趋势分析--增长率", notes = "今日预警--预警趋势分析--增长率")
    @RequestMapping(value = "/trend/lower/one/rate",method = RequestMethod.GET)
    List<NameDataModel> getLowerOneRate()throws IOException ;

    @ApiOperation(value = "今日预警--预警趋势分析--对比", notes = "今日预警--预警趋势分析--对比")
    @ApiImplicitParam(value = "时间--2018", name = "year", dataType = "string", paramType = "query", required = true)
    @RequestMapping(value = "/trend/lower/two",method = RequestMethod.GET)
    List<NameCodeValueModel> getLowerTwo(String year)throws IOException;

    /*今日预警-企业外迁信息*/
    @ApiOperation(value = "今日预警--预警趋势分析--对比二级下钻", notes = "今日预警--预警趋势分析-对比-二级下钻")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "预警编号--summary_code", name = "summary_code", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "时间--date--2018", name = "date", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/trend/lower/two/DrillDown",method = RequestMethod.GET)
    List<NameValueModel> getLowerTwoDrillDown(String summary_code,String date)throws IOException;
    
}
