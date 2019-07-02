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
 * Create_Date: 9:57 2019-05-23
 * Updater:     heliangming
 * Update_Date：9:57 2019-05-23
 * 更新描述:    heliangming 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyValueHasDetailModel {

    private String[] key;

    private String[] value;

    private Boolean hasDetailInfo;
}
