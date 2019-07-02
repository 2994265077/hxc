package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao.entity
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/14 15:02
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/14 15:02
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
public class UserRoleEntity {
    String user_id;
    String role_id;
    Date update_time;
    Date create_time;
}
