package com.cetccity.operationcenter.webframework.rundisplay.dao.entity;

import lombok.Data;

@Data
public class QHSJ_AQI_INFO {
    
    private String OBJECT_ID;
    private String MONITOR_TIME;
    private String SITE_CODE;
	private Integer AQI_VALUE;
	private String YJJC_CREATE_TIME;
    private String YJJC_UPDATE_TIME;
    
}
