//package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;
//
//import com.cetccity.operationcenter.webframework.trigger.dao.YjjcQwjjOrgVMapper;
//import com.cetccity.operationcenter.webframework.trigger.dao.YjjcQwjjSdmInfoVMapper;
//import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
//import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
//import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjOrgV;
//import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjSdmInfoV;
//import com.cetccity.operationcenter.webframework.trigger.service.alarm.AbstractEventAlarmTrigger;
//import com.cetccity.operationcenter.webframework.trigger.utils.DateUtil;
//import com.cetccity.operationcenter.webframework.trigger.utils.ObjectUtil;
//import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.service;
//
//import java.util.*;
//
///**
// * @Package: com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass
// * @Project: alarm-trigger
// * @Creator: huangzezhou
// * @Create_Date: 2019/3/3 12:28
// * @Updater: huangzezhou
// * @Update_Date: 2019/3/3 12:28
// * @Update_Description: huangzezhou 补充
// * @Description:
// **/
//@service
//public class WeijiAlarmTriggerServiceImpl extends AbstractEventAlarmTrigger<HashMap> {
//
//    @Autowired
//    YjjcQwjjOrgVMapper yjjcQwjjOrgVMapper;  //卫计_单位信息表 Mapper 接口
//
//    @Autowired
//    YjjcQwjjSdmInfoVMapper yjjcQwjjSdmInfoVMapper;  //卫计_特殊病种监控表 Mapper 接口
//
//    private List<YjjcQwjjOrgV> baseOrgList = null;
//
//    @Override
//    public List<HashMap> querySourceData(Date begin, Date end) {
//        Map map_time = new HashMap<>();
//        map_time.put("begin",DateUtil.date2Str(begin));map_time.put("end",DateUtil.date2Str(end));
//        List<YjjcQwjjSdmInfoV> query = yjjcQwjjSdmInfoVMapper.querySourceData(map_time);
//        //基础信息查询
//        baseOrgList = yjjcQwjjOrgVMapper.selectList();
//        try {
//            return ObjectUtil.listObjectToListMap(query);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public String getOriginTableName() {
//        return "YJJC_QWJJ_SDM_INFO_V";
//    }
//
//    @Override
//    public AlarmInformation row2Alarm(HashMap row) throws Exception {
//        YjjcQwjjSdmInfoV model = JsonUtils.mapToBean(YjjcQwjjSdmInfoV.class,row);
//        AlarmInformation alarm = new AlarmInformation();
//        //一般不变的指标
//        alarm.setOBJECT_ID(0);  // 任意值
//        alarm.setF_ROW_ID(model.getOBJECT_ID());       //数据外键关联object_id,一般是发生的事件
//        alarm.setORIGIN_TABLE_NAME(getOriginTableName());   //来源表
//        alarm.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));           //事件ID
//        alarm.setCANCEL_TIME(null);   //取消时间，默认为空
//        alarm.setRELEASE_PERSON("预警监测平台");           //发布人
//        alarm.setDISPOSAL_STATE(0);      //处置状态，默认0，未处置
//        alarm.setSEND_STATE(1);      //发送状态，默认1，已发送
//        alarm.setYJJC_CREATE_TIME(null);  //该条数据创建时间
//        alarm.setYJJC_UPDATE_TIME(null);  //该条数据更新时间
//        //一般需要定制化的指标
//        alarm.setRELEASE_TIME(DateUtil.str2Date(model.getDIAG_TIME().substring(0,19)));  //发布时间
//        alarm.setALARM_STATE(isAlarm(model.getDIAG_NAME_INHOS()));              //预警状态，1预警中，0取消预警
//        alarm.setCONTENTS(alarmContent(model.getDIAG_NAME_INHOS()));         //预警内容
//        alarm.setALARM_LEVEL("特殊病情");               //预警级别
//        alarm.setALARM_TYPE_LV1("009");         //事件预警
//        alarm.setALARM_TYPE_LV2("009001");     //应急突发事件预警
//        alarm.setCHANNEL("卫计局");   //渠道
//        // 以下为预警主体的相关字段，包括十一个基础字段：主体名、主体主键、经度、维度、地址、区域代码、街道代码、街道名、社区代码、社区名、楼栋代码。
//        YjjcQwjjOrgV baseOrg = null;
//        for (int i = 0; i < baseOrgList.size(); ++i){
//            if (baseOrgList.get(i).getORG_CODE().equals(model.getORG_CODE()))
//                baseOrg = baseOrgList.get(i);
//        }
//        if (baseOrg == null)
//            return null;
//
//        ObjectBaseInfo baseInfo = new ObjectBaseInfo();
//        baseInfo.setALARM_OBJECT(baseOrg.getORG_NAME());    //主体名
//        baseInfo.setF_OBJECT_ROW_ID(-1D);     //主体主键,事件没有主体，默认为-1
//        baseInfo.setJD84(baseOrg.getJD84());
//        baseInfo.setWD84(baseOrg.getWD84());
//        baseInfo.setADDRESS(baseOrg.getADDRESS());
//        baseInfo.setREGION_CODE(baseOrg.getREGION_CODE());
//        baseInfo.setSTREET_CODE(baseOrg.getSTREET_CODE());
//        baseInfo.setSTREET_NAME(baseOrg.getSTREET_NAME());
//        baseInfo.setCOMMUNITY_CODE(baseOrg.getCOMMUNITY_CODE());
//        baseInfo.setCOMMUNITY_NAME(baseOrg.getCOMMUNITY_NAME());
//        baseInfo.setLDDM(null);
//        alarm.initBaseInfo(baseInfo);
//        return alarm;
//    }
//
//    private int isAlarm(String diagNameInhos){
//        if (diagNameInhos.contains("刀") || diagNameInhos.contains("枪"))
//            return 1;
//        return 0;
//    }
//
//    private String alarmContent(String diagNameInhos){
//        return diagNameInhos.replaceAll("\\?","");
//    }
//}
