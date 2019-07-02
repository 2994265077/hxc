package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:05 2019-04-02
 * Updater:     heliangming
 * Update_Date：16:05 2019-04-02
 * 更新描述:    heliangming 补充
 **/
@Data
public class BUILD_SCORE {

	private String BUILD_ID; 
	private String SCORE; 
	private String SCORE_DETAIL;
	private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;
    private String DANGER_LEVEL;
	private String REGION_CODE; 
	private String ADDRESS; 
	private String COMMUNITY_CODE; 
	private String STREET_CODE; 
	private String JD84; 
	private String WD84; 
	private String STREET_NAME; 
	private String COMMUNITY_NAME; 
	private String CALCULATION_TIME;
	private String MAINDANGER;
}
