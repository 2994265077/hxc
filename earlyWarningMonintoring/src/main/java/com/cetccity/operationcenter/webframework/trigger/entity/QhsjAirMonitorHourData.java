package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-12-21
 */
@Data
public class QhsjAirMonitorHourData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date CREATETIME;

    private String CREATOR;

    private Double DATA_STATUS;

    private String ID;

    private Double IS_REVISED;

    private String MN;

    private String MONITOR_FACTOR_CODE;

    private Date MONITOR_TIME;

    private Double MONITOR_VALUE;

    private Double REVISED_MONITOR_VALUE;

    private Double SITE_CODE;

    private Double STANDARD_VALUE;

    private String UPDATER;

    private Date UPDATETIME;

    private Date UPDATE_DATE;

    private Date YXY_UPDATEDTIME;

    private Date ADQ_UPDATE_TIME;

    private String REGION_CODE;

    private String STREET_CODE;

    private String COMMUNITY_CODE;

    private String JD84;

    private String WD84;

    private String ADDRESS;

    private String STREET_NAME;

    private String COMMUNITY_NAME;

    private Double OBJECT_ID;
}
