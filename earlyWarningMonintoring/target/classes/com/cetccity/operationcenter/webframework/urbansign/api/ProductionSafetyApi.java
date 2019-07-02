package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:11 2019-06-03
 * Updater:     heliangming
 * Update_Date：10:11 2019-06-03
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "城市体征KPI--公共安全--生产安全")
@RequestMapping("/urbansign")
public interface ProductionSafetyApi {

    @ApiOperation(value = "生产安全--小散工程概况总览", notes = "生产安全--小散工程概况总览")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型--type=1、2、3、4、5", name = "type", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/social/security/xiaoSanProject",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> xiaoSanProject(String type);

    @ApiOperation(value = "生产安全--三小场所", notes = "生产安全--三小场所")
    @RequestMapping(value = "/social/security/sanXiaoPlace",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> sanXiaoPlaceToBar();

    @ApiOperation(value = "生产安全--危险源", notes = "生产安全--危险源")
    @RequestMapping(value = "/social/security/riskPoint",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> riskPointToBar();

}
