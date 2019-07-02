package com.cetccity.operationcenter.webframework.urbansign.dao.entity;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:01 2019-05-31
 * Updater:     heliangming
 * Update_Date：17:01 2019-05-31
 * 更新描述:    heliangming 补充
 **/
@Data
public class WEEKLY_EMERGENCY_CASE {
    
    private String ID;
    private String LEVEL;
	private String NAME;
	private String TYPE;
	private String TYPE_CODE;
	private String ADDRESS;
	private String DESCRIPTION;
	private String DEATH_PEOPLE;
    private String INJURE_PEOPLE;
    private String PROPERTY_DAMAGE;
	private String REASON;
	private String RELATE_PEOPLE;
    private String SEND_UNIT;
	private String X;
	private String Y;
	private String XY;
	private String START_TIME;
    private String GRID_NAME;
	private String REGISTER_PEOPLE_NAME;
	private String REGISTER_TIME;
    private String JD84;
	private String WD84;
	private String REGION_CODE;
	private String STREET_CODE;
	private String COMMUNITY_CODE;
	private String OBJECT_ID;
	private String ACCIDENT_TYPE;
	private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;
    private String STREET_NAME;
	private String COMMUNITY_NAME;
	private String ACCIDENT_TYPE_CODE;
        
}
