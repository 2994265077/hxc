package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.trigger.dao.WeeklyXinfangEventMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import com.cetccity.operationcenter.webframework.trigger.entity.WeeklyXinfangEvent;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.AbstractEventAlarmTrigger;
import com.cetccity.operationcenter.webframework.trigger.utils.DateUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.SpendTimeUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service.impl
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/3 16:25
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/3 16:25
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/

/**
 * 信访预警
 */
@Service
@Slf4j
public class XinfangAlarmTriggerServiceImpl extends AbstractEventAlarmTrigger<HashMap> {

    @Autowired
    WeeklyXinfangEventMapper weeklyXinfangEventMapper;

    public void scheduleTriggerAlarm(){
        long start = System.currentTimeMillis();
        log.info("xinfang scheduleTriggerAlarm start");
        triggerAlarm();
        log.info("xinfang scheduleTriggerAlarm finished! spend: " + SpendTimeUtil.hhMMss(System.currentTimeMillis() - start));
    }

    public void scheduleCancelAlarmTrigger(){
        long start = System.currentTimeMillis();
        log.info("xinfang scheduleCancelAlarmTrigger start");
        cancelAlarmTrigger(getOriginTableName());
        log.info("xinfang scheduleCancelAlarmTrigger finished! spend: " + SpendTimeUtil.hhMMss(System.currentTimeMillis() - start));
    }

    //上访人数超过30人上报报警
    @Override
    public List<HashMap> querySourceData(Date begin, Date end) {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:SS");
        Map map = new HashMap<>();
        map.put("begin",DateUtil.date2Str(begin));map.put("end",DateUtil.date2Str(end));map.put("VISITPERSONNELNUM","30");
        List<WeeklyXinfangEvent> query = weeklyXinfangEventMapper.querySourceData(map);
        try {
            return ObjectUtil.listObjectToListMap(query);
        } catch (IllegalAccessException e) {
        	log.error(e.toString());
        }
        return null;
    }

    @Override
    public String getOriginTableName() {
        return " ";
    }

    @Override
    public AlarmInformation row2Alarm(HashMap row) throws IOException, ParseException {
        WeeklyXinfangEvent model = JsonUtils.mapToBean(WeeklyXinfangEvent.class,row);
        AlarmInformation alarm = new AlarmInformation();
        //一般不变的指标
        alarm.setOBJECT_ID(0);  // 任意值
        alarm.setF_ROW_ID(model.getOBJECT_ID());       //数据外键关联object_id,一般是发生的事件
        alarm.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
        alarm.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));           //事件ID
        alarm.setCANCEL_TIME(null);   //取消时间，默认为空
        alarm.setRELEASE_PERSON("预警监测平台");           //发布人
        alarm.setDISPOSAL_STATE(0);      //处置状态，默认0，未处置
        alarm.setSEND_STATE(1);      //发送状态，默认1，已发送
        alarm.setYJJC_CREATE_TIME(null);  //该条数据创建时间
        alarm.setYJJC_UPDATE_TIME(null);  //该条数据更新时间
        //一般需要定制化的指标
        alarm.setRELEASE_TIME( DateUtil.str2Date(model.getVISITTIME()) );  //发布时间
        alarm.setALARM_STATE(1);              //预警状态，1预警中，0取消预警
        alarm.setCONTENTS(model.getVISITMATTERSREMARK());         //预警内容
        alarm.setALARM_LEVEL("三级-黄");               //预警级别
        alarm.setALARM_TYPE_LV1("006");         //事件预警
        alarm.setALARM_TYPE_LV2("006002");     //应急突发事件预警
        alarm.setCHANNEL("福田区信访大厅");   //渠道
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
