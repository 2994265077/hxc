package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.api
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/17 16:11
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/17 16:11
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "安全隐患一张图--三小场所右侧图表")
@RequestMapping("/hiddendanger")
public interface ThreeSmallPlaceChartApi {

    @ApiOperation(value = "饼图--全区三小场所数量统计", notes = "饼图--全区三小场所数量统计")
    @RequestMapping(value = "/threesmallchart/pie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> pie(String street);

    @ApiOperation(value = "折线图-按时间统计三小场所数量", notes = "折线图-按时间统计三小场所数量")
    @RequestMapping(value = "/threesmallchart/line",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<BarOrLineModel>> line(String street, String startTime, String endTime);

    @ApiOperation(value = "统计三小场所历史所有事件，已经整改情况", notes = "统计三小场所历史所有事件，已经整改情况")
    @RequestMapping(value = "/threesmallchart/bar",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<BarOrLineModel>> bar(String street, String startTime, String endTime);

    @ApiOperation(value = "条形图-按街道查询历史的三小场所数据和事件", notes = "条形图-按街道查询历史的三小场所数据和事件")
    @RequestMapping(value = "/threesmallchart/streetBar",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<BarOrLineModel>> streetBar(String street, String startTime, String endTime);
}
