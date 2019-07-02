package com.cetccity.operationcenter.webframework.trigger.service.alarm;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.core.BaseEventAlarmTrigger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

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
 * 事件预警触发器
 * <p>
 * 与对象预警不同，事件预警触发器的取消方式不同于对象预警触发器
 **/
@Slf4j
public abstract class AbstractEventAlarmTrigger<T> extends BaseEventAlarmTrigger implements AlarmTriggerAdapter<T> {

    /**
     * 只发布预警，不取消预警
     */
    public void triggerAlarm() {
        log.info("start " + getOriginTableName());

        //当前类型，最新发布预警的时间
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
//            final AlarmInformation last = queryLastAlarmData(source.getFRowId());    //获取该主体最新一条预警
            try {
                if (source.getALARM_STATE() == 1)
                    releaseAlarm(source);   //发布预警
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

    /**
     * 比较两条预警信息是否相同，事件预警由于没有主体，不需要用到此函数
     *
     * @param m1
     * @param m2
     * @return
     */
    public boolean compareAlarm(AlarmInformation m1, AlarmInformation m2) {
        return false;
    }

}
