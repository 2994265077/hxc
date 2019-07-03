package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.dao.FINANCIAL_WARNINGMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.FINANCIAL_WARNING;
import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import com.cetccity.operationcenter.webframework.trigger.utils.CheckData;
import com.cetccity.operationcenter.webframework.trigger.utils.DateUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:34 2019-03-11
 * Updater:     heliangming
 * Update_Date：15:34 2019-03-11
 * 更新描述:    heliangming 补充
 **/
@Service
@Slf4j
public class FinanceAlarmTriggerServiceImpl{

    @Autowired
    FINANCIAL_WARNINGMapper fINANCIAL_WARNINGMapper;

    @Autowired
    AlarmInformationV1Mapper alarmInformationMapper;

    /**
     * 发布报警，取消上一次报警
     */
    public void triggerAlarm() {
        log.info("start "+getOriginTableName());
        long startTime = System.currentTimeMillis();

        //当前类型，最新发布预警的时间
        //final Date start = queryLastAlarmDataReleaseTime(getOriginTableName());
        final Date start = null;
        final List<HashMap> query = querySourceData(start, new Date());
        for (int i = 0; i < query.size(); ++i) {    //遍历所有数据源
            final HashMap row = query.get(i); //获取一条告警信息
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
        long endTime = System.currentTimeMillis();
    }

    /**
     *
     * @param begin 开始时间,不包含
     * @param end   结束时间，包含
     * @return 去掉经纬度为空的数据--记得开发
     */

    public List<HashMap> querySourceData(Date begin, Date end) {
        Map map = new HashMap<>();
        map.put("begin", DateUtil.date2Str(begin));
        map.put("end",DateUtil.date2Str(end));
        List<FINANCIAL_WARNING> query = fINANCIAL_WARNINGMapper.querySourceData(map);
        try {
            return ObjectUtil.listObjectToListMap(query);
        } catch (IllegalAccessException e) {
        	log.error(e.toString());
        }
        return null;
    }

    public String getOriginTableName() {
        return "FINANCIAL_WARNING";
    }

    public AlarmInformation row2Alarm(HashMap row) throws Exception {
        FINANCIAL_WARNING model = JsonUtils.mapToBean(FINANCIAL_WARNING.class,row);
        AlarmInformation alarm = new AlarmInformation();
        //一般不变的指标
        alarm.setOBJECT_ID(0);  // 任意值
        alarm.setF_ROW_ID(model.getOBJECT_ID());       //数据外键关联object_id,一般是发生的事件
        alarm.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
        alarm.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));           //事件ID
        alarm.setCANCEL_TIME(null);   //取消时间，默认为空
        alarm.setRELEASE_PERSON("预警监测平台");           //发布人
        alarm.setDISPOSAL_STATE(0);      //处置状态，默认0，未处置
        alarm.setSEND_STATE(0);      //发送状态,0未发送,1，已发送
        alarm.setYJJC_CREATE_TIME(null);  //该条数据创建时间
        alarm.setYJJC_UPDATE_TIME(null);  //该条数据更新时间
        //一般需要定制化的指标
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        alarm.setRELEASE_TIME(model.getPUSH_TIME());  //发布时间
        alarm.setALARM_STATE(1);              //预警状态，1预警中，0取消预警
        alarm.setCONTENTS(model.getNAME()+"冒烟指数"+model.getMASS_SOCRE());         //预警内容
        alarm.setALARM_LEVEL(getAlarmLevel(model.getMASS_SOCRE()));               //预警级别
        alarm.setALARM_TYPE_LV1("004");         //事件预警
        alarm.setALARM_TYPE_LV2("004003");     //应急突发事件预警
        alarm.setCHANNEL("金融办");   //渠道
        String alarmObject = model.getNAME();
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

    public Map findLatelyAlarmState(){
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        List<AlarmInformation> source_list = alarmInformationMapper.findLatelyAlarmState(alarmInformation);
        Map map = new HashMap();
        map.put("LatelyState",source_list.get(0).getALARM_STATE());//上一次报警状态
        map.put("OBJECT_ID",source_list.get(0).getOBJECT_ID());//上一次报警id
        return map;
    }

    protected int releaseAlarm(AlarmInformation model) throws Exception {
        int object_id = alarmInformationMapper.getSequenceId();
        model.setOBJECT_ID(object_id);
        if (!CheckData.checkFieldNotNull(model)){
            throw new Exception("Alarm is error! Some field is null that should not be null!");
        }
        return alarmInformationMapper.insert(model);
    }

    //查询FFL的报警信息，若有数据则取消报警
    protected void cancelAlarm(int object_id) {
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setALARM_STATE(0);
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        alarmInformation.setOBJECT_ID(object_id);
        alarmInformationMapper.cancelFFLAlarm(alarmInformation);
    }

    public String getAlarmLevel(String score) {
        int scoreIntValue = 0;
        try {
            scoreIntValue = Integer.parseInt(score);
        } catch (Exception e) {
            log.error("冒烟指数转成int失败", e);
        }
        if (scoreIntValue >= 90) {
            return "三级-黄";
        }
        return "四级-蓝";
    }
}
