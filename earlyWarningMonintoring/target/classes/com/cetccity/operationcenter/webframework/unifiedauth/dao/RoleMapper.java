package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 19:16
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 19:16
 * @Update_Description: huangzezhou 补充
 **/
@Mapper
public interface RoleMapper {

    int insertRole(@Param("roleEntity")RoleEntity roleEntity);

    List<RoleEntity> queryAll();

    int update(RoleEntity roleEntity);

    RoleEntity queryRoleById(String role_id);

    List<PermissionEntity> queryRoleInfo(String role_id);

    List<String> queryPermissionIds(@Param("role_id") String roleId);

    long countAll();
}
