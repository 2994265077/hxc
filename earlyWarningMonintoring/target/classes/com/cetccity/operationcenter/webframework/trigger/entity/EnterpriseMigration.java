package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 企业外迁
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
@Data
public class EnterpriseMigration implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Double ROW_ID;

    /**
     * 企业名称
     */
    private String QYMC;

    /**
     * 楼栋代码
     */
    private String LDDM;

    /**
     * 录入时间
     */
    private Date WRITETIME;

    /**
     * 记录id
     */
    private String RECORDID;

    /**
     * 外迁概率
     */
    private String RELOCATION_CHANCE;

    /**
     * 深圳经度
     */
    private String LON;

    /**
     * 深圳纬度
     */
    private String LAT;

    /**
     * 区代码
     */
    private String REGION_CODE;

    /**
     * 街道编码
     */
    private String STREET_CODE;

    /**
     * 社区编码
     */
    private String COMMUNITY_CODE;

    /**
     * WGS84经度
     */
    private String JD84;

    /**
     * WGS84纬度
     */
    private String WD84;

    /**
     * 地址
     */
    private String ADDRESS;

    /**
     * 序列自增id
     */
    private Double OBJECT_ID;

    /**
     * 街道名
     */
    private String STREET_NAME;

    /**
     * 社区名称
     */
    private String COMMUNITY_NAME;

    /**
     * 创建时间
     */
    private Date YJJC_CREATE_TIME;

    /**
     * 更新时间
     */
    private Date YJJC_UPDATE_TIME;
}
