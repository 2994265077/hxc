package com.cetccity.operationcenter.webframework.trigger.service.alarm;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.core.BaseObjectAlarmTrigger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetc.cloud.datasynch.provider.service.impl
 * @Project: dataSyncher
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/13 10:19
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/13 10:19
 * @Update_Description: huangzezhou 补充
 * @Description: //预警触发器
 * 业务场景如下：
 * 所有的预警的产生和关闭，都由监测客体的状态来确定。而不由主观的事件状态。事件状态只是一个外围属性。
 * 而监测状态推送到库里之后，消费者需要去扫描库，根据扫描的数据来产生预警或取消预警。
 * <p>
 * 扫描的数据有以下几种可能，对于同一个主体：
 * 1.取消该主体的报警
 * 2.第一次推送一个告警
 * 3.告警升降级
 * 4.持续推送同一个告警（持续报警）
 * <p>
 * 扫描数据，策略如下：
 * 1.最开始是取消预警，则不发生任何操作
 * 2.最开始是告警信息，则产生一个预警信息
 * 3.一直推送同一个告警信息，则不发生任何操作
 * 4.预警中推送了一个新的告警信息（告警升降级），则取消之前的预警后，产生一个新的预警
 * 5.取消预警的时候，推送任何告警，则产生一条预警。
 * <p>
 * 也就是说，一个主体，在当前时刻只会有一种预警状态。而要做预警状态的变更时，需要先关闭上一个预警状态。
 * <p>
 * 推送来的告警信息有三种：
 * 1. 推送一条该主体告警（这时候需要判断，上一条告警信息是否被取消，如果被取消则不做任何操作。如果没有被取消，
 * 则需要判断当前的告警信息和上一条（数据库该主体最新的一条）比较是否相同，如果相同则不操作。不相同则把上一条预警取消，然后创建一条新的预警。
 * 2. 推送一条该主体的预警取消。则找到最新的一条预警信息，将它取消。
 * <p>
 * 而事件状态更新，需要再启动一个扫描器，来更新每一个预警信息里面的事件状态。
 * <p>
 * <p>
 * 预警信息触发器包含两个东西：一个是预警产生、一个是调用预警推送逻辑。
 * 另外还有一个扫描器，专门改变预警信息中事件的状态disposalState。
 * <p>
 * 调度逻辑：
 * 每5分钟执行一次以下逻辑
 * 1. 查询最后一条预警信息的时间A
 * 2. 查询A-当前时间范围内的源告警数据
 * 3. 处理这部分源数据，要么产生新的预警，要么取消以前的预警
 **/
@Slf4j
public abstract class AbstractObjectAlarmTrigger<T> extends BaseObjectAlarmTrigger implements AlarmTriggerAdapter<T> {

    /**
     * 将查询到的数据源，进行预警触发规则处理。
     * <p>
     * 目前该逻辑只支持单线程，因为是同步操作，序列后面的数据依赖于序列前面的结果。
     * 如果一定要写多线程，就需要对每一个origin-table的objectId进行严格顺序入队。
     *
     * @return
     */
    public void triggerAlarm() {
        //log.info("start "+getOriginTableName());
        //long startTime = System.currentTimeMillis();
        final Date start = queryLastAlarmDataReleaseTime(getOriginTableName());
        final List<T> query = querySourceData(start, new Date());
        for (int i = 0; i < query.size(); ++i) {    //遍历所有数据源
            final T row = query.get(i); //获取一条告警信息

            AlarmInformation source = null;  //将告警信息转换为预警模型
            try {
                source = row2Alarm(row);
            } catch (Exception e) {
                log.error("row2Alarm error! OriginTableName = " + getOriginTableName(), e);
            }
            if (source == null) continue;
            final AlarmInformation last = queryLastAlarmData(getOriginTableName(), source.getF_OBJECT_ROW_ID());//获取该主体最近一条预警

            if (source.getALARM_STATE() == 0) { //如果该条告警信息是取消预警
                if (last == null || last.getALARM_STATE() == 0) {    //没有上一条数据，或者上一条数据已取消发布，则不做任何操作
                    //不做任何操作
                } else { //有上一条数据，且没有取消发布
                    //取消最新一条预警发布
                    cancelAlarm(last.getSYSTEM_ID(), source.getRELEASE_TIME());
                }
            } else {  //该条预警信息不是取消预警
                if (last == null || last.getALARM_STATE() == 0) {    //没有上一条数据，或者上一条数据已取消发布
                    //发布一条新预警
                    try {
                        releaseAlarm(source);
                    } catch (Exception e) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            log.error("release alarm error! OriginTableName = " + getOriginTableName() + ", alarm = "+objectMapper.writeValueAsString(source),e);
                        } catch (JsonProcessingException e1) {
                            log.error("jackson error!", e1);
                        }
                    }
                } else {//有上一条数据，且没有取消发布
                    if (compareAlarm(source, last)) { //如果新的预警模型与最后一条预警相同，则不做任何操作
                        //则不做任何操作
                    } else {  //新的预警模型与最后一条预警不相同，则先取消上一条预警发布，在发布新的预警信息
                        cancelAlarm(last.getSYSTEM_ID(), source.getRELEASE_TIME());    //取消上一条预警信息
                        try {
                            releaseAlarm(source);    //发布一条新的信息
                        } catch (Exception e) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                log.error("release alarm error! OriginTableName = " + getOriginTableName() + ", alarm = "+objectMapper.writeValueAsString(source),e);
                            } catch (JsonProcessingException e1) {
                                log.error("jackson error!", e1);
                            }
                        }
                    }
                }
            }
            //long endTime = System.currentTimeMillis();
            //log.info("end "+getOriginTableName()+", spend "+ DateUtil.spendTime(startTime, endTime));
        }
    }

    /**
     * 为了兼容oracle的时间判断，时间转换为 2018-12-01 00:00:00 字符串样式
     * to_date('2018-12-01 00:00:00','yyyy-mm-dd hh24:mi:ss')
     *
     * @param date
     * @return
     */
    protected String data2str(Date date) {
        if (date == null)
            date = new Date(0);
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return bf.format(date);
    }
}
