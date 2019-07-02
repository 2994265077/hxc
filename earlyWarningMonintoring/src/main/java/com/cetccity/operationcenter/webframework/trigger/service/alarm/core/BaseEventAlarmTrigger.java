package com.cetccity.operationcenter.webframework.trigger.service.alarm.core;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.cetc.cloud.alarm.trigger.service
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/17 17:54
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/17 17:54
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
public class BaseEventAlarmTrigger extends BaseAlarmTrigger {

    public int maxCancelDay = 7;

    /**
     * 事件预警取消
     *
     * 步骤：
     * 1.遍历该类别所有事件预警
     * 2.判断时间是否超过阈值
     * 3.超过则取消预警
     *
     * maxCancelDay 最大预警天数，N天之后自动取消该事件预警
     * @param originTableName 源表名
     *
     */
    public void cancelAlarmTrigger(String originTableName) {
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(originTableName);
        alarmInformation.setALARM_STATE(1);
        List<AlarmInformation> list = alarmInformationMapper.selectAlarmInformation(alarmInformation);

        for (int i = 0; i < list.size(); i++){
            Date cancelDate = DateUtils.addDays(list.get(i).getRELEASE_TIME(), maxCancelDay);
            if (cancelDate.getTime() < new Date().getTime()){ //取消预警
                cancelAlarm(list.get(i).getSYSTEM_ID(), cancelDate);
            }
        }
    }

}
