package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInfoCodeMapper;
import com.cetccity.operationcenter.webframework.trigger.dao.LaborDisputeMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.*;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.AbstractEventAlarmTrigger;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service.impl
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/16 15:02
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/16 15:02
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/

/**
 * 劳资纠纷
 */
@Service
@Slf4j
public class LaborDisputeAlarmTriggerServiceImpl extends AbstractEventAlarmTrigger<HashMap> {

    @Autowired
    LaborDisputeMapper laborDisputeMapper;

    @Autowired
    AlarmInfoCodeMapper alarmInfoCodeMapper;

    /**
     * 不需要取消报警
     * @param originTableName 源表名
     */
    @Override
    public void cancelAlarmTrigger(String originTableName) {

    }

    @Override
    public List<HashMap> querySourceData(Date begin, Date end) {
        Map map = new HashMap<>();
        map.put("begin",begin);map.put("end",end);
        List<LaborDispute> query = laborDisputeMapper.querySourceData(map);
        try {
            return ObjectUtil.listObjectToListMap(query);
        } catch (IllegalAccessException e) {
        	log.error(e.toString());
        }
        return null;
    }

    @Override
    public String getOriginTableName() {
        return "LABOR_DISPUTE";
    }

    @Override
    public AlarmInformation row2Alarm(HashMap row) throws Exception {
        String jsonStr = objectMapper.writeValueAsString(row);
        LaborDispute model = objectMapper.readValue(jsonStr, LaborDispute.class);

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
        alarm.setRELEASE_TIME(model.getPUSH_TIME() );  //发布时间
        alarm.setALARM_STATE(1);              //预警状态，1预警中，0取消预警
        alarm.setCONTENTS(model.getCONTENT());         //预警内容
        alarm.setALARM_LEVEL("有劳资纠纷风险");               //预警级别
        alarm.setALARM_TYPE_LV1("004");         //事件预警
        alarm.setALARM_TYPE_LV2("004001");     //应急突发事件预警
        alarm.setCHANNEL("决策支撑平台");   //渠道
        String alarmObject = model.getQYMC();
        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
        ObjectBaseInfo baseInfo = new ObjectBaseInfo();
        baseInfo.setALARM_OBJECT(alarmObject);    //主体名
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

    class LEVEL{
        String Lv1;
        String Lv2;
    }

    private LEVEL getLevel(String alertcategory){
        List<AlarmInfoCode> alarmInfoCodeList = alarmInfoCodeMapper.selectList();
        for (int i = 0; i < alarmInfoCodeList.size(); ++i){
            if (alarmInfoCodeList.get(i).getLV_2().equals(alertcategory)) {
                LEVEL level = new LEVEL();
                level.Lv1 = alarmInfoCodeList.get(i).getLV_1();
                level.Lv2 = alarmInfoCodeList.get(i).getLV_2();
                return level;
            }
        }
        return null;
    }
}
