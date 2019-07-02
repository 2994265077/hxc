/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: WaterLoggingIotEventConverter
 * Author:   YHY
 * Date:     2019/5/14 16:15
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter.iot;

import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEventUnion;

/**
 * 〈积涝点报警转换器〉<br>
 *  表 iot_event
 *
 *  对象代码 00260001
 *
 *  预警对象 data_code
 *
 *
 *  预警阈值                    预警等级
 *  data_value < 15              蓝 IV
 *  15 ≤ data_value＜30          黄 III
 *  30 ≤ data_value＜40          橙 II
 *  data_value > 40              红 I
 *
 *  取消规则
 *  1、data_value = 0
 * 2、预警触发后 3 day
 *  预警内容
 *  “测量点地址”+“等级”+积捞报警
 *  推送规则
 *  目标：事件分拨平台
 *
 * @author yhy
 * @create 2019/5/14
 * @since 1.0.0
 */
public class WaterLoggingConverter extends AbstractIotEventConverter {

    @Override
    protected String getAlarmContents(IotEventUnion iotEventUnion) {
        String contents = iotEventUnion.getAddress() +"发生" + getAlarmLevel(iotEventUnion) + "色积涝报警";
        return contents;
    }

    @Override
    protected String getAlarmLevel(IotEventUnion iotEventUnion) {
        // 不用判空，为空就不会触发阈值 也不会调用此方法
        Integer dataValue = iotEventUnion.getDataValue().intValue();
        String alarmLevel = Enums.WatterLoggingLevel.getLevelByDataValue(dataValue).getName();
        return alarmLevel;
    }

    @Override
    protected String getAlarmObject(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDeviceName();
    }

    @Override
    protected Enums.IotTypeEnum getIotType() {
        return Enums.IotTypeEnum.WATER_LOGGING;
    }
}