package com.cetccity.operationcenter.webframework.rundisplay.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TimeValueModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.WaterDetailModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.QHSJ_ENTERPRISE_BASICINFOMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.QHSJ_ENTERPRISE_FACTOR_CODEMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.QHSJ_ENTERPRISE_HOUR_AND_DAY_DATAMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.*;
import com.cetccity.operationcenter.webframework.rundisplay.service.PollutionSourceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@CacheConfig(cacheNames = "ecoEnvironmentChart")
public class PollutionSourceStationServiceImpl implements PollutionSourceStationService {

    @Autowired
    QHSJ_ENTERPRISE_BASICINFOMapper qHSJ_ENTERPRISE_BASICINFOMapper;

    @Autowired
    QHSJ_ENTERPRISE_FACTOR_CODEMapper qHSJ_ENTERPRISE_FACTOR_CODEMapper;

    @Autowired
    QHSJ_ENTERPRISE_HOUR_AND_DAY_DATAMapper qHSJ_ENTERPRISE_HOUR_ANDDAY_DATAMapper;

    @Cacheable(key="#map_detail + 'PollutionSourceStationServiceImpl.getPollutionSourceStationDataOfDetails'")
    public WaterDetailModel getPollutionSourceStationDataOfDetails(Map<String,String> map_detail){
        WaterDetailModel waterDetailModel = new WaterDetailModel();
        //获取河口名称编号关系
        List<QHSJ_ENTERPRISE_BASICINFO> qHSJ_ENTERPRISE_BASICINFO_list = qHSJ_ENTERPRISE_BASICINFOMapper.getWaterBASICINFO(map_detail);

        List<NameDataModel> NameDataModel_list = new ArrayList<NameDataModel>();
        //获取监测站的检测字段 -PH、浊度等
        List<QHSJ_ENTERPRISE_FACTOR_CODE> qHSJ_ENTERPRISE_FACTOR_CODE_list = qHSJ_ENTERPRISE_FACTOR_CODEMapper.getWaterFactorCode();
        List<NameValueUnitModel> nameValueUnitModel_list = qHSJ_ENTERPRISE_HOUR_ANDDAY_DATAMapper.currentValue(map_detail.get("id"));
        nameValueUnitModel_list.stream()
                .forEach(nameValueUnitModel -> {
                    String value = nameValueUnitModel.getValue();
                    if ("-1".equals(value)){
                        nameValueUnitModel.setValue("无数据");
                    }
                });
        List<NameValueUnitModel> nameValueUnitModel_list_Simple = new ArrayList<NameValueUnitModel>();
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(1));//浊度
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(0));//PH
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(3));//水温
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(2));//溶解氧
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(4));//电导率
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(5));//化学需氧量
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(6));//总氮
        nameValueUnitModel_list_Simple.add(nameValueUnitModel_list.get(7));//氨氮
        waterDetailModel.setName(qHSJ_ENTERPRISE_BASICINFO_list.get(0).getPSNAME());
        waterDetailModel.setAttr(nameValueUnitModel_list_Simple);
        waterDetailModel.setData(nameValueUnitModel_list);
        waterDetailModel.setUrl("/img/images/test_03.png");
        return waterDetailModel;
    }

    //获取折线图
    @Cacheable(key="#map_time + 'PollutionSourceStationServiceImpl.getLinePollutionSourceStationDataOfTime'")
    public NameDataModel getLinePollutionSourceStationDataOfTime(Map<String,String> map_time){
        NameDataModel nameDataModel_obj = new NameDataModel();
        List<QHSJ_ENTERPRISE_BASICINFO> qHSJ_ENTERPRISE_BASICINFO_list = qHSJ_ENTERPRISE_BASICINFOMapper.getWaterBASICINFO(map_time);

        List<NameDataModel> NameDataModel_list = new ArrayList<NameDataModel>();
        //获取监测站的检测字段 -PH、浊度等
        List<QHSJ_ENTERPRISE_FACTOR_CODE> qHSJ_ENTERPRISE_FACTOR_CODE_list = qHSJ_ENTERPRISE_FACTOR_CODEMapper.getWaterFactorCode();
        if("day".equals(map_time.get("type"))){
            for (QHSJ_ENTERPRISE_FACTOR_CODE qHSJ_ENTERPRISE_FACTOR_CODE:qHSJ_ENTERPRISE_FACTOR_CODE_list) {
                NameDataModel nameDataModel = new NameDataModel();
                List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
                map_time.put("MONITOR_FACTOR_CODE",qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_CODE());
                List<QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA> qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_list = qHSJ_ENTERPRISE_HOUR_ANDDAY_DATAMapper.getENTERPRISEStationDataOfDay(map_time);
                for (QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA : qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_list) {
                    TimeValueModel timeValueModel = new TimeValueModel();
                    timeValueModel.setTime(LoadMyUtil.dateToString(qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA.getMONITOR_TIME()));
                    timeValueModel.setValue(qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA.getMONITOR_VALUE());
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
                List<QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA> qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_list = qHSJ_ENTERPRISE_HOUR_ANDDAY_DATAMapper.getENTERPRISEStationDataOfTime(map_time);
                for (QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA : qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_list) {
                    TimeValueModel timeValueModel = new TimeValueModel();
                    timeValueModel.setTime(LoadMyUtil.dateToString(qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA.getMONITOR_TIME()));
                    timeValueModel.setValue(qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA.getMONITOR_VALUE());
                    timeValueModel_list.add(timeValueModel);
                }
                nameDataModel.setName(qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_NAME());
                nameDataModel.setData(timeValueModel_list);
                NameDataModel_list.add(nameDataModel);
            }
        }
        nameDataModel_obj.setName(qHSJ_ENTERPRISE_BASICINFO_list.get(0).getPSNAME());
        nameDataModel_obj.setData(NameDataModel_list);
        return nameDataModel_obj;
    }

    //获取列表
    @Cacheable(key="#map_time + 'PollutionSourceStationServiceImpl.getListPollutionSourceStationDataOfTime'")
    public List<LinkedHashMap> getListPollutionSourceStationDataOfTime(Map<String,String> map_time){
        List<LinkedHashMap> map_list = new ArrayList<LinkedHashMap>();
        List<QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA> qhsjEnterpriseHourAndDayData;
        if("day".equals(map_time.get("type"))){
            qhsjEnterpriseHourAndDayData = qHSJ_ENTERPRISE_HOUR_ANDDAY_DATAMapper.getENTERPRISEStationDataOfDay(map_time);
        }else{
            qhsjEnterpriseHourAndDayData = qHSJ_ENTERPRISE_HOUR_ANDDAY_DATAMapper.getENTERPRISEStationDataOfTime(map_time);
        }
        //获取监测站的检测字段 -PH、浊度等
        List<QHSJ_ENTERPRISE_FACTOR_CODE> qHSJ_ENTERPRISE_FACTOR_CODE_list = qHSJ_ENTERPRISE_FACTOR_CODEMapper.getWaterFactorCode();
        Set<String> set_time = new TreeSet<String>();
        for (QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA : qhsjEnterpriseHourAndDayData) {
            set_time.add(LoadMyUtil.dateToString(qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA.getMONITOR_TIME()));
        }
        for (String time : set_time) {
            LinkedHashMap map = new LinkedHashMap();
            map.put("监测时间",time);
            for (QHSJ_ENTERPRISE_FACTOR_CODE qHSJ_ENTERPRISE_FACTOR_CODE:qHSJ_ENTERPRISE_FACTOR_CODE_list) {
                String value = "无数据";
                String current,FACTOR_CODE;
                //统计已经匹配上的model
                List<QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA> save = new ArrayList<QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA>();
                for(QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_return : qhsjEnterpriseHourAndDayData){
                    current = LoadMyUtil.dateToString(qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_return.getMONITOR_TIME());
                    FACTOR_CODE = qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_return.getMONITOR_FACTOR_CODE();
                    if(time.equals(current)&&qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_CODE().equals(FACTOR_CODE)){
                        value = qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_return.getMONITOR_VALUE();
                        save.add(qHSJ_ENTERPRISE_HOUR_ANDDAY_DATA_return);
                        break;//匹配上后跳出遍历
                    }
                }
                map.put(qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_NAME()+"("+qHSJ_ENTERPRISE_FACTOR_CODE.getFACTOR_UNIT()+")",value);
                qhsjEnterpriseHourAndDayData.removeAll(save);//删除已经匹配上的model，减少下次遍历次数
            }
            map_list.add(map);
        }
        return map_list;
    }
}

