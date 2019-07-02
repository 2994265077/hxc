package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class BuildSurroundingEnvironmentToolToCache {

    @Autowired
    BuildSurroundingEnvironmentToCache buildSurroundingEnvironmentToCache;

    public Map<String ,String> buildSurroundingEnvironmentScoreToCache(){

        Map<String ,String> map_surrounding_environment_score = new HashMap<String ,String>();

        String weather = buildSurroundingEnvironmentToCache.WeatherForecastOfToday;
        String value_004001;  //天气
        String value_004003;  //风级
        String maxt;  //最高温度
        String mint;//最低温度
        Double value_004002;//温差

        double score_004001;
        double score_004002;
        double score_004003;
        double score_004;

        Map map = (Map) JSON.parse(weather);
        value_004001=(String) map.get("report");
        value_004003=(String) map.get("ws");
        maxt=(String) map.get("maxt");
        mint=(String) map.get("mint");

        BuildSurroundingEnvironmentScoreTool buildSurroundingEnvironmentScoreTool =new BuildSurroundingEnvironmentScoreTool();
        /*天气状况 50% */     /*多云：100分   小雨，阵雨，晴：50分 其他：30分 暴雨：0分*/
        score_004001 = buildSurroundingEnvironmentScoreTool.getBuildWeatherConditionScore(value_004001);

        /*温度  30%*/  /*由火灾数据中最高温度，最低温度，温差3个维度对火灾影响构建聚类，为不同类别对火宅的影响程度打分。  第一类：100分  第二类：50分 第三类：30分*/
        value_004002 = Double.valueOf(maxt)-Double.valueOf(mint);
        score_004002 = buildSurroundingEnvironmentScoreTool.getBuildTemperatureDifferenceScore(value_004002);

        /*风级 20% 风级≤3级 ：100分  其他：0分*/
        score_004003 = buildSurroundingEnvironmentScoreTool.getBuildWindGradeScore(value_004003);

        /*计算建筑火灾安全管理  */
        score_004 = buildSurroundingEnvironmentScoreTool.getBuildSurroundingEnvironmentScore(score_004001,score_004002,score_004003);

        map_surrounding_environment_score.put("score_004001",String.valueOf(score_004001));
        map_surrounding_environment_score.put("value_004001",value_004001);
        map_surrounding_environment_score.put("score_004002",String.valueOf(score_004002));
        map_surrounding_environment_score.put("value_004002",String.valueOf(value_004002));
        map_surrounding_environment_score.put("score_004003",String.valueOf(score_004003));
        map_surrounding_environment_score.put("value_004003",String.valueOf(value_004003));

        map_surrounding_environment_score.put("score_004",String.valueOf(score_004));
        return map_surrounding_environment_score;
    }

}
