package com.cetccity.operationcenter.webframework.backstage.iot.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.AlarmInformationMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import com.cetccity.operationcenter.webframework.backstage.iot.dao.ALARM_PUSH_AVILABLEMapper;
import com.cetccity.operationcenter.webframework.backstage.iot.dao.IOT_DEVICE_NUM_TRENDMapper;
import com.cetccity.operationcenter.webframework.backstage.iot.dao.entity.ALARM_PUSH_AVILABLE;
import com.cetccity.operationcenter.webframework.backstage.iot.service.IotDeviceService;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueIconRateModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotDeviceMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotHiddenDangerChartMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.iot.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:17 2019-06-04
 * Updater:     heliangming
 * Update_Date：17:17 2019-06-04
 * 更新描述:    heliangming 补充
 **/
@Service
public class IotDeviceServiceImpl implements IotDeviceService {

    @Autowired
    AlarmInformationMapper aLARM_INFORMATIONMapper;

    @Autowired
    IotHiddenDangerChartMapper iotHiddenDangerChartMapper;

    @Autowired
    IOT_DEVICE_NUM_TRENDMapper iOT_DEVICE_NUM_TRENDMapper;

    @Autowired
    IotDeviceMapper iOT_DEVICEMapper;

    @Autowired
    ALARM_PUSH_AVILABLEMapper aLARM_PUSH_AVILABLEMapper;

    public HttpResponseModel iotDeviceTop(){
        List<NameValueIconRateModel> nameValueIconRateModelList = new ArrayList<>();
        long iotNum = iotHiddenDangerChartMapper.countIot();

        nameValueIconRateModelList.add(NameValueIconRateModel.builder().name("接入传感器数量").value(String.valueOf(iotNum)).icon("icon-sensor").rate(false).build());

        List<ALARM_INFORMATION> list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(ALARM_INFORMATION.builder().startTime(LoadMyUtil.getMyTime("DATE",-30)).endTime(LoadMyUtil.getMyTime("DATE",0)).originTableName("IOT_EVENT").build());

        nameValueIconRateModelList.add(NameValueIconRateModel.builder().name("最近一个月触发报警次数").value(String.valueOf(list.size())).icon("icon-trigger-alarm").rate(false).build());
        int count = list.size();
        int notSendNum = 0;
        for(ALARM_INFORMATION u : list){
            if(0 == u.getSEND_STATE()){
                notSendNum ++;
            }
        }
        nameValueIconRateModelList.add(NameValueIconRateModel.builder().name("近一个月推送报警次数").value(String.valueOf(count - notSendNum)).icon("icon-push-alarm").rate(false).build());
        int disposalNum = 0; //处置的报警数
        for(ALARM_INFORMATION u : list){
            if(0 != u.getDISPOSAL_STATE()){
                disposalNum ++;
            }
        }
        nameValueIconRateModelList.add(NameValueIconRateModel.builder().name("近一个月报警处置率").value(LoadMyUtil.myPercent(disposalNum,count).replace("%","")).icon("icon-alarm-disposal-rate").rate(true).build());
        return new HttpResponseModel(0,nameValueIconRateModelList);
    }

    public HttpResponseModel iotDeviceTrendOne(){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return iOT_DEVICE_NUM_TRENDMapper.iotDeviceTrendOne();
            }

            @Override
            public List<String> initX() {
                List<String> x= new ArrayList<>();
                queryData().stream().forEach(u->x.add((String) u.get("X")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("X".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String x = (String) row.get("X");
                for(Object key: row.keySet()){
                    if ("X".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(x).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String,String> map = new HashMap();
        map.put("type","line");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel iotDeviceTrendTwo(){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return iOT_DEVICEMapper.iotDeviceTrendTwo();
            }

            @Override
            public List<String> initX() {
                List<String> x= new ArrayList<>();
                queryData().stream().forEach(u->x.add((String) u.get("X")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("X".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String x = (String) row.get("X");
                for(Object key: row.keySet()){
                    if ("X".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(x).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String,String> map = new HashMap();
        map.put("type","bar");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel iotDeviceTrendThree(){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return aLARM_INFORMATIONMapper.iotDeviceTrendThree();
            }

            @Override
            public List<String> initX() {
                List<String> x= new ArrayList<>();
                queryData().stream().forEach(u->x.add((String) u.get("X")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("X".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String x = (String) row.get("X");
                for(Object key: row.keySet()){
                    if ("X".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(x).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String,String> map = new HashMap();
        map.put("type","line");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel iotDeviceAttributeList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ALARM_PUSH_AVILABLE> aLARM_PUSH_AVILABLEList = aLARM_PUSH_AVILABLEMapper.iotDeviceAttributeList();
        PageInfo<ALARM_PUSH_AVILABLE> pageInfo = new PageInfo(aLARM_PUSH_AVILABLEList);
        return new HttpResponseModel(0,pageInfo);
    }
}
