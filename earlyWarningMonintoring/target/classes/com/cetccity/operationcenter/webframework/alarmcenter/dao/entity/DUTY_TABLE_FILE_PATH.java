package com.cetccity.operationcenter.webframework.alarmcenter.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao.entity
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:08 2019-03-14
 * Updater:     heliangming
 * Update_Date：11:08 2019-03-14
 * 更新描述:    heliangming 补充
 **/
@Data
public class DUTY_TABLE_FILE_PATH {

    private Integer OBJECT_ID;
    private String FILE_NAME;
	private String FILE_SUFFIX; 
	private String PATH;
    private String FILE_CODE;
	private Date YJJC_CREATE_TIME;
    private Date YJJC_UPDATE_TIME;
}
