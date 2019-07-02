package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

//import com.cetccity.operationcenter.webframework.web.config.WebSocketServer;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.model.KdxfOrderModel;
import com.cetccity.operationcenter.webframework.web.service.LoadMapService;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@CacheConfig(cacheNames = "loadMap")
public class LoadMapUtil {

    public static KdxfOrderModel kdxfOrderModel = new KdxfOrderModel();

    public static List<LoadMap> loadMapList_kdxf = new ArrayList<LoadMap>();

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    LoadMapService loadMapService;

    public static ObjectMapper objectMapper = new ObjectMapper();

    @Cacheable
    public List<LoadMap> loadMapByDB(String tableName,String layerid,String id) {
        tableName = tableName.toUpperCase();
        String sql;
        if("T_BUILD_INFO_V".equals(tableName)) {
            sql = "select BUILDID,JD84,WD84 from " + tableName + " where BUILDID = '"+id+"'";
        }else{
            sql = "select ROWID,JD84,WD84 from " + tableName + " where ROWID = '"+id+"'";
        }
        return loadMapByDBForSql(tableName,layerid,sql);
    }

    @Cacheable
    public List<LoadMap> loadMapByDB(String tableName,String layerid) {
        tableName = tableName.toUpperCase();
        String sql;
        if("IOT_DEVICE".equals(tableName)) {
            sql = "select DEVICE_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304'";
        }else if("QHSJ_T_AIR_BASICINFO".equals(tableName)||"QHSJ_SURF_WATER_BASICINFO".equals(tableName)){
            sql = "select SITE_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304'";
        }else if("QHSJ_ENTERPRISE_BASICINFO".equals(tableName)){
            sql = "select PSCODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304'";
        }else if("VIDEO_POLICE".equals(tableName)){
            sql = "select GB_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304'";
        }else if("YJJC_QWJJ_ORG_V".equals(tableName)){
            sql = "select distinct ORG_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304'";
        } else {
            sql = "select ROWID,JD84,WD84 from " + tableName + " where REGION_CODE = '440304'";
        }
        return loadMapByDBForSql(tableName,layerid,sql);
    }

    //重写
    @Cacheable
    public List<LoadMap> loadMapByDB(String tableName,String layerid,String column,String columnEntity) {
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        String sql;
        if("IOT_DEVICE".equals(tableName)){
            sql = "select DEVICE_CODE,JD84,WD84 from "+tableName+" where "+column+" ='"+columnEntity +"' and REGION_CODE = '440304'";
        }else if(tableName.contains("VIDEO_POLICE")){
            sql = "select a.GB_CODE,a.GB_CODE,a.JD84,a.WD84,b.TAG \n" +
                    "from VIDEO_POLICE a,VIDEO_TAG b  \n" +
                    "where a.GB_CODE = b.GB_CODE\n" +
                    "and b.TAG = '"+columnEntity+"'";
        }else {
            sql = "select ROWID,JD84,WD84 from " + tableName + " where " + column + "='" + columnEntity + "' and REGION_CODE = '440304'";
        }
        return loadMapByDBForSql(tableName,layerid,sql);
    }

