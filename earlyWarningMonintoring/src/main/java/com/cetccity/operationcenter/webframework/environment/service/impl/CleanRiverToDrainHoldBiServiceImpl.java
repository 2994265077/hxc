package com.cetccity.operationcenter.webframework.environment.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.chart.factory.CetcFactoryProducer;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.environment.api.model.NumRateTrend;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_SEWERAGE_INFOMapper;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverToDrainHoldBiService;
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
 * Create_Date: 9:21 2019-05-28
 * Updater:     heliangming
 * Update_Date：9:21 2019-05-28
 * 更新描述:    heliangming 补充
 **/
@Service
public class CleanRiverToDrainHoldBiServiceImpl implements CleanRiverToDrainHoldBiService {

    @Autowired
    QJHH_SEWERAGE_INFOMapper qJHH_SEWERAGE_INFOMapper;

    public List<NameDataModel> rightOne(String street){
        Map map = new HashMap();
        map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        int count = qJHH_SEWERAGE_INFOMapper.count(map);//当前排水户总数
        String lastMonth = LoadMyUtil.getMyTime("MONTH",-1);    //上一月
        String currentMonth = LoadMyUtil.getMyTime("MONTH",0);  //本月
        map.put("lastMonthCount",lastMonth);
        int lastCount = qJHH_SEWERAGE_INFOMapper.count(map); //计算包括上个月之前所有的排水户
        map.put("month",currentMonth);
        map.put("lastMonthCount",null);
        int currentCount = qJHH_SEWERAGE_INFOMapper.count(map);//计算本月的排水户
        //1、计算排水户总数增长率,趋势
        String increaseRate,trend;
        increaseRate = lastCount ==0 ? "-" : LoadMyUtil.myPercent(currentCount,lastCount);
        if(currentCount > 0){
            trend = "↑";
        }else if(currentCount < 0){
            trend = "↓";
        }else {
            trend = "-";
            increaseRate = "0.00%";
        }
        List<NameDataModel> list = new ArrayList<>();
        list.add(NameDataModel.builder().name("排水户总数").data(NumRateTrend.builder().num(count).rate(increaseRate).trend(trend).build()).build());
        list.add(NameDataModel.builder().name("本月新增").data(NumRateTrend.builder().num(currentCount).trend(trend).build()).build());
        return list;
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(String street){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                List<HashMap> list = new ArrayList<>();
                Map map = new HashMap();
                map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
                //return qJHH_SEWERAGE_INFOMapper.rightTwo(map);
                for(int i = 11;i>= 0;i--){
                    String currentTime = LoadMyUtil.getMyTime("MONTH",-i);
                    String month = LoadMyUtil.getMyTime("MONTH",-i+1);
                    map.put("month",month);
                    HashMap newMap = new HashMap();
                    newMap.put("X_TIME",currentTime);
                    newMap.putAll(qJHH_SEWERAGE_INFOMapper.rightTwoRemoval(map));
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
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> rightThree(String street){
        Map map = new HashMap();
        map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        //map.put("month",LoadMyUtil.getMyTime("MONTH",0));
        List<HashMap> data = qJHH_SEWERAGE_INFOMapper.rightThree(map);
        Map<String,String> map2 = new HashMap();
        map2.put("type","pie");
        return CetcFactoryProducer.init(data,"X_NAME",map2,false);
    }

    public HttpResponseModel<ChartDetailModel> rightFour(String street) {
        List<HashMap> data;
        Map map = new HashMap();
        map.put("streetCode", StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        //map.put("month",LoadMyUtil.getMyTime("MONTH",0));
        if (StringUtils.isEmpty(street)) {
            data = qJHH_SEWERAGE_INFOMapper.rightFourNoStreet(map);
        } else {
            data = qJHH_SEWERAGE_INFOMapper.rightFourHasStreet(map);
        }
        Map<String, String> map2 = new HashMap();
        map2.put("type", "bar");
        return CetcFactoryProducer.init(data, "X_NAME", map2,false);
    }
}
