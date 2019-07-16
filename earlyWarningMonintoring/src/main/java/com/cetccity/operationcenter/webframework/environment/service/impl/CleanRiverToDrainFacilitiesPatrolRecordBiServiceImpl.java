package com.cetccity.operationcenter.webframework.environment.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.chart.factory.CetcFactoryProducer;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.core.tools.ListToPageInfoUtil;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.environment.api.model.PatrolRecordRightFour;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_PATROLMapper;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverToDrainFacilitiesPatrolRecordBiService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:29 2019-05-30
 * Updater:     heliangming
 * Update_Date：17:29 2019-05-30
 * 更新描述:    heliangming 补充
 **/
@Service
public class CleanRiverToDrainFacilitiesPatrolRecordBiServiceImpl implements CleanRiverToDrainFacilitiesPatrolRecordBiService {

    @Autowired
    QJHH_PATROLMapper qJHH_PATROLMapper;

    public List<NameValueModel> rightOne(String SEWERATE_ID){
        //获取最近一个月有隐患的巡查记录
        String currentDay = LoadMyUtil.getMyTime("DATE",0);  //today
        String lastDay = LoadMyUtil.getMyTime("DATE",-30);  //30天前
        int patrolNum = 0;   //巡查次数
        int patrolHiddenDangerNum = 0;   //巡查隐患次数
        Map map = new HashMap();
        map.put("SEWERAGE_ID",SEWERATE_ID);
        map.put("lastDay",lastDay);
        map.put("currentDay",currentDay);
        List<NameValueModel> list = qJHH_PATROLMapper.rightOne(map);
        for(NameValueModel u : list){
            patrolNum += Integer.valueOf(u.getValue());
            if("不正常".equals(u.getName())) {
                patrolHiddenDangerNum = Integer.valueOf(u.getValue());
            }
        }
        List<NameValueModel> nameValueModelList = new ArrayList<>();
        nameValueModelList.add(NameValueModel.builder().name("最近一个月巡查次数").value(String.valueOf(patrolNum)+"次").build());
        nameValueModelList.add(NameValueModel.builder().name("最近一个月巡查隐患").value(String.valueOf(patrolHiddenDangerNum)+"次").build());
        return nameValueModelList;
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(String SEWERATE_ID){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                Map map = new HashMap();
                map.put("SEWERAGE_ID",SEWERATE_ID);
                return qJHH_PATROLMapper.rightTwo(map);
            }

            @Override
            public List<String> initX() {
                List<String> x= new ArrayList<>();
                queryData().stream().forEach(u->x.add((String) u.get("X_TIME")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("X_TIME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String month = (String) row.get("X_TIME");
                for(Object key: row.keySet()){
                    if ("X_TIME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String,String> map = new HashMap();
        map.put("type","line");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID, String hiddenDanger){
        String startTime = LoadMyUtil.getMyTime("DATE",-30);  //开始时间
        String endTime = LoadMyUtil.getMyTime("DATE",0);  //结束时间
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                Map map = new HashMap();
                map.put("startTime",startTime);
                map.put("endTime",endTime);
                map.put("SEWERAGE_ID",SEWERATE_ID);
                if(StringUtils.isEmpty(hiddenDanger)) {
                    map.put("STATUS", "正常");
                }else {
                    map.put("STATUS", "不正常");
                }
                map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
                if(StringUtils.isEmpty(street)){
                    return qJHH_PATROLMapper.rightThreeNoStreet(map);
                }else {
                    return qJHH_PATROLMapper.rightThreeHasStreet(map);
                }
            }

            @Override
            public List<String> initX() {
                List<String> x= new ArrayList<>();
                queryData().stream().forEach(u->x.add((String) u.get("X_NAME")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("X_NAME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String month = (String) row.get("X_NAME");
                for(Object key: row.keySet()){
                    if ("X_NAME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String,String> map = new HashMap();
        map.put("type","bar");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public MyPageInfoModel rightFour(String street, String SEWERATE_ID, Integer pageNum, Integer pageSize){
        List<PatrolRecordRightFour> patrolRecordRightFourListReturn = new ArrayList<>();
        //获取最近一个月有隐患的巡查记录
        String currentDay = LoadMyUtil.getMyTime("DATE",0);  //today
        String lastDay = LoadMyUtil.getMyTime("DATE",-30);  //30天前
        Map map = new HashMap();
        map.put("lastDay",lastDay);
        map.put("currentDay",currentDay);
        map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        map.put("SEWERAGE_ID",SEWERATE_ID);
        int start = pageNum == 0 ? pageNum : pageNum-1;
        map.put("start",start * pageSize);
        map.put("end",(start+1) * pageSize);
        List<PatrolRecordRightFour> patrolRecordRightFourList = qJHH_PATROLMapper.rightFour(map);
        Map map2 = new HashMap();
        map2.put("沉砂池","QJHH_FACILITY_INFO@1");
        map2.put("隔油池","QJHH_FACILITY_INFO@2");
        map2.put("污水立管","QJHH_FACILITY_INFO@3");
        map2.put("毛发收集池","QJHH_FACILITY_INFO@4");
        map2.put("污水井","QJHH_FACILITY_INFO@5");
        map2.put("雨水口","QJHH_FACILITY_INFO@6");
        map2.put("雨水立管","QJHH_FACILITY_INFO@7");
        map2.put("雨水井","QJHH_FACILITY_INFO@8");
        map2.put("化粪池","QJHH_FACILITY_INFO@9");
        patrolRecordRightFourList.stream().forEach(u->{
            String str = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april."+map2.get(u.getFACILITY_NAME()));
            u.setLayerId(str.substring(0, str.indexOf("#")));
            patrolRecordRightFourListReturn.add(u);
        });
        int count = qJHH_PATROLMapper.rightFourCount(map);
        return MyPageInfoModel.builder().total(count).pageNum(pageNum).pageSize(pageSize).list(patrolRecordRightFourListReturn).pages(count % pageSize == 0 ? count / pageSize : count / pageSize + 1).build();
    }

    public HttpResponseModel<ChartDetailModel> rightFive(String street){
        //获取最近一个月有隐患的巡查记录
        String currentDay = LoadMyUtil.getMyTime("DATE",0);  //today
        String lastDay = LoadMyUtil.getMyTime("DATE",-30);  //30天前
        Map map = new HashMap();
        map.put("lastDay",lastDay);
        map.put("currentDay",currentDay);
        map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        List<HashMap> data = qJHH_PATROLMapper.rightFive(map);
        Map<String,String> map2 = new HashMap();
        map2.put("type","bar");
        return CetcFactoryProducer.init(data,"X_NAME",map2,false);
    }
}
