package com.cetccity.operationcenter.webframework.environment.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:16 2019-05-24
 * Updater:     heliangming
 * Update_Date：18:16 2019-05-24
 * 更新描述:    heliangming 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QJHH_SEWERAGE_INFO {

    private String UID;
    private String USER_ID;
    private String TYPE;
    private String ADDRESS;
    private String NAME;
    private String LNG;
    private String LAT;
    private String UNIT;
    private String REMARK;
    private String CREATOR;
    private String CREATE_TIME;
    private String LAST_EDITOR;
    private String LAST_EDIT_TIME;
    private String OBJECT_ID;
    private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;
    private String REGION_CODE;
    private String STREET_CODE;
    private String COMMUNITY_CODE;
    private String STREET_NAME;
    private String COMMUNITY_NAME;
    private String JD84;
    private String WD84;

}
