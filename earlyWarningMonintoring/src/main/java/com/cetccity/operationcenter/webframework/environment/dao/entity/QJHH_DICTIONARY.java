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
 * Create_Date: 15:09 2019-05-27
 * Updater:     heliangming
 * Update_Date：15:09 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QJHH_DICTIONARY {
    
    private String UID;
	private String CODE;
	private String NAME;
	private String PARENT_ID;
    private String LEVEL_NO;
    private String OBJECT_ID;
    private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;

}
