package com.cetccity.operationcenter.webframework.trigger.utils;


import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Package: com.cetc.cloud.alarm.trigger.utils
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/4 12:01
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/4 12:01
 * @Update_Description: huangzezhou 补充
 * @Description: 事件类别（无主体）的预警生成逻辑：只要产生事件，就产生预警。当时间过去7天就取消。
 * 对象预警生成逻辑：产生事件，要判断该事件的状态是预警，还是取消预警。一条数据只能是预警或者取消预警。
 * 校验数据：
 * 1. origin_table_name + f_object_row_id 定位同一个主体
 * 2. 同一个主体（非-1)只能有一条数据没有取消预警，不能同时存在多条预警
 * 3. 事件类预警在时间外被取消。
 **/
@Slf4j
public class CheckData {

    /**
     * 判断预警模型中不能为空的字段是否为空
     * 不能为空的字段有：
     * object_id
     * alarm_object
     * f_row_id
     * origin_table_name
     * system_id
     * release_time
     * release_person
     * alarm_state
     * contents
     * ALARM_TYPE_LV1
     * ALARM_TYPE_LV2
     * SEND_STATE
     * CHANNEL
     * F_OBJECT_ROW_ID
     *
     * @param alarm
     * @return
     */
    public static boolean checkFieldNotNull(AlarmInformation alarm) {
//        if (alarm.getAddress() == null)
//            throw new Exception("Address should not be null!");
        if (alarm.getJD84() == null) {
            log.error("jd84 is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getWD84() == null) {
            log.error("wd84 is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getALARM_OBJECT() == null) {
            log.error("alarm_object is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getF_ROW_ID() == null) {
            log.error("f_row_id is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getORIGIN_TABLE_NAME() == null) {
            log.error("origin_table_name is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getSYSTEM_ID() == null) {
            log.error("system_id is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getRELEASE_TIME() == null) {
            log.error("release_time is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getRELEASE_PERSON() == null) {
            log.error("release_person is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getALARM_STATE() == null) {
            log.error("alarm_state is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getCONTENTS() == null) {
            log.error("contents is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getALARM_TYPE_LV1() == null) {
            log.error("alarm_type_lv1 is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getALARM_TYPE_LV2() == null) {
            log.error("alarm_type_lv2 is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getSEND_STATE() == null) {
            log.error("send_state is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getCHANNEL() == null) {
            log.error("channel is null, alarm={}", alarm);
            return false;
        }
        if (alarm.getF_OBJECT_ROW_ID() == null) {
            log.error("f_object_row_id is null, alarm={}", alarm);
            return false;
        }
        return true;
    }
}
