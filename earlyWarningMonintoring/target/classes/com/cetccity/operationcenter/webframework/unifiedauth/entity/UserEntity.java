package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 14:52
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 14:52
 * @Update_Description: huangzezhou 补充
 **/
@Data
public class UserEntity extends BaseEntity{

    @ApiModelProperty(hidden = true)
    String user_id;
    @ApiModelProperty(value = "账号: admin", required = true)
    String account;     //唯一账号
    @ApiModelProperty(value = "密码: 123", required = true)
    String password;    //密码
    @ApiModelProperty(value = "账号是否有效", required = true)
    Integer enabled;     //是否有效，1有效，0被禁用
    @ApiModelProperty(value = "手机号", required = true)
    String phoneNumber; //手机号
    @ApiModelProperty(value = "身份证", required = true)
    String id_card;     //身份证
    @ApiModelProperty(value = "真实姓名", required = true)
    String real_name;   //真实姓名
    @ApiModelProperty(value = "创建用户", hidden = true)
    String create_user_account;  //创建用户的编号
    @ApiModelProperty(value = "组织架构编号", required = true)
    String organization_code;//组织机构编号

    String[] role_ids;

}
