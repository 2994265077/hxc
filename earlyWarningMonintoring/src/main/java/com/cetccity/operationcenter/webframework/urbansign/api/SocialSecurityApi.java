package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
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
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.api.publicsecurity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:40 2019-05-31
 * Updater:     heliangming
 * Update_Date：16:40 2019-05-31
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "城市体征KPI--公共安全--社会安全")
@RequestMapping("/urbansign")
public interface SocialSecurityApi {

    @ApiOperation(value = "社会安全--社会安全事件筛选菜单", notes = "社会安全--社会安全事件筛选菜单")
    @RequestMapping(value = "/social/security/rightOne/menu",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<String> rightOneMenu();

    @ApiOperation(value = "社会安全--社会安全事件柱状图", notes = "社会安全--社会安全事件")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类型--securityName=自然灾害类、社会安全类、事故灾难类、其他类、公共卫生类", name = "securityName", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "x轴筛选类型--x=time,street,type", name = "x", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/social/security/rightOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightOneToXName(String securityName, String x);

    @ApiOperation(value = "社会安全--交通安全-路政违法统计", notes = "社会安全--交通安全-路政违法统计")
    @RequestMapping(value = "/social/security/rightTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightTwo();

    @ApiOperation(value = "社会安全--交通安全-机动车临时许可证事项办理", notes = "社会安全--交通安全-机动车临时许可证事项办理")
    @RequestMapping(value = "/social/security/rightThree",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightThree();

    @ApiOperation(value = "社会安全--交通安全-主要路政违法事件", notes = "社会安全--交通安全-主要路政违法事件")
    @RequestMapping(value = "/social/security/rightFour",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String rightFour();
}
