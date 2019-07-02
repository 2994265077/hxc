package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.chart.engine.utils.DateUtil;
import com.cetccity.operationcenter.webframework.core.chart.factory.CetcFactoryProducer;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.tools.PropertiesLoadUtils;
import com.cetccity.operationcenter.webframework.urbansign.api.model.ChartDetailForRiverModel;
import com.cetccity.operationcenter.webframework.urbansign.dao.EcologicalEnvironmentMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.QHSJ_RIVERWATER_QUALITY_YEAR;
import com.cetccity.operationcenter.webframework.urbansign.service.EcologicalEnvironmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.service.impl
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/26 10:41
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/26 10:41
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
public class EcologicalEnvironmentServiceImpl implements EcologicalEnvironmentService {

    @Autowired
    EcologicalEnvironmentMapper ecologicalEnvironmentMapper;

    @Autowired
    PropertiesLoadUtils propertiesLoadUtils;

    /*public HttpResponseModel<ChartDetailModel> totalEcological() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.querySiteValueByMonth();
            }
            // --XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${/data/app/31project}
            @Override
            public List<String> initX() {
                // datautil.rencentMonth返回值中不是yyyy-MM格式，当月数为一位数时，会省略0，如2019年4月会返回2019-4而不是2019-04
                return LongStream.range(0, 6)
                        .mapToObj(month -> YearMonth.now().minusMonths(month))
                        .sorted()
                        .map(yearMonth -> yearMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")))
                        .collect(Collectors.toList());
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
                String month = (String) row.get("MONTH");
                for(Object key: row.keySet()){
                    if ("MONTH".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build());
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> waterQualityByMonth() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.queryEnterpriseValueAndTime();
            }

            @Override
            public List<String> initX() {
                return DateUtil.recentMonth(6);
            }

            @Override
            public List<String> initY() {
                return new ArrayList<String>(){{
                    add("污染源废水站点");
                }};
            }

            @Override
            public void match(HashMap row) {
                int monitor_value = ((BigDecimal)row.get("MONITOR_VALUE")).intValue();
                Date monitor_time = (Date) row.get("MONITOR_TIME");
                dataMap.get(DateUtil.getYearMonth(monitor_time)).put("污染源废水站点", monitor_value);
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build());

        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> waterQualityByDay() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.queryEnterpriseValueAndTime();
            }

            @Override
            public List<String> initX() {
                return DateUtil.recentDay(7);
            }

            @Override
            public List<String> initY() {
                return new ArrayList<String>(){{
                    add("污染源废水站点");
                }};
            }

            @Override
            public void match(HashMap row) {
                int monitor_value = ((BigDecimal)row.get("MONITOR_VALUE")).intValue();
                Date monitor_time = (Date) row.get("MONITOR_TIME");

                dataMap.get(DateUtil.getYearMonthDay(monitor_time)).put("污染源废水站点", monitor_value);
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build());

        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> airByMonth() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.queryEnterpriseValueAndTime();
            }

            @Override
            public List<String> initX() {
                return DateUtil.recentMonth(6);
            }

            @Override
            public List<String> initY() {
                return new ArrayList<String>(){{
                    add("环境空气站点");
                }};
            }

            @Override
            public void match(HashMap row) {
                int monitor_value = ((BigDecimal)row.get("MONITOR_VALUE")).intValue();
                Date monitor_time = (Date) row.get("MONITOR_TIME");
                dataMap.get(DateUtil.getYearMonth(monitor_time)).put("环境空气站点", monitor_value);
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build());

        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> airByDay() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.queryEnterpriseValueAndTime();
            }

            @Override
            public List<String> initX() {
                return DateUtil.recentDay(7);
            }

            @Override
            public List<String> initY() {
                return new ArrayList<String>(){{
                    add("环境空气站点");
                }};
            }

            @Override
            public void match(HashMap row) {
                int monitor_value = ((BigDecimal)row.get("MONITOR_VALUE")).intValue();
                Date monitor_time = (Date) row.get("MONITOR_TIME");

                dataMap.get(DateUtil.getYearMonthDay(monitor_time)).put("环境空气站点", monitor_value);
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build());

        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> waterOnGroundByMonth() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.queryEnterpriseValueAndTime();
            }

            @Override
            public List<String> initX() {
                return DateUtil.recentMonth(6);
            }

            @Override
            public List<String> initY() {
                return new ArrayList<String>(){{
                    add("地表水站点");
                }};
            }

            @Override
            public void match(HashMap row) {
                int monitor_value = ((BigDecimal)row.get("MONITOR_VALUE")).intValue();
                Date monitor_time = (Date) row.get("MONITOR_TIME");
                dataMap.get(DateUtil.getYearMonth(monitor_time)).put("地表水站点", monitor_value);
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build());

        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> waterOnGroundByDay() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.queryEnterpriseValueAndTime();
            }

            @Override
            public List<String> initX() {
                return DateUtil.recentDay(7);
            }

            @Override
            public List<String> initY() {
                return new ArrayList<String>(){{
                    add("地表水站点");
                }};
            }

            @Override
            public void match(HashMap row) {
                int monitor_value = ((BigDecimal)row.get("MONITOR_VALUE")).intValue();
                Date monitor_time = (Date) row.get("MONITOR_TIME");

                dataMap.get(DateUtil.getYearMonthDay(monitor_time)).put("地表水站点", monitor_value);
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build());

        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }*/

