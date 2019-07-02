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
 * @since 2018-12-17
 */
@Data
public class WeeklyEmergencyCase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原始id
     */
    private Double ID;

    /**
     * 事件等级
     */
    private String LEVEL;

    /**
     * 事件名称
     */
    private String NAME;

    /**
     * 事件类型
     */
    private String TYPE;

    /**
     * 事件类型代码
     */

    private String TYPE_CODE;

    /**
     * 事件发生地址
     */
    private String ADDRESS;

    /**
     * 事件描述
     */
    private String DESCRIPTION;

    /**
     * 死亡人数
     */
    private Double DEATH_PEOPLE;

    /**
     * 受伤人数
     */
    private Double INJURE_PEOPLE;

    /**
     * 财产损失
     */
    private String PROPERTY_DAMAGE;
    /**
     * 事件原因
     */
    private String REASON;
    /**
     * 相关人数
     */
    private Double RELATE_PEOPLE;
    /**
     * 上报单位
     */
    private String SEND_UNIT;
    /**
     * 经度
     */
    private String X;
    /**
     * 纬度
     */
    private String Y;
    /**
     * 经纬度
     */
    private String XY;
    /**
     * 事件起始时间
     */
    private Date START_TIME;
    /**
     * 网格名称
     */
    private String GRID_NAME;

    /**
     * 登记人姓名
     */

    private String REGISTER_PEOPLE_NAME;

    /**
     * 登记时间
     */

    private Date REGISTER_TIME;

    /**
     * 84经度
     */

    private String JD84;

    /**
     * 84纬度
     */

    private String WD84;

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
     * system自增id
     */

    private Double OBJECT_ID;

    /**
     * 事故类型
     */

    private String ACCIDENT_TYPE;

    /**
     * 创建时间
     */

    private Date YJJC_CREATE_TIME;

    /**
     * 更新时间
     */

    private Date YJJC_UPDATE_TIME;


    private String STREET_NAME;


    private String COMMUNITY_NAME;


}
