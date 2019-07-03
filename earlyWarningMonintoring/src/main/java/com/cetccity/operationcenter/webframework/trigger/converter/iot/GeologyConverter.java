/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: GeologyConverter
 * Author:   YHY
 * Date:     2019/5/15 10:40
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter.iot;

import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEventUnion;


/**
 * 〈地质报警转换器〉<br>
 *  表 iot_event
 *   对象代码              对象名称
 *  00233001	        土体含水计状态
 *  00233002	        裂缝计状态
 *  00233003	        收敛计状态
 *  00233004	        水位计状态
 *  00233005	        测斜绳计状态
 *  00233006	        沉降仪状态
 *  00233007	        渗压计状态
 *  00233008	        土压力计状态
 *  00233009	        风速风向仪状态
 *  00233010	        深部位移计状态
 *  00233011	        泥位计状态
 *  00233012	        温度计状态
 *  00233013	        表面位移计状态
 *  00233014	        水压计状态
 *  00233015	        电压计状态
 *  00233016	        雨量计状态
 *  00233017	        测斜仪状态
 *  00233018	        湿度计状态
 *  00233019	        GPS定位仪状态
 *
 *  预警对象 data_code
 *
 *
 *  预警阈值                    预警等级
 *  data_value > 0              重要
 *
 *  取消规则
 *  1、data_value = 0
 * 2、预警触发后 3 day
 *  预警内容
 *  "测量点地址"+“对象名称”+异常
 *  推送规则
 *  目标：事件分拨平台
 *  有报警即推送，若15天状态未改变，再次推送
 *  //TODO 添加15天推送 功能
 *
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
public class GeologyConverter extends AbstractIotEventConverter {


    @Override
    protected String getAlarmContents(IotEventUnion iotEventUnion) {
        return iotEventUnion.getAddress() + iotEventUnion.getDeviceTypeName() +"地质数据异常";
    }

    @Override
    protected String getAlarmLevel(IotEventUnion iotEventUnion) {
        return "三级-黄";
    }

    @Override
    protected String getAlarmObject(IotEventUnion iotEventUnion) {
        return iotEventUnion.getDeviceName();
    }

    @Override
    protected Enums.IotTypeEnum getIotType() {
        return Enums.IotTypeEnum.GEOLOGY_ALARM;
    }
}