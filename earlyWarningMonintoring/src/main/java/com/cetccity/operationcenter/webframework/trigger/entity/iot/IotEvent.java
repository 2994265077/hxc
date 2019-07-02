package com.cetccity.operationcenter.webframework.trigger.entity.iot;

import lombok.Data;
import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

/**
 * <p>
 * 物联_报警事件表
 * </p>
 *
 * @author jobob
 * @since 2019-01-04
 */
@Data
public class IotEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Double ID;
    /**
     * 全局唯一id
     */
    private String EVENT_ID;

    /**
     * 统一事件编码
     */
    private String EVENT_CODE;
    /**
     * 事件名称
     */

    private String EVENT_NAME;

    /**
     * 事件等级
     */
    private String EVENT_LEVEL;
    /**
     * 事件状态
     */
    private String EVENT_STATE;
    /**
     * 事件描述
     */
    private Clob EVENT_DESC;
    /**
     * 事件产生时间
     */

    private Date PRODUCE_TIME;
    /**
     * 时间结束时间
     */
    private Date END_TIME;
    /**
     * 创建时间
     */
    private String DURATION;
    /**
     * 更新时间
     */
    private Date UPDATE_TIME;

    /**
     * 更新状态
     */

    private String UPDATE_STATE;
    /**
     * 通用唯一识别码
     */
    private String UUID;

    /**
     * 设备编码
     */
    private String DEVICE_CODE;
    /**
     * 设备名称
     */
    private String DEVICE_NAME;

    /**
     * 数据编码
     */

    private String DATA_CODE;
    /**
     * 数据值
     */

    private String DATA_VALUE;
    /**
     * 参数编码
     */

    private String PARA_CODE;
    /**
     * 参数值
     */

    private String PARA_VALUE;

    /**
     * 是否被处理
     */
    private String IS_PROCESSED;

    /**
     * 处理责任人
     */
    private String PROCESSOR;

    /**
     * 处置单位
     */
    private String PROCESS_UNIT;

    /**
     * 处置系统来源
     */
    private String PROCESS_SYSTEM;

    /**
     * 处置时间
     */
    private Date PROCESS_TIME;

    /**
     * 处理方法
     */
    private String PROCESS_WAY;

    private String RESERVED1;

    private String RESERVED2;

    private String RESERVED3;

    private String RESERVED4;

    private String RESERVED5;

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
