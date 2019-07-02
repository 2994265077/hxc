package com.cetccity.operationcenter.webframework.search.controller;

import com.cetccity.operationcenter.webframework.search.api.PolygonSearchApi;
import com.cetccity.operationcenter.webframework.search.api.model.NearSearchLoadMap;
import com.cetccity.operationcenter.webframework.search.service.PolygonSearchService;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:32 2019-03-28
 * Updater:     heliangming
 * Update_Date：10:32 2019-03-28
 * 更新描述:    heliangming 补充
 **/
@RestController
public class PolygonSearchController implements PolygonSearchApi {

    @Autowired
    PolygonSearchService polygonSearchService;

    //赵李明
    public List<NearSearchLoadMap> findPolygonResourceObj(String tableName, String rings) throws IOException{
        List<NearSearchLoadMap> loadMap_list = new ArrayList<>();
        String tableName_Obj[];
        if(tableName.contains(",")){
            tableName_Obj = tableName.split(",");
        }else{
            tableName_Obj = new String[] {tableName};
        }
        List<NearbyResourcesModel> nearbyResourcesModel_list = polygonSearchService.findPolygonResourceObj(tableName_Obj,rings);
        nearbyResourcesModel_list.stream().forEach(u->{
            List<NearSearchLoadMap> loadMap_list2 = (List<NearSearchLoadMap>)u.getData();
            loadMap_list2.stream().forEach(s->
                loadMap_list.add(s));
        });
        return loadMap_list;
    }
    //董鑫
    public List<NearbyResourcesModel> findPolygonNearbyResource(String tableName,String rings) throws IOException{
        String tableName_Obj[];
        if(tableName.contains(",")){
            tableName_Obj = tableName.split(",");
        }else{
            tableName_Obj = new String[] {tableName};
        }
        return polygonSearchService.findPolygonResourceObj(tableName_Obj,rings);
    }

}
