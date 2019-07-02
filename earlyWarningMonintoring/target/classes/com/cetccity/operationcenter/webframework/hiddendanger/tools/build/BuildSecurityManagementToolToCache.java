package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BuildSecurityManagementToolToCache {

    @Autowired
    BuildSecurityManagementToCache buildSecurityManagementToCache;

    public Map<String ,String> buildSecurityManagementScoreToCache(String buildid){

        Map<String ,String> map_security_management_score = new HashMap<String ,String>();
        BuildSecurityManagementScoreTool buildSecurityManagementScoreTool = new BuildSecurityManagementScoreTool();
        List<LinkedHashMap> list_build_fire_alarm_score = new ArrayList<LinkedHashMap>();
        List<LinkedHashMap> list_build_fire_control_room_score = new ArrayList<LinkedHashMap>();
        String ss[] = {};
        String XFKZS;
        int t;
        Integer value_002003 = 0;
        double score_002001; //火灾报警器情况得分
        double score_002002; //消防控制室得分
        double score_002003;//消防设备完好度得分
        double score_002004;//消防巡查得分

        double score_002; //火灾报警器情况得分

        /*火灾报警器情况 25%*/
        /*String sql_fire_alarm = "select XFSSQK from \""+tableName+"\" where BUILDID='"+buildid+"'";
        List<LinkedHashMap> list_build_fire_alarm_score = oracleOperateService.querySql(sql_fire_alarm);*/

        List<LinkedHashMap> list_build_fire_alarm_cache = BuildSecurityManagementToCache.list_build_securityManagement_information;
        for(int i=0;i<list_build_fire_alarm_cache.size();i++){
            if(buildid.equals(list_build_fire_alarm_cache.get(i).get("BUILDID"))){
                list_build_fire_alarm_score.add(list_build_fire_alarm_cache.get(i));
                break;
            }
        }
        if(list_build_fire_alarm_score.size()==0){
            score_002001 = 60;
        }else {
            String XFSSQK = (String) list_build_fire_alarm_score.get(0).get("XFSSQK");
            if("".equals(XFSSQK)||XFSSQK==null){
                t = 6;
            }else {
                ss = XFSSQK.split(",");
                t = ss.length;
            }
            score_002001 = buildSecurityManagementScoreTool.getBuildFireAlarmScore(t);
        }


        /*消防控制室 25%*/
        /*String sql_fire_control_room = "select XFKZS from \""+tableName+"\" where BUILDID='"+buildid+"'";
        List<LinkedHashMap> list_build_fire_control_room_score = oracleOperateService.querySql(sql_fire_control_room);*/
        List<LinkedHashMap> list_build_fire_control_room_cache = BuildSecurityManagementToCache.list_build_securityManagement_information;
        for(int i=0;i<list_build_fire_control_room_cache.size();i++){
            if(buildid.equals(list_build_fire_control_room_cache.get(i).get("BUILDID"))){
                list_build_fire_control_room_score.add(list_build_fire_control_room_cache.get(i));
                break;
            }
        }
        if(list_build_fire_control_room_score.size()==0){
            XFKZS = "0";
            score_002002 = 100;
        }else {
            XFKZS = (String) list_build_fire_control_room_score.get(0).get("XFKZS");
            if("".equals(XFKZS)||XFKZS==null){
                XFKZS = "0";
            }
            score_002002 = buildSecurityManagementScoreTool.getBuildControlRoomScore(XFKZS);
        }

        /*消防设备完好度 25%*/
        /*String sql_fire_equipment = "select BUILD_ID,CHECK_TIME,FW,HJ,ZDY,YXZT,STATUS,BYDY,BJKZQQK from \"t_build_check_control\" where BUILD_ID='"+buildid+"' order by CHECK_TIME DESC";
        List<LinkedHashMap> list_build_fire_equipment_score = oracleOperateService.querySql(sql_fire_equipment);*/
        List<LinkedHashMap> list_build_fire_equipment_score = new ArrayList<LinkedHashMap>();
        List<LinkedHashMap> list_build_fire_equipment_cache = BuildSecurityManagementToCache.list_build_fire_equipment_information;
        for(int k=0;k<list_build_fire_equipment_cache.size();k++){
            if(buildid.equals(list_build_fire_equipment_cache.get(k).get("BUILD_ID"))){
                list_build_fire_equipment_score.add(list_build_fire_equipment_cache.get(k));
            }
        }
        if(list_build_fire_equipment_score.size()==0){
            score_002003 = 60;
        }else {
            for (LinkedHashMap map_fire_equipment:list_build_fire_equipment_score) {
                value_002003 = map_fire_equipment.size();
            }
            score_002003 = buildSecurityManagementScoreTool.getBuildfFireEquipmentScore(list_build_fire_equipment_score);
        }
        /*消防巡查 25%   计算近一周消防巡查的次数*/
        /*Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -8);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        String sql_fire_patrol = "select CHECK_TIME from \"t_build_checkday_danger\" where CHECK_TIME>='"+date+"' and BUILD_ID='"+buildid+"'";
        List<LinkedHashMap> list_build_fire_patrol_score = oracleOperateService.querySql(sql_fire_patrol);*/
        List<LinkedHashMap> list_build_fire_patrol_score = new ArrayList<LinkedHashMap>();
        List<LinkedHashMap> list_build_fire_patrol_cache = BuildSecurityManagementToCache.list_build_fire_patrol_information;
        for(int k=0;k<list_build_fire_patrol_cache.size();k++){
            if(buildid.equals(list_build_fire_patrol_cache.get(k).get("BUILD_ID"))){
                list_build_fire_patrol_score.add(list_build_fire_patrol_cache.get(k));
            }
        }
        Integer total = list_build_fire_patrol_score.size();
        score_002004 = buildSecurityManagementScoreTool.getBuildFirePatrolScore(total);

        /*计算建筑火灾安全管理  */
        score_002 = buildSecurityManagementScoreTool.getBuildSecurityManagementScore(score_002001,score_002002,score_002003,score_002004);

        map_security_management_score.put("score_002001",String.valueOf(score_002001));
        map_security_management_score.put("value_002001",String.valueOf(ss.length));
        map_security_management_score.put("score_002002",String.valueOf(score_002002));
        map_security_management_score.put("value_002002",XFKZS);
        map_security_management_score.put("score_002003",String.valueOf(score_002003));
        map_security_management_score.put("value_002003",String.valueOf(value_002003));
        map_security_management_score.put("score_002004",String.valueOf(score_002004));
        map_security_management_score.put("value_002004",String.valueOf(total));

        map_security_management_score.put("score_002",String.valueOf(score_002));
        return map_security_management_score;
    }
}
