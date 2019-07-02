package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import lombok.Data;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/16 16:25
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/16 16:25
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
public class Street{
    String street_code;
    String street_name;
    List<Community> communities;
}
