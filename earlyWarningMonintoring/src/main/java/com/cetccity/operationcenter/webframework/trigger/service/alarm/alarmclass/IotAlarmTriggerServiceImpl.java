package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.dao.IotAlarmTriggerMapper;
import com.cetccity.operationcenter.webframework.trigger.dao.IotCodeMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.*;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotCode;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotDevice;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEvent;
import com.cetccity.operationcenter.webframework.trigger.utils.CheckData;
import com.cetccity.operationcenter.webframework.trigger.utils.DateUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
import com.cetccity.operationcenter.webframework.trigger.utils.SpendTimeUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Package: com.cetc.cloud.datasynch.provider.service.impl
 * @Project: dataSyncher
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/11 15:45
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/11 15:45
 * @Update_Description: huangzezhou 补充
 * @Description: 逻辑如下：
 * 查询全量的相关物联网数据->通过预警规则触发->生成预警列表->批量入口
 **/

/**
 * 物联网预警
 */
@Service
@Slf4j
public class IotAlarmTriggerServiceImpl {

    @Autowired
    IotAlarmTriggerMapper iotAlarmTriggerMapper;

    @Autowired
    IotCodeMapper iotCodeMapper;

    @Autowired
    protected AlarmInformationV1Mapper alarmInformationMapper;

    public void schedule(){
        long start = System.currentTimeMillis();
        log.info("iot trigger start");
        triggerAlarm();
        log.info("iot trigger finished! spend: "+ SpendTimeUtil.hhMMss(System.currentTimeMillis() - start));
    }

    /**
     * 根据设备类型获取预警类型编号
     *
     * @param device_type
     * @return LV1 为一级预警 LV2 为二级预警
     */
    private AlarmLevelType getAlarmTypeByDeviceCode(String device_type) {
        final String SMOKE = "0003";//烟雾传感器
        final String GAS = "0021";//气体传感器
        final String ELECTRIC = "0024";//电器火灾检测设备
        final String FIRE = "0025";//消防栓水压传感器
        final String GROUND = "0023";//地质灾害监测设备
        final String WATER = "0026";//易涝点水位传感器
        final String FIRE_DOOR = "0022";//防火门传感器

        AlarmLevelType result = new AlarmLevelType();

        if (device_type.equals(SMOKE)) {
            result.LV1 = "003";
            result.LV2 = "003001";
        } else if (device_type.equals(GAS)) {
            result.LV1 = "002";
            result.LV2 = "002003";
        } else if (device_type.equals(ELECTRIC)) {
            result.LV1 = "003";
            result.LV2 = "003002";
        } else if (device_type.equals(FIRE)) {
            result.LV1 = "003";
            result.LV2 = "003003";
        } else if (device_type.equals(FIRE_DOOR)) {
            result.LV1 = "003";
            result.LV2 = "003005";
        } else if (device_type.equals(GROUND)) {
            result.LV1 = "001";
            result.LV2 = "001002";
        } else if (device_type.equals(WATER)) {
            result.LV1 = "001";
            result.LV2 = "001003";
        }
        return result;
    }

    /**
     * 根据预警模型的信息判断当前预警状态，返回预警状态：1预警中，0预警结束
     * <p>
     * 执行逻辑：根据物联网数据不同，有两套逻辑。
     * 1.每次推送过来的预警信息都是监测值，相当于一直在推送新的预警。根据originTableName获取表名，然后根据object_id获取到最近一条预警信息，查看其预警状态
     * 如果预警状态是预警中，则将上一次的预警状态改为预警结束，然后新建预警。
     * 2.每次推送的不是监测值，而是预警状态，1表示预警，0表示语句结束。
     * <p>
     * 另一种解决方案（目前采取这种）：
     * 可以找到规律，除了消防水压之外的其他传感器，例如水位、烟感，都是当值为0时，就取消预警。只有消防水压取消不了。
     *
     * @param data_value 非0预警中，0预警结束
     * @return 预警状态：1预警中，0预警结束
     */
    private int getAlarmStateByDataValue(String data_value) {
        if ("0".equals(data_value)) {
            return 0;
        }
        return 1;
    }

    /**
     * 预警类型
     */
    class AlarmLevelType {
        String LV1;
        String LV2;
    }

