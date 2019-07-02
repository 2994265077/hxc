/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: HydrantPressureConverter
 * Author:   YHY
 * Date:     2019/5/15 11:44
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
 *  对象代码 00251001
 *
 *  预警对象 data_code
 *
 *  预警阈值                     预警等级
 *  data_value > 0.6             超压
 *  data_value < 0.16            欠压
 *  取消规则
 *  1、data_value = 0
 *  2、触发事件 ＞15 day
 *  预警内容
 *  "IOT_DEVICE : NAME"+超压报警
 *  "IOT_DEVICE : NAME"+欠压报警
 *  推送规则
 *  目标：事件分拨平台
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
public class HydrantPressureConverter extends AbstractIotEventConverter {


    @Override
    protected String getAlarmContents(IotEventUnion iotEventUnion) {
        String contents = iotEventUnion.getDeviceName();
        double doubleValue = iotEventUnion.getDataValue().doubleValue();
        if (doubleValue < 0.16 ) {
            contents += "欠压报警";
        }
        if (doubleValue > 0.6) {
            contents += "超压报警";
        }
        return contents;
    }

    @Override
    protected String getAlarmLevel(IotEventUnion iotEventUnion) {
        return "重要";
    }

    @Override
    protected String getAlarmObject(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDeviceName();
    }

    @Override
    protected boolean isExceededThreshold(IotEventUnion iotEventUnion) {
        double doubleValue = iotEventUnion.getDataValue().doubleValue();
        return  doubleValue >= 0.16 && doubleValue <= 0.6;
    }

    @Override
    protected Enums.IotTypeEnum getIotType() {
        return Enums.IotTypeEnum.HYDRANT_PRESSURE;
    }
}