    public HttpResponseModel<ChartDetailModel> totalEcological() {
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.querySiteValueByMonth();
            }
            // --XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${/data/app/31project}
            @Override
            public List<String> initX() {
                // datautil.rencentMonth返回值中不是yyyy-MM格式，当月数为一位数时，会省略0，如2019年4月会返回2019-4而不是2019-04
                List<String> x = new ArrayList<>();
                //横轴为24小时
                for(int i = 23;i>= 0;i--){
                    x.add(LoadMyUtil.getMyTime("HOUR",-i).substring(0,13));
                }
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("TIME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            int i = 0;
            @Override
            public void match(HashMap row) {
                for(Object key: row.keySet()){
                    if ("TIME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(initX().get(i)).put(String.valueOf(key), decimal.intValue());
                }
                i++;
            }
        };
        List<NameValueTypeModel<Object>> nameValueTypeModel_list = new ArrayList<>();
        nameValueTypeModel_list.add(NameValueTypeModel.builder().name("空气质量指数").value(chartFactory.build(null).get(0).getData().get(0).getValue()).build());
        nameValueTypeModel_list.add(NameValueTypeModel.builder().name("空气质量级别").value(caculationAirAqiLevel(chartFactory.build(null).get(0).getData().get(0).getValue())).build());
        nameValueTypeModel_list.add(NameValueTypeModel.builder().name("河流水质级别").value("V类").build());

        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(null));
        model.setDetail(nameValueTypeModel_list);
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<ChartDetailModel> riverWaterQualityByQuarter(String year, String quarter){
        String yearNew; String quarterNew;
        //获取下拉框
        List<NameValueModel> Dropdown = new ArrayList<>();
        List<QHSJ_RIVERWATER_QUALITY_YEAR> dropDownList= ecologicalEnvironmentMapper.riverWaterDropDown();
        dropDownList.stream().forEach(u->{
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY1())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("1").build());
            }
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY2())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("2").build());
            }
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY3())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("3").build());
            }
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY4())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("4").build());
            }
        });
        if(StringUtils.isEmpty(year)||StringUtils.isEmpty(quarter)){
            yearNew = Dropdown.get(0).getName();
            quarterNew = Dropdown.get(0).getValue();
        }else{
            yearNew = year;
            quarterNew = quarter;
        }
        return riverWaterQualityByQuarterNew(yearNew, quarterNew);
    }

    /**
     * 河流水质状况-按季度
     * @return
     */
    public HttpResponseModel<ChartDetailModel> riverWaterQualityByQuarterNew(String year, String quarter){
        //获取下拉框
        List<NameValueModel> Dropdown = new ArrayList<>();
        List<QHSJ_RIVERWATER_QUALITY_YEAR> dropDownList= ecologicalEnvironmentMapper.riverWaterDropDown();
        dropDownList.stream().forEach(u->{
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY4())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("4").build());
            }
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY3())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("3").build());
            }
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY2())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("2").build());
            }
            if(StringUtils.isNotEmpty(u.getNOW_WATER_QUALITY1())){
                Dropdown.add(NameValueModel.builder().name(u.getYEAR()).value("1").build());
            }
        });

        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                List<HashMap> result = new ArrayList<>();
                switch (quarter){
                    case "1" :
                        result = ecologicalEnvironmentMapper.riverWaterQualityByQuarter1(year);
                        break;
                    case "2" :
                        result = ecologicalEnvironmentMapper.riverWaterQualityByQuarter2(year);
                        break;
                    case "3" :
                        result = ecologicalEnvironmentMapper.riverWaterQualityByQuarter3(year);
                        break;
                    case "4" :
                        result = ecologicalEnvironmentMapper.riverWaterQualityByQuarter4(year);
                        break;
                }
                return result;
            }

            @Override
            public List<String> initX() {
                List<String> x = new ArrayList<>();
                queryData().stream().forEach(u->{
                    x.add((String) u.get("RIVER_NAME"));
                });
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<>();
                for (Object key: input.get(0).keySet()){
                    if ("RIVER_NAME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String x = (String) row.get("RIVER_NAME");
                for(Object key: row.keySet()){
                    if ("RIVER_NAME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = new BigDecimal((String) row.get(key));
                    dataMap.get(x).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map map = new HashMap();
        map.put(1,"Ⅰ类");map.put(2,"Ⅱ类");map.put(3,"Ⅲ类");map.put(4,"Ⅳ类");map.put(5,"Ⅴ类");map.put(6,"劣Ⅴ类");
        ChartDetailForRiverModel model = new ChartDetailForRiverModel();
        model.setHomologous(map);
        model.setDropdown(Dropdown);
        model.setChart(chartFactory.build(null));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    /**
     * 空气质量指数-按天
     * @return
     */
    public HttpResponseModel<ChartDetailModel> airAqiByDay(){

        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.queryAirValueAndDay();
            }

            @Override
            public List<String> initX() {
                List<String> x= new ArrayList<>();
                queryData().stream().forEach(u->x.add((String) u.get("TIME")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("TIME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String month = (String) row.get("TIME");
                for(Object key: row.keySet()){
                    if ("TIME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(null));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }


    /**
     * 计算AQI等级
     * @param value AQI值
     * @return
     */
    public String caculationAirAqiLevel(int value) {
        if(value<50) {
            return "优";
        } else if(value>=50&&value<100) {
            return "良";
        } else if(value>=100&&value<150) {
            return "轻度污染";
        } else if(value>=150&&value<200) {
            return "中度污染";
        } else if(value>=200&&value<300) {
            return "重度污染";
        } else {
            return "严重污染";
        }
    }

    /**
     * 空气监测站超标统计
     * @param type 类型
     * @return
     */
    public HttpResponseModel<ChartDetailModel> airExcessByStation(String type, int num_x, String station){

        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                String stationName = "";
                switch (station){
                    case "QHSJ_AIR_MONITOR_HOUR_DATA" :
                        stationName = "空气检测站超标数量";
                        break;
                    case "QHSJ_SFW_MONITOR_HOUR_DATA" :
                        stationName = "水质检测站超标数量";
                        break;
                }
                List<HashMap> list = new ArrayList<>();
                List<HashMap> list_u = new ArrayList<>();
                Map map_type = new HashMap();
                map_type.put("station",station);
                if("DATE".equals(type)){
                    map_type.put("day",LoadMyUtil.getMyTime("DATE", -num_x));
                    list = ecologicalEnvironmentMapper.airExcessByStationDay(map_type);
                }else if("MONTH".equals(type)){
                    map_type.put("month",LoadMyUtil.getMyTime("MONTH", -num_x));
                    list = ecologicalEnvironmentMapper.airExcessByStationMonth(map_type);
                }
                if (list.isEmpty()) {
                    for(int i=0;i<num_x;i++) {
                        HashMap map = new HashMap();
                        map.put("TIME", LoadMyUtil.getMyTime(type, -i));
                        map.put(stationName, 0);
                        list_u.add(map);
                    }
                } else {
                    for(int i=0;i<num_x;i++) {
                        HashMap map = new HashMap();
                        BigDecimal vaule = new BigDecimal(0);
                        //获取当前时间值
                        for (int j=0;j<list.size();j++){
                            if(LoadMyUtil.getMyTime(type, -i).equals(list.get(j).get("TIME"))){
                                vaule = (BigDecimal) list.get(j).get("检测站超标数量");
                                break;
                            }
                        }
                        map.put("TIME", LoadMyUtil.getMyTime(type, -i));
                        map.put(stationName, vaule);
                        list_u.add(map);
                    }
                }
                return list_u;
            }
            @Override
            public List<String> initX() {
                List<String> x = new ArrayList<>();
                for(int i=queryData().size()-1; i>0; i--){
                    x.add((String) queryData().get(i).get("TIME"));
                }
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key : input.get(0).keySet()) {
                    if ("TIME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String month = (String) row.get("TIME");
                for (Object key : row.keySet()) {
                    if ("TIME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(null));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    /**
     * 监测站分布
     * @return
     */
    public HttpResponseModel<ChartDetailModel> stationDistribution(){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return ecologicalEnvironmentMapper.stationDistribution();
            }

            @Override
            public List<String> initX() {
                List<String> x = new ArrayList<>();
                for(int i=0;i<queryData().size(); i++){
                    x.add(propertiesLoadUtils.getProperty((String) queryData().get(i).get("STREET_CODE")));
                }
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key : input.get(0).keySet()) {
                    if ("STREET_CODE".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String streetName = propertiesLoadUtils.getProperty((String) row.get("STREET_CODE"));
                for (Object key : row.keySet()) {
                    if ("STREET_CODE".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(streetName).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chartFactory.build(null));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }
}
