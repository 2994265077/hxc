package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:29 2019-04-16
 * Updater:     heliangming
 * Update_Date：11:29 2019-04-16
 * 更新描述:    heliangming 补充
 **/
@Component
public class IotMapUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public List<IconTypeLoadMap> iconTypeLoadMapByIot(String tableName, String layerid, String column, String columnEntity) {
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<>();
        String IconType;
        String date_current = LoadMyUtil.getMyTime("DATE",0);
        String sql = "select DEVICE_CODE from IOT_EVENT where to_char(PRODUCE_TIME,'yyyy-mm-dd hh24:mi:ss') like concat('"+date_current+"','%')";
        List<LinkedHashMap> list_event_alarm = oracleOperateService.querySql(sql);
        Set set = new HashSet<>();
        list_event_alarm.stream().forEach(u->set.add(u.get("DEVICE_CODE")));

        String sql_iot_loadmap = "select DEVICE_CODE,JD84,WD84 from " + tableName + " where "  + column + "='"+ columnEntity + "' and REGION_CODE = '440304'";
        List<LinkedHashMap> list_iot_loadmap = oracleOperateService.querySql(sql_iot_loadmap);
        for (LinkedHashMap<String, String> map : list_iot_loadmap) {
            if (set.contains(map.get("DEVICE_CODE"))) {
                IconType = "1";
            } else {
                IconType = "0";
            }
            IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("DEVICE_CODE"),map.get("JD84"),map.get("WD84"),layerid,IconType);
            iconTypeLoadMap_list.add(iconTypeLoadMap);
        }
        return iconTypeLoadMap_list;
    }


    public List<IconTypeLoadMap> iconTypeLoadMapByIotStreet(String tableName,String layerid,String column,String columnEntity,String StreetCode) {
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<IconTypeLoadMap>();
        String IconType;
        String date_current = LoadMyUtil.getMyTime("DATE",0);
        String sql = "select DEVICE_CODE from IOT_EVENT where to_char(PRODUCE_TIME,'yyyy-mm-dd hh24:mi:ss') like concat('"+date_current+"','%')";
        List<LinkedHashMap> list_event_alarm = oracleOperateService.querySql(sql);
        Set set = new HashSet<>();
        list_event_alarm.stream().forEach(u->set.add(u.get("DEVICE_CODE")));

        String sql_iot_loadmap = "select DEVICE_CODE,JD84,WD84 from " + tableName + " where "  + column + "='"+ columnEntity + "' and REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        List<LinkedHashMap> list_iot_loadmap = oracleOperateService.querySql(sql_iot_loadmap);
        for (LinkedHashMap<String, String> map : list_iot_loadmap) {
            if (set.contains(map.get("DEVICE_CODE"))) {
                IconType = "1";
            } else {
                IconType = "0";
            }
            IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("DEVICE_CODE"),map.get("JD84"),map.get("WD84"),layerid,IconType);
            iconTypeLoadMap_list.add(iconTypeLoadMap);
        }
        return iconTypeLoadMap_list;
    }

    public List<IconTypeLoadMap> iconTypeLoadMapByIotID(String tableName,String layerid,String id) {
        tableName = tableName.toUpperCase();
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<IconTypeLoadMap>();
        String sql_iot_loadmap = "select DEVICE_CODE,JD84,WD84 from " + tableName + " where DEVICE_CODE = '"+id+"'";
        List<LinkedHashMap> list_iot_loadmap = oracleOperateService.querySql(sql_iot_loadmap);
        for (LinkedHashMap<String, String> map : list_iot_loadmap) {
            IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("DEVICE_CODE"),map.get("JD84"),map.get("WD84"),layerid,"0");
            iconTypeLoadMap_list.add(iconTypeLoadMap);
        }
        return iconTypeLoadMap_list;
    }


}
