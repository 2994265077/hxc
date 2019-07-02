package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao
 * @Project: 31project-Apr4
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/9 11:40
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/9 11:40
 * @Update_Description: huangzezhou 补充
 **/
public interface PermissionMapper {

    List<PermissionEntity> queryAll();

    List<PermissionNode> queryAllForTree();

    long save(PermissionEntity permissionEntity);

    long updateSelectedKey(PermissionEntity permissionEntity);

    long delete(@Param("permission_id") String permissionId);

    long countById(@Param("permission_id") String permissionId);

    long countByParent(@Param("parent") String parent);

    PermissionEntity queryById(String permissionId);

}
