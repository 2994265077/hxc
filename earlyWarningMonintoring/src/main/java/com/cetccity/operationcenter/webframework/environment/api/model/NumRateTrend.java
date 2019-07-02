package com.cetccity.operationcenter.webframework.environment.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api.model
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:05 2019-05-28
 * Updater:     heliangming
 * Update_Date：15:05 2019-05-28
 * 更新描述:    heliangming 补充
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NumRateTrend {

    private Integer num;

    private String rate;

    private String trend;
}
