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
 * Create_Date: 11:04 2019-05-24
 * Updater:     heliangming
 * Update_Date：11:04 2019-05-24
 * 更新描述:    heliangming 补充
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class XXZX_POPULATION_SUMMARYNode {

    private String X_TIME;

    private String PPU_TYPE;

    private String PPU_COUNT_MONTH;
}
