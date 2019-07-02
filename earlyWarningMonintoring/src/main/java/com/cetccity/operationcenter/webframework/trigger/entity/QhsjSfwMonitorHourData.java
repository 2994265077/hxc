package com.cetccity.operationcenter.webframework.trigger.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class QhsjSfwMonitorHourData implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private Date CREATETIME;
    @JsonProperty
    private String CREATOR;
    @JsonProperty
    private Long DATA_STATUS;
    @JsonProperty
    private String ID;
    @JsonProperty
    private Long IS_REVISED;
    @JsonProperty
    private String MN;
    @JsonProperty
    private String MONITOR_FACTOR_CODE;
    @JsonProperty
    private Date MONITOR_TIME;
    @JsonProperty
    private Double MONITOR_VALUE;
    @JsonProperty
    private Double REVISED_MONITOR_VALUE;
    @JsonProperty
    private String SITE_CODE;
    @JsonProperty
    private String STANDARD_VALUE;
    @JsonProperty
    private String UPDATER;
    @JsonProperty
    private Date UPDATETIME;
    @JsonProperty
    private Date UPDATE_DATE;
    @JsonProperty
    private Date YXY_UPDATEDTIME;
    @JsonProperty
    private Date ADQ_UPDATE_TIME;
    @JsonProperty
    private String REGION_CODE;
    @JsonProperty
    private String STREET_CODE;
    @JsonProperty
    private String COMMUNITY_CODE;
    @JsonProperty
    private String JD84;
    @JsonProperty
    private String WD84;
    @JsonProperty
    private String ADDRESS;
    @JsonProperty
    private String STREET_NAME;
    @JsonProperty
    private String COMMUNITY_NAME;
    @JsonProperty
    private Double OBJECT_ID;

}
