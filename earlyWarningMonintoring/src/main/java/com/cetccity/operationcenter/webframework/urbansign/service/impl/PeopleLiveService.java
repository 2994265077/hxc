package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.urbansign.dao.PeopleLiveMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.service.impl
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/7 17:38
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/7 17:38
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
@Slf4j
public class PeopleLiveService {

    @Autowired
    PeopleLiveMapper peopleLiveMapper;

    public HttpResponseModel<Object> peopleLiveTotal() {
        String jsonStr = null;
        List<HashMap> input = peopleLiveMapper.queryAll();
        for (int i = 0; i < input.size(); ++i){
            if ("A0009".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList json = null;
        try {
            json = objectMapper.readValue(jsonStr, ArrayList.class);
        } catch (IOException e) {
            log.error("",e);
        }
        ArrayList finalJson = json;
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return finalJson;
            }

            @Override
            public List<String> initX() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("2012");
                arrayList.add("2013");
                arrayList.add("2014");
                arrayList.add("2015");
                arrayList.add("2016");
                arrayList.add("2017");
                return arrayList;
            }

            @Override
            public List<String> initY() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("学位数量");
                return arrayList;
            }

            @Override
            public void match(HashMap row) {
                String year = String.valueOf(row.get("年份"));
                dataMap.get(year).put("学位数量", Integer.valueOf(String.valueOf(row.get("学位数量"))));
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        for (int i = 0; i < input.size(); ++i){
            if ("A0016".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        List json1 = null;
        try {
            HashMap map = objectMapper.readValue(jsonStr, HashMap.class);
            List<LinkedHashMap> chartData = (List) map.get("chart");
            json1 = chartData.stream()
                    .flatMap(linkedHashMap -> {
                        List<LinkedHashMap<String, Object>> data = (List) linkedHashMap.get("data");
                        return data.stream();
                    })
                    .collect(Collectors.toMap(obj -> obj.get("name"), obj -> (Integer)obj.get("value"), (o1, o2) -> o1.intValue() + o2.intValue())
                    )
                    .entrySet()
                    .stream()
                    .map(entry -> {
                        HashMap hashMap = new HashMap();
                        hashMap.put("name", entry.getKey());
                        hashMap.put("value", entry.getValue());
                        return hashMap;
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("",e);
        }
        List finalJson1 = new ArrayList(json1);
        ChartFactory chartFactory1 = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return finalJson1;
            }

            @Override
            public List<String> initX() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("2012");
                arrayList.add("2013");
                arrayList.add("2014");
                arrayList.add("2015");
                arrayList.add("2016");
                arrayList.add("2017");
                return arrayList;
            }

            @Override
            public List<String> initY() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("医院床位");
                return arrayList;
            }

            @Override
            public void match(HashMap row) {
                String year = String.valueOf(row.get("name"));
                dataMap.get(year).put("医院床位", (Integer)row.get("value"));
            }
        };
        List<BarOrLineModel> chart1 = chartFactory1.build(null);
        chart.addAll(chart1);
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chart);
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<Object> littleSchool() {
        String jsonStr = null;
        List<HashMap> input = peopleLiveMapper.queryAll();
        for (int i = 0; i < input.size(); ++i){
            if ("A0011".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList json = null;
        try {
            json = objectMapper.readValue(jsonStr, ArrayList.class);
        } catch (IOException e) {
            log.error("",e);
        }
        ArrayList finalJson = json;
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return finalJson;
            }

            @Override
            public List<String> initX() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("2012");
                arrayList.add("2013");
                arrayList.add("2014");
                arrayList.add("2015");
                arrayList.add("2016");
                arrayList.add("2017");
                return arrayList;
            }

            @Override
            public List<String> initY() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("学校数量");
                arrayList.add("在校人数");
                arrayList.add("招生人数");
                return arrayList;
            }

            @Override
            public void match(HashMap row) {
                String year = String.valueOf(row.get("年份"));
                dataMap.get(year).put("学校数量", Integer.valueOf(String.valueOf(row.get("学校数量"))));
                dataMap.get(year).put("在校人数", Integer.valueOf(String.valueOf(row.get("在校人数"))));
                dataMap.get(year).put("招生人数", Integer.valueOf(String.valueOf(row.get("招生人数"))));
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chart);
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<Object> smallSchool() {
        String jsonStr = null;
        List<HashMap> input = peopleLiveMapper.queryAll();
        for (int i = 0; i < input.size(); ++i){
            if ("A0012".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList json = null;
        try {
            json = objectMapper.readValue(jsonStr, ArrayList.class);
        } catch (IOException e) {
            log.error("",e);
        }
        ArrayList finalJson = json;
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return finalJson;
            }

            @Override
            public List<String> initX() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("2012");
                arrayList.add("2013");
                arrayList.add("2014");
                arrayList.add("2015");
                arrayList.add("2016");
                arrayList.add("2017");
                return arrayList;
            }

            @Override
            public List<String> initY() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("学校数量");
                arrayList.add("在校人数");
                arrayList.add("招生人数");
                return arrayList;
            }

