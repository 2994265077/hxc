package com.cetccity.operationcenter.webframework.trigger.entity.iot;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 物联网_设备列表
 * </p>
 *
 * @author jobob
 * @since 2019-01-04
 */
@Data
public class IotDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */

    private String ID;

    /**
     * 名称
     */

    private String NAME;

    /**
     * 设备编号
     */

    private String DEVICE_CODE;

    /**
     * 84经度
     */

    private String WD84;

    /**
     * 84纬度
     */

    private String JD84;

    /**
     * 安装地址
     */

    private String ADDRESS;

    /**
     * 创建时间
     */

    private LocalDateTime CREATE_TIME;

    /**
     * 更新时间
     */

    private LocalDateTime UPDATE_TIME;

    /**
     * 更新状态
     */

    private String UPDATE_STATUS;

    /**
     * 全局唯一id
     */

    private String UUID;

    /**
     * 区
     */

    private String REGION;

    /**
     * 保留
     */

    private String RESERVED3;

    /**
     * 保留
     */

    private String RESERVED4;

    /**
     * 保留
     */

    private String RESERVED5;

    /**
     * 街道
     */

    private String STREET;

    /**
     * 设备类型
     */

    private String DEVICE_TYPE;

    /**
     * 深圳经度
     */

    private String JDSZ;

    /**
     * 深圳纬度
     */

    private String WDSZ;

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


    private Double OBJECT_ID;


    private String STREET_NAME;


    private String COMMUNITY_NAME;


}
