package com.cetccity.operationcenter.webframework.unifiedauth.api.model;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model
 * @Project: 31project-Apr4
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/9 11:29
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/9 11:29
 * @Update_Description: huangzezhou 补充
 **/
public class RoleUpdateModel extends RoleEntity {


    @ApiModelProperty(value = "角色编号", required = true)
    String role_id;

    @Override
    public String getRole_id() {
        return role_id;
    }

    @Override
    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
