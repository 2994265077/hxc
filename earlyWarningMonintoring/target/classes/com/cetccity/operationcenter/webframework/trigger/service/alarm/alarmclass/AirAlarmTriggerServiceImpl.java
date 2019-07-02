package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.QhsjAirFactorCodeMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QhsjAirFactorCode;
import com.cetccity.operationcenter.webframework.trigger.dao.QhsjAirMonitorHourDataMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import com.cetccity.operationcenter.webframework.trigger.entity.QhsjAirMonitorHourData;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.AbstractObjectAlarmTrigger;
import com.cetccity.operationcenter.webframework.trigger.utils.DateUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.NumberUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.SpendTimeUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service.impl
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/21 17:34
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/21 17:34
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/

/**
 * 空气预警
 */
@Service
@Slf4j
public class AirAlarmTriggerServiceImpl extends AbstractObjectAlarmTrigger<HashMap> {

    @Autowired
    QhsjAirMonitorHourDataMapper airMonitorHourDataMapper;

    @Autowired
    QhsjAirFactorCodeMapper qHSJ_AIR_FACTOR_CODEMapper;

    public void schedule(){
        long start = System.currentTimeMillis();
        log.info("air trigger start");
        triggerAlarm();
        log.info("air trigger finished! spend: "+ SpendTimeUtil.hhMMss(System.currentTimeMillis() - start));
    }

    @Override
    public List<HashMap> querySourceData(Date begin, Date end) {
        List<String> siteCodes = airMonitorHourDataMapper.queryAllBaseInfoSiteCode();
        List<QhsjAirMonitorHourData> query = new ArrayList<>();
        for(int i = 0; i < siteCodes.size(); i++) {
            Map map = new HashMap();
            map.put("begin", DateUtil.date2Str(begin));
            map.put("end", DateUtil.date2Str(end));
            map.put("MONITOR_VALUE", "150");
            map.put("SITE_CODE", siteCodes.get(i));
            query.addAll(airMonitorHourDataMapper.querySourceData(map));
        }
        List<HashMap> result = new ArrayList<HashMap>();
        for (int i  = 0; i < query.size(); ++i){
            try {
                result.add(ObjectUtil.objectToMap(query.get(i)));
            } catch (IllegalAccessException e) {
                log.error("",e);
            }
        }
        return result;
    }

    @Override
    public String getOriginTableName() {
        return "QHSJ_AIR_MONITOR_HOUR_DATA";
    }

    @Override
    public AlarmInformation row2Alarm(HashMap row) throws Exception {
        QhsjAirMonitorHourData model = JsonUtils.mapToBean(QhsjAirMonitorHourData.class,row);
        String site_code = NumberUtil.double2Str(model.getSITE_CODE());
        ObjectBaseInfo baseInfo = airMonitorHourDataMapper.queryBaseInfo(site_code);
        String objectName = airMonitorHourDataMapper.querySiteName(site_code);
        if (baseInfo == null) {
            throw new Exception("baseInfo is null, originTableName = "+getOriginTableName()+", site_code = " + baseInfo.getF_OBJECT_ROW_ID());
        }
        NameValueUnitModel nameValueUnitModel = alarmState(model.getMONITOR_FACTOR_CODE(), model.getMONITOR_VALUE());
        AlarmInformation alarm = new AlarmInformation();
        //一般不变的指标
        alarm.setOBJECT_ID(0);  // 任意值
        alarm.setF_ROW_ID(model.getOBJECT_ID());       //数据外键关联object_id,一般是发生的事件
        alarm.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
        alarm.setSYSTEM_ID(UUID.randomUUID().toString().replace("-",""));           //事件ID
        alarm.setCANCEL_TIME(null);   //取消时间，默认为空
        alarm.setRELEASE_PERSON("预警监测平台");           //发布人
        alarm.setDISPOSAL_STATE(0);      //处置状态，默认0，未处置
        alarm.setSEND_STATE(0);      //发送状态,0未发送,1，已发送
        alarm.setYJJC_CREATE_TIME(null);  //该条数据创建时间
        alarm.setYJJC_UPDATE_TIME(null);  //该条数据更新时间
        //一般需要定制化的指标
        alarm.setRELEASE_TIME(model.getMONITOR_TIME());  //发布时间
        alarm.setALARM_STATE(Integer.valueOf(nameValueUnitModel.getValue()));              //预警状态，1预警中，0取消预警
        alarm.setCONTENTS("空气存在"+getLevel(model.getMONITOR_VALUE())+"");         //预警内容
        alarm.setCONTENTS("发现" + objectName + "["+nameValueUnitModel.getName()+"("+model.getMONITOR_FACTOR_CODE()+")-->"+model.getMONITOR_VALUE()+" "+nameValueUnitModel.getUnit()+"]"+"指标超标");
        alarm.setALARM_LEVEL(getLevel(model.getMONITOR_VALUE()));               //预警级别
        alarm.setALARM_TYPE_LV1("005");         //生态环境预警
        alarm.setALARM_TYPE_LV2("005002");     //空气预警
        alarm.setCHANNEL("环水局");   //渠道
        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
        alarm.initBaseInfo(baseInfo);
        return alarm;
    }

    @Override
    public boolean compareAlarm(AlarmInformation m1, AlarmInformation m2) {
        return m1.getALARM_LEVEL().equals(m2.getALARM_LEVEL());
    }

    public NameValueUnitModel alarmState(String type, Double value){
        NameValueUnitModel nameValueUnitModel = new NameValueUnitModel();
        List<QhsjAirFactorCode> qHSJ_AIR_FACTOR_CODE_list;
        if ("a34004".equals(type)) {  //PM2.5
            qHSJ_AIR_FACTOR_CODE_list = qHSJ_AIR_FACTOR_CODEMapper.getAirFactorCodeToName(type);
            String factor_name = qHSJ_AIR_FACTOR_CODE_list.get(0).getFACTOR_NAME();
            String factor_unit = qHSJ_AIR_FACTOR_CODE_list.get(0).getFACTOR_UNIT();
            nameValueUnitModel.setName(factor_name);
            nameValueUnitModel.setUnit(factor_unit);
            nameValueUnitModel.setValue(value > 150 ? "1" : "0");
        } else{
            nameValueUnitModel.setValue("0");
        }
        return nameValueUnitModel;
    }

    /**
     * （PM2.5浓度）
     *  优：0-50μg/m³
     *  良：50-100μg/m³
     *  轻度污染：100-150μg/m³
     *  中度污染：150-200μg/m³
     *  重度污染：200-300μg/m³
     *  严重污染：300-500μg/m³
     * @param value
     * @return
     */
    public String getLevel(Double value){
        if (value > 150)
            return "中度污染";
        else if (value > 200)
            return "重度污染";
        else if (value > 300)
            return "严重污染";
        return null;
    }
}
