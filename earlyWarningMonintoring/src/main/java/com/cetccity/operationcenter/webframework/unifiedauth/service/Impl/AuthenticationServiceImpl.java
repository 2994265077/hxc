package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.AuthClientMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.Code;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.support.CodeManager;
import com.cetccity.operationcenter.webframework.web.util.MD5Encoder;

import cn.hutool.core.codec.Base64;

import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service.Impl
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/2 16:42
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/2 16:42
 * @Update_Description: huangzezhou 补充
 **/
@Service
public class AuthenticationServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthCode authCode;

    @Autowired
    private CodeManager codeManager;

    @Autowired
    private JwtServiceImpl jwtService;
    
    @Autowired
    private AuthClientMapper authClientMapper;

    public HttpResponseModel<TokenModel> login(String account, String password, Code code, HttpServletResponse response) {
        HttpResponseModel<TokenModel> result;
        //1.校验验证码
        boolean validate = codeManager.validate(code);
        if (!validate) {
            throw CetcCommonException.defaultException("验证码错误！");
        }
        return login(account, password, response);
    }

    public HttpResponseModel<Boolean> validateToken(String token) {
        try {
            return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE,
                    jwtService.validateToken(token)
            );
        }catch (Exception e){
            logger.error("validate token error!\n", e);
            return new HttpResponseModel<Boolean>(authCode.VALIDATE_TOKEN_ERROR);
        }
    }
    
    /**
     * 第三方系统单点登录平台认证
     * @param account
     * @param password
     * @param response
     * @return
     */
    public HttpResponseModel<TokenModel> authClient(String account, String clientId, String clientSecret, HttpServletResponse response){
    	if(authClientMapper.selectByClientId(clientId, clientSecret) == null){
    		throw CetcCommonException.defaultException("无效的clientId");
    	}
    	
        UserEntity userEntity = userMapper.selectByAccount(account);
        if (Objects.isNull(userEntity)){
        	throw CetcCommonException.defaultException("无效的账号信息！");
        }
        //3.生成token
        try {
            TokenModel tokenModel = jwtService.token(account);
            Cookie cookie = new Cookie("token", tokenModel.getToken());
            cookie.setPath("/");
            response.addCookie(cookie);
            return new HttpResponseModel<TokenModel>(SysCode.SYS_SUCCESS_CODE, tokenModel);
        } catch (Exception e) {
            logger.error("登录失败!"+userEntity, e);
            throw CetcCommonException.defaultException("登录失败！");
        }
    } 
    
    
    public HttpResponseModel<TokenModel> login(String account, String password, HttpServletResponse response){
        //2.校验密码
        UserEntity userEntity = userMapper.selectByAccount(account);
        if (Objects.isNull(userEntity)){
        	throw CetcCommonException.defaultException("用户名或密码错误！");
        }
        //登录密码加盐（与前端约定加盐内容为yyyy-mm-dd）
        String date = DateUtils.formatDate(new Date(), "YYYY-MM-dd");
        String saltPwd = userEntity.getPassword();
        // 前端密码加密方式：base64.enCode(MD5Encoder.encode(MD5Encoder.encode(password)+date))
		try {
			saltPwd = MD5Encoder.encode(saltPwd + date);
		} catch (Exception e) {
			logger.error("登录md5异常!"+userEntity, e);
		}
        if (!password.equals(saltPwd)) {
            throw CetcCommonException.defaultException("用户名或密码错误！");
        }

        //3.生成token
        try {
            TokenModel tokenModel = jwtService.token(account);
            return new HttpResponseModel<TokenModel>(SysCode.SYS_SUCCESS_CODE, tokenModel);
        } catch (Exception e) {
            logger.error("登录失败!"+userEntity, e);
            throw CetcCommonException.defaultException("登录失败！");
        }
    }

    public HttpResponseModel<TokenModel> refreshToken(String token){
        try {
            String account = jwtService.account(token);
            return new HttpResponseModel<TokenModel>(SysCode.SYS_SUCCESS_CODE,jwtService.token(account));
        } catch (UnsupportedEncodingException e) {
            logger.error("validate token error!\n", e);
            return new HttpResponseModel<TokenModel>(authCode.VALIDATE_TOKEN_ERROR);
        } catch (InterruptedException e) {
            logger.error("token generate error!\n", e);
            return new HttpResponseModel<TokenModel>(authCode.GENERATOR_TOKEN_ERROR);
        }  catch (Exception e){
            return new HttpResponseModel<TokenModel>(SysCode.UNKNOWN_ERROR_CODE);
        }
    }

    
    public boolean accountIsValid(String account){
        return userMapper.selectByAccount(account).getEnabled() == 1;
    }
}