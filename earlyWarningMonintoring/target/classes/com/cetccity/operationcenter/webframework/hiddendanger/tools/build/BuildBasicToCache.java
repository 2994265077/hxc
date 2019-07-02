package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildBasicToCache {

    @Autowired
    OracleOperateService oracleOperateService;

    public static List<LinkedHashMap> list_build_basic_information;

    public static Map<String,Float> map_attribute_build_basic_information;

    public void getAllbuildBasicInformation(String tableName) {
        String sql = "select BUILDID,NHDJ,JG_DATE,JZGD from \"" + tableName + "\"";
        list_build_basic_information = oracleOperateService.querySql(sql);
    }

    public void getAttributeBuildBasicInformation(String tableName,String PID) {
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM "+tableName+" WHERE PID = '"+PID+"'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        map_attribute_build_basic_information = map_attribute;
    }
}
