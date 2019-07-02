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
 * Create_Date: 11:43 2019-01-16
 * Updater:     heliangming
 * Update_Date：11:43 2019-01-16
 * 更新描述:    heliangming 补充
 **/
@Component
public class EmergencyScoreTool_SanXiao {

    @Autowired
    OracleOperateService oracleOperateService;

    //火灾报警器情况
    public double buildscore_006001(Integer value_006001){
         /*     消防设施情况一共有 19 个类别，某个建筑拥有的消防设施类别个数为a；分数s算法为：100-100*a/19    */
        double fire_alarm_score = 100-100*value_006001/19;
        return fire_alarm_score;
    }
    //消防控制室情况
    public double buildscore_006002(String value_006002){
        double score_fire_control_room = 0.0;
        /*消防控制室情况：1、有；0、无   有，分数为 0 分；无，分数为 100分*/
        if("1".equals(value_006002)){
            score_fire_control_room = 0;
        }else {
            score_fire_control_room = 100;
        }
        return score_fire_control_room;
    }
    //消防设备完好度
    public double buildscore_006003(List<LinkedHashMap> list_build_fire_equipment_score){
        double score_fire_equipment = 0.0;
        /*一共有 7 个设备的状态信息，7 个设备的状态数字求和为a；分数s算法：100*a/11*/
        LinkedHashMap map_fire_equipment = list_build_fire_equipment_score.get(0);
        if("".equals(map_fire_equipment.get("FW"))||map_fire_equipment.get("FW")==null) {
            map_fire_equipment.put("FW","1");
        }
        if("".equals(map_fire_equipment.get("HJ"))||map_fire_equipment.get("HJ")==null){
            map_fire_equipment.put("HJ","1");
        }
        if("".equals(map_fire_equipment.get("ZDY"))||map_fire_equipment.get("ZDY")==null){
            map_fire_equipment.put("ZDY","1");
        }
        if("".equals(map_fire_equipment.get("YXZT"))||map_fire_equipment.get("YXZT")==null){
            map_fire_equipment.put("YXZT","1");
        }
        if("".equals(map_fire_equipment.get("STATUS"))||map_fire_equipment.get("STATUS")==null){
            map_fire_equipment.put("STATUS","1");
        }
        if("".equals(map_fire_equipment.get("BYDY"))||map_fire_equipment.get("BYDY")==null){
            map_fire_equipment.put("BYDY","1");
        }
        if("".equals(map_fire_equipment.get("BJKZQQK"))||map_fire_equipment.get("BJKZQQK")==null){
            map_fire_equipment.put("BJKZQQK","1");
        }
        score_fire_equipment = 10*(Double.valueOf((String)map_fire_equipment.get("FW"))
                +Double.valueOf((String)map_fire_equipment.get("HJ"))
                +Double.valueOf((String)map_fire_equipment.get("ZDY"))
                +Double.valueOf((String)map_fire_equipment.get("YXZT"))
                +Double.valueOf((String)map_fire_equipment.get("STATUS"))
                +Double.valueOf((String)map_fire_equipment.get("BYDY"))
                +Double.valueOf((String)map_fire_equipment.get("BJKZQQK")))/11;

        return score_fire_equipment;
    }
    //物业人力配备
    public double buildscore_006004(String value_006004){
        double score_006004 = 60; //法人经营业务得分
        return score_006004;
    }

    public double getBuildemergencyScore(double score_006001,double score_006002,double score_006003,double score_006004){
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '006'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute_emergency_information = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute_emergency_information.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.25+耐火等级*0.2+隐患等级*0.2+建筑用途*0.2*/
        float attribute_006001 = map_attribute_emergency_information.get("006001");
        float attribute_006002 = map_attribute_emergency_information.get("006002");
        float attribute_006003 = map_attribute_emergency_information.get("006003");
        float attribute_006004 = map_attribute_emergency_information.get("006004");

        double score_Escape_attributes = attribute_006001*score_006001+attribute_006002*score_006002+attribute_006003*score_006003+attribute_006004*score_006004;
        return score_Escape_attributes;
    }
}
