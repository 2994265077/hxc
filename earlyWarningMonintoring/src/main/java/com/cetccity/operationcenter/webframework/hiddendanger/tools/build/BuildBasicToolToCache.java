package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class BuildBasicToolToCache {

    @Autowired
    BuildBasicToCache buildBasicToCache;

    public Map<String ,String> buildBasicScoreToCache(String buildid){

        BuildBasicScoreTool buildBasicScoreTool = new BuildBasicScoreTool();
        Map<String ,String> map_basic_score = new HashMap<String ,String>();
        List<LinkedHashMap> list_build_score = new ArrayList<LinkedHashMap>();
        List<LinkedHashMap> list_build_score_cache;
        double score_001 = 0.0;
        String completionTime;
        Integer buildTime;
        int value_001001=0;
        double value_001002=0.0;
        String value_001003 = ""; //耐火等级
        String value_001004 = "";

        double score_001001 = 0.0; //楼栋年龄得分
        double score_001002 = 0.0; //楼栋高度得分
        double score_001003 = 0.0; //耐火等级得分
        double score_001004 = 0.0;//隐患等级得分

        list_build_score_cache = buildBasicToCache.list_build_basic_information;
        for(int i=0;i<list_build_score_cache.size();i++){
            if(buildid.equals(list_build_score_cache.get(i).get("BUILDID"))){
                list_build_score.add(list_build_score_cache.get(i));
                break;
            }
        }

        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        Integer year = Integer.valueOf(date.split("-")[0]);

        for (LinkedHashMap map_score:list_build_score) {
            /*楼栋年龄评分模块*/
            completionTime = (String)map_score.get("JG_DATE");
            if ("".equals(completionTime) || completionTime == null) {
                value_001001 = 25;
                score_001001 = value_001001 + 60;
            } else {
                if(completionTime.contains("-")){
                    buildTime = Integer.valueOf((completionTime).split("-")[0]);
                    value_001001 = year - buildTime;
                }else if(completionTime.contains("年")){
                    buildTime = Integer.valueOf((completionTime).split("年")[0]);
                    value_001001 = year - buildTime;
                }else if(completionTime.contains(".")){
                    buildTime = Integer.valueOf(completionTime.substring(0, completionTime.indexOf(".")));
                    value_001001 = year - buildTime;
                }else{
                    value_001001 = 25;
                }
                score_001001 = buildBasicScoreTool.getBuildAgeScore(value_001001);
            }
            /*建筑高度评分模块*/
            if ("".equals(map_score.get("JZGD")) || map_score.get("JZGD") == null) {
                value_001002 = 35.0;
                score_001002 = (5 * value_001002 + 50) / 6;
            } else {
                value_001002 = Double.parseDouble((String) map_score.get("JZGD"));
                score_001002 = buildBasicScoreTool.getBuildHeightScore(value_001002);
            }
            /*耐火等级模块*/
            if ("".equals(map_score.get("NHDJ")) || map_score.get("NHDJ") == null) {
                //buildFireResistance = "3";
                score_001003 = 50;
            } else {
                value_001003 = (String) map_score.get("NHDJ");
                score_001003 = buildBasicScoreTool.getBuildFireResistanceScore(value_001003);
            }
            /*隐患等级模块*/
            if("".equals(map_score.get("SCORE"))||map_score.get("SCORE")==null){
                /*默认70即黄色预警，分数为 30；*/
                value_001004 = "70";
                score_001004=30;
            }else{
                value_001004 = (String) map_score.get("SCORE");
                score_001004 = buildBasicScoreTool.getBuildFireGradeScore(Double.valueOf(map_score.get("SCORE").toString()));
            }
            /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.2+耐火等级*0.2+隐患等级*0.15*/
            score_001 = buildBasicScoreTool.getBuildBasicAttributes(score_001001, score_001002, score_001003,score_001004);
        }

        map_basic_score.put("score_001001",String.valueOf(score_001001));
        map_basic_score.put("value_001001",String.valueOf(value_001001));
        map_basic_score.put("score_001002",String.valueOf(score_001002));
        map_basic_score.put("value_001002",String.valueOf(value_001002));
        map_basic_score.put("score_001003",String.valueOf(score_001003));
        map_basic_score.put("value_001003",String.valueOf(value_001003));
        map_basic_score.put("score_001004",String.valueOf(score_001004));
        map_basic_score.put("value_001004",String.valueOf(value_001004));
        map_basic_score.put("score_001",String.valueOf(score_001));

        return map_basic_score;
    }
}
