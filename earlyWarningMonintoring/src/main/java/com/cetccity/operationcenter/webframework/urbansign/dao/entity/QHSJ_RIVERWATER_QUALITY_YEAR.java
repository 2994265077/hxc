package com.cetccity.operationcenter.webframework.urbansign.dao.entity;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:50 2019-05-16
 * Updater:     heliangming
 * Update_Date：9:50 2019-05-16
 * 更新描述:    heliangming 补充
 **/
@Data
public class QHSJ_RIVERWATER_QUALITY_YEAR {

    private String RIVER_NAME;
	private String YEAR;
	private String TARGET_WATER_QUALITY1; 
	private String TARGET_WATER_QUALITY2; 
	private String TARGET_WATER_QUALITY3; 
	private String TARGET_WATER_QUALITY4; 
	private String NOW_WATER_QUALITY1; 
	private String NOW_WATER_QUALITY2; 
	private String NOW_WATER_QUALITY3; 
	private String NOW_WATER_QUALITY4; 
	private String YXY_UPDATEDTIME; 
	private String ADQ_UPDATE_TIME; 
	private String REGION_CODE; 
	private String STREET_CODE; 
	private String COMMUNITY_CODE; 
	private String ADDRESS; 
	private String OBJECT_ID; 
	private String STREET_NAME; 
	private String COMMUNITY_NAME; 
	private String JD84; 
	private String WD84;
}
