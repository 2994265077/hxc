package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.api
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/18 18:35
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/18 18:35
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "安全隐患一张图--消防隐患图表")
@RequestMapping("/hiddendanger")
@CacheConfig(cacheNames = "fireHiddenDangerChartApi")
public interface FireHiddenDangerChartApi {

    @ApiOperation(value = "安全隐患一张图--消防隐患图表--饼图", notes = "安全隐患一张图--消防隐患图表--饼图")
    @RequestMapping(value = "/firehiddendangerchart/pie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'pie' + #street")
    HttpResponseModel<PieModel> pie(String street);

    @ApiOperation(value = "安全隐患一张图--消防隐患图表--条形图", notes = "安全隐患一张图--消防隐患图表--条形图")
    @RequestMapping(value = "/firehiddendangerchart/bar",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'bar' + #street")
    HttpResponseModel<List<BarOrLineModel>> bar(String street);

}
