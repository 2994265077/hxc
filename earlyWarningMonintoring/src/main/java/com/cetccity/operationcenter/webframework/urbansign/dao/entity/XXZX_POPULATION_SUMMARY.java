package com.cetccity.operationcenter.webframework.urbansign.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:33 2019-05-24
 * Updater:     heliangming
 * Update_Date：9:33 2019-05-24
 * 更新描述:    heliangming 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XXZX_POPULATION_SUMMARY {

    private String PPU_TYPE; 
	private String PPU_COUNT; 
	private String PPU_DURATION; 
	private String UPDATE_DT; 
	private String NO; 
	private String OBJECT_ID;
    private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;

}
