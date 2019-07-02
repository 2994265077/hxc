package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao
 * 项目名称:   futian-EarlyWarningMonitoring
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:17 2019-01-15
 * Updater:     heliangming
 * Update_Date：18:17 2019-01-15
 * 更新描述:    heliangming 补充
 **/
@Component
public class EscapeScoreTool_SanXiao {

    @Autowired
    OracleOperateService oracleOperateService;

    public Map buildscore_00500X(String buildid){
        String sql = "select COUNT(*) from BLK_POPULATION WHERE LDDM = '"+buildid+"'";
        int populationNum = oracleOperateService.queryCount(sql);
        //儿童、中年人、老年人、孕妇默认比例 0.24:0.64:0.1:0.02
        double childPeople = Math.round(0.24 * populationNum); //儿童
        double oldPeople = Math.round(0.1 * populationNum);   //老年人
        double regnantWoman = Math.round(0.02 * populationNum); //孕妇
        double score_005001; //儿童数量得分  儿童\老人数量为M，则得分S=100/(1+e^(-m/10)),e^(-m/10) 为 e的负m/10次方
        double score_005002; //老人数量得分
        double score_005003; //孕妇数量得分  孕妇数量为M，则得分S=100/(1+e^(-m/10)),e^(-m/10) 为 e的负m/10次方
        if(populationNum == 0){
            score_005001 = 60;
            score_005002 = 60;
            score_005003 = 60;
        }else {
            score_005001 = 100 / (1 + Math.pow(2.718281828459045, (-childPeople / 10)));
            score_005002 = 100 / (1 + Math.pow(2.718281828459045, (-oldPeople / 10)));
            score_005003 = 100 / (1 + Math.pow(2.718281828459045, (-regnantWoman / 10)));
        }
        Map<String,Long> map = new HashMap();
        map.put("score_005001",Math.round(score_005001));map.put("score_005002",Math.round(score_005002));map.put("score_005003",Math.round(score_005003));
        map.put("value_005001",Math.round(childPeople));map.put("value_005002",Math.round(oldPeople));map.put("value_005003",Math.round(regnantWoman));
        return map;
    }

    //消防巡查情况得分
    public double buildscore_005004(Integer value_005004){
        double score_005004;
        /*分数s算法：a>=7，分数为 0；4<=a<7，分数为 25；a=3，分数为 45；a=2，分数为 65；a=1，分数为 80；a<1，分数为 100*/
        if(value_005004==0){
            score_005004 = 100;
        }else if(value_005004 == 1){
            score_005004 = 80;
        }else if(value_005004 == 2){
            score_005004 = 65;
        }else if(value_005004 == 3){
            score_005004 = 45;
        }else if(value_005004>=4&&value_005004<7){
            score_005004 = 25;
        }else{
            score_005004 = 0;
        }
        return score_005004;
    }

    public double getEscapeAttributes(double score_005001,double score_005002,double score_005003,double score_005004){
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '005'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute_Escape_information = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute_Escape_information.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.25+耐火等级*0.2+隐患等级*0.2+建筑用途*0.2*/
        float attribute_005001 = map_attribute_Escape_information.get("005001");
        float attribute_005002 = map_attribute_Escape_information.get("005002");
        float attribute_005003 = map_attribute_Escape_information.get("005003");
        float attribute_005004 = map_attribute_Escape_information.get("005004");

        double score_Escape_attributes = attribute_005001*score_005001+attribute_005002*score_005002+attribute_005003*score_005003+attribute_005004*score_005004;
        return score_Escape_attributes;
    }
}
