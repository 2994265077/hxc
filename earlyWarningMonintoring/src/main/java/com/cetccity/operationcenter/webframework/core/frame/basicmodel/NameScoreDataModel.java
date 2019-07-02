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
 * Create_Date: 17:22 2019-06-18
 * Updater:     heliangming
 * Update_Date：17:22 2019-06-18
 * 更新描述:    heliangming 补充
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameScoreDataModel<T> {

    private String name;

    private String score;

    private T data;

}
