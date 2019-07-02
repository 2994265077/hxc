package com.cetccity.operationcenter.webframework.web.util;

import static com.cetccity.operationcenter.webframework.core.tools.ESOperate.extractQueryResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.model.incident.LoadMapEs;
import com.cetccity.operationcenter.webframework.web.model.incident.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ScrollSplicingUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static List<LoadMap> loadMapByEs(String source,String tableName,String layerid){
        List<LinkedHashMap> linkedHashMaps = new ArrayList<LinkedHashMap>();
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        LinkedHashMap map = null;
        try {
            map = objectMapper.readValue(source, LinkedHashMap.class);
        } catch (IOException e) {
            log.error(e.toString());
        }
        List<LinkedHashMap> temp =extractQueryResult(map);
        linkedHashMaps.addAll(temp);
        /*移动游标，直到取完所有数据*/
        while(true){
            /*获取到游标*/
            String scroll_id = (String) map.get("_scroll_id");
            String body="{\n" +
                    "\t\"scroll\": \"1m\", \n" +
                    "    \"scroll_id\" : \""+scroll_id+"\"\n" +
                    "}";
            String urlStr2 = "http://"+ CommonStaticInstance.elasticsearchIp+":"+ CommonStaticInstance.elasticsearchPort+"/_search/scroll";
            String result = UrlApiToSource.doJsonPost(urlStr2, body);
            try {
                map = objectMapper.readValue(result, LinkedHashMap.class);
            } catch (IOException e) {
                log.error(e.toString());
            }
            temp = extractQueryResult(map);
            if (temp.size()<=0)break;
            linkedHashMaps.addAll(temp);
        }
        for(int i=0 ;i<linkedHashMaps.size();i++){
            LoadMap loadMap = new LoadMap();
            String str = JSON.toJSONString(linkedHashMaps.get(i));//list转成json
            LoadMapEs loadMapEs = JsonUtils.jsonToBean(str, LoadMapEs.class);
            Location location = JsonUtils.jsonToBean(loadMapEs.getLocation().toString(), Location.class);
            loadMap.setTableName(tableName);
            loadMap.setId(loadMapEs.getId());
            loadMap.setJd(location.getLon());
            loadMap.setWd(location.getLat());
            loadMap.setLayerid(layerid);
            loadMapList.add(loadMap);
        }
        return loadMapList;
    }

    //去重
    public static List<LoadMap> loadMapRemovalByEs(String source,String tableName,String layerid){
        List<LinkedHashMap> linkedHashMaps = new ArrayList<LinkedHashMap>();
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        LinkedHashMap map = null;
        try {
            map = objectMapper.readValue(source, LinkedHashMap.class);
        } catch (IOException e) {
            log.error(e.toString());
        }
        List<LinkedHashMap> temp =extractQueryResult(map);
        linkedHashMaps.addAll(temp);
        /*移动游标，直到取完所有数据*/
        while(true){
            /*获取到游标*/
            String scroll_id = (String) map.get("_scroll_id");
            String body="{\n" +
                    "\t\"scroll\": \"1m\", \n" +
                    "    \"scroll_id\" : \""+scroll_id+"\"\n" +
                    "}";
            String urlStr2 = "http://"+ CommonStaticInstance.elasticsearchIp+":"+ CommonStaticInstance.elasticsearchPort+"/_search/scroll";
            String result = UrlApiToSource.doJsonPost(urlStr2, body);
            try {
                map = objectMapper.readValue(result, LinkedHashMap.class);
            } catch (IOException e) {
                log.error(e.toString());
            }
            temp = extractQueryResult(map);
            if (temp.size()<=0)break;
            linkedHashMaps.addAll(temp);
        }
        Map<String, String> Removalmap = new HashMap();

        for(int i=0 ;i<linkedHashMaps.size();i++){
            LoadMap loadMap = new LoadMap();
            String str = JSON.toJSONString(linkedHashMaps.get(i));//list转成json
            Gson gson = new Gson();
            Map mapStr = new HashMap();
            mapStr = gson.fromJson(str, mapStr.getClass());
            if (Removalmap.get(mapStr.get("org_code")) == null) {
                String lon = (String) ((Map)mapStr.get("location")).get("lon");
                String lat = (String) ((Map)mapStr.get("location")).get("lat");
                Removalmap.put((String)mapStr.get("org_code"), (String)mapStr.get("org_name"));
                loadMap.setTableName(tableName);
                loadMap.setId((String)mapStr.get("org_code"));
                loadMap.setJd(lon);
                loadMap.setWd(lat);
                loadMap.setLayerid(layerid);
                loadMapList.add(loadMap);
            }
        }
        return loadMapList;
    }
}
