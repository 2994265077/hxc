package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
@Data
public class AlarmInfoCode implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 全表自增id
     */
    private Double OBJECT_ID;
    /**
     * 一级预警等级编码
     */
    private String LV_1;
    /**
     * 一级预警等级名称
     */
    private String LV_1_NAME;
    /**
     * 二级预警等级编码
     */
    private String LV_2;
    /**
     * 二级预警等级名称
     */
    private String LV_2_NAME;
    /**
     * 创建时间
     */
    private LocalDateTime YJJC_CREATE_TIME;
    /**
     * 修改时间
     */
    private LocalDateTime YJJC_UPDATE_TIME;

    private String REGION_CODE;

    private String ADDRESS;

    private String COMMUNITY_CODE;

    private String STREET_CODE;

    private String JD84;

    private String WD84;

    private String STREET_NAME;

    private String COMMUNITY_NAME;
}
