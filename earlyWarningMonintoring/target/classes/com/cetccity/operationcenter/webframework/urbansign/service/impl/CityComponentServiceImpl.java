package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.chart.engine.utils.DateUtil;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.urbansign.dao.CityComponentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.service.impl
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/27 10:03
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/27 10:03
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
public class CityComponentServiceImpl implements TableName{

    @Autowired
    CityComponentMapper cityComponentMapper;

    public HttpResponseModel<ChartDetailModel>  totalCityComponent() {

        ChartFactory chartFactoryX = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return cityComponentMapper.queryTotalByStreet();
            }

            @Override
            public List<String> initX() {
                List<String> x = new ArrayList<String>();
                for (HashMap anInput : input) {
                    String street_name = String.valueOf(anInput.get("STREET_NAME"));
                    if (isNull(street_name)) continue;
                    x.add(street_name);
                }
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object col: input.get(0).keySet()){
                    String type = String.valueOf(col);
                    if (isNull(type) || "STREET_NAME".equals(type)) continue;
                    y.add(type);
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String street_name = String.valueOf(row.get("STREET_NAME"));
                for (Object key: row.keySet()){
                    String colName = String.valueOf(key);
                    if ("STREET_NAME".equals(colName))continue;
                    int num = str2num(String.valueOf(row.get(colName)));
                    dataMap.get(street_name).put(colName, num);
                }
            }
        };
        List<BarOrLineModel> chart = chartFactoryX.build(null);

        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chart);
        model.setDetail(ChartFactory.countByType(chart));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE,model);
    }

    private List<String> recentYearMonth(Long monthNum) {
        return LongStream.range(0, monthNum)
                .mapToObj(index -> YearMonth.now().minusMonths(index))
                .sorted()
                .map(yearMonth -> yearMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")))
                .collect(Collectors.toList());
    }

    public HttpResponseModel<ChartDetailModel> iotCityComponent() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return cityComponentMapper.countIotByDate("'yyyy-mm'");
            }
            @Override
            public List<String> initX() {
                return recentYearMonth(6L);
            }
            @Override
            public List<String> initY() {
                HashSet<String> typeSet = new HashSet<String>();
                for (int i = 0; i < input.size(); ++i){
                    String type = String.valueOf(input.get(i).get("DEVICE_NAME"));
                    if (StringUtils.isEmpty(type)|| type.equals("null"))continue;
                    typeSet.add(type);
                }
                return new ArrayList<String>(typeSet);
            }
            @Override
            public void match(HashMap row) {
                String date =  (String) row.get("PRODUCE_TIME");
                String device = String.valueOf(row.get("DEVICE_NAME"));
                BigDecimal count = (BigDecimal)row.get("COUNT");
                if (date == null || device == null)return;
                HashMap<String,Integer> x = dataMap.get(date);
                if (x == null)return;
                if (x.get(device) == null) return;
                x.put(device, x.get(device)+count.intValue());
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        int iotNum = cityComponentMapper.countIot();
        int iotEventCurrentMonth = 0; //最后一个月的值
        for (int i = 0; i < chart.size(); ++i){
            iotEventCurrentMonth += chart.get(i).getData().get(chart.size()-1).getValue();
        }
        ChartDetailModel chartDetailModel = new ChartDetailModel();
        chartDetailModel.setChart(chart);
        List<NameValueTypeModel<Integer>> list = new ArrayList<NameValueTypeModel<Integer>>();
        list.add(new NameValueTypeModel<Integer>("物联网感知设备保有量", iotNum));
        list.add(new NameValueTypeModel<Integer>("当月物联网告警数", iotEventCurrentMonth));
        chartDetailModel.setDetail(list);
        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, chartDetailModel);
    }

    public HttpResponseModel<ChartDetailModel> siteCityComponent() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return cityComponentMapper.querySiteValueByMonth(LocalDateTime.now().minusMonths(6));
            }

            @Override
            public List<String> initX() {
                return recentYearMonth(6L);
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("MONTH".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String yearMonth = (String) row.get("MONTH");
                for(Object key: row.keySet()){
                    if ("MONTH".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(yearMonth).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(null));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> cameraCityComponent() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return cityComponentMapper.queryCameraNumByStreet();
            }

            @Override
            public List<String> initX() {
                List<String> result = new ArrayList<String>();
                for (int i = 0; i < input.size(); ++i){
                    String name = String.valueOf(input.get(i).get("STREET_NAME"));
                    result.add(name);
                }
                return result;
            }

            @Override
            public List<String> initY() {
                List<String> result = new ArrayList<String>();
                result.add("摄像头");
                return result;
            }

            @Override
            public void match(HashMap row) {
                String street_name = String.valueOf(row.get("STREET_NAME"));
                int camera_num = str2num(String.valueOf(row.get("摄像头")));
                dataMap.get(street_name).put("摄像头",camera_num);
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        ChartDetailModel model = new ChartDetailModel();
        model.setDetail(ChartFactory.countByType(chart));
        model.setChart(chart);
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);

    }

    public HttpResponseModel<ChartDetailModel> cityComponentCityComponent() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return cityComponentMapper.queryCityComponentByStreet();
            }

            @Override
            public List<String> initX() {
                List<String> result = new ArrayList<String>();
                for (int i = 0; i < input.size(); ++i){
                    String name = String.valueOf(input.get(i).get("STREET_NAME"));
                    result.add(name);
                }
                return result;
            }

            @Override
            public List<String> initY() {
                List<String> result = new ArrayList<String>();
                result.add("城市部件");
                return result;
            }

            @Override
            public void match(HashMap row) {
                String street_name = String.valueOf(row.get("STREET_NAME"));
                int camera_num = str2num(String.valueOf(row.get("城市部件")));
                dataMap.get(street_name).put("城市部件",camera_num);
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        ChartDetailModel model = new ChartDetailModel();
        model.setDetail(ChartFactory.countByType(chart));
        model.setChart(chart);
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

}

interface TableName{

    String SITE_WATER_DAY_VALUE = "QHSJ_ENTERPRISE_HOUR_AND_DAY_DATA";  //水质
    String SITE_WATER_DAY_VALUE_TIME_COL = "MONITOR_TIME";  //水质-监测时间
    String SITE_WATER_DAY_VALUE_VALUE_COL = "MONITOR_VALUE";    //水质-检测值

    String SITE_AIR_DAY_VALUE = "QHSJ_AIR_MONITOR_HOUR_AND_DAY_DATA";   //空气
    String SITE_AIR_DAY_VALUE_TIME_COL = "MONITOR_TIME";        //空气-监测时间
    String SITE_AIR_DAY_VALUE_VALUE_COL = "MONITOR_VALUE";    //空气-检测值

    String SITE_SURFACE_WATER_DAY_VALUE = "QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA";   //地表水
    String SITE_SURFACE_WATER_DAY_VALUE_TIME_COL = "MONITOR_TIME";        //地表水-监测时间
    String SITE_SURFACE_WATER_DAY_VALUE_VALUE_COL = "MONITOR_VALUE";      //地表水-检测值

}
