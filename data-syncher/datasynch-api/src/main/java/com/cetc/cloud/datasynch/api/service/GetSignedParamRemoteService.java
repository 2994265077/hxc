package com.cetc.cloud.datasynch.api.service;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2019/5/29.
 */
@Api(tags = "在线生成签名")
public interface GetSignedParamRemoteService {
    @RequestMapping(value = "/sign/getSignedParam", produces = "application/text", method = RequestMethod.POST)
    @ApiOperation(value = "根据表名和列名导出Excel", notes = "", produces = "application/text")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appkey", value = "appkey", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "content", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "接口编号【T01/T02/T03/T04】", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "secretKey", value = "密钥【4L$yX0M%Il%PQ7ZcAZo0RztQpoAcS3sD】", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "format", value = "json/text", required = true, dataType = "String", paramType = "query")
    })
    String getSignedParam(String appkey,String content, String code, String secretKey,String format);
}
