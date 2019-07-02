package com.cetccity.operationcenter.webframework.unifiedauth.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.CaptchaModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.service
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 11:00
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 11:00
 * @Update_Description: huangzezhou 补充
 **/

@Api(tags = "认证逻辑")
@RequestMapping("/unifiedauth/authentication")
public interface AuthenticationApi {
    @ApiOperation(value = "注销接口【暂不实现】", notes = "注销接口，暂时由前端实现，删除token")
    @ApiImplicitParam(name = "token", value = "令牌", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<Boolean> logout(String token, HttpServletResponse response, HttpServletRequest request) throws Exception;

    @ApiOperation(value = "请求验证码【暂不实现】", notes = "请求验证码接口")
    @ApiImplicitParam(name = "captcha", value = "验证码", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/captcha/query", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<CaptchaModel> queryCaptcha();

    @ApiOperation(value = "验证令牌是否有效", notes = "验证令牌是否有效接口")
    @ApiImplicitParam(name = "token", value = "令牌", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/token/validate", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<Boolean> validateToken(String token);

    @ApiOperation(value = "刷新token【暂不实现】", notes = "刷新token接口。用之前的有效令牌来换取新的令牌，之前令牌失效")
    @ApiImplicitParam(name = "token", value = "有效令牌", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<TokenModel> refreshToken(String token) throws Exception;

}
