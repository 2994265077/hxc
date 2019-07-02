package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryResultModel;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.PermissionMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.RolePermissionsMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserRolesMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service.normal
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/14 9:58
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/14 9:58
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
public class MyAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RolePermissionsMapper rolePermissionsMapper;

    /**
     * 根据该用户id，找到该用户的角色信息和权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = (String) principals.getPrimaryPrincipal();
//        System.out.println(username);
        UserRolePermissionEntity userRolePermissionEntity = userMapper.queryUserAllInfo("1");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRolePermissionEntity.getAllRoles());   //该用户的所有角色
        authorizationInfo.setStringPermissions(userRolePermissionEntity.getAllPermissions());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
