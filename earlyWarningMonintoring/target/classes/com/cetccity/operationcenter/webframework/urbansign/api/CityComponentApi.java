package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
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
@Api(tags = "城市体征KPI-城市部件")
@RequestMapping("/urbansign")
public interface CityComponentApi {

    @ApiOperation(value = "城市体征--城市部件-总图表", notes = "城市体征--城市部件-总图表")
    @RequestMapping(value = "/citySign/cityComponent/total",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> totalCityComponent();

    @ApiOperation(value = "城市体征--城市部件-物联感知图表", notes = "城市体征--城市部件-物联感知图表")
    @RequestMapping(value = "/citySign/cityComponent/iot",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> iotCityComponent();

    @ApiOperation(value = "城市体征--城市部件-环水监测站图表", notes = "城市体征--城市部件-环水监测站图表")
    @RequestMapping(value = "/citySign/cityComponent/site",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> siteCityComponent();

    @ApiOperation(value = "城市体征--城市部件-摄像头图表", notes = "城市体征--城市部件-摄像头图表")
    @RequestMapping(value = "/citySign/cityComponent/camera",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> cameraCityComponent();

    @ApiOperation(value = "城市体征--城市部件-城市部件", notes = "城市体征--城市部件-城市部件")
    @RequestMapping(value = "/citySign/cityComponent/cityComponent",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> cityComponentCityComponent();
}
