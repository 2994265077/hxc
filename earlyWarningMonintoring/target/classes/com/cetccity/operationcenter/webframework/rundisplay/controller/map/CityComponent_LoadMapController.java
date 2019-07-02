package com.cetccity.operationcenter.webframework.rundisplay.controller.map;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.controller.map.ALL_LoadMapController;
import com.cetccity.operationcenter.webframework.rundisplay.api.map.CityComponentLoadMapApi;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapUtil;
import com.cetccity.operationcenter.webframework.rundisplay.service.RunDisplayService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.*;

@RestController
@Slf4j
public class CityComponent_LoadMapController implements CityComponentLoadMapApi {

    @Autowired
    RunDisplayService runDisplayService;

    @Autowired
    ALL_LoadMapController aLL_LoadMapController;

    @Autowired
    LoadMapUtil loadMapUtil;

    @Autowired
    OracleOperateService oracleOperateService;

    public List<LoadMap> findVideoByType(@PathVariable("tableNameUrl")String tableNameUrl, String street, String name,String id){
        List<LoadMap> loadMap_list = findLoadMapByDbNoField(tableNameUrl,street,name,id);
        return loadMap_list;
    }

    public List<LoadMap> findLoadMapByDbNoField(String tableNameUrl,String street,String name,String id){
        String tableName;
        List<LoadMap> loadMap_list;
        String layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName+"."+tableNameUrl);
        if(StringUtils.isEmpty(street)) {
            tableName = tableNameUrl;
            log.info("layerid-->" + layerid);
            loadMap_list = runDisplayService.loadVideoMapByDB(tableName,layerid,name,id,street);
        }else{
            String StreetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties",street).split(",")[0];
            tableName = tableNameUrl;
            log.info("layerid-->" + layerid);
            loadMap_list = runDisplayService.loadVideoMapByDBForStreet(tableName,layerid,StreetCode,name,id,street);
        }
        return loadMap_list;
    }

    public List<LoadMap> findObjectByDB(@PathVariable("tableNameUrl") String tableNameUrl,String street,String id) throws IOException {
        List<LoadMap> loadMap_list = aLL_LoadMapController.findObjectByDB(tableNameUrl,street,id);
        return loadMap_list;
    }

    public List<LoadMap> findHospitalByDB(@PathVariable("tableNameUrl") String tableNameUrl,String street,String id) throws IOException {
        List<LoadMap> loadMap_list = aLL_LoadMapController.findObjectByDB(tableNameUrl,street,id);
        return loadMap_list;
    }
}
