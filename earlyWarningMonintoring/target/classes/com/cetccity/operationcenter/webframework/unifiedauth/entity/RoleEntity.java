package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao.entity
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/2 17:31
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/2 17:31
 * @Update_Description: huangzezhou 补充
 **/
@Data
public class RoleEntity extends BaseEntity{

    @ApiModelProperty(hidden = true)
    String role_id;     //角色编号
    @ApiModelProperty(value = "角色名", required = true)
    String role_name;   //角色
    @ApiModelProperty(value = "描述", required = true)
    String description; //描述

}
