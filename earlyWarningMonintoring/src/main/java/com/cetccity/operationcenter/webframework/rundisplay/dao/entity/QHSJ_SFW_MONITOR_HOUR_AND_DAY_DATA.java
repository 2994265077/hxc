package com.cetccity.operationcenter.webframework.rundisplay.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA {

    private String CREATETIME;
    private String CREATOR;
	private String DATA_STATUS; 
	private String ID; 
	private String IS_REVISED; 
	private String MN; 
	private String MONITOR_FACTOR_CODE; 
	private Date MONITOR_TIME;
    private String MONITOR_VALUE;
    private String REVISED_MONITOR_VALUE;
    private String SITE_CODE;
    private String STANDARD_VALUE;
	private String UPDATER; 
	private String UPDATETIME;
    private String UPDATE_DATE;
    private String YXY_UPDATEDTIME;
    private String ADQ_UPDATE_TIME;
}
