package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 劳资纠纷
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
@Data
public class LaborDispute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Double ROW_ID;

    /**
     * 数据来源
     */
    private String SOURCE;

    /**
     * 推送时间
     */
    private Date PUSH_TIME;

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
    private String WRITETIME;

    /**
     * 涉及金额
     */
    private String MONEY;

    /**
     * 详细内容
     */
    private String CONTENT;

    /**
     * 类别
     */
    private String TYPE;

    /**
     * 标题
     */
    private String TITLE;

    /**
     * 区代码
     */
    private String REGION_CODE;

    /**
     * 街道代码
     */
    private String STREET_CODE;

    /**
     * 社区代码
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
     * 街道名称
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
