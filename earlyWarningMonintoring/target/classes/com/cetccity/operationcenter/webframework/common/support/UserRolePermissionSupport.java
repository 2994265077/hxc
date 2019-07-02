/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: RequestAttributeUtil
 * Author:   YHY
 * Date:     2019/5/29 10:59
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.support;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/29
 * @since 1.0.0
 */
@Component
public class UserRolePermissionSupport {

    private String ROLE_KEY = "CETC_ROLE";
    private String USER_KEY = "CETC_USER";
    private String PERMISSION_KEY = "CETC_PERMISSION";
    private String ROLE_PERMISSION_KEY = "CETC_ROLE_PERMISSION";

    public String roleIds(String delimiter) {
        return roles().stream().map(RoleEntity::getRole_id).collect(Collectors.joining(delimiter));
    }

    public String roleIds() {
        return roleIds(",");
    }

    public List<String> roleIdList() {
        return roles().stream().map(RoleEntity::getRole_id).collect(Collectors.toList());
    }

    public Set<RoleEntity> roles() {
        return (Set<RoleEntity>) RequestContextHolder.getRequestAttributes()
                .getAttribute(ROLE_KEY, SCOPE_REQUEST);
    }

    public boolean setRoles(Set<RoleEntity> roleEntities) {
        return setRequestAttributes(ROLE_KEY, roleEntities);
    }

    public void setRoles(Set<RoleEntity> roleEntities, ServletRequest request) {
        request.setAttribute(ROLE_KEY, roleEntities);
    }


    public UserEntity user() {
        return (UserEntity) RequestContextHolder.getRequestAttributes()
                .getAttribute(USER_KEY, SCOPE_REQUEST);
    }

    public boolean setUser(UserEntity userEntity) {
        return setRequestAttributes(USER_KEY, userEntity);
    }

    public void setUser(UserEntity userEntity, ServletRequest request) {
        request.setAttribute(USER_KEY, userEntity);
    }

    public Set<PermissionEntity> permissions() {
        return (Set<PermissionEntity>) RequestContextHolder.getRequestAttributes()
                .getAttribute(PERMISSION_KEY, SCOPE_REQUEST);
    }

    public boolean setPermissions(Set<PermissionEntity> permissionEntities) {
        return setRequestAttributes(PERMISSION_KEY, permissionEntities);
    }

    public void setPermissions(Set<PermissionEntity> permissionEntities, ServletRequest request) {
        request.setAttribute(PERMISSION_KEY, permissionEntities);
    }

    public UserRolePermissionEntity rolePermission() {
        return (UserRolePermissionEntity) RequestContextHolder.getRequestAttributes()
                .getAttribute(ROLE_PERMISSION_KEY, SCOPE_REQUEST);
    }

    public boolean setRolePermission(UserRolePermissionEntity userRolePermissionEntity) {
        return setRequestAttributes(ROLE_PERMISSION_KEY, userRolePermissionEntity);
    }

    public void setRolePermission(UserRolePermissionEntity userRolePermissionEntity, ServletRequest request) {
        request.setAttribute(ROLE_PERMISSION_KEY, userRolePermissionEntity);
    }

    public boolean setRequestAttributes(String key, Object value) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            requestAttributes.setAttribute(key, value, SCOPE_REQUEST);
            return true;
        }
        return false;
    }




}