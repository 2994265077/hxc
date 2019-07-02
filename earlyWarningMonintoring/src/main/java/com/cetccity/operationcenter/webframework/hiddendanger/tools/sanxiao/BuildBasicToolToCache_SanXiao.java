package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildBasicToCache;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildPurposeToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class BuildBasicToolToCache_SanXiao {

    @Autowired
    BuildBasicToCache buildBasicToCache;

    @Autowired
    BuildPurposeToCache buildPurposeToCache;

    @Autowired
    BuildBasicScoreTool_SanXiao buildBasicScoreToolSanXiao;

    public Map<String ,String> buildBasicScoreToCache(String buildid){

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
        String value_001005 = "2";

        double score_001001 = 0.0; //楼栋年龄得分
        double score_001002 = 0.0; //楼栋高度得分
        double score_001003 = 0.0; //耐火等级得分
        double score_001004 = 0.0;//隐患等级得分
        double score_001005 = 0.0; //建筑用途得分

        list_build_score_cache = buildBasicToCache.list_build_basic_information;
        list_build_score_cache.stream().filter(u->u.get("BUILDID").equals(buildid)).forEach(u->{
            list_build_score.add(u);
        });
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        Integer year = Integer.valueOf(date.split("-")[0]);

        for (LinkedHashMap map_score : list_build_score) {
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
                score_001001 = buildBasicScoreToolSanXiao.getBuildAgeScore(value_001001);
            }
            /*建筑高度评分模块*/
            if ("".equals(map_score.get("JZGD")) || map_score.get("JZGD") == null) {
                value_001002 = 35.0;
                score_001002 = (5 * value_001002 + 50) / 6;
            } else {
                value_001002 = Double.parseDouble((String) map_score.get("JZGD"));
                score_001002 = buildBasicScoreToolSanXiao.getBuildHeightScore(value_001002);
            }
            /*耐火等级模块*/
            if ("".equals(map_score.get("NHDJ")) || map_score.get("NHDJ") == null) {
                //buildFireResistance = "3";
                score_001003 = 50;
            } else {
                value_001003 = (String) map_score.get("NHDJ");
                score_001003 = buildBasicScoreToolSanXiao.getBuildFireResistanceScore(value_001003);
            }
            /*隐患等级模块*/
            if("".equals(map_score.get("SCORE"))||map_score.get("SCORE")==null){
                /*默认70即黄色预警，分数为 30；*/
                value_001004 = "70";
                score_001004=30;
            }else{
                value_001004 = (String) map_score.get("SCORE");
                score_001004 = buildBasicScoreToolSanXiao.getBuildFireGradeScore(Double.valueOf(map_score.get("SCORE").toString()));
            }
            /*建筑用途模块*/

            List<LinkedHashMap> list_build_purpose_score = new ArrayList<LinkedHashMap>();

            List<LinkedHashMap> list_build_purpose_cache =  buildPurposeToCache.list_build_purpose_information;

            /*建筑用途 100%*/
          /*  for (int i=0;i<list_build_purpose_cache.size();i++){
                if(buildid.equals(list_build_purpose_cache.get(i).get("LDDM"))){
                    list_build_purpose_score.add(list_build_purpose_cache.get(i));
                    break;
                }
            }*/
            list_build_purpose_cache.stream().filter(u->u.get("LDDM").equals(buildid)).forEach(u->{
                list_build_purpose_score.add(u);
            });
            if(list_build_purpose_score.size()==0){
                score_001005 = 60;
            }else {
                if("".equals(list_build_purpose_score.get(0).get("SYYT"))||list_build_purpose_score.get(0).get("SYYT")==null){
                    value_001005 = "2";
                }else{
                    String purpose = (String) list_build_purpose_score.get(0).get("SYYT");
                    switch (purpose) {
                        case "1":
                            purpose = "公共娱乐场所";break;
                        case "2":
                            purpose = "宾馆、饭店";break;
                        case "3":
                            purpose = "商场、市场";break;
                        case "4":
                            purpose = "体育场馆、会堂";break;
                        case "5":
                            purpose = "展览馆、博物馆";break;
                        case "6":
                            purpose = "民用机场航站楼，客运车站候车室、码头候船厅";break;
                        case "7":
                            purpose = "医院、疗养院";break;
                        case "8":
                            purpose = "养老院、福利院";break;
                        case "9":
                            purpose = "国家机关办公楼、电力调度楼、电信楼、邮政楼、防灾指挥调度楼、广播电视楼、档案楼";break;
                    }
                    value_001005 = purpose;
                }
                score_001005 = buildBasicScoreToolSanXiao.getBuildPurposeScore(value_001005);
            }
            /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.2+耐火等级*0.2+隐患等级*0.15*/
            score_001 = buildBasicScoreToolSanXiao.getBuildBasicAttributes(score_001001, score_001002, score_001003,score_001004,score_001005);
        }

        map_basic_score.put("score_001001",String.valueOf(score_001001));
        map_basic_score.put("value_001001",String.valueOf(value_001001));
        map_basic_score.put("score_001002",String.valueOf(score_001002));
        map_basic_score.put("value_001002",String.valueOf(value_001002));
        map_basic_score.put("score_001003",String.valueOf(score_001003));
        map_basic_score.put("value_001003",String.valueOf(value_001003));
        map_basic_score.put("score_001004",String.valueOf(score_001004));
        map_basic_score.put("value_001004",String.valueOf(value_001004));
        map_basic_score.put("score_001005",String.valueOf(score_001005));
        map_basic_score.put("value_001005",String.valueOf(value_001005));
        map_basic_score.put("score_001",String.valueOf(score_001));

        return map_basic_score;
    }
}
