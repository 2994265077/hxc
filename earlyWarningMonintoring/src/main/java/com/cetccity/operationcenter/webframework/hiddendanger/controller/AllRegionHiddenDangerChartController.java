package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.hiddendanger.api.AllRegionHiddenDangerChartApi;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.ThreeSmallHiddenDangerInfo;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.NameValuePlus;
import com.cetccity.operationcenter.webframework.hiddendanger.service.impl.AllRegionHiddenDangerCharServiceImpl;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.web.model.incident.AlarmTodayType;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.controller
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/20 11:04
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/20 11:04
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@RestController
public class AllRegionHiddenDangerChartController implements AllRegionHiddenDangerChartApi{

    @Autowired
    AllRegionHiddenDangerCharServiceImpl allRegionHiddenDangerCharService;

    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public HttpResponseModel<PieModel> pie(String street) {
        return allRegionHiddenDangerCharService.pie(street);
    }

    @Override
    public HttpResponseModel<List<BarOrLineModel>> bar(String street, String startTime, String endTime) {
        LocalDateTime start = LocalDate.parse(startTime, DEFAULT_FORMATTER).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endTime, DEFAULT_FORMATTER).plusDays(1).atStartOfDay();
        List<BarOrLineModel> bar = allRegionHiddenDangerCharService.bar(street, start, end);
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, bar);
    }

    @ApiOperation(value = "安全隐患一张图--全区隐患--条形图根据街道名称统计", notes = "安全隐患一张图--全区隐患--条形图根据街道名称统计")
    @RequestMapping(value = "/allrigionhiddendangerchart/{street}/bar", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public HttpResponseModel<List<BarOrLineModel>> barByStreetName(@PathVariable("street") String streetName, String startTime, String endTime) {
        LocalDateTime start = LocalDate.parse(startTime, DEFAULT_FORMATTER).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endTime, DEFAULT_FORMATTER).plusDays(1).atStartOfDay();
        List<BarOrLineModel> bar = allRegionHiddenDangerCharService.barByStreetName(streetName, start, end);
        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE,SysCode.SYS_SUCCESS_MESSAGE, bar);
    }

    @Override
    public HttpResponseModel<MyPageInfoModel<List<ThreeSmallHiddenDangerInfo>>> list(String startTime, String endTime, int pageNum, int pageSize,String placeType, String street) {
        return HttpResponseModel.defaultSuccess(allRegionHiddenDangerCharService.list(startTime, endTime, pageNum, pageSize, placeType, street));
    }

    @ApiOperation(value = "安全隐患一张图--全区隐患--三小隐患饼图", notes = "安全隐患一张图--全区隐患--三小隐患饼图")
    @RequestMapping(value = "/threeSmall/pie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public HttpResponseModel<NameValuePlus> dangerPie(String street,String startTime,String endTime) {
        List<NameValuePlus> nameValuePlus = allRegionHiddenDangerCharService.countThreeSmallDangerByType(street,startTime,endTime);
        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE,SysCode.SYS_SUCCESS_MESSAGE, nameValuePlus);
    }

    @ApiOperation(value = "安全隐患一张图--全区隐患--报警事件饼图", notes = "安全隐患一张图--全区隐患--报警事件饼图")
    @RequestMapping(value = "/alarm/pie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public HttpResponseModel<List<NameValuePlus>> alarmPieByType(String street , String startTime, String endTime) {
        LocalDateTime start = LocalDate.parse(startTime, DEFAULT_FORMATTER).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endTime, DEFAULT_FORMATTER).plusDays(1).atStartOfDay();
        return HttpResponseModel.defaultSuccess(allRegionHiddenDangerCharService.countAlarmByType(street, start, end));
    }

    @ApiOperation(value = "安全隐患一张图--全区隐患--报警事件列表", notes = "安全隐患一张图--全区隐患--报警事件列表")
    @RequestMapping(value = "/alarm/page",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public HttpResponseModel<PageInfo<AlarmTodayType>> alarmByType(String type,int pageNum, int pageSize, String street , String startTime, String endTime) {
        LocalDateTime start = LocalDate.parse(startTime, DEFAULT_FORMATTER).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endTime, DEFAULT_FORMATTER).plusDays(1).atStartOfDay();
        PageInfo<AlarmTodayType> pageInfo = allRegionHiddenDangerCharService.queryAlarmByType(type, pageNum, pageSize, street, start, end);
        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE,SysCode.SYS_SUCCESS_MESSAGE, pageInfo);
    }

}
