package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildSecurityManagementToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
public class EmergencyScoreToolToCache_SanXiao {

    @Autowired
    EmergencyScoreTool_SanXiao emergencyScoreTool_SanXiao;

    public Map<String ,String> buildEmergencyScoreToCache(String buildid){

        Map<String ,String> map_emergency_score = new HashMap<String ,String>();
        //EmergencyScoreTool_SanXiao emergencyScoreTool_SanXiao = new EmergencyScoreTool_SanXiao();
        List<LinkedHashMap> list_build_fire_alarm_score = new ArrayList<>();
        List<LinkedHashMap> list_build_fire_control_room_score = new ArrayList<>();
        String ss[];
        Integer value_006001 = 0;
        String value_006002;
        Integer value_006003 = 0;
        String value_006004 = "";
        double score_006001; //火灾报警器情况得分
        double score_006002; //消防控制室得分
        double score_006003;//消防设备完好度得分
        double score_006004;//消防巡查得分

        double score_006; //火灾报警器情况得分

        /*火灾报警器情况 25%*/
        List<LinkedHashMap> list_build_fire_alarm_cache = BuildSecurityManagementToCache.list_build_securityManagement_information;
        for(int i=0;i<list_build_fire_alarm_cache.size();i++){
            if(buildid.equals(list_build_fire_alarm_cache.get(i).get("BUILDID"))){
                list_build_fire_alarm_score.add(list_build_fire_alarm_cache.get(i));
                break;
            }
        }
        if(list_build_fire_alarm_score.size()==0){
            score_006001 = 60;
        }else {
            String XFSSQK = (String) list_build_fire_alarm_score.get(0).get("XFSSQK");
            if("".equals(XFSSQK)||XFSSQK==null){
                value_006001 = 6;
            }else {
                ss = XFSSQK.split(",");
                value_006001 = ss.length;
            }
            score_006001 = emergencyScoreTool_SanXiao.buildscore_006001(value_006001);
        }


        /*消防控制室 25%*/
        List<LinkedHashMap> list_build_fire_control_room_cache = BuildSecurityManagementToCache.list_build_securityManagement_information;
        for(int i=0;i<list_build_fire_control_room_cache.size();i++){
            if(buildid.equals(list_build_fire_control_room_cache.get(i).get("BUILDID"))){
                list_build_fire_control_room_score.add(list_build_fire_control_room_cache.get(i));
                break;
            }
        }
        if(list_build_fire_control_room_score.size()==0){
            value_006002 = "0";
            score_006002 = 100;
        }else {
            value_006002 = (String) list_build_fire_control_room_score.get(0).get("XFKZS");
            if("".equals(value_006002)||value_006002==null){
                value_006002 = "0";
            }
            score_006002 = emergencyScoreTool_SanXiao.buildscore_006002(value_006002);
        }

        /*消防设备完好度 25%*/
        List<LinkedHashMap> list_build_fire_equipment_score = new ArrayList<LinkedHashMap>();
        List<LinkedHashMap> list_build_fire_equipment_cache = BuildSecurityManagementToCache.list_build_fire_equipment_information;
        for(int k=0;k<list_build_fire_equipment_cache.size();k++){
            if(buildid.equals(list_build_fire_equipment_cache.get(k).get("BUILD_ID"))){
                list_build_fire_equipment_score.add(list_build_fire_equipment_cache.get(k));
            }
        }
        if(list_build_fire_equipment_score.size()==0){
            score_006003 = 60;
        }else {
            for (LinkedHashMap map_fire_equipment:list_build_fire_equipment_score) {
                value_006003 = map_fire_equipment.size();
            }
            score_006003 = emergencyScoreTool_SanXiao.buildscore_006003(list_build_fire_equipment_score);
        }

        score_006004 = emergencyScoreTool_SanXiao.buildscore_006004(value_006004);
        /*计算建筑火灾安全管理  */
        score_006 = emergencyScoreTool_SanXiao.getBuildemergencyScore(score_006001,score_006002,score_006003,score_006004);

        map_emergency_score.put("score_006001",String.valueOf(score_006001));
        map_emergency_score.put("value_006001",String.valueOf(value_006001));
        map_emergency_score.put("score_006002",String.valueOf(score_006002));
        map_emergency_score.put("value_006002",value_006002);
        map_emergency_score.put("score_006003",String.valueOf(score_006003));
        map_emergency_score.put("value_006003",String.valueOf(value_006003));
        map_emergency_score.put("score_006004",String.valueOf(score_006004));
        map_emergency_score.put("value_006004",value_006004);

        map_emergency_score.put("score_006",String.valueOf(score_006));
        return map_emergency_score;
    }
}
