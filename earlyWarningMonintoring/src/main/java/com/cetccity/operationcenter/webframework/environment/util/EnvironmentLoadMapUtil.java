package com.cetccity.operationcenter.webframework.environment.util;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_DICTIONARYMapper;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_FACILITY_INFOMapper;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_SEWERAGE_INFOMapper;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_DICTIONARY;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_FACILITY_INFO;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_SEWERAGE_INFO;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapAttribute;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.util
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:14 2019-05-21
 * Updater:     heliangming
 * Update_Date：18:14 2019-05-21
 * 更新描述:    heliangming 补充
 **/
@Component
public class EnvironmentLoadMapUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    QJHH_SEWERAGE_INFOMapper qJHH_SEWERAGE_INFOMapper;

    @Autowired
    QJHH_FACILITY_INFOMapper qJHH_FACILITY_INFOMapper;

    @Autowired
    QJHH_DICTIONARYMapper qJHH_DICTIONARYMapper;

    public List<LoadMap> LoadMapToDrainHold(String tableNameUrl, String street, String id) {
        String currentMonth = LoadMyUtil.getMyTime("MONTH", 0);
        List<LoadMap> loadMapList = new ArrayList<>();
        String value = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName + "." + tableNameUrl);
        String tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
        String layerid = value.substring(0, value.indexOf("#"));
        String column = value.substring(value.indexOf("#") + 1, value.indexOf("$"));
        String columnEntity = value.substring(value.indexOf("$") + 1, value.length());
        Map map = new HashMap();
        map.put("STREET_CODE", StringUtils.isEmpty(street) ? null : LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0]);
        map.put("UID", id);
        map.put(column, columnEntity);
        List<QJHH_SEWERAGE_INFO> list = qJHH_SEWERAGE_INFOMapper.loadMap(map);
        list.stream().filter(u -> StringUtils.isNotEmpty(u.getJD84()) || StringUtils.isNotEmpty(u.getWD84()))
                .collect(Collectors.toList()).stream().forEach(u ->
                loadMapList.add(LoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84()).build())
        );
        return loadMapList;
    }

    public List<IconTypeLoadMap> LoadMapDrainFacilities(String tableNameUrl, String street, String id, String patrolType) {
        String lastDay = LoadMyUtil.getMyTime("DATE", -30) + " 00:00:00";
        String currentDay = LoadMyUtil.getMyTime("DATE", 0) + " 23:59:59";
        List<IconTypeLoadMap> iconTypeLoadMapList = new ArrayList<>();
        String tableName = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("tableName");
        String layerid = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("layerid");
        String column = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("column");
        String columnEntity = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("columnEntity");
        Map map = new HashMap();
        map.put("STREET_CODE", StringUtils.isEmpty(street) ? null : LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0]);
        map.put("SEWERAGE_ID", id);
        map.put(column, columnEntity);
        if(StringUtils.isEmpty(patrolType)) {
            map.put("lastDay", lastDay);
            map.put("currentDay", currentDay);
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            String iconType;
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                if ("不正常".equals(u.getRISK())) {
                    iconType = "0";   //隐患
                } else if ("1".equals(u.getSTATUS())) {
                    iconType = "1";   //正常
                } else {
                    iconType = "2";   //废弃
                }
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType(iconType).build());
            }
        }else if("1".equals(patrolType)){
            map.put("lastDay", lastDay);
            map.put("currentDay", currentDay);
            map.put("patrolStatus", "正常"); //近一个月的正常巡查
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType("1").build());
            }
        }else if("2".equals(patrolType)){
            map.put("lastDay", lastDay);
            map.put("currentDay", currentDay);
            map.put("patrolStatus", "不正常");    //近一个月的隐患巡查
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType("0").build());
            }
        }else if("3".equals(patrolType)){
            map.put("facilitiesStatus", "1");//所有的正常设施
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType("1").build());
            }
        }else if("4".equals(patrolType)){
            map.put("facilitiesStatus", "2");//所有的废弃设施
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType("2").build());
            }
        }else if("5".equals(patrolType)){
            map.put("patrolStatus", "不正常"); //所有的隐患巡查
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType("0").build());
            }
        }
        return iconTypeLoadMapList;
    }

    public List<IconTypeLoadMap> LoadMapDrainFacilitiesAll(String tableNameUrl, String street, String facilitiesType) throws IOException {
        List<IconTypeLoadMap> iconTypeLoadMapList = new ArrayList<>();
        String tableName = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("tableName");
        String layerid = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("layerid");
        String column = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("column");
        String columnEntity = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("columnEntity");
        Map map = new HashMap();
        map.put("STREET_CODE", StringUtils.isEmpty(street) ? null : LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0]);
        map.put(column, columnEntity);
        if("1".equals(facilitiesType)) {
            map.put("facilitiesStatus", "1");
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            String iconType;
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                if ("不正常".equals(u.getRISK())) {
                    iconType = "0";
                } else if ("1".equals(u.getSTATUS())) {
                    iconType = "1";
                } else {
                    iconType = "2";
                }
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType(iconType).build());
            }
        }else if(facilitiesType.equals("1")){
            map.put("status", "正常");
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType("1").build());
            }
        }else {
            map.put("status", "不正常");
            List<QJHH_FACILITY_INFO> list = qJHH_FACILITY_INFOMapper.loadMap(map);
            for (QJHH_FACILITY_INFO u : list) {
                if (StringUtils.isEmpty(u.getJD84()) || StringUtils.isEmpty(u.getWD84())) continue;
                iconTypeLoadMapList.add(IconTypeLoadMap.builder().tableName(tableName).id(u.getUID())
                        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84())
                        .iconType("0").build());
            }
        }
        return iconTypeLoadMapList;
    }
}