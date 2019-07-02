package com.cetccity.operationcenter.webframework.unifiedauth.utils;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.utils
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/7 14:07
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/7 14:07
 * @Update_Description: huangzezhou 补充
 **/
@Slf4j
public class RequestUtil {

    /**
     * 在Header、request请求和cookie中，获取token的值。
     * @param request
     * @return
     * @throws Exception
     */
    public static String getParam(HttpServletRequest request, String paramName){
        try{
            String token=request.getHeader(paramName);
            if (Objects.nonNull(token)){
                return token;
            }
            token=request.getParameter(paramName);
            if (Objects.nonNull(token)){
                return token;
            }
            Cookie[] cookies = request.getCookies();
            if (Objects.isNull(cookies) || cookies.length == 0) {
                return null;
            }
            for (int i = 0; i < cookies.length; ++i){
                if (paramName.equalsIgnoreCase(cookies[i].getName())){
                    token = cookies[i].getValue();
                    return token;
                }
            }
        } catch (Throwable throwable) {
            log.error("获取用户token失败", throwable);
            throw new CetcCommonException(AuthCode.USER_NO_LOGIN_ERROR, AuthCode.USER_NO_LOGIN_ERROR_MESSAGE);
        }
        return null;
    }
}