    @Cacheable
    public List<LoadMap> loadMapByDBForStreet(String tableName,String layerid,String StreetCode) {
        tableName = tableName.toUpperCase();
        String sql;
        if("IOT_DEVICE".equals(tableName)){
            sql = "select DEVICE_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }else if("QHSJ_T_AIR_BASICINFO".equals(tableName)||"QHSJ_SURF_WATER_BASICINFO".equals(tableName)){
            sql = "select SITE_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }else if("QHSJ_ENTERPRISE_BASICINFO".equals(tableName)){
            sql = "select PSCODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }else if("VIDEO_POLICE".equals(tableName)){
            sql = "select GB_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }else if("YJJC_QWJJ_ORG_V".equals(tableName)){
            sql = "select distinct ORG_CODE,JD84,WD84 from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        } else {
            sql = "select ROWID,JD84,WD84 from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }
        return loadMapByDBForSql(tableName,layerid,sql);
    }

    //重写
    @Cacheable
    public List<LoadMap> loadMapByDBForStreet(String tableName,String layerid,String column,String columnEntity,String StreetCode) {
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        String sql;
        if("IOT_DEVICE".equals(tableName)){
            sql = "select DEVICE_CODE,JD84,WD84 from " + tableName + " where " + column + "='" + columnEntity + "' and REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }else {
            sql = "select ROWID,JD84,WD84 from " + tableName + " where " + column + "='" + columnEntity + "' and REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }
        return loadMapByDBForSql(tableName,layerid,sql);
    }

    List<LoadMap> loadMapByDBForSql(String tableName,String layerid,String sql){
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        String id;
        List<LinkedHashMap> list = oracleOperateService.querySql(sql);
        for (LinkedHashMap<String, String> map:list) {
            if("IOT_DEVICE".equals(tableName)){
                id = map.get("DEVICE_CODE");
            }else if("QHSJ_T_AIR_BASICINFO".equals(tableName)||"QHSJ_SURF_WATER_BASICINFO".equals(tableName)){
                id = map.get("SITE_CODE");
            } else if ("QHSJ_ENTERPRISE_BASICINFO".equals(tableName)) {
                id = map.get("PSCODE");
            } else if("VIDEO_POLICE".equals(tableName)){
                id = map.get("GB_CODE");
            } else if("YJJC_QWJJ_ORG_V".equals(tableName)){
                id = map.get("ORG_CODE");
            }else if("T_BUILD_INFO_V".equals(tableName)){
                id = map.get("BUILDID");
            }else {
                id = map.get("ROWID");
            }
            LoadMap loadMap = getLoadMap(tableName, id, map.get("JD84"), map.get("WD84"), layerid);
            loadMapList.add(loadMap);
        }
        return loadMapList;
    }

    @Cacheable
    public List<LoadMap> loadMapRemovalByDb(String tableName,String layerid) {
        tableName = tableName.toUpperCase();
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        String sql = "select ORG_NAME,ORG_CODE,JD84,WD84 from "+tableName+" where REGION_CODE = '440304'";
        List<LinkedHashMap> list = oracleOperateService.querySql(sql);
        Map<String,String> Removalmap = new HashMap();
        for (LinkedHashMap<String, String> map:list) {
            if (Removalmap.get(map.get("ORG_NAME")) == null) {
                Removalmap.put(map.get("ORG_NAME"),map.get("ORG_CODE"));
                String sql_removal = "select ORG_CODE,JD84,WD84 from "+tableName+" where ORG_CODE = '"+map.get("ORG_CODE")+"'";
                List<LinkedHashMap> list_removal = oracleOperateService.querySql(sql_removal);
                LoadMap loadMap = getLoadMap(tableName, map.get("ORG_CODE"), (String)list_removal.get(0).get("JD84"), (String)list_removal.get(0).get("WD84"), layerid);
                loadMapList.add(loadMap);
            }
        }
        return loadMapList;
    }

    @Cacheable
    public List<LoadMap> loadMapRemovalByDb(String tableName,String layerid,String column,String columnEntity) {
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        String sql = "select ORG_NAME,ORG_CODE,JD84,WD84 from "+tableName+" where "+column+"='"+columnEntity+"' and REGION_CODE = '440304'";
        List<LinkedHashMap> list = oracleOperateService.querySql(sql);
        Map<String,String> Removalmap = new HashMap();
        for (LinkedHashMap<String, String> map:list) {
            if (Removalmap.get(map.get("ORG_NAME")) == null) {
                Removalmap.put(map.get("ORG_NAME"),map.get("ORG_CODE"));
                String sql_removal = "select ORG_CODE,JD84,WD84 from "+tableName+" where ORG_CODE = '"+map.get("ORG_CODE")+"'";
                List<LinkedHashMap> list_removal = oracleOperateService.querySql(sql_removal);
            LoadMap loadMap = getLoadMap(tableName, map.get("ORG_CODE"), (String)list_removal.get(0).get("JD84"), (String)list_removal.get(0).get("WD84"), layerid);
            loadMapList.add(loadMap);
        }
        }
        return loadMapList;
    }

    LoadMap getLoadMap(String tableName, String id,String longitude,String latitude,String layerid){
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

        LoadMap loadMap = new LoadMap();
        loadMap.setTableName(tableName);
        loadMap.setId(id);
        loadMap.setJd(map_point.get("jd84"));
        loadMap.setWd(map_point.get("wd84"));
        loadMap.setLayerid(layerid);
        return loadMap;
    }

    /*public List<LoadMap> loadMapByKdxfDBVedio(String tableName,String layerid,String videoMsg) {
        tableName = tableName.toUpperCase();
        int i = 1;
        String jd84,wd84;
        String sql = "select GB_CODE from VIDEO_TAG where TAG like '%" +videoMsg +"%'";
        List<LinkedHashMap> list_cameraid = oracleOperateService.querySql(sql);
        Map map = new HashMap();
        loadMapList_kdxf = new ArrayList<>();
        for (LinkedHashMap map_cameraid:list_cameraid) {
            map.put(i,map_cameraid.get("GB_CODE"));
            i++;
        }
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            LoadMap loadMap = new LoadMap();
            Map.Entry<String, String> entry = entries.next();
            String video_code = entry.getValue();
            List<LoadMap> loadMap_list_code = loadMapService.loadMapByVideoCodeDB(video_code);
            if (loadMap_list_code.get(0).getJd() == null || "".equals(loadMap_list_code.get(0).getJd())) {
                jd84 = "0.0";
            } else {
                jd84 = loadMap_list_code.get(0).getJd();
            }
            if (loadMap_list_code.get(0).getWd() == null || "".equals(loadMap_list_code.get(0).getWd())) {
                wd84 = "0.0";
            } else {
                wd84 = loadMap_list_code.get(0).getWd();
            }
            loadMap.setId(loadMap_list_code.get(0).getId());
            loadMap.setJd(jd84);
            loadMap.setWd(wd84);
            loadMap.setTableName(tableName);
            loadMap.setLayerid(layerid);
            loadMapList_kdxf.add(loadMap);
        }
        kdxfOrderModel.setVoiceMsg(videoMsg);
        kdxfOrderModel.setType("loadMap");
        kdxfOrderModel.setLayerid("layer_keyinformation_realtimevideo_yuyinshipin");
        try {
            WebSocketServer.sendInfo(JsonUtils.objectToJson(kdxfOrderModel));
        }catch (IOException e) {
        }
        return loadMapList_kdxf;
    }*/

    //重写
    public List<IconTypeLoadMap> iconTypeLoadMapByDB(String tableName,String layerid,String column,String columnEntity) {
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<IconTypeLoadMap>();
        if("URBAN_RISK".equals(tableName)) {
            String RISK_ASSESS_LV;
            String sql = "select ROWID,JD84,WD84,RISK_ASSESS_LV from " + tableName + " where " + column + "='" + columnEntity + "' and REGION_CODE = '440304'";
            List<LinkedHashMap> list = oracleOperateService.querySql(sql);
            for (LinkedHashMap<String, String> map : list) {
                if (map.get("RISK_ASSESS_LV") == null || "".equals(map.get("RISK_ASSESS_LV"))) {
                    RISK_ASSESS_LV = "3";
                } else if ("4".equals(map.get("RISK_ASSESS_LV"))){
                    continue;
                } else {
                    RISK_ASSESS_LV = map.get("RISK_ASSESS_LV");
                }
                IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("ROWID"),map.get("JD84"),map.get("WD84"),layerid,RISK_ASSESS_LV);
                iconTypeLoadMap_list.add(iconTypeLoadMap);
            }
        }else if("BLK_SANXIAO_PLACE".equals(tableName)){
            String IconType;
            String sanxiao_place_sql = "select ID,HAS_TROUBLE,JD84,WD84 from " + tableName + " where "  + column + "='"+ columnEntity + "' and REGION_CODE = '440304'";
            List<LinkedHashMap> sanxiao_place_loadmap = oracleOperateService.querySql(sanxiao_place_sql);
            for (LinkedHashMap<String, String> map : sanxiao_place_loadmap) {
                if("1".equals(map.get("HAS_TROUBLE"))){
                    IconType = "1";
                } else{
                    IconType = "0";
                }
                IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("ID"),map.get("JD84"),map.get("WD84"),layerid,IconType);
                iconTypeLoadMap_list.add(iconTypeLoadMap);
            }
        }
        return iconTypeLoadMap_list;
    }

