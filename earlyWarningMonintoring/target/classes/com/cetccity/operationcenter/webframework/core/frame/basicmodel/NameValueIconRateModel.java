package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.frame.basicmodel
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:28 2019-06-05
 * Updater:     heliangming
 * Update_Date：16:28 2019-06-05
 * 更新描述:    heliangming 补充
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameValueIconRateModel {

    private String name;

    private String value;

    private String icon;

    private boolean rate;
}
