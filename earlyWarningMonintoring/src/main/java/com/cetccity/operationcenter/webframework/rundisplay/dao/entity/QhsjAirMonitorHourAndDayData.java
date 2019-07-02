package com.cetccity.operationcenter.webframework.rundisplay.dao.entity;

import lombok.Data;

import java.util.Date;

//空气监测站
@Data
public class QhsjAirMonitorHourAndDayData {

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

    private String UPDATE_DATE;

    private String YXY_UPDATEDTIME;

    private String ADQ_UPDATE_TIME;

    private String FACTOR_UNIT;

}
