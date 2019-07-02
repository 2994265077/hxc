package com.cetccity.operationcenter.webframework.unifiedauth.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/7 16:56
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/7 16:56
 * @Update_Description: huangzezhou 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryModel {

    String user_id;
    String account;
    String real_name;
    String phoneNumber;
    String role_id;
    String create_user_account;
    String orgId;
    int pageSize;
    int pageNum;
}
