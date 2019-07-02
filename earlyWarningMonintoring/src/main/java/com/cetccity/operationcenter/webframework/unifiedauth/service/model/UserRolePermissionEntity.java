package com.cetccity.operationcenter.webframework.unifiedauth.service.model;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao.entity
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/14 17:19
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/14 17:19
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
public class UserRolePermissionEntity extends UserEntity {
    private Set<RoleEntity> roleEntities;
    private Set<PermissionEntity> permissionEntities;

    public Set<String> getAllPermissions(){
        Set<String> result = new HashSet<String>();
        for (PermissionEntity permissionEntity: permissionEntities){
            result.add(permissionEntity.getPermission_str());
        }
        return result;
    }

    public Set<String> getAllRoles(){
        Set<String> result = new HashSet<String>();
        for (RoleEntity roleEntity: roleEntities){
            result.add(roleEntity.getRole_name());
        }
        return result;
    }
}

