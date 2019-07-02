package com.cetccity.operationcenter.webframework.backstage.utils;

import com.cetccity.operationcenter.webframework.unifiedauth.utils.RequestUtil;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.utils
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:20 2019-05-21
 * Updater:     heliangming
 * Update_Date：10:20 2019-05-21
 * 更新描述:    heliangming 补充
 **/
@Component
@Slf4j
public class AuthenticationUser {

    @Autowired
    TokenManager<String> tokenManager;

    public String getUser(ServletRequest servletRequest) {
        String account = null;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            String token = RequestUtil.getParam(request, "token");
            Claims claims = tokenManager.deToken(token);
            account = claims.getSubject();
        } catch (Exception e) {
        	log.error(e.toString());
        }
        return account;
    }
}
