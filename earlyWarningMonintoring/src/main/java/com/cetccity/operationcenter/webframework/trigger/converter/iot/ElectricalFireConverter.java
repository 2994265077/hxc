/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ElectricalFireConverter
 * Author:   YHY
 * Date:     2019/5/15 14:29
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter.iot;

import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEventUnion;

/**
 * 〈一句话功能简述〉<br> 
 *  表 iot_event
 *
 *  对象代码 00241005
 *
 *  预警对象 data_code
 *
 *  预警阈值                     预警等级
 *  data_value > 0              异常
 *  取消规则
 *  1、data_value = 0
 *  预警内容
 *  "IOT_DEVICE : NAME"+超压报警
 *  "IOT_DEVICE : NAME"+欠压报警
 *  推送规则
 *  目标：事件分拨平台
 *
 * TODO 15天
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
public class ElectricalFireConverter extends AbstractIotEventConverter {

    @Override
    protected String getAlarmContents(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDeviceName() +"报警";
    }

    @Override
    protected String getAlarmLevel(IotEventUnion iotEventUnion) {
        return "二级-橙";
    }

    @Override
    protected String getAlarmObject(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDeviceName();
    }

    @Override
    protected Enums.IotTypeEnum getIotType() {
        return Enums.IotTypeEnum.ELECTRICAL_FIRE;
    }
}