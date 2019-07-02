package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.filter;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.common.support.UserRolePermissionSupport;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.config.AuthorizationFilterConfig;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.AuthorizationServiceImpl;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.PathMatchUtil;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.RequestUtil;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Objects;

import static com.cetccity.operationcenter.webframework.core.frame.model.SysCode.UNKNOWN_ERROR_CODE;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service.Impl
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/5 19:35
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/5 19:35
 * @Update_Description: huangzezhou 补充
 **/
@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "tokenAuthorFilter")
public class AuthorizationFilter implements Filter, AuthorizationFilterConfig {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Autowired
    private TokenManager<String> tokenManager;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private AuthorizationServiceImpl authorizationService;

    @Autowired
    private UserRolePermissionSupport userRolePermissionSupport;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
            try {
                if (!PathMatchUtil.match(exceptUrls, path)) {//需要权限校验
                    // 获取token和解析token
                    String token = RequestUtil.getParam(request, "token");
                    if (StringUtils.isBlank(token)) {
                        throw new CetcCommonException(AuthCode.USER_NO_LOGIN_ERROR, AuthCode.USER_NO_LOGIN_ERROR_MESSAGE);
                    }
                    Claims claims = tokenManager.deToken(token);
                    // 验证token是否有效
                    Date expiration = claims.getExpiration();
                    if (expiration.compareTo(new Date()) < 0) {
                        throw CetcCommonException.defaultException(AuthCode.VALIDATE_TOKEN_ERROR, AuthCode.VALIDATE_TOKEN_ERROR_MESSAGE);
                    }
                    String account = claims.getSubject();
                    // 判断用户启用状态
                    UserEntity userEntity = authorizationService.queryUserEnityByAccount(account);
                    if (userEntity.getEnabled() != 1) {
                        throw CetcCommonException.defaultException(AuthCode.ACCOUNT_DISABLED_ERROR, AuthCode.ACCOUNT_DISABLED_ERROR_MESSAGE);
                    }
                    // 判断url访问权限
                    UserRolePermissionEntity userRolePermissionEntity = authorizationService.queryRolePermission(userEntity);
                    boolean hasPermission = authorizationService.hasPermission(userRolePermissionEntity.getPermissionEntities(), path);
                    // TODO 不再校验是否需要校验权限  不在免登陆范围内的都需要校验权限
                    if (PathMatchUtil.match(permittUrls, path) && !hasPermission) {
                        //权限不足
                        throw CetcCommonException.defaultException(AuthCode.PERMISSION_DENIED_ERROR, AuthCode.PERMISSION_DENIED_ERROR_MESSAGE);
                    }
                    // 用户数据绑定request
                    userRolePermissionSupport.setUser(userEntity, request);
                    userRolePermissionSupport.setRoles(userRolePermissionEntity.getRoleEntities(), request);
                    userRolePermissionSupport.setPermissions(userRolePermissionEntity.getPermissionEntities(), request);
                    userRolePermissionSupport.setRolePermission(userRolePermissionEntity, request);
                }
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (ServletException se) {
                logger.error("servletException", se);
                throw se;
            }
            catch (CetcCommonException ex) {
                int code = Objects.isNull(ex.getCode()) ? UNKNOWN_ERROR_CODE : ex.getCode();
                writeResponse(servletResponse, code, ex.getMessage());
            } catch (Throwable throwable) {
                logger.error("权限校验失败", throwable);
                writeResponse(servletResponse, AuthCode.UNKNOWN_ERROR_CODE, AuthCode.UNKNOWN_ERROR_MESSAGE);
            }
    }

    private void writeResponse(ServletResponse servletResponse, int code, String message) throws IOException {
        HttpResponseModel<String> model = new HttpResponseModel<String>(code);
        model.setMessage(message);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(model));
        writer.close();
    }

    @Override
    public void destroy() {

    }

}
