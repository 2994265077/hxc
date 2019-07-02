package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.api
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/7 17:24
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/7 17:24
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "城市体征KPI-民生服务")
@RequestMapping("/urbansign")
public interface PeopleLiveApi {

    @ApiOperation(value = "城市体征--民生服务-总图表", notes = "城市体征--民生服务-总图表")
    @RequestMapping(value = "/citySign/peopleLive/total",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Object> peopleLiveTotal();

    @ApiOperation(value = "城市体征--民生服务-幼儿园", notes = "城市体征--民生服务-幼儿园")
    @RequestMapping(value = "/citySign/peopleLive/littleSchool",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Object> littleSchool();

    @ApiOperation(value = "城市体征--民生服务-小学", notes = "城市体征--民生服务-小学")
    @RequestMapping(value = "/citySign/peopleLive/smallSchool",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Object> smallSchool();


    @ApiOperation(value = "城市体征--民生服务-职业中学", notes = "城市体征--民生服务-职业中学")
    @RequestMapping(value = "/citySign/peopleLive/professionalSchool",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Object> professionalSchool();

    @ApiOperation(value = "城市体征--民生服务-普通中学", notes = "城市体征--民生服务-普通中学")
    @RequestMapping(value = "/citySign/peopleLive/middleSchool",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Object> middleSchool();

    @ApiOperation(value = "城市体征--民生服务-养老", notes = "城市体征--民生服务-养老")
    @RequestMapping(value = "/citySign/peopleLive/feedOld",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Object> feedOld();

    @ApiOperation(value = "城市体征--民生服务-医院床位", notes = "城市体征--民生服务-医院床位")
    @RequestMapping(value = "/citySign/peopleLive/hospitalBed",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Object> hospitalBed();
}
