package com.cetccity.operationcenter.webframework.backstage.iot.dao.entity;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.iot.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:38 2019-06-14
 * Updater:     heliangming
 * Update_Date：10:38 2019-06-14
 * 更新描述:    heliangming 补充
 **/
@Data
public class ALARM_PUSH_AVILABLE {

    private String OBJECT_ID;
	private String ALARM_TYPE_LV1; 
	private String ALARM_TYPE_LV2;
	private String DESC; 
	private Integer isEnabled;
    private Integer NUM;
    private String DEVICE_TYPE;
    private String PUSH_TYPE;
    private String ALARM_CANCEL_TIME;
    private String UNIT;
    private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;

    public boolean getEnabled(){
        if(isEnabled == 1)
            return true;
        return false;
    }

}
