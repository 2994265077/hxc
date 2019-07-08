package com.cetccity.operationcenter.webframework.core.chart.factory;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.chart.factory
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:29 2019-06-21
 * Updater:     heliangming
 * Update_Date：11:29 2019-06-21
 * 更新描述:    heliangming 补充
 **/
@UtilityClass
public class CetcFactoryProducer {

    public HttpResponseModel<ChartDetailModel> init(List<HashMap> data,String xaxis,Map<String, String> map, boolean hasConvert) {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return data;
            }

            @Override
            public List<String> initX() {
                List<String> x = new ArrayList<>();
                queryData().stream().forEach(u -> x.add((String) u.get(xaxis)));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<>();
                for (Object key : input.get(0).keySet()) {
                    if (xaxis.equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String x = (String) row.get(xaxis);
                for (Object key : row.keySet()) {
                    if (xaxis.equals(String.valueOf(key))) continue;
                    if(hasConvert){
                        dataMap.get(x).put(String.valueOf(key), Integer.valueOf((String)row.get(key)));
                    }else {
                        BigDecimal decimal = (BigDecimal) row.get(key);
                        dataMap.get(x).put(String.valueOf(key), decimal.intValue());
                    }
                }
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }
}
