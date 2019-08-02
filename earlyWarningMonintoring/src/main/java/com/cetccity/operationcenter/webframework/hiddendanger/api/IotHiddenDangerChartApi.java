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
 * @Create_Date: 2018/11/19 17:25
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/19 17:25
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "安全隐患一张图--物联监测图表")
@RequestMapping("/hiddendanger")
public interface IotHiddenDangerChartApi {

    @ApiOperation(value = "安全隐患一张图--物联监测图表--左侧饼图", notes = "安全隐患一张图--物联监测图表--左侧饼图")
    @RequestMapping(value = "/iothiddendangerchart/leftpie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> leftPie(String street);

    @ApiOperation(value = "安全隐患一张图--物联监测图表--右侧饼图", notes = "安全隐患一张图--物联监测图表--左侧饼图")
    @RequestMapping(value = "/iothiddendangerchart/rightpie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> rightPie(String street);

    @ApiOperation(value = "安全隐患一张图--物联监测图表--折线图", notes = "安全隐患一张图--物联监测图表--折线图")
    @RequestMapping(value = "/iothiddendangerchart/line",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<BarOrLineModel>> line(String street, String startTime, String endTime);
}
