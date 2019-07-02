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
 * @since 2019-01-03
 */
@Data
public class WeeklyXinfangEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String VISITTIME;

    private String VISITNO;

    private String VISITPERSONNELNUM;

    private String CONTRADICTION;

    private String MATTERSNAME;

    private String VISITMATTERSREMARK;

    private String EVENTADDRESS;

    private String PERFORMANCENAME;

    private String DEPARTMENTNAME;

    private String VISITPLACE;

    private String OUTATTITUDE;

    private String ISINCLUDEDSTATISTICS;

    private String VISITADDRESSNAME;

    private String SOURCE;

    private String VISITTYPE;

    private String REGION_CODE;

    private String COMMUNITY_CODE;

    private String STREET_CODE;

    private Double OBJECT_ID;

    private String JD84;

    private String WD84;

    private String STREET_NAME;

    private String COMMUNITY_NAME;

    private String ADDRESS;

    private Date YJJC_CREATE_TIME;

    private Date YJJC_UPDATE_TIME;
}
