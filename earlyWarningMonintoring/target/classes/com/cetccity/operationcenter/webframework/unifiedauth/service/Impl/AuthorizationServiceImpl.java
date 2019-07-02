package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.PathMatchUtil;
import org.apache.shiro.subject.SimplePrincipalMap;
import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.validation.constraints.NotBlank;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;


/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service
 * @Project: unified-auth
 * @Description: //权限校验逻辑
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/1 14:29
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/1 14:29
 * @Update_Description: huangzezhou 补充
 **/
@Service
public class AuthorizationServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private AuthCode authCode;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 流程：
     * 1.校验token是否有效
     * 2.获取该用户的所有权限
     * 3.判断是否contains权限，只要有一个未被包含，则返回false
     *
     * @param token
     * @param permissions
     * @return
     */
    public HttpResponseModel<Boolean> validatePermission(String token, String[] permissions) {
        try {
            if (authenticationService.validateToken(token).getData()) {
                String account = jwtService.account(token);
                SimplePrincipalMap simplePrincipalMap = new SimplePrincipalMap();
                simplePrincipalMap.put("key", account);
                boolean[] permitted = securityManager.isPermitted(simplePrincipalMap, permissions);
                boolean result = true;
                for (int i = 0; i < permitted.length; ++i) {
                    if (!permitted[i]) {
                        result = false;
                        break;
                    }
                }
                return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, result);
            } else {
                return new HttpResponseModel<Boolean>(authCode.VALIDATE_TOKEN_ERROR, false);
            }
        } catch (Exception e) {
            logger.error("token error", e);
            return new HttpResponseModel<Boolean>(authCode.VALIDATE_TOKEN_ERROR, false);
        }
    }

    /**
     * 流程：
     * 1.校验token是否有效
     * 2.获取该用户的所有权限
     * 3.判断是否contains权限，只要有一个未被包含，则返回false
     * @param token
     * @param url
     * @return
     */
    public HttpResponseModel<Boolean> validatePermissionByUrl(String token, String[] url) {
        try {
            if (authenticationService.validateToken(token).getData()) {
                String account = jwtService.account(token);
                UserEntity userEntity =  userMapper.selectByAccount(account);
                UserRolePermissionEntity entity =  userMapper.queryUserAllInfo(userEntity.getUser_id());
                if (matchUrl(entity.getPermissionEntities(), url))
                    return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, true);
                else
                    return new HttpResponseModel<>(authCode.PERMISSION_DENIED_ERROR, false);
            } else {
                return new HttpResponseModel<Boolean>(authCode.VALIDATE_TOKEN_ERROR, false);
            }
        } catch (Exception e) {
            logger.error("token error", e);
            return new HttpResponseModel<Boolean>(authCode.VALIDATE_TOKEN_ERROR, false);
        }
    }

    public UserRolePermissionEntity queryRolePermission(UserEntity userEntity) {
        return userMapper.queryUserAllInfo(userEntity.getUser_id());
    }

//    @Cacheable(key = "'userByToken'+#account")
    public UserEntity queryUserEnityByAccount(String account){
        return userMapper.selectByAccount(account);
    }

    public boolean hasPermission(Collection<PermissionEntity> permissionEntities, String url) {
        Set<String> permissionUrls = permissionEntities.stream()
                .filter(permissionEntity -> !permissionEntity.isParent())
                .map(PermissionEntity::getPermission_url)
                .collect(Collectors.toSet());
        return PathMatchUtil.match(permissionUrls, url);
    }




    /**
     *
     * @param permissionEntities 权限实体集合
     * @param urls 需要匹配的url集合
     * @return 是否匹配成功
     */
    private Boolean matchUrl(Set<PermissionEntity> permissionEntities, String[] urls){
        Set<String> patterns = new HashSet<String>();
        for (PermissionEntity entity: permissionEntities){
            if ( ! entity.isParent() ) {
                patterns.add(entity.getPermission_url());
            }
        }
        for (int i = 0; i < urls.length; ++i){
            if (PathMatchUtil.match(patterns, urls[i]))
                return true;
        }
        return false;
    }
}
