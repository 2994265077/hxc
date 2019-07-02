package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "安全隐患一张图--应急仓库详情")
@RequestMapping("/hiddendanger")
public interface EmergencyWarehouseDetailsApi {

    @ApiOperation(value = "应急仓库详情", notes = "应急仓库详情 表名EMERGENCY_MATERIAL_MEM")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "name=皇岗中学", name = "name", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/emergency/warehouseDetails",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel findEmergencyWarehouseDetails(String name) throws Exception;
}