    public List<HashMap> querySourceData(Date begin, Date end) {
        Map map_time = new HashMap();
        map_time.put("begin", DateUtil.date2Str(begin));map_time.put("end",DateUtil.date2Str(end));
        List<IotEvent> query = iotAlarmTriggerMapper.querySourceData(map_time);

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

    public String getOriginTableName() {
        return "IOT_EVENT";
    }

    public AlarmInformation row2Alarm(HashMap row) throws Exception {
        IotEvent model = JsonUtils.mapToBean(IotEvent.class,row);
        List<ObjectBaseInfo> baseInfo_list = iotAlarmTriggerMapper.queryBaseInfo(model.getDEVICE_CODE());

        if (baseInfo_list.size() == 0) {
            throw new Exception("baseInfo is null,originTableName="+getOriginTableName()+", deviceCode=" + model.getDEVICE_CODE());
        }
            IotDevice iotDevice = iotAlarmTriggerMapper.queryDevice(model.getDEVICE_CODE());
            IotCode iotCode = iotCodeMapper.queryIOT_CODE(iotDevice.getDEVICE_CODE().substring(0, 4));
            AlarmLevelType type = getAlarmTypeByDeviceCode(iotDevice.getDEVICE_TYPE());
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
            alarm.setRELEASE_TIME(model.getPRODUCE_TIME());  //发布时间
            alarm.setALARM_STATE(getAlarmStateByDataValue(model.getDATA_VALUE()));              //预警状态，1预警中，0取消预警
            if (Objects.isNull(iotCode)) {
                log.info("iot 设备未备案{}", iotDevice);
                alarm.setCONTENTS("未备案设备：" + iotDevice.getDEVICE_CODE() + "发生报警");         //预警内容
            } else {
                alarm.setCONTENTS(iotCode.getDEVICE_NAME() + "发生报警");         //预警内容
            }
            alarm.setALARM_LEVEL(model.getEVENT_LEVEL());               //预警级别
            alarm.setALARM_TYPE_LV1(type.LV1);         //生态环境预警
            alarm.setALARM_TYPE_LV2(type.LV2);     //水环境预警
            alarm.setCHANNEL("物联网平台");   //渠道
            // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
            alarm.initBaseInfo(baseInfo_list.get(0));
        return alarm;
    }

    public boolean compareAlarm(AlarmInformation m1, AlarmInformation m2) {
        //物联网恒等于true，因为不存在预警升级的情况，所有预警都是一样的
        return true;
    }

    /*创建报警规则*/
    public void triggerAlarm() {
        log.info("start "+getOriginTableName());
        long startTime = System.currentTimeMillis();
        //查询某一张预警来源表在预警表中,生成的最后一条预警信息的发布时间
        final Date start = queryLastAlarmDataReleaseTime(getOriginTableName());
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
            final AlarmInformation last = queryLastAlarmData(getOriginTableName(), source.getF_OBJECT_ROW_ID());//获取该主体最近一条预警

            if (source.getALARM_STATE() == 1) {  //该条预警信息是预警
                if (last == null || last.getALARM_STATE() == 0) {    //没有上一条数据
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
                }
            }
            long endTime = System.currentTimeMillis();

            //执行取消物联网报警---不需要自己去取消报警，由dba的触发器查到该条数据的处置状态DISPOSAL_STATE发生变更的情况下自动改动报警状态ALARM_STATE
        }

        //执行取消物联网报警--七天后失效
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        java.util.Date date =cal.getTime();
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setALARM_STATE(1);
        alarmInformation.setORIGIN_TABLE_NAME("IOT_EVENT");
        alarmInformation.setRELEASE_TIME(date);
        List<AlarmInformation> alarmInformation_list = alarmInformationMapper.selectAlarmInformationIot(alarmInformation);
        SimpleDateFormat df = new SimpleDateFormat();// 格式化时间

        Calendar cal_current = Calendar.getInstance();// 获取当前时间
        Date cancelTime = cal_current.getTime();
        for(AlarmInformation alarmInformations:alarmInformation_list) {
            cancelAlarm(alarmInformations.getSYSTEM_ID(),cancelTime);
        }
    }

    /**
     * 查询某一张预警来源表在预警表中,生成的最后一条预警信息的发布时间
     */
    protected Date queryLastAlarmDataReleaseTime(String originTableName) {
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(originTableName);
        List<AlarmInformation> result = alarmInformationMapper.queryLastAlarmDataReleaseTime(alarmInformation);
        return result.size() == 0 ? new Date(0) : result.get(0).getRELEASE_TIME();
    }

    /**
     * 查询该预警对象最后一条预警信息
     * 保证主体唯一性：来源表+objectId
     *
     * 将 RELEASE_TIME 降序 改为 object_id 降序，因为时间可能有重复的，导致获取不到最后一条预警
     */
    protected AlarmInformation queryLastAlarmData(String originTableName, double fObjectRowId) {
        Page<AlarmInformation> page = new Page<AlarmInformation>(1,1);
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(originTableName);
        alarmInformation.setF_OBJECT_ROW_ID(fObjectRowId);
        List<AlarmInformation> result = alarmInformationMapper.queryLastAlarmData(alarmInformation);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }

    /**
     * 发布一条新预警
     * 1.需要先存放至数据库，
     * 2.然后推送，修改推送时间。推送是其他模块实现。
     * @param model
     * @return
     */
    protected int releaseAlarm(AlarmInformation model) throws Exception {
        int object_id = alarmInformationMapper.getSequenceId();
        model.setOBJECT_ID(object_id);
        if (!CheckData.checkFieldNotNull(model)){
            throw new Exception("Alarm is error! Some field is null that should not be null!");
        }
        return alarmInformationMapper.insert(model);
    }

    /**
     * 取消预警
     * @param systemId  事件编号
     * @param cancelTime  取消发布时间
     * @return
     */
    protected int cancelAlarm(String systemId, Date cancelTime) {
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setALARM_STATE(0);
        alarmInformation.setCANCEL_TIME(cancelTime);
        alarmInformation.setSYSTEM_ID(systemId);
        return alarmInformationMapper.updateState(alarmInformation);
    }


}
