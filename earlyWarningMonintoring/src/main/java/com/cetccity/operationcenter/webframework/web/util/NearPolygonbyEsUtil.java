package com.cetccity.operationcenter.webframework.web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHits2Model;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHitsModel;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticSearchModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.model.incident.Location;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyLocationModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * ES多边形GeoJSON检索
 */
@Slf4j
public class NearPolygonbyEsUtil {
    public static List<LoadMap> nearByEs(String geoJson, String tableName, String layerId){
        ObjectMapper objectMappers = new ObjectMapper();
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        try {
            Map map = objectMappers.readValue(geoJson, Map.class);
            String rings = map.get("rings").toString();
            //去掉首尾字符--截取(1,rings.length()-1)个字符
            String geoJsonStr = rings.substring(1,rings.length()-1);
        String urlStr = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/"+tableName+"@31project_april/_doc/_search";
        String json = "{\n" +
                "  \"from\":\"0\",\n" +
                "  \"size\":\"9999\",\n" +
                "  \"_source\":[\"UUID\",\"location\"],\n" +
                "  \"query\": {\n" +
                "        \"bool\" : {\n" +
                "            \"must\" : {\n" +
                "                \"match_all\" : {}\n" +
                "            },\n" +
                "            \"filter\" : {\n" +
                "                \"geo_polygon\" : {\n" +
                "                    \"location\" : {\n" +
                "                        \"points\" : \n" +
                "            "+geoJsonStr+"\n" +
                "                        \n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String source = UrlApiToSource.doJsonPost(urlStr,json);
        /**
         *json转为实体
         */
        ElasticSearchModel elasticSearchModel = JsonUtils.jsonToBean(source,ElasticSearchModel.class);
        String hits = elasticSearchModel.getHits().toString();
        ElasticHitsModel elasticHitsModel = JsonUtils.jsonToBean(hits,ElasticHitsModel.class);
        String hits2 = elasticHitsModel.getHits().toString();
        /**
         *json转为List
         */
        List<ElasticHits2Model> elasticHits2Modellist = JsonUtils.jsonToList(hits2,ElasticHits2Model.class);
        for (ElasticHits2Model elasticHits2Model:elasticHits2Modellist) {
            LoadMap loadMap = new LoadMap();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String id = elasticHits2Model.get_id();
                String str = objectMapper.writeValueAsString(elasticHits2Model.get_source());
                NearbyLocationModel nearbyLocationModel = JsonUtils.jsonToBean(str,NearbyLocationModel.class);
                Location location = JsonUtils.jsonToBean(nearbyLocationModel.getLocation().toString(),Location.class);
                loadMap.setId(id);
                loadMap.setTableName(tableName);
                loadMap.setLayerid(layerId);
                loadMap.setJd(location.getLon());
                loadMap.setWd(location.getLat());
                loadMapList.add(loadMap);
            } catch (JsonProcessingException e) {
            	log.error(e.toString());
            }
        }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return loadMapList;
    }
}
