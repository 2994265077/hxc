package com.cetccity.operationcenter.webframework.environment.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.chart.factory.CetcFactoryProducer;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.environment.api.model.NumRateTrend;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_FACILITY_INFOMapper;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_PATROLMapper;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverToDrainFacilitiesBiService;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
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
 * Create_Date: 9:11 2019-05-29
 * Updater:     heliangming
 * Update_Date：9:11 2019-05-29
 * 更新描述:    heliangming 补充
 **/
@Service
public class CleanRiverToDrainFacilitiesBiServiceImpl implements CleanRiverToDrainFacilitiesBiService {

    @Autowired
    QJHH_FACILITY_INFOMapper qJHH_FACILITY_INFOMapper;

    @Autowired
    QJHH_PATROLMapper qJHH_PATROLMapper;

    public List<NameDataModel> rightOne(String street, String SEWERATE_ID){
        /*Map map = new HashMap();
        map.put("SEWERAGE_ID",SEWERATE_ID);
        map.put("streetCode", StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        int count = qJHH_FACILITY_INFOMapper.count(map);//计算排水设施总数
        String lastMonth = LoadMyUtil.getMyTime("MONTH",-1);    //上一月
        String currentMonth = LoadMyUtil.getMyTime("MONTH",0);  //本月
        map.put("lastMonthCount",lastMonth);
        int lastCount = qJHH_FACILITY_INFOMapper.count(map);//计算包括上个月之前所有的排水设施
        map.put("month",currentMonth);
        map.put("lastMonthCount",null);
        int currentCount = qJHH_FACILITY_INFOMapper.count(map);//本月新增的正常排水设施
        //1、计算排水户总数增长率,趋势
        String increaseRate,trend;
        increaseRate = (lastCount ==0 ? "-" : LoadMyUtil.myPercent(currentCount,lastCount));
        if(currentCount > 0){
            trend = "↑";
        }else if(currentCount < 0){
            trend = "↓";
        }else {
            trend = "-";
            increaseRate = "0.00%";
        }
        //2、计算正常排设施总数、增长率,趋势
        String increaseRate_status_1,trend_status_1;
        Map map_status_1 = new HashMap();
        map_status_1.put("SEWERAGE_ID",SEWERATE_ID);
        map_status_1.put("streetCode", StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        map_status_1.put("STATUS",1);
        int count_status_1 = qJHH_FACILITY_INFOMapper.count(map_status_1); //计算正常排水设施总数
        map_status_1.put("lastMonthCount",lastMonth);
        int lastCount_status_1 = qJHH_FACILITY_INFOMapper.count(map_status_1); //计算包括上个月之前所有的正常排水设施
        map_status_1.put("month",currentMonth);
        map_status_1.put("lastMonthCount",null);
        int currentCount_status_1 = qJHH_FACILITY_INFOMapper.count(map_status_1); //本月新增的正常排水设施
        increaseRate_status_1 = (lastCount_status_1 ==0 ? "-" : LoadMyUtil.myPercent(currentCount_status_1,lastCount_status_1));
        if(currentCount_status_1> 0){
            trend_status_1 = "↑";
        }else if(currentCount_status_1 < 0){
            trend_status_1 = "↓";
        }else {
            trend_status_1 = "-";
            increaseRate_status_1 = "0.00%";
        }
        //3、计算异常排设施总数、增长率,趋势
        String increaseRate_status_2,trend_status_2;
        Map map_status_2 = new HashMap();
        map_status_2.put("SEWERAGE_ID",SEWERATE_ID);
        map_status_2.put("streetCode", StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        map_status_2.put("STATUS",2);
        int count_status_2 = qJHH_FACILITY_INFOMapper.count(map_status_2);//计算异常排水设施总数
        map_status_2.put("lastMonthCount",lastMonth);
        int lastCount_status_2 = qJHH_FACILITY_INFOMapper.count(map_status_2);
        map_status_2.put("month",currentMonth);
        map_status_2.put("lastMonthCount",null);
        int currentCount_status_2 = qJHH_FACILITY_INFOMapper.count(map_status_2);
        increaseRate_status_2 = (lastCount_status_2 ==0 ? "-" : LoadMyUtil.myPercent(currentCount_status_2,lastCount_status_2));
        if(currentCount_status_2 > 0){
            trend_status_2 = "↑";
        }else if(currentCount_status_2 < 0){
            trend_status_2 = "↓";
        }else {
            trend_status_2 = "-";
            increaseRate_status_2 = "0.00%";
        }
        List<NameDataModel> list = new ArrayList<>();
        list.add(NameDataModel.builder().name("排水设施总数").data(NumRateTrend.builder().num(count).rate(increaseRate).trend(trend).build()).build());
        list.add(NameDataModel.builder().name("本月新增").data(NumRateTrend.builder().num(currentCount).trend(trend).build()).build());
        list.add(NameDataModel.builder().name("正常排水设施").data(NumRateTrend.builder().num(count_status_1).rate(increaseRate_status_1).trend(trend_status_1).build()).build());
        list.add(NameDataModel.builder().name("异常排水设施").data(NumRateTrend.builder().num(count_status_2).rate(increaseRate_status_2).trend(trend_status_2).build()).build());
        return list;*/
        Map map = new HashMap();
        map.put("SEWERAGE_ID",SEWERATE_ID);
        map.put("streetCode", StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        int count = qJHH_FACILITY_INFOMapper.count(map);//计算排水设施总数
        String time1 = LoadMyUtil.getMyTime("DATE",-60);  //30天前
        String time2 = LoadMyUtil.getMyTime("DATE",-30);  //30天前
        String time3 = LoadMyUtil.getMyTime("DATE",0);  //today
        map.put("startTime",time2);
        map.put("endTime",time3);
        List<NameValueModel> list1 = qJHH_PATROLMapper.rightOne(map);
        int abnormal1 = 0,abnormal2 = 0;//异常排水设施
        for (NameValueModel u:list1) {
            if("不正常".equals(u.getName())){
                abnormal1 = Integer.valueOf(u.getValue());
            }
        }
        map.put("startTime",time1);
        map.put("endTime",time2);
        List<NameValueModel> list2 = qJHH_PATROLMapper.rightOne(map);
        for (NameValueModel u:list2) {
            if("不正常".equals(u.getName())){
                abnormal2 = Integer.valueOf(u.getValue());
            }
        }
        String increaseRate_status_2,trend_status_2;
        if(abnormal1 - abnormal2 > 0){
            trend_status_2 = "↑";
            increaseRate_status_2 = LoadMyUtil.myPercent(abnormal1,abnormal2);

        }else if(abnormal1 - abnormal2 < 0){
            trend_status_2 = "↓";
            increaseRate_status_2 = LoadMyUtil.myPercent(abnormal1,abnormal2);

        }else {
            trend_status_2 = "-";
            increaseRate_status_2 = "0.00%";
        }
        List<NameDataModel> list = new ArrayList<>();
        list.add(NameDataModel.builder().name("排水设施总数").data(NumRateTrend.builder().num(count).rate("0.00%").trend("-").build()).build());
        list.add(NameDataModel.builder().name("本月新增").data(NumRateTrend.builder().num(0).trend("-").build()).build());
        list.add(NameDataModel.builder().name("正常排水设施").data(NumRateTrend.builder().num(count).rate("0.00%").trend("-").build()).build());
        list.add(NameDataModel.builder().name("近一月异常排水设施").data(NumRateTrend.builder().num(abnormal1).rate(increaseRate_status_2).trend(trend_status_2).build()).build());
        return list;
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(String street, String SEWERATE_ID){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                List<HashMap> list = new ArrayList<>();
                Map map = new HashMap();
                map.put("SEWERAGE_ID",SEWERATE_ID);
                map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
                for(int i = 11;i>= 0;i--){
                    String currentTime = LoadMyUtil.getMyTime("MONTH",-i);
                    String month = LoadMyUtil.getMyTime("MONTH",-i+1);
                    map.put("month",month);
                    HashMap newMap = new HashMap();
                    newMap.put("X_TIME",currentTime);
                    newMap.putAll(qJHH_FACILITY_INFOMapper.rightTwoRemoval(map));
                    list.add(newMap);
                }
               return list;
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
        map.put("type","bar");
        map.put("stack","排水设施");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID){
        Map map = new HashMap();
        map.put("SEWERAGE_ID",SEWERATE_ID);
        map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        List<HashMap> date = qJHH_FACILITY_INFOMapper.rightThree(map);
        Map<String,String> map2 = new HashMap();
        map2.put("type","pie");
        return CetcFactoryProducer.init(date,"X_NAME",map2,false);
    }

    public HttpResponseModel<ChartDetailModel> rightFour(String SEWERATE_ID){
        Map map = new HashMap();
        map.put("SEWERATE_ID",SEWERATE_ID);
        List<HashMap> date = qJHH_FACILITY_INFOMapper.rightThree(map);
        Map<String,String> map2 = new HashMap();
        map2.put("type","pie");
        map2.put("stack","排水设施");
        return CetcFactoryProducer.init(date,"X",map2,false);
    }

    public HttpResponseModel<ChartDetailModel> rightFive(){
        List<HashMap> date = qJHH_FACILITY_INFOMapper.rightFive();
        Map<String,String> map = new HashMap();
        map.put("type","pie");
        return CetcFactoryProducer.init(date,"X",map,false);
    }
}
