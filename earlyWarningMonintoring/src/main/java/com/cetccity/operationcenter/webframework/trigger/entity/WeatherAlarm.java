package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 气象预警
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
@Data
public class WeatherAlarm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发布区域
     */
    private String RELEASEAREA;

    /**
     * 预警类别
     */
    private String ALERTCATEGORY;

    /**
     * 预警等级
     */
    private String WARNINGLEVEL;

    /**
     * 发布时间
     */
    private String RELEASETIME;

    /**
     * 取消时间
     */
    private String ENDTIME;

    /**
     * 预警内容
     */
    private String WARNINGCONTENT;


    private String REGION_CODE;

    private String STREET_CODE;

    private String COMMUNITY_CODE;

    private String JD84;

    private String WD84;

    private String ADDRESS;

    private Double OBJECT_ID;

    private String STREET_NAME;

    private String COMMUNITY_NAME;

    private Date YJJC_CREATE_TIME;

    private Date YJJC_UPDATE_TIME;
}
