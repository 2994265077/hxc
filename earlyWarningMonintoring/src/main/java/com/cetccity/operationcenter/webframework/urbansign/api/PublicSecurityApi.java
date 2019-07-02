package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.urbansign.api.model.PublicSecurityDetail;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "城市体征KPI--公共安全")
@RequestMapping("/urbansign")
public interface PublicSecurityApi {

    @ApiOperation(value = "城市体征--公共安全", notes = "城市体征--公共安全")
    @RequestMapping(value = "/citySign/publicSecurity",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel publicSecurity();

    @ApiOperation(value = "城市体征--公共安全--详情", notes = "城市体征--公共安全--详情")
    @RequestMapping(value = "/citySign/publicSecurity/detail",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PublicSecurityDetail publicSecurityDetail();

}
