package com.cetccity.operationcenter.webframework.hiddendanger.controller.map;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.map.IotMapApi;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.PageInfo_LoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.IotMapUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapAttribute;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.PageInfoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:27 2019-04-16
 * Updater:     heliangming
 * Update_Date：11:27 2019-04-16
 * 更新描述:    heliangming 补充
 **/
@RestController
@CacheConfig(cacheNames = "loadMap")
public class IotMapController implements IotMapApi {

    @Autowired
    IotMapUtil iotMapUtil;

    @Autowired
    PageInfoUtil pageInfoUtil;

    //@Cacheable
    public List<IconTypeLoadMap> findIotByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id, String alarm) throws IOException {
        List<IconTypeLoadMap> loadMap_list;
        String tableName = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("tableName");
        String layerid = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("layerid");
        String column = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("column");
        String columnEntity = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("columnEntity");
        if(StringUtils.isEmpty(id)) {
            if (StringUtils.isEmpty(street)) {
                if(StringUtils.isEmpty(alarm)){
                    loadMap_list = iotMapUtil.iconTypeLoadMapByIot(tableName, layerid, column, columnEntity);
                }else {
                    loadMap_list = iotMapUtil.iconTypeLoadMapByIot(tableName, layerid, column, columnEntity);
                    List<IconTypeLoadMap> save = new ArrayList<>();
                    for (IconTypeLoadMap iconTypeLoadMap:loadMap_list) {
                        if("0".equals(iconTypeLoadMap.getIconType())){
                            save.add(iconTypeLoadMap);
                        }
                    }
                    loadMap_list.removeAll(save);
                }
            } else {
                String StreetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
                if(StringUtils.isEmpty(alarm)){
                    loadMap_list = iotMapUtil.iconTypeLoadMapByIotStreet(tableName, layerid, column, columnEntity, StreetCode);
                }else {
                    loadMap_list = iotMapUtil.iconTypeLoadMapByIotStreet(tableName, layerid, column, columnEntity, StreetCode);
                    List<IconTypeLoadMap> save = new ArrayList<>();
                    for (IconTypeLoadMap iconTypeLoadMap:loadMap_list) {
                        if("1".equals(iconTypeLoadMap.getIconType())){
                            save.add(iconTypeLoadMap);
                        }
                    }
                    loadMap_list = save;
                }
            }
        }else{
            if(id.contains(" ")) id = id.replace(" ","+");
            loadMap_list = iotMapUtil.iconTypeLoadMapByIotID(tableName,layerid,id);
        }
        return loadMap_list;
    }

    public PageInfo_LoadMap findPageInfoIotByDb(@PathVariable("tableNameUrl") String tableNameUrl, String pageNum, String pageSize, String street) throws IOException {
        return pageInfoUtil.getPageInfoDbUtil(tableNameUrl,pageNum,pageSize,street);
    }
}
