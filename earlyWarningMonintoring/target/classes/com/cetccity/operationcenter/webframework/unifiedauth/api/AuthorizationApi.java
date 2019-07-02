package com.cetccity.operationcenter.webframework.unifiedauth.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 组织架构、权限、角色。
 * 创建角色时：会绑定响应的组织架构和权限到角色上
 * 分配给用户的只有角色（带有权限和组织架构）。
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.service
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 11:01
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 11:01
 * @Update_Description: huangzezhou 补充
 **/
@Api(tags = "授权逻辑")
@RequestMapping("/unifiedauth/authorization")
public interface AuthorizationApi {
    @ApiOperation(value = "校验当前登录用户是否有集合中的所有权限", notes = "校验当前登录用户是否有集合中的所有权限权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户令牌", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "permissions", value = "权限标识集合", paramType = "query", required = true, dataType = "Array[string]")
    })
    @RequestMapping(value = "/permission/validate/str", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<Boolean> validatePermission(String token, String[] permissions);


    @ApiOperation(value = "校验当前登录用户是否有访问该url的权限", notes = "校验当前登录用户是否有访问该url的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户令牌", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "uri", value = "uri", paramType = "query", required = true, dataType = "String")
    })
    @PostMapping(value = "/permission/validate/url")
    HttpResponseModel<Boolean> validatePermissionByUrl(String token, String uri, HttpServletRequest request);

    @ApiOperation(value = "批量校验当前登录用户是否有访问该url的权限", notes = "批量校验当前登录用户是否有访问该url的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户令牌", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "urls", value = "url集合", paramType = "query", required = true, dataType = "Array[string]")
    })
    @PostMapping(value = "/permission/batchValidate/url")
    HttpResponseModel<Map<String, Boolean>> batchValidatePermissionByUrl(String token, String urls[], HttpServletRequest request);

}
