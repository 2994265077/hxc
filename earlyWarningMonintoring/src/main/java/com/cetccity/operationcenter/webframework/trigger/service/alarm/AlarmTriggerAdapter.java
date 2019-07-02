package com.cetccity.operationcenter.webframework.trigger.service.alarm;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetc.cloud.alarm.trigger.service
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/3 11:52
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/3 11:52
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/

/**
 *
 * 预警本身就是同步操作，因此没有必要做多线程。
 *
 *
 * 对象预警：是指发生的预警是能对应上某一个唯一对象，例如物联网预警传感器
 * 事件预警：是指发生的预警没有办法对应上唯一对象，例如见访事件（关联人、自然人）、天气预警（地区、区域），其对象没法指定。
 *
 * 预警升级：是指
 * 预警生成：推送过来的数据达到设定的阈值，则产生一条预警。
 * 预警取消：根据推送过来的数据，小于阈值，则取消预警。
 */
interface  AlarmTriggerAdapter<T> {
    //================ 查询接口

    /**
     * 查询源数据
     * 查询在时间区间内产生的源数据信息，用于生成预警
     *
     * @param begin 开始时间,不包含
     * @param end   结束时间，包含
     * @return
     */
    abstract List<T> querySourceData(Date begin, Date end);

    /**
     * 获取源表名
     *
     * @return
     */
    abstract String getOriginTableName();

    //============= 逻辑处理接口

    /**
     * 将告警信息转换为预警模型
     *
     * @param row
     * @return
     */
    abstract AlarmInformation row2Alarm(T row) throws Exception;


    /**
     * 比较两条预警信息是否相同
     *
     * @param m1
     * @param m2
     * @return
     */
    abstract boolean compareAlarm(AlarmInformation m1, AlarmInformation m2);

    /**
     * 将查询到的数据源，进行预警触发规则处理。
     * <p>
     * 目前该逻辑只支持单线程，因为是同步操作，序列后面的数据依赖于序列前面的结果。
     * 如果一定要写多线程，就需要对每一个origin-table的objectId进行严格顺序入队。
     *
     * @return
     */

}
