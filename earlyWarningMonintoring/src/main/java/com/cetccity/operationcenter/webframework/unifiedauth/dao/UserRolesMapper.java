package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/2 17:32
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/2 17:32
 * @Update_Description: huangzezhou 补充
 **/
@Mapper
public interface UserRolesMapper {

    /**
     * account_roles:{
     *     user_id: String
     *     role_ids: Set<String>
     * }
     * @param account_roles
     * @return
     */
    int insertRolesByUser(@Param("account_roles") Map account_roles);

    /**
     * 将之前的删除然后
     * @param user_id
     * @return
     */
    int deleteRolesByUser(String user_id);

    List<Map> queryUserRoleMapByUser(String user_id);
}
