package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.alibaba.fastjson.JSON;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildSurroundingEnvironmentScoreTool;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildSurroundingEnvironmentToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class BuildSurroundingEnvironmentToolToCache_SanXiao {

    @Autowired
    BuildSurroundingEnvironmentToCache buildSurroundingEnvironmentToCache;

    public Map<String ,String> buildSurroundingEnvironmentScoreToCache(){

        Map<String ,String> map_surrounding_environment_score = new HashMap<String ,String>();

        String weather = buildSurroundingEnvironmentToCache.WeatherForecastOfToday;
        String value_002001;  //天气
        String value_002003;  //风级
        String maxt;  //最高温度
        String mint;//最低温度
        Double value_002002;//温差

        double score_002001;
        double score_002002;
        double score_002003;
        double score_002;

        Map map = (Map) JSON.parse(weather);
        value_002001=(String) map.get("report");
        value_002003=(String) map.get("ws");
        maxt=(String) map.get("maxt");
        mint=(String) map.get("mint");

        BuildSurroundingEnvironmentScoreTool buildSurroundingEnvironmentScoreTool =new BuildSurroundingEnvironmentScoreTool();
        /*天气状况 50% */     /*多云：100分   小雨，阵雨，晴：50分 其他：30分 暴雨：0分*/
        score_002001 = buildSurroundingEnvironmentScoreTool.getBuildWeatherConditionScore(value_002001);

        /*温度  30%*/  /*由火灾数据中最高温度，最低温度，温差3个维度对火灾影响构建聚类，为不同类别对火宅的影响程度打分。  第一类：100分  第二类：50分 第三类：30分*/
        value_002002 = Double.valueOf(maxt)-Double.valueOf(mint);
        score_002002 = buildSurroundingEnvironmentScoreTool.getBuildTemperatureDifferenceScore(value_002002);

        /*风级 20% 风级≤3级 ：100分  其他：0分*/
        score_002003 = buildSurroundingEnvironmentScoreTool.getBuildWindGradeScore(value_002003);

        /*计算建筑火灾安全管理  */
        score_002 = buildSurroundingEnvironmentScoreTool.getBuildSurroundingEnvironmentScore(score_002001,score_002002,score_002003);

        map_surrounding_environment_score.put("score_002001",String.valueOf(score_002001));
        map_surrounding_environment_score.put("value_002001",value_002001);
        map_surrounding_environment_score.put("score_002002",String.valueOf(score_002002));
        map_surrounding_environment_score.put("value_002002",String.valueOf(value_002002));
        map_surrounding_environment_score.put("score_002003",String.valueOf(score_002003));
        map_surrounding_environment_score.put("value_002003",String.valueOf(value_002003));

        map_surrounding_environment_score.put("score_002",String.valueOf(score_002));
        return map_surrounding_environment_score;
    }

}
