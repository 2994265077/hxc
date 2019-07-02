package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/20 9:14
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/20 9:14
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "安全隐患一张图--地质类图表")
@RequestMapping("/hiddendanger")
public interface GeologyHiddenDangerChartApi {

    @ApiOperation(value = "安全隐患一张图--地质类图表--统计地质隐患总数", notes = "安全隐患一张图--地质类图表--统计地质隐患总数")
    @RequestMapping(value = "/geologyhiddendangerchart/countAll",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<NameValueTypeModel> countAll(String street);

    @ApiOperation(value = "安全隐患一张图--地质类图表--左上角饼图-隐患类型", notes = "安全隐患一张图--地质类图表--左上角饼图-隐患类型")
    @RequestMapping(value = "/geologyhiddendangerchart/lefttoppie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> leftTopPie(String street);

    @ApiOperation(value = "安全隐患一张图--地质类图表--右上角饼图-巡查类型", notes = "安全隐患一张图--地质类图表--右上角饼图-巡查类型")
    @RequestMapping(value = "/geologyhiddendangerchart/righttoppie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> rightTopPie(String street);

    @ApiOperation(value = "安全隐患一张图--地质类图表--下方饼图-隐患类型", notes = "安全隐患一张图--地质类图表--左上角饼图-隐患类型")
    @RequestMapping(value = "/geologyhiddendangerchart/bottompie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> bottomPie(String street);
}
