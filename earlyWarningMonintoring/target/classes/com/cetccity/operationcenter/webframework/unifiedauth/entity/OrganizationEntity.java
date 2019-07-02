package com.cetccity.operationcenter.webframework.unifiedauth.entity;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao.entity
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 14:23
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 14:23
 * @Update_Description: huangzezhou 补充
 **/
public class OrganizationEntity {

    String organization_id;
    String organization_name;

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }
}
