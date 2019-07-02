package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class BuildSecurityManagementToCache {

    @Autowired
    OracleOperateService oracleOperateService;

    public static List<LinkedHashMap> list_build_securityManagement_information;
    public static List<LinkedHashMap> list_build_fire_equipment_information;
    public static List<LinkedHashMap> list_build_fire_patrol_information;

    public static Map<String ,Float> map_attribute_build_SecurityManagement_information;

    public void getAllbuildSecurityManagementBuildInfo(String tableName) {
        String sql = "select BUILDID,XFSSQK,XFKZS from \""+tableName+"\" ";
        list_build_securityManagement_information = oracleOperateService.querySql(sql);
    }

    public void getAllbuildSecurityManagementCheckControl() {
        String sql_fire_equipment = "select BUILD_ID,CHECK_TIME,FW,HJ,ZDY,YXZT,STATUS,BYDY,BJKZQQK from T_BUILD_CHECK_CONTROL order by CHECK_TIME DESC";
        list_build_fire_equipment_information = oracleOperateService.querySql(sql_fire_equipment);
    }

    public void getAllbuildSecurityManagementCheckdayDanger() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -8);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        String sql_fire_patrol = "select BUILD_ID,CHECK_TIME from T_BUILD_CHECKDAY_DANGER where CHECK_TIME>='"+date+"'";
        list_build_fire_patrol_information = oracleOperateService.querySql(sql_fire_patrol);
    }

    public void getAttributeBuildSecurityManagementInformation(String tableName,String PID) {
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM "+tableName+" WHERE PID = '"+PID+"'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        map_attribute_build_SecurityManagement_information = map_attribute;
    }
}
