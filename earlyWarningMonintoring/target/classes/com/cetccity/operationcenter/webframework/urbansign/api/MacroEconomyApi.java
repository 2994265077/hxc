package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.web.model.citySign.MacroEconomyDetail;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "城市体征KPI--宏观经济")
@RequestMapping("/urbansign")
public interface MacroEconomyApi {

    @ApiOperation(value = "城市体征--宏观经济", notes = "城市体征--宏观经济")
    @RequestMapping(value = "/citySign/macroEconomy",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel macroEconomy();

    @ApiOperation(value = "城市体征--宏观经济--详情", notes = "城市体征--宏观经济--详情")
    @RequestMapping(value = "/citySign/macroEconomy/detail",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MacroEconomyDetail macroEconomyDetail();
}
