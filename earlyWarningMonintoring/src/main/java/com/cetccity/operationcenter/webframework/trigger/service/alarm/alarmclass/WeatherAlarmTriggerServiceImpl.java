package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInfoCodeMapper;
import com.cetccity.operationcenter.webframework.trigger.dao.WeatherAlarmMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import com.cetccity.operationcenter.webframework.trigger.entity.WeatherAlarm;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.AbstractEventAlarmTrigger;
import com.cetccity.operationcenter.webframework.trigger.utils.DateUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;

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
 * 气象预警
 */
@Service
@Slf4j
public class WeatherAlarmTriggerServiceImpl extends AbstractEventAlarmTrigger<HashMap> {

    @Autowired
    WeatherAlarmMapper weatherAlarmMapper;

    @Autowired
    AlarmInfoCodeMapper alarmInfoCodeMapper;

    public WeatherAlarmTriggerServiceImpl() {
        super();
        this.maxCancelDay = 2;
    }

    @Override
    public List<HashMap> querySourceData(Date begin, Date end) {
        Map map = new HashMap();
        map.put("begin",begin);map.put("end",end);
        List<WeatherAlarm> query = weatherAlarmMapper.querySourceData(map);
        try {
            return ObjectUtil.listObjectToListMap(query);
        } catch (IllegalAccessException e) {
        	log.error(e.toString());
        }
        return null;
    }

    @Override
    public String getOriginTableName() {
        return "WEATHER_ALARM";
    }

    @Override
    public AlarmInformation row2Alarm(HashMap row) throws Exception {
        WeatherAlarm model = JsonUtils.mapToBean(WeatherAlarm.class,row);
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
        alarm.setRELEASE_TIME(DateUtil.str2Date(model.getRELEASETIME()) );  //发布时间
        alarm.setALARM_STATE(1);              //预警状态，1预警中，0取消预警
        alarm.setCONTENTS(model.getWARNINGCONTENT());         //预警内容
        alarm.setALARM_LEVEL(getAlarmLevel(model.getWARNINGLEVEL()));               //预警级别
        alarm.setALARM_TYPE_LV1("001");         //事件预警
        alarm.setALARM_TYPE_LV2("001001");     //应急突发事件预警
        alarm.setCHANNEL("市气象局");   //渠道
        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
        ObjectBaseInfo baseInfo = new ObjectBaseInfo();
        baseInfo.setALARM_OBJECT("深圳市气象局");    //主体名
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

    public String getAlarmLevel(String sourceLevel) {
        if (Objects.nonNull(sourceLevel)) {
            if (sourceLevel.contains("红色")) {
                return "一级-红";
            }
            if (sourceLevel.contains("橙色")) {
                return "二级-橙";
            }
            if (sourceLevel.contains("黄色")) {
                return "三级-黄";
            }
            if (sourceLevel.contains("蓝色") || sourceLevel.contains("白色")) {
                return "四级-蓝";
            }
        }
        return sourceLevel;
    }
}
