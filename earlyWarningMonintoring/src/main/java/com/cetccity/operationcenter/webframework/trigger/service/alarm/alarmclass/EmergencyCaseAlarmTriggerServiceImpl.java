package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.trigger.dao.EmergencyCaseAlarmTriggerMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import com.cetccity.operationcenter.webframework.trigger.entity.WeeklyEmergencyCase;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.AbstractEventAlarmTrigger;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.SpendTimeUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

/**
 * @Package: com.cetc.cloud.alarm.trigger.service.impl
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/17 16:06
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/17 16:06
 * @Update_Description: huangzezhou 补充
 * @Description: //应急事件预警触发器
 **/

/**
 * 应急事件预警
 */
@Service
@Slf4j
public class EmergencyCaseAlarmTriggerServiceImpl extends AbstractEventAlarmTrigger<HashMap> {

    @Autowired
    EmergencyCaseAlarmTriggerMapper emergencyCaseAlarmTriggerMapper;

    public EmergencyCaseAlarmTriggerServiceImpl() {
        super();
        this.maxCancelDay = 7;
    }

    public void scheduleTriggerAlarm() {
        long start = System.currentTimeMillis();
        log.info("emergency scheduleTriggerAlarm start");
        triggerAlarm();
        log.info("emergency scheduleTriggerAlarm finished! spend: " + SpendTimeUtil.hhMMss(System.currentTimeMillis() - start));
    }

    public void scheduleCancelAlarmTrigger() {
        long start = System.currentTimeMillis();
        log.info("emergency scheduleCancelAlarmTrigger start");
        cancelAlarmTrigger(getOriginTableName());
        log.info("emergency scheduleCancelAlarmTrigger finished! spend: " + SpendTimeUtil.hhMMss(System.currentTimeMillis() - start));
    }

    /**
     * 未定级别
     * 较大级别
     * 未定
     * 未达级别
     * 一般级别
     *
     * @param begin 开始时间,不包含
     * @param end   结束时间，包含
     * @return
     */
    @Override
    public List<HashMap> querySourceData(Date begin, Date end) {
        Map map = new HashMap<>();
        map.put("begin",begin);map.put("end",end);
        List<WeeklyEmergencyCase> query = emergencyCaseAlarmTriggerMapper.querySourceData(map);
        try {
            return ObjectUtil.listObjectToListMap(query);
        } catch (IllegalAccessException e) {
        	log.error(e.toString());
        }
        return null;
    }

    @Override
    public String getOriginTableName() {
        return "WEEKLY_EMERGENCY_CASE";
    }

    @Override
    public AlarmInformation row2Alarm(HashMap row) throws IOException {
        WeeklyEmergencyCase model = JsonUtils.mapToBean(WeeklyEmergencyCase.class,row);
        AlarmInformation alarm = new AlarmInformation();
        //一般不变的指标
        alarm.setOBJECT_ID(0);  // 任意值
        alarm.setF_ROW_ID(model.getOBJECT_ID());       //数据外键关联object_id,一般是发生的事件
        alarm.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
        alarm.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));           //事件ID
        alarm.setCANCEL_TIME(null);   //取消时间，默认为空
        alarm.setRELEASE_PERSON("预警监测平台");           //发布人
        alarm.setDISPOSAL_STATE(2);      //处置状态，默认0，已办结
        alarm.setSEND_STATE(1);      //发送状态，默认1，已发送
        alarm.setYJJC_CREATE_TIME(null);  //该条数据创建时间
        alarm.setYJJC_UPDATE_TIME(null);  //该条数据更新时间
        //一般需要定制化的指标
        alarm.setRELEASE_TIME(model.getSTART_TIME());  //发布时间
        alarm.setALARM_STATE(1);              //预警状态，1预警中，0取消预警
        alarm.setCONTENTS(model.getDESCRIPTION());         //预警内容
        alarm.setALARM_LEVEL(model.getAlarmLevel());               //预警级别
        alarm.setALARM_TYPE_LV1("006");         //事件预警
        alarm.setALARM_TYPE_LV2("006001");     //应急突发事件预警
        alarm.setCHANNEL("应急办");   //渠道
        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
        ObjectBaseInfo baseInfo = new ObjectBaseInfo();
        baseInfo.setALARM_OBJECT(model.getADDRESS());    //主体名
        baseInfo.setF_OBJECT_ROW_ID(-1D);     //主体主键,事件没有主体，默认为-1
        baseInfo.setJD84(model.getJD84());
        baseInfo.setWD84(model.getWD84());
        baseInfo.setADDRESS(model.getADDRESS());
        baseInfo.setREGION_CODE(model.getREGION_CODE());
        baseInfo.setSTREET_CODE(model.getSTREET_CODE());
        baseInfo.setSTREET_NAME(model.getSTREET_NAME());
        baseInfo.setCOMMUNITY_CODE(model.getCOMMUNITY_CODE());
        baseInfo.setCOMMUNITY_NAME(model.getCOMMUNITY_NAME());
        baseInfo.setLDDM(null);
        alarm.initBaseInfo(baseInfo);
        return alarm;
    }



}
