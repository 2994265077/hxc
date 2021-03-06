/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: DangerAirConverter
 * Author:   YHY
 * Date:     2019/5/15 11:22
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter.iot;

import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEventUnion;

/**
 * 〈危化品气体报警转换器〉<br>
 *  表 iot_event
 *
 *  对象代码 00213001
 *
 *  预警对象 data_code
 *
 *  预警阈值                    预警等级
 *  data_value > 0              紧急
 *
 *  取消规则
 *  1、data_value = 0
 * 2、预警触发后 30 day
 *  预警内容
 *  "IOT_DEVICE : NAME"+异常
 *  推送规则
 *  目标：事件分拨平台
 *
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
public class DangerAirConverter extends AbstractIotEventConverter {

    @Override
    protected String getAlarmContents(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDeviceName() +"危化品气体数据异常报警";
    }

    @Override
    protected String getAlarmLevel(IotEventUnion iotEventUnion) {
        return "一级-红";
    }

    @Override
    protected String getAlarmObject(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDeviceName();
    }

    @Override
    protected Enums.IotTypeEnum getIotType() {
        return Enums.IotTypeEnum.DANGER_AIR;
    }
}