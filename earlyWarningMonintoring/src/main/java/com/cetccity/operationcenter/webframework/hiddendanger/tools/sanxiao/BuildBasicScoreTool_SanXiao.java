package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildBasicScoreTool_SanXiao {

    @Autowired
    OracleOperateService oracleOperateService;

    public double getBuildAgeScore(Integer buildAge) {
        double score_age; //楼栋年龄得分
        /*楼栋年龄评分模块*/
        if (buildAge < 8) {
                    /*0~8年：20*（a/8）=2.5a；*/
            score_age = 2.5 * buildAge;
        } else if (buildAge >= 8 && buildAge < 20) {
                    /*8~20年：20+60*（a-8)/(20-8)=5a-20;*/
            score_age = 5 * buildAge - 20;
        } else if (buildAge >= 20 && buildAge < 40) {
                    /*20年以上40年以下：80+（a-20)=a+60;*/
            score_age = buildAge + 60;
        } else {
                    /*超过40年的楼都为100分*/
            score_age = 100;
        }
        return score_age;
    }

    public double getBuildHeightScore(Double buildHeight) {
        double score_height; //楼栋高度得分
        /*楼栋高度评分模块*/
        if(buildHeight<20){
                    /*0~20米：25*（a/20)=1.25a*/
            score_height = 1.25*buildHeight;
        }else if(buildHeight>=20&&buildHeight<50){
                    /*20~50米：25+25*(a-20)/（50-20）=（5a+50)/6*/
            score_height = (5*buildHeight+50)/6;
        }else if(buildHeight>=50&&buildHeight<100){
                    /*50~100米：50+25*(a-50)/(100-50)=0.5a+25*/
            score_height =0.5*buildHeight+25;
        }else if(buildHeight>=100&&buildHeight<125){
                    /*100米以上125以下：75+a-100=a-25;*/
            score_height = buildHeight-25;
        }else{
                    /*超过125米的建筑都为100分*/
            score_height = 100;
        }
        return LoadMyUtil.retainToPoint(score_height,2);
    }

    /*耐火等级模块*/
    public double getBuildFireResistanceScore(String buildFireResistance) {
        double score_fireResistance; //耐火等级得分
        /*耐火等级评分模块*/
        if("1".equals(buildFireResistance)) {
                       /* 一级为10分；*/
            score_fireResistance = 10;
        }else if("2".equals(buildFireResistance)){
                        /*二级为20分；*/
            score_fireResistance = 20;
        }else if("3".equals(buildFireResistance)){
                        /*三级为50分；*/
            score_fireResistance = 50;
        }else{
                        /*四级为80分*/
            score_fireResistance = 80;
        }
        return score_fireResistance;
    }

    /*隐患等级模块*/
    public double getBuildFireGradeScore(double score_build_fireGrade) {
        double score_fireGrade;
        if("0".equals(score_build_fireGrade)){
                    /*0即红色预警，分数为 60；*/
            score_fireGrade=60;
        }else if ("70".equals(score_build_fireGrade)){
                    /*70即黄色预警，分数为 30；*/
            score_fireGrade=30;
        }else if("100".equals(score_build_fireGrade)){
                    /*100即绿色预警，分数为 10*/
            score_fireGrade=10;
        }else{
            score_fireGrade=30;
        }
        return score_fireGrade;
    }

    public double getBuildPurposeScore(String SYYT) {
        double purpose_score;
        /* 1.易燃易爆工厂：100分 2.住宅：50分 3.其他为0分 */
        /*blk_building.SYYT.1=公共娱乐场所
        blk_building.JZYT.2=宾馆、饭店
        blk_building.JZYT.3=商场、市场
        blk_building.JZYT.4=体育场馆、会堂
        blk_building.JZYT.5=展览馆、博物馆
        blk_building.JZYT.6=民用机场航站楼，客运车站候车室、码头候船厅
        blk_building.JZYT.7=医院、疗养院
        blk_building.JZYT.8=养老院、福利院
        blk_building.JZYT.9=国家机关办公楼、电力调度楼、电信楼、邮政楼、防灾指挥调度楼、广播电视楼、档案楼*/
        if("1".equals(SYYT)){
            purpose_score = 50;
        }else if("2".equals(SYYT)){
            purpose_score = 30;
        }else{
            purpose_score = 0;
        }
        return purpose_score;
    }

    /*建筑基础属性得分*/
    public double getBuildBasicAttributes(double score_age,double score_height,double score_fireResistance,double score_fireGrade,double score_001005){
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '001'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute_build_basic_information = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute_build_basic_information.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.25+耐火等级*0.2+隐患等级*0.2+建筑用途*0.2*/
        float attribute_001001 = map_attribute_build_basic_information.get("001001");
        float attribute_001002 = map_attribute_build_basic_information.get("001002");
        float attribute_001003 = map_attribute_build_basic_information.get("001003");
        float attribute_001004 = map_attribute_build_basic_information.get("001004");
        float attribute_001005 = map_attribute_build_basic_information.get("001005");
        double score_build_basic_attributes = attribute_001001*score_age+attribute_001002*score_height+attribute_001003*score_fireResistance+attribute_001004*score_fireGrade+attribute_001005*score_001005;
        return score_build_basic_attributes;
    }

}
