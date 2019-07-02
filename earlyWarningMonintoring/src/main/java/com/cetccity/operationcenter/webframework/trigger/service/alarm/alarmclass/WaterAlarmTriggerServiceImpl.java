package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.QHSJ_ENTERPRISE_FACTOR_CODEMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_ENTERPRISE_FACTOR_CODE;
import com.cetccity.operationcenter.webframework.trigger.dao.QhsjSfwMonitorHourDataMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import com.cetccity.operationcenter.webframework.trigger.entity.QhsjSfwMonitorHourData;
import com.cetccity.operationcenter.webframework.trigger.service.alarm.AbstractObjectAlarmTrigger;
import com.cetccity.operationcenter.webframework.trigger.utils.DateUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.SpendTimeUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service.impl
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/21 14:27
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/21 14:27
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/

/**
 * 水环境预警
 */
@Service
@Slf4j
public class WaterAlarmTriggerServiceImpl extends AbstractObjectAlarmTrigger<HashMap> {

    @Autowired
    QhsjSfwMonitorHourDataMapper qhsjSfwMonitorHourDataMapper;

    @Autowired
    QHSJ_ENTERPRISE_FACTOR_CODEMapper qHSJ_ENTERPRISE_FACTOR_CODEMapper;

    public void schedule() {
        long start = System.currentTimeMillis();
        log.info("water trigger start");
        triggerAlarm();
        log.info("water trigger finished! spend: " + SpendTimeUtil.hhMMss(System.currentTimeMillis() - start));
    }

    @Override
    public List<HashMap> querySourceData(Date begin, Date end) {
        List<String> siteCodes = qhsjSfwMonitorHourDataMapper.queryAllBasicSiteCode();
        List<QhsjSfwMonitorHourData> query = new ArrayList<>();
        for(int i = 0; i < siteCodes.size(); i++) {
            Map map = new HashMap();
            map.put("begin", DateUtil.date2Str(begin));
            map.put("end", DateUtil.date2Str(end));
            map.put("SITE_CODE", siteCodes.get(i));
            query.addAll(qhsjSfwMonitorHourDataMapper.querySourceData(map));
        }
        List<HashMap> result = new ArrayList<HashMap>();
        for (int i = 0; i < query.size(); ++i) {
            try {
                result.add(ObjectUtil.objectToMap(query.get(i)));
            } catch (IllegalAccessException e) {
                log.error("", e);
            }
        }
        return result;
    }

    @Override
    public String getOriginTableName() {
        return "QHSJ_SFW_MONITOR_HOUR_DATA";
    }

    @Override
    public AlarmInformation row2Alarm(HashMap row) throws Exception {
        QhsjSfwMonitorHourData model = JsonUtils.mapToBean(QhsjSfwMonitorHourData.class,row);
        String site_code = new BigDecimal(model.getSITE_CODE()).toString();
        ObjectBaseInfo baseInfo = qhsjSfwMonitorHourDataMapper.queryBaseInfo(site_code);
        if (baseInfo == null) {
            throw new Exception("water baseInfo is null, originTableName="+getOriginTableName()+",siteCode=" + site_code);
        }
        String objectName = qhsjSfwMonitorHourDataMapper.querySiteName(site_code);
        NameValueUnitModel nameValueUnitModel = alarmState(model.getMONITOR_FACTOR_CODE(), model.getMONITOR_VALUE());

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
        alarm.setRELEASE_TIME(model.getMONITOR_TIME());  //发布时间
        alarm.setALARM_STATE(Integer.valueOf(nameValueUnitModel.getValue()));              //预警状态，1预警中，0取消预警
        alarm.setCONTENTS("发现" + objectName + "["+nameValueUnitModel.getName()+"("+model.getMONITOR_FACTOR_CODE()+")-->"+model.getMONITOR_VALUE()+" "+nameValueUnitModel.getUnit()+"]"+"指标超标");         //预警内容
        alarm.setALARM_LEVEL("超标");               //预警级别
        alarm.setALARM_TYPE_LV1("005");         //生态环境预警
        alarm.setALARM_TYPE_LV2("005001");     //水环境预警
        alarm.setCHANNEL("环水局");   //渠道
        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
        alarm.initBaseInfo(baseInfo);
        return alarm;
    }

    @Override
    public boolean compareAlarm(AlarmInformation m1, AlarmInformation m2) {
        return true;
    }

    /**
     * 预警状态
     * 总磷(w21011)大于0.2（2/5不达标)；
     * 总氮(w21001)大于2（所有都不达标）
     * 溶解氧(w01009)小于2；
     * 氨氮(w21003)大于2
     * 化学需氧量(w01018）大于40
     *
     * @param type
     * @param value
     * @return
     */
    public NameValueUnitModel alarmState(String type, Double value) {
        NameValueUnitModel nameValueUnitModel = new NameValueUnitModel();
        List<QHSJ_ENTERPRISE_FACTOR_CODE> qHSJ_ENTERPRISE_FACTOR_CODE_list = qHSJ_ENTERPRISE_FACTOR_CODEMapper.getWaterFactorCodeToName(type);
        String factor_name = qHSJ_ENTERPRISE_FACTOR_CODE_list.get(0).getFACTOR_NAME();
        String factor_unit = qHSJ_ENTERPRISE_FACTOR_CODE_list.get(0).getFACTOR_UNIT();
        nameValueUnitModel.setName(factor_name);
        nameValueUnitModel.setUnit(factor_unit);
        switch(type){
            case "w21011" :
                nameValueUnitModel.setValue(value > 0.2 ? "1" : "0");
                break;
            case "w21001" :
                nameValueUnitModel.setValue(value > 2 ? "1" : "0");
                break;
            case "w01009" :
                nameValueUnitModel.setValue(value > 2 ? "1" : "0");
                break;
            case "w21003" :
                nameValueUnitModel.setValue(value > 2 ? "1" : "0");
                break;
            case "w01018" :
                nameValueUnitModel.setValue(value > 2 ? "1" : "0");
                break;
            default :
                nameValueUnitModel.setValue("0");
        }
        return nameValueUnitModel;
    }
}
