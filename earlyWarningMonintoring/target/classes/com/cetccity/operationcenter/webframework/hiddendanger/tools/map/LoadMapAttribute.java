package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:02 2019-03-29
 * Updater:     heliangming
 * Update_Date：15:02 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@UtilityClass
public class LoadMapAttribute {

    public static Map<String,String> getLoadMapAttribute(String tableNameUrl){
        String tableName;
        String layerid;
        String column = null;
        String columnEntity = null;
        String value = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName+"."+tableNameUrl);
        if(tableNameUrl.contains("@")){
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            layerid = value.substring(0, value.indexOf("#"));
            column = value.substring(value.indexOf("#") + 1, value.indexOf("$"));
            columnEntity = value.substring(value.indexOf("$") + 1, value.length());
        }else{
            tableName = tableNameUrl;
            layerid = value;
        }
        Map<String,String> map = new HashMap();
        map.put("tableName",tableName);
        map.put("layerid",layerid);
        map.put("column",column);
        map.put("columnEntity",columnEntity);
        return map;
    }

    public static IconTypeLoadMap geticonTypeLoadMap(String tableName, String id, String longitude, String latitude, String layerid, String iconType){
        String jd84, wd84;
        if (longitude == null || "".equals(longitude)) {
            jd84 = "0.0";
        } else {
            jd84 = longitude;
        }
        if (latitude == null || "".equals(latitude)) {
            wd84 = "0.0";
        } else {
            wd84 = latitude;
        }
        Map<String,String> map_point = new HashMap();
        map_point.put("jd84",jd84);
        map_point.put("wd84",wd84);

        IconTypeLoadMap iconTypeLoadMap = new IconTypeLoadMap();
        iconTypeLoadMap.setTableName(tableName);
        iconTypeLoadMap.setId(id);
        iconTypeLoadMap.setJd(map_point.get("jd84"));
        iconTypeLoadMap.setWd(map_point.get("wd84"));
        iconTypeLoadMap.setLayerid(layerid);
        iconTypeLoadMap.setIconType(iconType);
        return iconTypeLoadMap;
    }
}
