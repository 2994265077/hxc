package com.cetccity.operationcenter.webframework.unifiedauth.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cetccity.operationcenter.webframework.oa.model.Employee;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryResultModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao.dao
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/2 11:39
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/2 11:39
 * @Update_Description: huangzezhou 补充
 **/
@Mapper
public interface UserMapper {

    Integer insert(UserEntity userEntity);

    Integer update(UserUpdateModel userUpdateModel);

    UserEntity selectByAccount(String account);

    List<UserQueryResultModel> queryUser(UserQueryModel userQueryModel);
    
    int queryUserCount(UserQueryModel userQueryModel);
    
    List<String > queryAllUserAccount();

    /**
     * 查询用户所有信息：用户信息+用户角色+用户权限
     * @param user_id
     * @return
     */
    UserRolePermissionEntity queryUserAllInfo(String user_id);

    List<String> queryRoles(String user_id);
    
    /**
     * 从oa获取用户信息后批量插入
     * @param employeeList
     * @return
     */
    int batchInsertUserInfo(List<Employee> employeeList);
    
    /**
     * 根据组织机构查询用户
     * @param orgId
     * @return
     */
    List<UserEntity> queryByOrgId(String orgId);
}
