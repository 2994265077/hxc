package com.cetccity.operationcenter.webframework.trigger.entity.iot;

/**
 * 工程包名:   com.cetc.cloud.alarm.trigger.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:56 2019-03-20
 * Updater:     heliangming
 * Update_Date：18:56 2019-03-20
 * 更新描述:    heliangming 补充
 **/
import lombok.Data;
/**
 * <p>
 * 物联网_设备编号
 * </p>
 *
 * @author jobob
 * @since 2019-01-04
 */
@Data
public class IotCode {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */

    private String OBJECT_ID;

    /**
     * 名称
     */

    private String DEVICE_NAME;

    /**
     * 设备编号
     */

    private String DEVICE_TYPE;

}
