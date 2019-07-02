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
 * Create_Date: 17:35 2019-01-15
 * Updater:     heliangming
 * Update_Date：17:35 2019-01-15
 * 更新描述:    heliangming 补充
 **/
@Component
public class BuildLegal_PersonScoreTool_SanXiao {

    @Autowired
    OracleOperateService oracleOperateService;

    //法人经营业务
    public double buildscore_004001(String value_004001){
        double score_004001 = 60; //法人经营业务得分
        return score_004001;
    }
    //是否三小场所
    public double buildscore_004002(String value_004002){
        double score_004002 = 100; //是否三小场所得分
        return score_004002;
    }
    //消防隐患历史情况
    public double buildscore_004003(int num){
        double score_004003; //消防隐患历史情况得分
        if(num==0){
            score_004003 = 0;
        }else{
            score_004003 = 100;
        }
        return score_004003;
    }

    public double getLegal_PersonAttributes(double score_004001,double score_004002,double score_004003){
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '004'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute_Legal_Person_information = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute_Legal_Person_information.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.25+耐火等级*0.2+隐患等级*0.2+建筑用途*0.2*/
        float attribute_004001 = map_attribute_Legal_Person_information.get("004001");
        float attribute_004002 = map_attribute_Legal_Person_information.get("004002");
        float attribute_004003 = map_attribute_Legal_Person_information.get("004003");

        double score_Legal_Person_attributes = attribute_004001*score_004001+attribute_004002*score_004002+attribute_004003*score_004003;
        return score_Legal_Person_attributes;
    }

}
