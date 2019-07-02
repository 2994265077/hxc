package com.cetccity.operationcenter.webframework.rundisplay.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.AlarmInformationMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.rundisplay.dao.*;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TimeValueModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.*;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.WaterDetailModel;
import com.cetccity.operationcenter.webframework.rundisplay.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("waterStationService")
@CacheConfig(cacheNames = "ecoEnvironmentChart")
public class WaterStationServiceImpl implements WaterStationService {

    @Autowired
    QHSJ_SURF_WATER_BASICINFOMapper qHSJ_SURF_WATER_BASICINFOMapper;

    @Autowired
    QHSJ_ENTERPRISE_FACTOR_CODEMapper qHSJ_ENTERPRISE_FACTOR_CODEMapper;  //地表水使用污染源的code表

    @Autowired
    QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper qHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper;

    @Autowired
    QHSJ_SFW_MONITOR_DAY_DATAMapper qHSJ_SFW_MONITOR_DAY_DATAMapper;

    @Autowired
    AlarmInformationMapper aLARM_INFORMATIONMapper;

    @Autowired
    QhsjTAirBasicInfoMapper qHSJ_T_AIR_BASICINFOMapper;

    @Cacheable(key="#id + 'WaterStationServiceImpl.getWaterStationDataOfDetails'")
    public WaterDetailModel getWaterStationDataOfDetails(String id){
        WaterDetailModel waterDetailModel = new WaterDetailModel();
        //获取河口名称编号关系
        HashMap map = new HashMap();
        map.put("id", id);
        List<QHSJ_SURF_WATER_BASICINFO> qHSJ_SURF_WATER_BASICINFO_list = qHSJ_SURF_WATER_BASICINFOMapper.getWaterBASICINFO(map);
        //获取监测站的检测字段 -PH、
        List<NameValueUnitModel> nameValueUnitModels = qHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper.currentValue(id);
        nameValueUnitModels.stream()
                .forEach(nameValueUnitModel -> {
                    String value = nameValueUnitModel.getValue();
                    if ("-1".equals(value)){
                        nameValueUnitModel.setValue("无数据");
                    }
                });
        List<NameValueUnitModel> nameValueUnitModelListSimple = new ArrayList<NameValueUnitModel>();
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(1));//浊度
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(0));//PH
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(3));//水温
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(2));//溶解氧
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(4));//电导率
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(5));//化学需氧量
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(6));//总氮
        nameValueUnitModelListSimple.add(nameValueUnitModels.get(7));//氨氮
        waterDetailModel.setName(qHSJ_SURF_WATER_BASICINFO_list.get(0).getSITE_NAME());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        waterDetailModel.setTime(sdf.format(new Date()));
        waterDetailModel.setAttr(nameValueUnitModelListSimple);
        waterDetailModel.setData(nameValueUnitModels);
        waterDetailModel.setUrl("/img/images/test_03.png");
        return waterDetailModel;
    }

    //获取折线图
    @Cacheable(key="#map_time + 'WaterStationServiceImpl.getLineWaterStationDataOfTime'")
    public NameDataModel getLineWaterStationDataOfTime(Map<String,String> map_time){
        NameDataModel nameDataModel_obj = new NameDataModel();
        List<QHSJ_SURF_WATER_BASICINFO> qHSJ_SURF_WATER_BASICINFO_list = qHSJ_SURF_WATER_BASICINFOMapper.getWaterBASICINFO(map_time);
        List<NameDataModel> NameDataModel_list = new ArrayList<NameDataModel>();
        //获取监测站的检测字段 -PH、浊度等
        List<QHSJ_ENTERPRISE_FACTOR_CODE> qHSJ_ENTERPRISE_FACTOR_CODE_list = qHSJ_ENTERPRISE_FACTOR_CODEMapper.getWaterFactorCode();
        if("day".equals(map_time.get("type"))){
            for (QHSJ_ENTERPRISE_FACTOR_CODE qHSJ_ENTERPRISE_FACTOR_CODE:qHSJ_ENTERPRISE_FACTOR_CODE_list) {
                NameDataModel nameDataModel = new NameDataModel();
                List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
                map_time.put("MONITOR_FACTOR_CODE",qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_CODE());
                List<QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA> qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list = qHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper.getSFWStationDataOfDay(map_time);
                for (QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA : qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list) {
                    TimeValueModel timeValueModel = new TimeValueModel();
                    timeValueModel.setTime(LoadMyUtil.dateToString(qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA.getMONITOR_TIME()));
                    timeValueModel.setValue(qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA.getMONITOR_VALUE());
                    timeValueModel_list.add(timeValueModel);
                }
                nameDataModel.setName(qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_NAME());
                nameDataModel.setData(timeValueModel_list);
                NameDataModel_list.add(nameDataModel);
            }
        }else {
            for (QHSJ_ENTERPRISE_FACTOR_CODE qHSJ_ENTERPRISE_FACTOR_CODE : qHSJ_ENTERPRISE_FACTOR_CODE_list) {
                NameDataModel nameDataModel = new NameDataModel();
                List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
                map_time.put("MONITOR_FACTOR_CODE", qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_CODE());
                List<QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA> qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list = qHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper.getSFWStationDataOfTime(map_time);
                for (QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA : qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list) {
                    TimeValueModel timeValueModel = new TimeValueModel();
                    timeValueModel.setTime(LoadMyUtil.dateToString(qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA.getMONITOR_TIME()));
                    timeValueModel.setValue(qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA.getMONITOR_VALUE());
                    timeValueModel_list.add(timeValueModel);
                }
                nameDataModel.setName(qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_NAME());
                nameDataModel.setData(timeValueModel_list);
                NameDataModel_list.add(nameDataModel);
            }
        }
        nameDataModel_obj.setName(qHSJ_SURF_WATER_BASICINFO_list.get(0).getSITE_NAME());
        nameDataModel_obj.setData(NameDataModel_list);
        return nameDataModel_obj;
    }

    //获取列表
    //@Cacheable(key="#map_time + 'WaterStationServiceImpl.getListWaterStationDataOfTime'")
    public List<LinkedHashMap> getListWaterStationDataOfTime(Map<String,String> map_time){
        List<LinkedHashMap> map_list = new ArrayList<LinkedHashMap>();
        //获取监测站的检测字段 -PH、浊度等
        List<QHSJ_ENTERPRISE_FACTOR_CODE> qHSJ_ENTERPRISE_FACTOR_CODE_list = qHSJ_ENTERPRISE_FACTOR_CODEMapper.getWaterFactorCode();
        List<QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA> qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list;
        if("day".equals(map_time.get("type"))){
            qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list = qHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper.getSFWStationDataOfDay(map_time);
        }else{
            qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list = qHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper.getSFWStationDataOfTime(map_time);
        }
        Set<String> set_time = new TreeSet<String>();
        for (QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA : qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list) {
            set_time.add(LoadMyUtil.dateToString(qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA.getMONITOR_TIME()));
        }
        for (String time : set_time) {
            LinkedHashMap map = new LinkedHashMap();
            map.put("监测时间",time);
            for (QHSJ_ENTERPRISE_FACTOR_CODE qHSJ_ENTERPRISE_FACTOR_CODE:qHSJ_ENTERPRISE_FACTOR_CODE_list) {
                String value = "无数据";
                String current,FACTOR_CODE;
                //统计已经匹配上的model
                List<QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA> save = new ArrayList<QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA>();
                for(QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_return : qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list){
                    current = LoadMyUtil.dateToString(qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_return.getMONITOR_TIME());
                    FACTOR_CODE = qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_return.getMONITOR_FACTOR_CODE();
                    if(time.equals(current)&&qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_CODE().equals(FACTOR_CODE)){
                        value = qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_return.getMONITOR_VALUE();
                        save.add(qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_return);
                        break;//匹配上后跳出遍历
                    }
                }
                map.put(qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_NAME()+"("+qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_UNIT()+")",value);
                qHSJ_SFW_MONITOR_HOUR_ANDDAY_DATA_list.removeAll(save);//删除已经匹配上的model，减少下次遍历次数
            }
            map_list.add(map);
        }
        return map_list;
    }

    //最近十天各个地表水监测站按天检测的平均值
    //（总磷(w21011)、总氮(w21001)、溶解氧(w01009)小于2、氨氮(w21003)、化学需氧量(w01018）
    @Cacheable(key="'WaterStationServiceImpl.waterStationAverageLineDay'")
    public NameDataModel waterStationAverageLineDay(){
        NameDataModel nameDataModel = new NameDataModel();
        //1、获取监测站
        Map map_waterStation = new HashMap();
        List<QHSJ_SURF_WATER_BASICINFO> qHSJ_SURF_WATER_BASICINFO_list = qHSJ_SURF_WATER_BASICINFOMapper.getWaterBASICINFO(map_waterStation);
        int waterStationNum = 0;

        String tag[] = {"w21011","w21001","w01009","w21003","w01018"};
        String tagName[] = {"总磷","总氮","溶解氧","氨氮","化学需氧量"};
        //分别计算五个指标
        List<NameDataModel> nameDataModel_tag_list = new ArrayList<>();
        for (int i = 0;i<tag.length;i++){
            NameDataModel nameDataModel_tag = new NameDataModel();
            //获取四个监测站的该指标值
            List<NameValueModel> nameValueModel_list = new ArrayList<>();
            for (int j=11;j>0;j--) {
                double tagVaule = 0;
                Double avgTagVaule;
                String Day = LoadMyUtil.getMyTime("DATE", -j);
                for (QHSJ_SURF_WATER_BASICINFO qHSJ_SURF_WATER_BASICINFO : qHSJ_SURF_WATER_BASICINFO_list) {
                    Map<String, String> map_DAY_DATA = new HashMap();
                    map_DAY_DATA.put("MONITOR_FACTOR_CODE", tag[i]);
                    map_DAY_DATA.put("SITE_CODE", qHSJ_SURF_WATER_BASICINFO.getSITE_CODE());
                    map_DAY_DATA.put("DAY", Day);
                    List<QHSJ_SFW_MONITOR_DAY_DATA> qHSJ_SFW_MONITOR_DAY_DATA_list = qHSJ_SFW_MONITOR_DAY_DATAMapper.getSFWStationDataOfDay(map_DAY_DATA);
                    if(qHSJ_SFW_MONITOR_DAY_DATA_list.isEmpty()){
                        tagVaule += 0;
                    }else{
                        waterStationNum++;
                        tagVaule += Double.valueOf(qHSJ_SFW_MONITOR_DAY_DATA_list.get(0).getMONITOR_VALUE());
                    }
                }
                if(waterStationNum==0){
                    avgTagVaule = 0.0;
                }else{
                    avgTagVaule = LoadMyUtil.exceptToPoint(tagVaule, waterStationNum);
                }
                nameValueModel_list.add(NameValueModel.builder().name(Day).value(String.valueOf(avgTagVaule)).build());
            }
            nameDataModel_tag.setName(tagName[i]);
            nameDataModel_tag.setData(nameValueModel_list);
            nameDataModel_tag_list.add(nameDataModel_tag);
        }
        nameDataModel.setName("最近十天各个地表水监测站按天检测的平均值");
        nameDataModel.setData(nameDataModel_tag_list);
        return nameDataModel;
    }

    @Cacheable(key="'WaterStationServiceImpl.alarmWaterStatistics'")
    public NameDataModel alarmWaterStatistics() {
        NameDataModel nameDataModel = new NameDataModel();
        //1、获取监测站
        Map map_waterStation = new HashMap();
        List<QHSJ_SURF_WATER_BASICINFO> qHSJ_SURF_WATER_BASICINFO_list = qHSJ_SURF_WATER_BASICINFOMapper.getWaterBASICINFO(map_waterStation);
        List<NameDataModel> nameDataModel_baseInfo_list = new ArrayList<>();
        for (QHSJ_SURF_WATER_BASICINFO qHSJ_SURF_WATER_BASICINFO : qHSJ_SURF_WATER_BASICINFO_list) {
            NameDataModel nameDataModel_baseInfo = new NameDataModel();
            List<NameValueModel> nameValueModel_list = new ArrayList<>();
            for(int i=10;i>0;i--) {
                String DATA = LoadMyUtil.getMyTime("DATE", -i);
                ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
                aLARM_INFORMATION.setALARM_TYPE_LV2("007002");
                aLARM_INFORMATION.setALARM_OBJECT(qHSJ_SURF_WATER_BASICINFO.getSITE_NAME());
                aLARM_INFORMATION.setRELEASE_TIME(DATA);
                int num = aLARM_INFORMATIONMapper.getCount(aLARM_INFORMATION);
                nameValueModel_list.add(NameValueModel.builder().name(DATA).value(String.valueOf(num)).build());
            }
            nameDataModel_baseInfo.setName(qHSJ_SURF_WATER_BASICINFO.getSITE_NAME());
            nameDataModel_baseInfo.setData(nameValueModel_list);
            nameDataModel_baseInfo_list.add(nameDataModel_baseInfo);
        }
        nameDataModel.setName("最近十天各个地表水监测站报警数量");
        nameDataModel.setData(nameDataModel_baseInfo_list);
        return nameDataModel;
    }

}
