package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BlkSanxiaoPlaceMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.RiskPointMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.XIAOSAN_PROJECTMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.XIAOSAN_PROJECT;
import com.cetccity.operationcenter.webframework.urbansign.service.ProductionSafetyService;
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
 * Create_Date: 10:16 2019-06-03
 * Updater:     heliangming
 * Update_Date：10:16 2019-06-03
 * 更新描述:    heliangming 补充
 **/
@Service
public class ProductionSafetyServiceImpl implements ProductionSafetyService {

    @Autowired
    XIAOSAN_PROJECTMapper xIAOSAN_PROJECTMapper;

    @Autowired
    BlkSanxiaoPlaceMapper bLK_SANXIAO_PLACEMapper;

    @Autowired
    RiskPointMapper rISK_POINTMapper;

    public List<NameValueModel> xiaoSanProject(String type) {
        List<NameValueModel> list = new ArrayList<>();
        switch(type){
            case "1" : type = "备案情况";break;
            case "2" : type = "备案类型";break;
            case "3" : type = "风险类别";break;
            case "4" : type = "街道备案情况";break;
            case "5" : type = "街道隐患整改";break;
        }
        for (XIAOSAN_PROJECT u : xIAOSAN_PROJECTMapper.getList(type)) {
            list.add(NameValueModel.builder().name(u.getKEY()).value(u.getVALUE()).build());
        }
        return list;
    }

    public HttpResponseModel<ChartDetailModel> sanXiaoPlaceToBar(){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return bLK_SANXIAO_PLACEMapper.urbanSignProductionSafetyBi();
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
        map.put("stack","三小场所");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> riskPointToBar(){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return rISK_POINTMapper.urbanSignProductionSafetyBi();
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
        map.put("stack","危险源");
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }
}
