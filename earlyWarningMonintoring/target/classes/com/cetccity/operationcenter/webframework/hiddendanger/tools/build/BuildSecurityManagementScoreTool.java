package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildSecurityManagementScoreTool {

    @Autowired
    BuildSecurityManagementToCache buildSecurityManagementToCache;


    public double getBuildFireAlarmScore(Integer ss_length) {
        /*     消防设施情况一共有 19 个类别，某个建筑拥有的消防设施类别个数为a；分数s算法为：100-100*a/19    */
        double fire_alarm_score = 100-100*ss_length/19;
        return fire_alarm_score;
    }

    public double getBuildControlRoomScore(String XFKZS){
        double score_fire_control_room = 0.0;
        /*消防控制室情况：1、有；0、无   有，分数为 0 分；无，分数为 100分*/
        if("1".equals(XFKZS)){
            score_fire_control_room = 0;
        }else {
            score_fire_control_room = 100;
        }
        return score_fire_control_room;
    }

    public double getBuildfFireEquipmentScore(List<LinkedHashMap> list_build_fire_equipment_score){
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

    public double getBuildFirePatrolScore(Integer total){
        double score_fire_patrol = 0.0;
        /*分数s算法：a>=7，分数为 0；4<=a<7，分数为 25；a=3，分数为 45；a=2，分数为 65；a=1，分数为 80；a<1，分数为 100*/
        if(total==0){
            score_fire_patrol = 100;
        }else if(total == 1){
            score_fire_patrol = 80;
        }else if(total == 2){
            score_fire_patrol = 65;
        }else if(total == 3){
            score_fire_patrol = 45;
        }else if(total>=4&&total<7){
            score_fire_patrol = 25;
        }else{
            score_fire_patrol = 0;
        }
        return score_fire_patrol;
    }

    public double getBuildSecurityManagementScore(double score_fire_alarm,double score_fire_control_room,double score_fire_equipment,double score_fire_patrol){
        /*火灾报警器情况 25%   消防控制室 25%    消防设备完好度 25%   消防巡查 25%  */
        Map<String ,Float> map_attribute_build_SecurityManagement_information = buildSecurityManagementToCache.map_attribute_build_SecurityManagement_information;
        float attribute_002001 = map_attribute_build_SecurityManagement_information.get("002001");
        float attribute_002002 = map_attribute_build_SecurityManagement_information.get("002002");
        float attribute_002003 = map_attribute_build_SecurityManagement_information.get("002003");
        float attribute_002004 = map_attribute_build_SecurityManagement_information.get("002004");
        double score_security_management = attribute_002001*score_fire_alarm+attribute_002002*score_fire_control_room+attribute_002003*score_fire_equipment+attribute_002004*score_fire_patrol;
        return score_security_management;
    }
}
