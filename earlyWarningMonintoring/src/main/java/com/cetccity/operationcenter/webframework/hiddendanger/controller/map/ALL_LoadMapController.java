package com.cetccity.operationcenter.webframework.hiddendanger.controller.map;

import com.cetccity.operationcenter.webframework.hiddendanger.api.map.ALL_LoadMapApi;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapAttribute;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.SanXiaoPlaceMapUtil;
import com.cetccity.operationcenter.webframework.search.service.ResourcesSearchService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapUtil;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@Component
@CacheConfig(cacheNames = "loadMap")
public class ALL_LoadMapController implements ALL_LoadMapApi {

    Logger logger = LoggerFactory.getLogger(ALL_LoadMapController.class);

    @Autowired
    ResourcesSearchService searchResourcesService;

    @Autowired
    LoadMapUtil loadMapUtil;

    @Autowired
    SanXiaoPlaceMapUtil sanXiaoPlaceMapUtil;

    public List<LoadMap> findObjectByDB(@PathVariable("tableNameUrl") String tableNameUrl,String street,String id) throws IOException {
        List<LoadMap> loadMap_list;
        String tableName = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("tableName");
        String layerid = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("layerid");
        String column = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("column");
        String columnEntity = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("columnEntity");
            if ("".equals(id) || id == null) {
                if ("".equals(street) || street == null) {
                    if (tableNameUrl.contains("@")) {
                        loadMap_list = loadMapUtil.loadMapByDB(tableName, layerid, column, columnEntity);
                    } else {
                        loadMap_list = loadMapUtil.loadMapByDB(tableName, layerid);
                    }
                } else {
                    String StreetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
                    logger.info("street-->" + street + "-->" + StreetCode);
                    if (tableNameUrl.contains("@")) {
                        loadMap_list = loadMapUtil.loadMapByDBForStreet(tableName, layerid, column, columnEntity, StreetCode);
                    } else {
                        loadMap_list = loadMapUtil.loadMapByDBForStreet(tableName, layerid, StreetCode);
                    }
                }
            } else {
                if (id.contains(" ")) id = id.replace(" ", "+");
                loadMap_list = loadMapUtil.loadMapByDB(tableName, layerid, id);
            }
        return loadMap_list;
    }

    public List<LoadMap> findNearResourceObj(String tableName, String jd,String wd, String range) throws IOException{
        String[] tableName_Obj = {tableName};
        List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
        List<NearbyResourcesModel> nearbyResourcesModelList;
        if("0".equals(range)){
            
        }else {
            nearbyResourcesModelList = searchResourcesService.searchByEsNearResourceObj(tableName_Obj, jd, wd, range);
            loadMap_list.addAll((List<LoadMap>)nearbyResourcesModelList.get(0).getData());
        }
        return loadMap_list;
    }

//    @Cacheable
    public List<IconTypeLoadMap> findIconTypeByDB(@PathVariable("tableNameUrl") String tableNameUrl,String street,String id,String alarm,String startTime,String endTime) throws IOException {
        List<IconTypeLoadMap> loadMap_list;
        String tableName = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("tableName");
        String layerid = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("layerid");
        String column = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("column");
        String columnEntity = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("columnEntity");
        if(StringUtils.isEmpty(id)) {
            if (StringUtils.isEmpty(street)) {
                    if(StringUtils.isEmpty(alarm)){
                        loadMap_list = loadMapUtil.iconTypeLoadMapByDB(tableName, layerid, column, columnEntity);
                    }else {
                        loadMap_list = sanXiaoPlaceMapUtil.sanXiaoLoadMapByDBAlarm(tableName, layerid, column, columnEntity,null, startTime, endTime);
                    }
            } else {
                String StreetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
                    if(StringUtils.isEmpty(alarm)){
                        loadMap_list = loadMapUtil.iconTypeLoadMapByDBStreet(tableName, layerid, column, columnEntity, StreetCode);
                    }else {
                        loadMap_list = sanXiaoPlaceMapUtil.sanXiaoLoadMapByDBAlarm(tableName, layerid, column, columnEntity, StreetCode, startTime, endTime);
                    }
            }
        }else{
            if(id.contains(" ")) id = id.replace(" ","+");
            loadMap_list = loadMapUtil.iconTypeLoadMapByDBID(tableName,layerid,id);
        }
        return loadMap_list;
    }
}
