package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.model
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:52 2019-03-06
 * Updater:     heliangming
 * Update_Date：15:52 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Data
public class SanXiaoTip<T> {

    private String info_alert;

    private String hasDetailInfo;

    private String title;

    private T data;
}
