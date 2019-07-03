package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetccity.operationcenter.webframework.backstage.log.util.LogAnnotation;
import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.api.AuthenticationApi;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.CaptchaModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.Code;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.AuthenticationServiceImpl;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.TokenManager;

import cn.hutool.core.codec.Base64;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.controller
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 11:38
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 11:38
 * @Update_Description: huangzezhou 补充
 **/

@RestController
public class AuthenticationController implements AuthenticationApi{

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationServiceImpl authenticationService;
    @Autowired
    AuthCode authCode;
    @Autowired
    private TokenManager<String> tokenManager;

    @LogAnnotation(module="用户登录")
    @ApiOperation(value = "登录接口", notes = "登录接口:\neyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDRVRDIiwic3ViIjoiYWRtaW4iLCJhdWQiOiJDRVRDICIsImV4cCI6MTU0MTQ0MDgzMSwiaWF0IjoxNTQxNDE3OTcxLCJqdGkiOiIzNzA2YTBkZS0zNGFiLTQwMTItYTM2NS03Mjk3MDA0ZGZlMGMifQ.onuGDuhbgs219YCB7iBy2wEipyM92vyvotH9PttTlHs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号:admin", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码sha256值:202cb962ac59075b964b07152d234b70", paramType = "query", required = true, dataType = "String"),
    })
    @PostMapping(value = "/login/{account}/{password}/{code}")
    public HttpResponseModel<TokenModel> login(@PathVariable("account") String account, @PathVariable("password") String password, @PathVariable("code") String code, @CookieValue("Cetc_image_code_id") String uuid, HttpServletResponse response, HttpServletRequest request) {
    	if (StringUtils.isBlank(account)) {
            throw CetcCommonException.defaultException("请输入账号");
        }
        if (StringUtils.isBlank(password)) {
            throw CetcCommonException.defaultException("请输入密码");
        }
        if (StringUtils.isBlank(code)) {
            throw CetcCommonException.defaultException("请输入验证码");
        }
        if (StringUtils.isBlank(uuid)) {
            throw CetcCommonException.defaultException("缺少验证码id");
        }
        
        Code checkCode = new Code();
        checkCode.setId(uuid);
        checkCode.setValue(code);
        return authenticationService.login(Base64.decodeStr(account), Base64.decodeStr(password), checkCode, response);
    }
    

    @LogAnnotation(module="用户登录")
    @ApiOperation(value = "登录接口_非验证码", notes = "登录接口:\neyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDRVRDIiwic3ViIjoiYWRtaW4iLCJhdWQiOiJDRVRDICIsImV4cCI6MTU0MTQ0MDgzMSwiaWF0IjoxNTQxNDE3OTcxLCJqdGkiOiIzNzA2YTBkZS0zNGFiLTQwMTItYTM2NS03Mjk3MDA0ZGZlMGMifQ.onuGDuhbgs219YCB7iBy2wEipyM92vyvotH9PttTlHs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号:admin", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码sha256值:202cb962ac59075b964b07152d234b70", paramType = "query", required = true, dataType = "String"),
    })
    @PostMapping(value = "/login/v2/{account}/{password}")
    public HttpResponseModel<TokenModel> login(@PathVariable("account") String account, @PathVariable("password") String password, HttpServletResponse response, HttpServletRequest request) {
        if (StringUtils.isBlank(account)) {
            throw CetcCommonException.defaultException("请输入账号");
        }
        if (StringUtils.isBlank(password)) {
            throw CetcCommonException.defaultException("请输入密码");
        }
        
        return authenticationService.login(Base64.decodeStr(account), Base64.decodeStr(password), response);
    }

    @LogAnnotation(module="用户登出")
    @Override
    public HttpResponseModel<Boolean> logout(String token, HttpServletResponse response, HttpServletRequest request) throws Exception{
        TokenModel evict = tokenManager.evict(token);
        String newToken = evict.getToken();
        if (!newToken.equals(token)) {
            Cookie cookie = new Cookie("token", newToken);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, true);
        }
        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, false);
    }

    @Override
    public HttpResponseModel<CaptchaModel> queryCaptcha() {
        return null;
    }

    @Override
    public HttpResponseModel<Boolean> validateToken(String token) {
        try {
            return authenticationService.validateToken(token);
        }catch (Exception e){
            return new HttpResponseModel<Boolean>();
        }
    }

    @Override
    public HttpResponseModel<TokenModel> refreshToken(String token) throws Exception{
        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE,tokenManager.refresh(token));
    }
    
    
    /**
     * 用于第三方系统单点登录平台认证
     * @param account
     * @param password 
     * @param response
     * @return
     */
    @PostMapping(value = "/authClient/{account}/{clientId}/{clientSecret}")
    public HttpResponseModel<TokenModel> authClient(@PathVariable("account") String account, @PathVariable("clientId") String clientId, @PathVariable("clientSecret") String clientSecret, HttpServletResponse response, HttpServletRequest request) {
    	if (StringUtils.isBlank(account)) {
            throw CetcCommonException.defaultException("请输入账号");
        }
        if (StringUtils.isBlank(clientId)) {
            throw CetcCommonException.defaultException("请输入clientId");
        }
        if (StringUtils.isBlank(clientSecret)) {
            throw CetcCommonException.defaultException("请输入clientSecret");
        }
        return authenticationService.authClient(Base64.decodeStr(account), Base64.decodeStr(clientId),  Base64.decodeStr(clientSecret), response);
    }

}