    @Cacheable
    public List<IconTypeLoadMap> iconTypeLoadMapByDBStreet(String tableName, String layerid, String column, String columnEntity, String StreetCode) {
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<IconTypeLoadMap>();
        if("URBAN_RISK".equals(tableName)) {
            String RISK_ASSESS_LV = "3";
            String sql = "select ROWID,JD84,WD84,RISK_ASSESS_LV from " + tableName + " where " + column + "='" + columnEntity + "' and REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
            List<LinkedHashMap> list = oracleOperateService.querySql(sql);
            for (LinkedHashMap<String, String> map : list) {
                if (map.get("RISK_ASSESS_LV") == null || "".equals(map.get("RISK_ASSESS_LV"))) {
                    RISK_ASSESS_LV = "3";
                } else if ("4".equals(map.get("RISK_ASSESS_LV"))){
                    continue;
                } else {
                    RISK_ASSESS_LV = map.get("RISK_ASSESS_LV");
                }
                IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("ROWID"),map.get("JD84"),map.get("WD84"),layerid,RISK_ASSESS_LV);
                iconTypeLoadMap_list.add(iconTypeLoadMap);
            }
        }else if("BLK_SANXIAO_PLACE".equals(tableName)){
            String IconType;
            String sanxiao_place_sql = "select ID,HAS_TROUBLE,JD84,WD84 from " + tableName + " where "  + column + "='"+ columnEntity + "' and REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
            List<LinkedHashMap> sanxiao_place_loadmap = oracleOperateService.querySql(sanxiao_place_sql);
            for (LinkedHashMap<String, String> map : sanxiao_place_loadmap) {
                if("0".equals(map.get("HAS_TROUBLE"))){
                    IconType = "0";
                } else{
                    IconType = "1";
                }
                IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("ID"),map.get("JD84"),map.get("WD84"),layerid,IconType);
                iconTypeLoadMap_list.add(iconTypeLoadMap);
            }
        }
        return iconTypeLoadMap_list;
    }

    public List<IconTypeLoadMap> iconTypeLoadMapByDBID(String tableName,String layerid,String id) {
        tableName = tableName.toUpperCase();
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<IconTypeLoadMap>();
        if("URBAN_RISK".equals(tableName)) {
            String RISK_ASSESS_LV;
            String sql = "select ROWID,JD84,WD84,RISK_ASSESS_LV from " + tableName + " where ROWID = '"+id+"'";
            List<LinkedHashMap> list = oracleOperateService.querySql(sql);
            for (LinkedHashMap<String, String> map : list) {
                if (map.get("RISK_ASSESS_LV") == null || "".equals(map.get("RISK_ASSESS_LV"))) {
                    RISK_ASSESS_LV = "3";
                } else if ("4".equals(map.get("RISK_ASSESS_LV"))){
                    continue;
                } else {
                    RISK_ASSESS_LV = map.get("RISK_ASSESS_LV");
                }
                IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("ROWID"),map.get("JD84"),map.get("WD84"),layerid,RISK_ASSESS_LV);
                iconTypeLoadMap_list.add(iconTypeLoadMap);
            }
        }else if("BLK_SANXIAO_PLACE".equals(tableName)){
            String sanxiao_place_sql = "select ID,HAS_TROUBLE,JD84,WD84 from " + tableName + " where ID = '"+id+"'";
            List<LinkedHashMap> sanxiao_place_loadmap = oracleOperateService.querySql(sanxiao_place_sql);
            for (LinkedHashMap<String, String> map : sanxiao_place_loadmap) {
                IconTypeLoadMap iconTypeLoadMap = LoadMapAttribute.geticonTypeLoadMap(tableName,map.get("ID"),map.get("JD84"),map.get("WD84"),layerid,"0");
                iconTypeLoadMap_list.add(iconTypeLoadMap);
            }
        }
        return iconTypeLoadMap_list;
    }

}
