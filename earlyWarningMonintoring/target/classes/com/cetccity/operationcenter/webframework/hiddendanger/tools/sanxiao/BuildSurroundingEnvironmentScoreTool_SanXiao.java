package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildSurroundingEnvironmentToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BuildSurroundingEnvironmentScoreTool_SanXiao {

    @Autowired
    BuildSurroundingEnvironmentToCache buildSurroundingEnvironmentToCache;

    /*天气状况 50% */     /*多云：100分   小雨，阵雨，晴：50分 其他：30分 暴雨：0分*/
    public double getBuildWeatherConditionScore(String report) {
        double score_report;
        if (report.contains("阵雨") || report.contains("小雨") || report.contains("晴")) {
            score_report = 50;
        } else if (report.contains("暴雨")) {
            score_report = 0;
        } else if (report.contains("多云")) {
            score_report = 100;
        } else {
            score_report = 30;
        }
        return score_report;
    }
        /*温度  30%*/  /*由火灾数据中最高温度，最低温度，温差3个维度对火灾影响构建聚类，为不同类别对火宅的影响程度打分。  第一类：100分  第二类：50分 第三类：30分*/
    public double getBuildTemperatureDifferenceScore(Double temperatureDifference) {
        double score_temperatureDifference;
        if (temperatureDifference < 3) {
            score_temperatureDifference = 0;
        } else if (temperatureDifference >= 3 && temperatureDifference < 10) {
            score_temperatureDifference = 50;
        } else {
            score_temperatureDifference = 100;
        }
        return score_temperatureDifference;
    }
        /*风级 20% 风级≤3级 ：100分  其他：0分*/
    public double getBuildWindGradeScore(String ws) {
        if(ws.contains("-")){
            ws = ws.split("-")[0];
        }
        Integer WindGrade = Integer.valueOf(ws);
        double score_ws;
        if (WindGrade <= 3) {
            score_ws = 100;
        } else {
            score_ws = 0;
        }
        return score_ws;
    }

    public double getBuildSurroundingEnvironmentScore(double score_report,double score_temperatureDifference,double score_ws){
        /*    天气状况*0.5+温度*0.3+风级*0.2   */
        Map<String,Float> map_attribute_build_SurroundingEnvironment_information = buildSurroundingEnvironmentToCache.map_attribute_build_Weather_information;
        float attribute_002001 = map_attribute_build_SurroundingEnvironment_information.get("002001");
        float attribute_002002 = map_attribute_build_SurroundingEnvironment_information.get("002002");
        float attribute_002003 = map_attribute_build_SurroundingEnvironment_information.get("002003");
        double score_SurroundingEnvironment_attributes = score_report*attribute_002001+score_temperatureDifference*attribute_002002+score_ws*attribute_002003;
        return score_SurroundingEnvironment_attributes;
    }
}
