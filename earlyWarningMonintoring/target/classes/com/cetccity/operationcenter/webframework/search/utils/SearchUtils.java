package com.cetccity.operationcenter.webframework.search.utils;

import com.alibaba.fastjson.JSON;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.search.api.model.NearSearchLoadMap;
import com.cetccity.operationcenter.webframework.web.model.ElasticSearchObj;
import com.cetccity.operationcenter.webframework.web.model.ElasticSearchObjContent;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.utils
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:49 2019-03-28
 * Updater:     heliangming
 * Update_Date：10:49 2019-03-28
 * 更新描述:    heliangming 补充
 **/
@UtilityClass
public class SearchUtils {

    public NearbyResourcesModel handleSearchNearbyResources(String urlStr, String json, String tableName, String layerid){
        NearbyResourcesModel nearbyResourcesModel = new NearbyResourcesModel();
        List<NearSearchLoadMap> loadMap_list = new ArrayList<>();
        String source = UrlApiToSource.doJsonPost(urlStr, json);
        Map<String, String> map_source = (Map) JSON.parse(source);
        String source_hits = JsonUtils.objectToJson(map_source.get("hits"));
        ElasticSearchObj elasticSearchObj = JsonUtils.jsonToBean(source_hits, ElasticSearchObj.class);
        //数据总数
        Integer total = Integer.valueOf(elasticSearchObj.getTotal());
        String tableName_all;

        //实体内容
        String hits_con = elasticSearchObj.getHits().toString();
        List<ElasticSearchObjContent> elasticSearchObjContent_list;
        Map<String, String> map_loaction;
        if(!"[]".equals(hits_con)) {
            elasticSearchObjContent_list = JsonUtils.jsonToList(hits_con, ElasticSearchObjContent.class);
            map_loaction = (Map) JSON.parse(JsonUtils.objectToJson(elasticSearchObjContent_list.get(0).get_source()));
            if (StringUtils.isEmpty(map_loaction.get("LOAD_MAP_TAG_LV2"))) {
                tableName_all = tableName.toUpperCase();
            } else {
                tableName_all = tableName.toUpperCase() + "@" + map_loaction.get("LOAD_MAP_TAG_LV2");
            }
            for (ElasticSearchObjContent elasticSearchObjContent : elasticSearchObjContent_list) {
                map_loaction = (Map) elasticSearchObjContent.get_source();
                Map<String, String> point = (Map) JSON.parse(JsonUtils.objectToJson(map_loaction.get("location")));
                NearSearchLoadMap loadMap = new NearSearchLoadMap();
                loadMap.setLayerid(layerid);
                if ("video_police".equals(tableName)) {
                    loadMap.setId(map_loaction.get("CAMERAID"));
                } else if ("blk_sanxiao_place".equals(tableName)) {
                    loadMap.setId(map_loaction.get("ID"));
                } else if ("iot_device".equals(tableName)) {
                    loadMap.setId(map_loaction.get("DEVICE_CODE"));
                } else if ("t_build_info_v".equals(tableName)) {
                    loadMap.setId(map_loaction.get("BUILDID"));
                } else {
                    loadMap.setId(elasticSearchObjContent.get_id());
                }
                loadMap.setTableName(tableName_all);
                loadMap.setJd(point.get("lon"));
                loadMap.setWd(point.get("lat"));
                loadMap.setName(map_loaction.get("NAME"));
                loadMap.setAddress(map_loaction.get("ADDRESS"));
                loadMap_list.add(loadMap);
            }
            nearbyResourcesModel.setObj_Name(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",tableName_all.toUpperCase()));
            nearbyResourcesModel.setCount(loadMap_list.size());
            nearbyResourcesModel.setTableName(tableName_all);
            nearbyResourcesModel.setLayerid(layerid);
            nearbyResourcesModel.setData(loadMap_list);
        } else {
            //continue;
        }
        return nearbyResourcesModel;
    }
}
