package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.urbansign.dao.ROAD_ADMIN_PROBLEMMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.ROAD_ADMIN_VEHICLE_PERMITMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.WEEKLY_EMERGENCY_CASEMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.ROAD_ADMIN_PROBLEM;
import com.cetccity.operationcenter.webframework.urbansign.service.SocialSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:58 2019-05-31
 * Updater:     heliangming
 * Update_Date：16:58 2019-05-31
 * 更新描述:    heliangming 补充
 **/
@Service
public class SocialSecurityServiceImpl implements SocialSecurityService {

    @Autowired
    WEEKLY_EMERGENCY_CASEMapper wEEKLY_EMERGENCY_CASEMapper;

    @Autowired
    ROAD_ADMIN_VEHICLE_PERMITMapper rOAD_ADMIN_VEHICLE_PERMITMapper;

    @Autowired
    ROAD_ADMIN_PROBLEMMapper rOAD_ADMIN_PROBLEMMapper;

    public HttpResponseModel<ChartDetailModel> rightOneToXName(String securityName, String x){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                List<HashMap> list = new ArrayList<>();
                String endTime = LoadMyUtil.getMyTime("MONTH", 1);
                String startTime = LoadMyUtil.getMyTime("MONTH", -11);
                Map map = new HashMap();
                map.put("securityName", securityName);
                //map.put("year",year);
                if (x.equals("time")) {
                    for(int i = 11; i>=0; i--) {
                        //return wEEKLY_EMERGENCY_CASEMapper.rightOneToTime(map);
                        String currentTime = LoadMyUtil.getMyTime("MONTH", -11);
                        map.put("month", currentTime);
                        HashMap newMap = new HashMap();
                        newMap.put("X", currentTime);
                        newMap.putAll(wEEKLY_EMERGENCY_CASEMapper.rightOneToTimeRemoval(map));
                        list.add(newMap);
                    }
                } else if (x.equals("street")) {
                    //return wEEKLY_EMERGENCY_CASEMapper.rightOneToStreet(map);
                    map.put("startTime",startTime);
                    map.put("endTime",endTime);
                    list = wEEKLY_EMERGENCY_CASEMapper.rightOneToStreetRemoval(map);
                } else {
                    //return wEEKLY_EMERGENCY_CASEMapper.rightOneToType(map);
                    map.put("startTime",startTime);
                    map.put("endTime",endTime);
                    list = wEEKLY_EMERGENCY_CASEMapper.rightOneToTypeRemoval(map);
                }
                return list;
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
        map.put("stack","社会安全事件");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return rOAD_ADMIN_VEHICLE_PERMITMapper.rightTwo();
            }

            @Override
            public List<String> initX() {
                List<String> x = new ArrayList<>();
                queryData().stream().forEach(u -> x.add((String) u.get("X")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key : input.get(0).keySet()) {
                    if ("X".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String month = (String) row.get("X");
                for (Object key : row.keySet()) {
                    if ("X".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String, String> map = new HashMap();
        map.put("type", "bar");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> rightThree() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return rOAD_ADMIN_VEHICLE_PERMITMapper.rightThree();
            }

            @Override
            public List<String> initX() {
                List<String> x = new ArrayList<>();
                queryData().stream().forEach(u -> x.add((String) u.get("X_NAME")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key : input.get(0).keySet()) {
                    if ("X_NAME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String month = (String) row.get("X_NAME");
                for (Object key : row.keySet()) {
                    if ("X_NAME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String, String> map = new HashMap();
        map.put("type", "bar");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public String rightFour(){
        ROAD_ADMIN_PROBLEM model = rOAD_ADMIN_PROBLEMMapper.rightFour();
        return model.getVALUE();
    }
}
