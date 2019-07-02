package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 17:51
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 17:51
 * @Update_Description: huangzezhou 补充
 **/
@Mapper
public interface RolePermissionsMapper {

    /**
     * role_permissions（map）{
     *     role_id: String
     *     permission_ids: Set<String>
     * }
     *
     * @param map
     * @return
     */
    int insertRolePermissions(@Param("role_permissions") Map map);

    int delete(String role_id);

    long deleteByPermission(@Param("permission_id") String permissionId);
}
