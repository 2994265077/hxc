package com.cetccity.operationcenter.webframework.search.service.impl;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.search.service.PolygonSearchService;
import com.cetccity.operationcenter.webframework.search.utils.SearchUtils;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:34 2019-03-28
 * Updater:     heliangming
 * Update_Date：10:34 2019-03-28
 * 更新描述:    heliangming 补充
 **/
@Service
public class PolygonSearchServiceImpl implements PolygonSearchService {

	@Autowired
    private CommonInstance commonInstance;

    //多边形搜索
    public List<NearbyResourcesModel> findPolygonResourceObj(String[] tableName_Obj, String poiJson) throws IOException{
        List<NearbyResourcesModel> nearbyResourcesModelList = new ArrayList<NearbyResourcesModel>();
        for (String tableName : tableName_Obj) {
            //NearbyResourcesModel nearbyResourcesModel = new NearbyResourcesModel();
            //List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
            String layerid,urlStr,json;
            String columnEntity =null;
            String value = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName + "." + tableName);
            if (tableName.contains("@")) {
                columnEntity = value.substring(value.indexOf("$") + 1, value.length());
                layerid = value.substring(0, value.indexOf("#"));
            }else{
                layerid = value;
            }
            tableName = tableName.toLowerCase();
            if(tableName.contains("@")){
                tableName = tableName.substring(0, tableName.indexOf("@"));
                urlStr = "http://" + commonInstance.getElasticsearchIp() + ":" + commonInstance.getElasticsearchPort() + "/" + tableName + "@zhftyjjcpt/_doc/_search";
                json = "{\n" +
                        "  \"from\":\"0\",\n" +
                        "  \"size\":\"9999\",\n" +
                        "  \"_source\":[\"location\",\"NAME\",\"ADDRESS\",\"REGION_CODE\",\"CAMERAID\",\"ID\",\"DEVICE_CODE\",\"BUILDID\",\"LOAD_MAP_TAG_LV2\"],\n" +
                        "  \"query\": {\n" +
                        "        \"bool\" : {\n" +
                        "             \"must\":[\n" +
                        "              {\"match_phrase\":{\"LOAD_MAP_TAG_LV2\":\""+columnEntity+"\"}}\n" +
                        "             ],\n" +
                        "            \"filter\" : {\n" +
                        "                \"geo_polygon\" : {\n" +
                        "                    \"location\" : {\n" +
                        "                        \"points\" : "+poiJson+"\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
            }else {
                urlStr = "http://" + commonInstance.getElasticsearchIp() + ":" + commonInstance.getElasticsearchPort() + "/" + tableName + "@zhftyjjcpt/_doc/_search";
                json = "{\n" +
                        "  \"from\":\"0\",\n" +
                        "  \"size\":\"9999\",\n" +
                        "  \"_source\":[\"location\",\"CAMERAID\",\"ID\",\"BUILDID\"],\n" +
                        "  \"query\": {\n" +
                        "        \"bool\" : {\n" +
                        "             \"must\":{\n" +
                        "             \"match_all\" : {}\n" +
                        "             },\n" +
                        "            \"filter\" : {\n" +
                        "                \"geo_polygon\" : {\n" +
                        "                    \"location\" : {\n" +
                        "                        \"points\" : "+poiJson+"\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
            }
            NearbyResourcesModel nearbyResourcesModel = SearchUtils.handleSearchNearbyResources(urlStr,json,tableName,layerid);
            if(nearbyResourcesModel.getCount()==0) {
                continue;
            }
            nearbyResourcesModelList.add(nearbyResourcesModel);
        }
        return nearbyResourcesModelList;
    }

}
