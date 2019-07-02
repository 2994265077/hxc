package com.cetccity.operationcenter.webframework.unifiedauth.api.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/7 17:01
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/7 17:01
 * @Update_Description: huangzezhou 补充
 **/
@Data
public class UserQueryResultModel {
    String account;
    String real_name;
    String phoneNumber;
    List<RoleEntity> roles;
    String create_user_account;
    String user_id;
    String id_card;
    String organization_code;
    String role_id;
    Integer enabled;
    Date create_time;
}
