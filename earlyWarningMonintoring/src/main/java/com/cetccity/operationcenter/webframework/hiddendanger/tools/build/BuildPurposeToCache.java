package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildPurposeToCache {

    @Autowired
    OracleOperateService oracleOperateService;

    public static List<LinkedHashMap> list_build_purpose_information;

    public static Map<String,Float> map_attribute_build_Purpose_information;

    public void getAllbuildPurpose() {
        String sql_blk_building = "select LDDM,SYYT from BLK_BUILDING";
        list_build_purpose_information = oracleOperateService.querySql(sql_blk_building);
    }

    public void getAttributeBuildPurposeInformation(String tableName,String PID) {
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM "+tableName+" WHERE PID = '"+PID+"'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        map_attribute_build_Purpose_information = map_attribute;
    }
}
