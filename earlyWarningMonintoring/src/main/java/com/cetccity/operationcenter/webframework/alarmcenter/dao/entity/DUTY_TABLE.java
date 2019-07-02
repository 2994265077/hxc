package com.cetccity.operationcenter.webframework.alarmcenter.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:42 2019-03-15
 * Updater:     heliangming
 * Update_Date：17:42 2019-03-15
 * 更新描述:    heliangming 补充
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DUTY_TABLE {

    private Integer OBJECT_ID;
    private String SCHEDULE_TIME;
    private String DEPT_NAME;
	private String DUTY_LEADER; 
	private String DUTY_LEADER_POSITION; 
	private String DUTY_LEADER_PHONE; 
	private String DUTY_SEC; 
	private String DUTY_SEC_POSITION; 
	private String DUTY_SEC_PHONE; 
	private String DUTY_CLERK; 
	private String DUTY_CLERK_POSITION; 
	private String DUTY_CLERK_PHONE; 
	private String DUTY_CODE; 
	private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;
	private String DUTY_DAY;
}
