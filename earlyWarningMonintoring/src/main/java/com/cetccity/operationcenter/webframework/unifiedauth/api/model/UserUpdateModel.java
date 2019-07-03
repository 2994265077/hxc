package com.cetccity.operationcenter.webframework.unifiedauth.api.model;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model
 * @Project: 31project-Apr4
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/8 17:08
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/8 17:08
 * @Update_Description: huangzezhou 补充
 **/
public class UserUpdateModel extends UserEntity {
    @ApiModelProperty(value = "用户唯一编号", required = true)
    String user_id;
    @ApiModelProperty(hidden = true)
    String account;
    
    public UserUpdateModel(String userId, String account, String pwd){
    	this.user_id = userId;
    	this.account = account;
    	setPassword(pwd);
    }

    @Override
    public String getUser_id() {
        return user_id;
    }

    @Override
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }
}