            @Override
            public void match(HashMap row) {
                String year = String.valueOf(row.get("年份"));
                dataMap.get(year).put("学校数量", Integer.valueOf(String.valueOf(row.get("学校数量"))));
                dataMap.get(year).put("在校人数", Integer.valueOf(String.valueOf(row.get("在校人数"))));
                dataMap.get(year).put("招生人数", Integer.valueOf(String.valueOf(row.get("招生人数"))));
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chart);
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<Object> professionalSchool() {
        String jsonStr = null;
        List<HashMap> input = peopleLiveMapper.queryAll();
        for (int i = 0; i < input.size(); ++i){
            if ("A0013".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList json = null;
        try {
            json = objectMapper.readValue(jsonStr, ArrayList.class);
        } catch (IOException e) {
            log.error("",e);
        }
        ArrayList finalJson = json;
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return finalJson;
            }

            @Override
            public List<String> initX() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("2012");
                arrayList.add("2013");
                arrayList.add("2014");
                arrayList.add("2015");
                arrayList.add("2016");
                arrayList.add("2017");
                return arrayList;
            }

            @Override
            public List<String> initY() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("学校数量");
                arrayList.add("在校人数");
                arrayList.add("招生人数");
                return arrayList;
            }

            @Override
            public void match(HashMap row) {
                String year = String.valueOf(row.get("年份"));
                dataMap.get(year).put("学校数量", Integer.valueOf(String.valueOf(row.get("学校数量"))));
                dataMap.get(year).put("在校人数", Integer.valueOf(String.valueOf(row.get("在校人数"))));
                dataMap.get(year).put("招生人数", Integer.valueOf(String.valueOf(row.get("招生人数"))));
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chart);
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<Object> middleSchool() {
        String jsonStr = null;
        List<HashMap> input = peopleLiveMapper.queryAll();
        for (int i = 0; i < input.size(); ++i){
            if ("A0014".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList json = null;
        try {
            json = objectMapper.readValue(jsonStr, ArrayList.class);
        } catch (IOException e) {
            log.error("",e);
        }
        ArrayList finalJson = json;
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return finalJson;
            }

            @Override
            public List<String> initX() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("2012");
                arrayList.add("2013");
                arrayList.add("2014");
                arrayList.add("2015");
                arrayList.add("2016");
                arrayList.add("2017");
                return arrayList;
            }

            @Override
            public List<String> initY() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("学校数量");
                arrayList.add("在校人数");
                arrayList.add("招生人数");
                return arrayList;
            }

            @Override
            public void match(HashMap row) {
                String year = String.valueOf(row.get("年份"));
                dataMap.get(year).put("学校数量", Integer.valueOf(String.valueOf(row.get("学校数量"))));
                dataMap.get(year).put("在校人数", Integer.valueOf(String.valueOf(row.get("在校人数"))));
                dataMap.get(year).put("招生人数", Integer.valueOf(String.valueOf(row.get("招生人数"))));
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chart);
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public HttpResponseModel<Object> feedOld() {
        String jsonStr = null;
        List<HashMap> input = peopleLiveMapper.queryAll();
        for (int i = 0; i < input.size(); ++i){
            if ("A0019".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList json = null;
        try {
            json = objectMapper.readValue(jsonStr, ArrayList.class);
        } catch (IOException e) {
            log.error("",e);
        }
        ArrayList finalJson = json;
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                return finalJson;
            }

            @Override
            public List<String> initX() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("440304001");
                arrayList.add("440304002");
                arrayList.add("440304003");
                arrayList.add("440304004");
                arrayList.add("440304005");
                arrayList.add("440304006");
                arrayList.add("440304007");
                arrayList.add("440304008");
                arrayList.add("440304009");
                arrayList.add("440304010");
                return arrayList;
            }

            @Override
            public List<String> initY() {
                ArrayList arrayList = new ArrayList();
                arrayList.add("养老机构数量");
                return arrayList;
            }

            @Override
            public void match(HashMap row) {
                String street_code = String.valueOf(row.get("STREET_CODE"));
                dataMap.get(street_code).put("养老机构数量", Integer.valueOf(String.valueOf(row.get("养老机构数量"))));
            }
        };
        List<BarOrLineModel> chart = chartFactory.build(null);
        for (int i = 0; i < chart.size(); ++i){
            for (NameValueTypeModel model :chart.get(i).getData()){
                model.setName(peopleLiveMapper.queryStreetNameByCode(model.getName()));
            }
        }
        ChartDetailModel model = new ChartDetailModel();
        model.setChart(chart);
        return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }


    public HttpResponseModel<Object> hospitalBed() {
        String jsonStr = null;
        List<HashMap> input = peopleLiveMapper.queryAll();
        for (int i = 0; i < input.size(); ++i){
            if ("A0016".equals(input.get(i).get("CHART_CODE"))){
                jsonStr = String.valueOf(input.get(i).get("VALUE"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //解决转义符问题
            HashMap map = objectMapper.readValue(jsonStr,HashMap.class);
            return new HttpResponseModel<Object>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, map);
        } catch (IOException e) {
        	log.error(e.toString());
        }
        return new HttpResponseModel<>(SysCode.UNKNOWN_ERROR_CODE, SysCode.UNKNOWN_ERROR_MESSAGE);
    }
}
