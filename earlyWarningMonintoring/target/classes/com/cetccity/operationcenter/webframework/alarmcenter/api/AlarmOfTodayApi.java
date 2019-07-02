package com.cetccity.operationcenter.webframework.alarmcenter.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameCodeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import java.util.List;

@Api(tags = "预警中心--功能列表Api")
@RequestMapping("/alarmcenter")
public interface AlarmOfTodayApi {

    @ApiOperation(value = "今日预警--待处置", notes = "今日预警--待处置--表名ALARM_INFORMATION")
    @RequestMapping(value = "/left/one",method = RequestMethod.GET)
    List<NameValueModel> LeftOne();

    @ApiOperation(value = "今日预警--一级预警类型数量", notes = "今日预警--预警类型--表名ALARM_INFORMATION")
    @RequestMapping(value = "/left/two",method = RequestMethod.GET)
    List<NameCodeValueModel> LeftTwo(String date) throws IOException ;

    /*@ApiOperation(value = "今日预警--二级预警类型", notes = "今日预警--预警类型--表名ALARM_INFORMATION")
    @RequestMapping(value = "{alarm_code}/left/two/monitor",method = RequestMethod.GET)
    List<NameValueModel> LeftTwoMonitor(@PathVariable("alarm_code") String alarm_code);*/


    @ApiOperation(value = "今日预警--一级、二级预警类型", notes = "今日预警--预警类型--ALARM_INFO_CODE")
    @RequestMapping(value = "/early/type",method = RequestMethod.GET)
    List<NameCodeDataModel> earlyType();

    @ApiOperation(value = "今日预警--预警信息中心--处置状态", notes = "今日预警--预警信息中心--处置状态")
    @RequestMapping(value = "/early/disposalState",method = RequestMethod.GET)
    List<NameValueModel> earlyDisposalState();

    @ApiOperation(value = "今日预警--预警信息中心", notes = "今日预警--预警信息中心--表名ALARM_INFORMATION[{\"pageNum\":\"1\", \"pageSize\":\"3\", \"startTime\": \"2018-11-12\", \"endTime\":\"2018-12-24\", \"STREET_CODE\":\"440304001\", \"COMMUNITY_CODE\":\"440304001007\", \"ALARM_TYPE_LV1\":\"003\", \"ALARM_TYPE_LV2\":\"003002\",\n" +
            "\"DISPOSAL_STATE\":\"0\" }]  \n")
    @RequestMapping(value = "/early/information/center",method = RequestMethod.POST)
    EarlyWarningCenter earlyInformationCenter(@RequestBody ALARM_INFORMATION aLARM_INFORMATION);

    @ApiOperation(value = "今日预警--预警信息详情", notes = "今日预警--预警信息详情--表名ALARM_INFORMATION[详情]  \n")
    @ApiImplicitParam(value = "id--OBJECT_ID", name = "id", dataType = "string", paramType = "query", required = true)
    @RequestMapping(value = "/early/center/list/detail",method = RequestMethod.GET)
    List earlyCenterListDetail(String id);

    @ApiOperation(value = "安全隐患一张图--四项预警", notes = "安全隐患一张图--四项预警--表名ALARM_INFORMATION[详情]  \n")
    @RequestMapping(value = "/hidden/danger/alarm/list",method = RequestMethod.GET)
    NameDataModel fourAlarm();
}
