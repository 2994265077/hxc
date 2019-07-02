package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BuildAttributeRateForOneToCache {

    @Autowired
    OracleOperateService oracleOperateService;

    static Map<String,Float> map_attribute_build_One_information;

    static List<NameValueModel> list_name_build_One_information;

    public void getAttributeBuildOneInformation() {
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM BUILD_SCORE_WEIGHT WHERE PID = '0'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        map_attribute_build_One_information = map_attribute;
    }

    public void getNameBuildOneInformation() {
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM BUILD_SCORE_WEIGHT WHERE PID = '0'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        for (LinkedHashMap map:map_attribute_list) {
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName((String)map.get("MAIN_ID"));
            nameValueModel.setValue((String)map.get("NAME"));
            nameValueModel_list.add(nameValueModel);*/
            nameValueModel_list.add(NameValueModel.builder().name((String)map.get("MAIN_ID")).value((String)map.get("NAME")).build());
        }
        list_name_build_One_information = nameValueModel_list;
    }
}
