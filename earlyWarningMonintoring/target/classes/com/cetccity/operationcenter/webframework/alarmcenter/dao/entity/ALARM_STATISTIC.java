package com.cetccity.operationcenter.webframework.alarmcenter.dao.entity;

import lombok.Data;

@Data
public class ALARM_STATISTIC {
    private String UUID;
    private String LV_1_NAME;
    private String ALARM_TYPE_LV1;
    private String ALARM_NUM;
    private String CANCEL_ALARM;
    private Integer OBJECT_ID;
    private String TIME;
    private String ALARM_TYPE_LV2;
    private String LV_2_NAME;
}
