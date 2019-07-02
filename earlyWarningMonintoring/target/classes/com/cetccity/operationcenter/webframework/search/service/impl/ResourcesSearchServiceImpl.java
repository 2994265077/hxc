package com.cetccity.operationcenter.webframework.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.ListToPageInfoUtil;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import com.cetccity.operationcenter.webframework.search.service.ResourcesSearchService;
import com.cetccity.operationcenter.webframework.search.utils.SearchUtils;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.model.ElasticSearchObj;
import com.cetccity.operationcenter.webframework.web.model.ElasticSearchObjContent;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:27 2019-03-12
 * Updater:     heliangming
 * Update_Date：9:27 2019-03-12
 * 更新描述:    heliangming ES矩形GeoJSON检索
 **/
@Service
@Slf4j
public class ResourcesSearchServiceImpl implements ResourcesSearchService {
	@Autowired
	private CommonInstance commonInstance;

    @Autowired
    TipUtil tipUtil;

    //一级对象搜索（提供搜索内容）
    public MyPageInfoModel<List<SearchObjList>> searchByEsObjOne(String content, Integer page, Integer size,String tag)throws IOException {
        MyPageInfoModel<List<SearchObjList>> pageInfo = new MyPageInfoModel<List<SearchObjList>>();
        String json;
        String urlStr = "http://"+ commonInstance.getElasticsearchIp()+":"+ commonInstance.getElasticsearchPort()+"/_search";
        if(StringUtils.isEmpty(tag)) {
            json = "{\n" +
                    "\t\"from\":\"" + page + "\",\n" +
                    "    \"size\":\"" + size + "\",\n" +
                    "\t\"_source\":[\"NAME\",\"ADDRESS\",\"location\",\"CAMERAID\",\"ID\",\"DEVICE_CODE\",\"BUILDID\",\"LOAD_MAP_TAG_LV2\"],\n" +
                    "\t\"query\":{\n" +
                    "\t\t\"bool\":{\n" +
                    "\t\t\t\"must\":{\n" +
                    "\t\t\t\t\"multi_match\": {\n" +
                    "\t\t\t        \"query\":       \"" + content + "\",\n" +
                    "\t\t\t        \"type\":        \"most_fields\",\n" +
                    "\t\t\t        \"fields\":      [ \"NAME\",\"ADDRESS\" ]\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\t\"highlight\": {\n" +
                    "\t    \"fields\":{\n" +
                    "\t    \t\"*\":{}\n" +
                    "\t    }\n" +
                    "\t}\n" +
                    "}";
        }else{
            json = "{\n" +
                    "\t\"from\":\"0\",\n" +
                    "    \"size\":\"1000\",\n" +
                    "\t\"_source\":[\"location\",\"NAME\",\"ADDRESS\",\"REGION_CODE\",\"CAMERAID\",\"ID\",\"DEVICE_CODE\",\"BUILDID\",\"LOAD_MAP_TAG_LV2\"],\n" +
                    "\t\"query\":{\n" +
                    "\t\t\"bool\":{\n" +
                    "\t\t\t\"must\":[\n" +
                    "            \t{\"match_phrase\":{\n" +
                    "            \t\t\"CATEGORY\":\""+tag+"\"\n" +
                    "            \t}},\n" +
                    "\t\t\t\t{\"multi_match\": {\n" +
                    "\t\t\t        \"query\":       \""+content+"\",\n" +
                    "\t\t\t        \"type\":        \"most_fields\",\n" +
                    "\t\t\t        \"fields\":      [\"NAME\",\"ADDRESS\"]\n" +
                    "\t\t\t\t}}\n" +
                    "\t\t\t\t]\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\t\"highlight\": {\n" +
                    "\t    \"fields\":{\n" +
                    "\t    \t\"*\":{}\n" +
                    "\t    }\n" +
                    "\t}\n" +
                    "}";
        }
        String source = UrlApiToSource.doJsonPost(urlStr,json);
        Map<String,String> map_source = (Map) JSON.parse(source);
        String source_hits = JsonUtils.objectToJson(map_source.get("hits"));
        ElasticSearchObj elasticSearchObj = JsonUtils.jsonToBean(source_hits,ElasticSearchObj.class);
        //数据总数
        Integer total = Integer.valueOf(elasticSearchObj.getTotal());
        //实体内容
        String hits_con = elasticSearchObj.getHits().toString();
        List<ElasticSearchObjContent> elasticSearchObjContent_list = JsonUtils.jsonToList(hits_con,ElasticSearchObjContent.class);

        List<SearchObjList> searchObjList_list = new ArrayList<SearchObjList>();
        for (ElasticSearchObjContent elasticSearchObjContent:elasticSearchObjContent_list) {
            String id;
            Map<String,String> map_loaction = (Map) JSON.parse(JsonUtils.objectToJson(elasticSearchObjContent.get_source()));
            Map<String,String> point = (Map) JSON.parse(JsonUtils.objectToJson(map_loaction.get("location")));
            String name = map_loaction.get("NAME");
            String address = map_loaction.get("ADDRESS");
            String tableNameOne = elasticSearchObjContent.get_index().substring(0,elasticSearchObjContent.get_index().indexOf("@")).toUpperCase();
            String layeridOne;
            if("".equals(map_loaction.get("LOAD_MAP_TAG_LV2"))||map_loaction.get("LOAD_MAP_TAG_LV2")==null) {
                layeridOne = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName+"."+tableNameOne);
            }else{
                tableNameOne = tableNameOne+"@"+map_loaction.get("LOAD_MAP_TAG_LV2");
                layeridOne = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+tableNameOne).split("#")[0];
            }
            if("VIDEO_POLICE".equals(tableNameOne)){
                id = map_loaction.get("CAMERAID");
            }else if(tableNameOne.contains("BLK_SANXIAO_PLACE")){
                id = map_loaction.get("ID");
            }else if(tableNameOne.contains("IOT_DEVICE")){
                id = map_loaction.get("DEVICE_CODE");
            }else if("T_BUILD_INFO_V".equals(tableNameOne)){
                id = map_loaction.get("BUILDID");
            } else{
                id = elasticSearchObjContent.get_id();
            }
            SearchObjList searchObjList = new SearchObjList();
            searchObjList.setTag(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",tableNameOne));
            searchObjList.setName(name);
            searchObjList.setAddress(address);
            searchObjList.setTableName(tableNameOne);
            searchObjList.setLayerid(layeridOne);
            searchObjList.setId(id);
            searchObjList.setJd(point.get("lon"));
            searchObjList.setWd(point.get("lat"));
            //details部分
            List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
            String tableName;
            try {
                InputStream inputStream = ESOperate.class.getResourceAsStream("/tip.properties");
                Properties properties = new Properties();
                properties.load(new InputStreamReader(inputStream, "UTF-8"));
                String tableKey = properties.getProperty(ESOperate.dbName + "." + searchObjList.getTableName() + "Key");
                String tableValue = properties.getProperty(ESOperate.dbName + "." + searchObjList.getTableName() + "Value").toUpperCase();
                String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
                String[] value = tableValue.split(",");
                if (searchObjList.getTableName().contains("@")) {
                    tableName = searchObjList.getTableName().substring(0, searchObjList.getTableName().indexOf("@"));
                } else {
                    tableName = searchObjList.getTableName();
                }
                LinkedHashMap<String, String> map = tipUtil.loadMapTipToOracle(tableName, searchObjList.getId());  //请求oracle
                Gson gson = new Gson();
                List<String> result = new ArrayList();
                for (int i = 0; i < key.length; i++) {
                    if (i == 0) {
                        result.add(key[i]);
                        result.add(value[i]);
                    } else {
                        if (value[i].contains("*")) {
                            result.add(key[i]);
                            value[i] = value[i].substring(0, value[i].indexOf("*"));
                            String stu = map.get(value[i]);
                            String columnValueDesc = properties.getProperty(ESOperate.dbName + "." + tableName + "*" + value[i]);
                            if (columnValueDesc.contains(stu)) {
                                Map<String, String> maps = new HashMap<String, String>();
                                maps = gson.fromJson(columnValueDesc, maps.getClass());
                                String statusValue = maps.get(stu);
                                //String statusValue = columnValueDesc.substring(columnValueDesc.indexOf(stu)+1, columnValueDesc.indexOf("；"));
                                result.add(statusValue);
                            } else {
                                result.add(map.get(value[i]));
                            }
                        } else {
                            result.add(key[i]);
                            if ("null".equals(map.get(value[i])) || map.get(value[i]) == null) {
                                map.put(value[i], "无");
                            }
                            result.add(map.get(value[i]));
                        }
                    }
                }
                for(int n = 2;n<result.size();n=n+2) {
                    /*NameValueModel nameValueModel = new NameValueModel();
                    nameValueModel.setName(result.get(n));
                    nameValueModel.setValue(result.get(n+1));
                    nameValueModel_list.add(nameValueModel);*/
                    nameValueModel_list.add(NameValueModel.builder().name(result.get(n)).value(result.get(n+1)).build());
                }
            }catch (Exception e){
            	log.error(e.toString());
            }
            searchObjList.setDetails(nameValueModel_list);
            searchObjList_list.add(searchObjList);
        }
        pageInfo.setTotal(total);
        pageInfo.setPageNum(page);
        pageInfo.setList(searchObjList_list);
        pageInfo.setPages(total%size==0?total/size:total/size+1);
        pageInfo.setPageSize(size);
        return pageInfo;
    }

    //一级对象Tag搜索（提供搜索内容）
    public MyPageInfoModel<List<SearchObjList>> searchByEsObjTag(String content,Integer page,Integer size,String[] tableName_Obj){
        MyPageInfoModel<List<SearchObjList>> pageInfo = new MyPageInfoModel<List<SearchObjList>>();
        List<SearchObjList> searchObjList_list = new ArrayList<>();
        for(int s =0;s<tableName_Obj.length;s++) {
             searchObjList_list.addAll(TagUtil(content,1,20,tableName_Obj[s]));
        }
        //pageInfo = ListToPageInfoUtil.listToPageInfo(searchObjList_list,page,size);
        int pages;
        if(searchObjList_list.size()%size==0){
            pages = searchObjList_list.size()/size;
        }else{
            pages = searchObjList_list.size()/size+1;
        }
        searchObjList_list.stream().limit(size);
        pageInfo.setTotal(searchObjList_list.size());
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(size);
        pageInfo.setPages(pages);
        pageInfo.setList(searchObjList_list.stream().skip((page-1) * size).
                limit(size).
                collect(toList()));
        return pageInfo;
    }

    public List<SearchObjList> TagUtil(String content,Integer page,Integer size,String tag){
        String urlStr = "http://" + commonInstance.getElasticsearchIp() + ":" + commonInstance.getElasticsearchPort() +"/"+ tag + "/_doc/_search";
        String json = "{\n" +
                "\t\"from\":\"" + page + "\",\n" +
                "    \"size\":\"" + size + "\",\n" +
                "\t\"_source\":[\"NAME\",\"ADDRESS\",\"location\",\"CAMERAID\",\"ID\",\"DEVICE_CODE\",\"LOAD_MAP_TAG_LV2\"],\n" +
                "\t\"query\":{\n" +
                "\t\t\"bool\":{\n" +
                "\t\t\t\"must\":{\n" +
                "\t\t\t\t\"multi_match\": {\n" +
                "\t\t\t        \"query\":       \"" + content + "\",\n" +
                "\t\t\t        \"type\":        \"most_fields\",\n" +
                "\t\t\t        \"fields\":      [ \"NAME\",\"ADDRESS\" ]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"highlight\": {\n" +
                "\t    \"fields\":{\n" +
                "\t    \t\"*\":{}\n" +
                "\t    }\n" +
                "\t}\n" +
                "}";
        String source = UrlApiToSource.doJsonPost(urlStr, json);
        Map<String, String> map_source = (Map) JSON.parse(source);
        String source_hits = JsonUtils.objectToJson(map_source.get("hits"));
        ElasticSearchObj elasticSearchObj = JsonUtils.jsonToBean(source_hits, ElasticSearchObj.class);
        //实体内容
        String hits_con = elasticSearchObj.getHits().toString();
        List<ElasticSearchObjContent> elasticSearchObjContent_list = JsonUtils.jsonToList(hits_con, ElasticSearchObjContent.class);

        List<SearchObjList> searchObjList_list = new ArrayList<SearchObjList>();
        for (ElasticSearchObjContent elasticSearchObjContent : elasticSearchObjContent_list) {
            String id;
            Map<String, String> map_loaction = (Map) JSON.parse(JsonUtils.objectToJson(elasticSearchObjContent.get_source()));
            Map<String, String> point = (Map) JSON.parse(JsonUtils.objectToJson(map_loaction.get("location")));

            String name = map_loaction.get("NAME");
            String address = map_loaction.get("ADDRESS");
            String tableNameOne = elasticSearchObjContent.get_index().substring(0, elasticSearchObjContent.get_index().indexOf("@")).toUpperCase();
            String layeridOne;
            if ("".equals(map_loaction.get("LOAD_MAP_TAG_LV2")) || map_loaction.get("LOAD_MAP_TAG_LV2") == null) {
                layeridOne = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName + "." + tableNameOne);
            } else {
                tableNameOne = tableNameOne + "@" + map_loaction.get("LOAD_MAP_TAG_LV2");
                layeridOne = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName + "." + tableNameOne).split("#")[0];
            }
            if ("VIDEO_POLICE".equals(tableNameOne)) {
                id = map_loaction.get("CAMERAID");
            } else if (tableNameOne.contains("BLK_SANXIAO_PLACE")) {
                id = map_loaction.get("ID");
            } else if (tableNameOne.contains("IOT_DEVICE")) {
                id = map_loaction.get("DEVICE_CODE");
            } else {
                id = elasticSearchObjContent.get_id();
            }
            SearchObjList searchObjList = new SearchObjList();
            searchObjList.setTag(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", tableNameOne));
            searchObjList.setName(name);
            searchObjList.setAddress(address);
            searchObjList.setTableName(tableNameOne);
            searchObjList.setLayerid(layeridOne);
            searchObjList.setId(id);
            searchObjList.setJd(point.get("lon"));
            searchObjList.setWd(point.get("lat"));
            //details部分
            List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
            String tableName;
            try {
                InputStream inputStream = ESOperate.class.getResourceAsStream("/tip.properties");
                Properties properties = new Properties();
                properties.load(new InputStreamReader(inputStream, "UTF-8"));
                String tableKey = properties.getProperty(ESOperate.dbName + "." + searchObjList.getTableName() + "Key");
                String tableValue = properties.getProperty(ESOperate.dbName + "." + searchObjList.getTableName() + "Value").toUpperCase();
                String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
                String[] value = tableValue.split(",");
                if (searchObjList.getTableName().contains("@")) {
                    tableName = searchObjList.getTableName().substring(0, searchObjList.getTableName().indexOf("@"));
                } else {
                    tableName = searchObjList.getTableName();
                }
                LinkedHashMap<String, String> map = tipUtil.loadMapTipToOracle(tableName, searchObjList.getId());  //请求oracle
                Gson gson = new Gson();
                List<String> result = new ArrayList();
                for (int i = 0; i < key.length; i++) {
                    if (i == 0) {
                        result.add(key[i]);
                        result.add(value[i]);
                    } else {
                        if (value[i].contains("*")) {
                            result.add(key[i]);
                            value[i] = value[i].substring(0, value[i].indexOf("*"));
                            String stu = map.get(value[i]);
                            String columnValueDesc = properties.getProperty(ESOperate.dbName + "." + tableName + "*" + value[i]);
                            if (columnValueDesc.contains(stu)) {
                                Map<String, String> maps = new HashMap<String, String>();
                                maps = gson.fromJson(columnValueDesc, maps.getClass());
                                String statusValue = maps.get(stu);
                                //String statusValue = columnValueDesc.substring(columnValueDesc.indexOf(stu)+1, columnValueDesc.indexOf("；"));
                                result.add(statusValue);
                            } else {
                                result.add(map.get(value[i]));
                            }
                        } else {
                            result.add(key[i]);
                            if ("null".equals(map.get(value[i])) || map.get(value[i]) == null) {
                                map.put(value[i], "无");
                            }
                            result.add(map.get(value[i]));
                        }
                    }
                }
                for (int n = 2; n < result.size(); n = n + 2) {
                    /*NameValueModel nameValueModel = new NameValueModel();
                    nameValueModel.setName(result.get(n));
                    nameValueModel.setValue(result.get(n + 1));
                    nameValueModel_list.add(nameValueModel);*/
                    nameValueModel_list.add(NameValueModel.builder().name(result.get(n)).value(result.get(n + 1)).build());
                }
            } catch (Exception e) {
            	log.error(e.toString());
            }
            searchObjList.setDetails(nameValueModel_list);
            searchObjList_list.add(searchObjList);
        }
        return searchObjList_list;
    }

    public MyPageInfoModel<List<SearchObjList>> searchByEsObjOneToTag(String content, Integer pageNum, Integer pageSize,String[] tableName_Obj)throws IOException{
        List<SearchObjList> list_new = new ArrayList<>();
        for(int i =0; i<tableName_Obj.length;i++){
            MyPageInfoModel<List<SearchObjList>> pageInfo_data = searchByEsObjOne( content,  1,  100,tableName_Obj[i]);
            list_new.addAll(pageInfo_data.getList());
        }
        MyPageInfoModel<List<SearchObjList>> pageInfo = (MyPageInfoModel<List<SearchObjList>>) ListToPageInfoUtil.listToPageInfo(list_new,pageNum,pageSize);
        return pageInfo;
    }

    //二级对象搜索（提供搜索内容和point点的周边资源）[已废弃]
    public MyPageInfoModel<List<SearchObjList>> searchByEsObj(String content, Integer page, Integer size, String jd, String wd){
        MyPageInfoModel<List<SearchObjList>> pageInfo = new MyPageInfoModel<List<SearchObjList>>();
        String urlStr = "http://"+ commonInstance.getElasticsearchIp()+":"+ commonInstance.getElasticsearchPort()+"/_search";
        String json = "{\n" +
                "  \"from\":\""+page+"\",\n" +
                "  \"size\":\""+size+"\",\n" +
                "  \"_source\":[\"location\"],\n" +
                "\t\"query\":{\n" +
                "\t\t\"bool\":{\n" +
                "\t\t\t\"must\":{\n" +
                "\t\t\t\t\"multi_match\": {\n" +
                "\t\t\t        \"query\":       \""+content+"\",\n" +
                "\t\t\t        \"type\":        \"most_fields\",\n" +
                "\t\t\t        \"fields\":      [ \"*\" ]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"filter\": {\n" +
                "        \"geo_distance\": {\n" +
                "          \"distance\": \"1km\", \n" +
                "          \"location\": { \n" +
                "            \"lat\": "+wd+",\n" +
                "            \"lon\": "+jd+"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"highlight\": {\n" +
                "\t    \"fields\":{\n" +
                "\t    \t\"*\":{}\n" +
                "\t    }\n" +
                "\t}\n" +
                "}";
        String source = UrlApiToSource.doJsonPost(urlStr,json);
        Map<String,String> map_source = (Map) JSON.parse(source);
        String source_hits = JsonUtils.objectToJson(map_source.get("hits"));
        ElasticSearchObj elasticSearchObj = JsonUtils.jsonToBean(source_hits,ElasticSearchObj.class);
        //数据总数
        Integer total = Integer.valueOf(elasticSearchObj.getTotal());
        //实体内容
        String hits_con = elasticSearchObj.getHits().toString();
        List<ElasticSearchObjContent> elasticSearchObjContent_list = JsonUtils.jsonToList(hits_con,ElasticSearchObjContent.class);

        List<SearchObjList> searchObjList_list = new ArrayList<SearchObjList>();
        for (ElasticSearchObjContent elasticSearchObjContent:elasticSearchObjContent_list) {
            int i = 1;
            Map<String,String> map_loaction = (Map) JSON.parse(JsonUtils.objectToJson(elasticSearchObjContent.get_source()));
            Map<String,String> point = (Map) JSON.parse(JsonUtils.objectToJson(map_loaction.get("location")));
            String str = JsonUtils.objectToJson(elasticSearchObjContent.getHighlight()).replace("[", "");
            str= str.replace("]", "");
            Map<String,String> map_Keyword_alibaba = (Map) JSON.parse(str);
            Map<String,String> map_Keyword = new HashMap();
            for (String keyWord : map_Keyword_alibaba.values()) {
                map_Keyword.put(String.valueOf(i),keyWord);
                i++;
            }
            if(map_Keyword.get("2")==null){
                map_Keyword.put("2",map_Keyword.get("1"));
            }
            String tableName = elasticSearchObjContent.get_index().substring(0,elasticSearchObjContent.get_index().indexOf("@")).toUpperCase();
            String layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+tableName);
            SearchObjList searchObjList = new SearchObjList();
            searchObjList.setName(map_Keyword.get("2"));
            searchObjList.setAddress(map_Keyword.get("1"));
            searchObjList.setTableName(tableName);
            searchObjList.setLayerid(layerid);
            searchObjList.setId(elasticSearchObjContent.get_id());
            searchObjList.setJd(point.get("lon"));
            searchObjList.setWd(point.get("lat"));
            searchObjList_list.add(searchObjList);
        }
        pageInfo.setTotal(total);
        pageInfo.setPageNum(page/size+1);
        pageInfo.setList(searchObjList_list);
        pageInfo.setPages(total%size==0?total/size:total/size+1);
        pageInfo.setPageSize(size);
        return pageInfo;
    }

    //特定point点周边的实体
    public List<NearbyResourcesModel> searchByEsNearResourceObj(String[] tableName_Obj, String jd, String wd, String range)throws IOException {
        List<NearbyResourcesModel> nearbyResourcesModelList = new ArrayList<NearbyResourcesModel>();
        for (String tableName:tableName_Obj) {
            String layerid,urlStr,json;
            String column =null;
            String columnEntity =null;
            String value = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName + "." + tableName);
            if (tableName.contains("@")) {
                //column = value.substring(value.indexOf("#") + 1, value.indexOf("$"));
                column = "LOAD_MAP_TAG_LV2";
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
                        "  \"_source\":[\"location\",\"NAME\",\"ADDRESS\",\"ID\",\"DEVICE_CODE\",\""+column+"\"],\n" +
                        "  \"query\": {\n" +
                        "    \"bool\" : {\n" +
                        "     \"must\":[\n" +
                        "              {\"match_phrase\":{\""+column+"\":\""+columnEntity+"\"}}\n" +
                        "             ],\n" +
                        "      \"filter\": {\n" +
                        "        \"geo_distance\": {\n" +
                        "          \"distance\": \""+range+"km\", \n" +
                        "          \"location\": { \n" +
                        "            \"lat\":  "+wd+",\n" +
                        "            \"lon\": "+jd+"\n" +
                        "          }\n" +
                        "        }\n" +
                        "      }\n" +
                        "     }\n" +
                        "  }\n" +
                        "}";
            }else {
                urlStr = "http://" + commonInstance.getElasticsearchIp() + ":" + commonInstance.getElasticsearchPort() + "/" + tableName + "@zhftyjjcpt/_doc/_search";
                json = "{\n" +
                        "  \"from\":\"0\",\n" +
                        "  \"size\":\"9999\",\n" +
                        "  \"_source\":[\"location\",\"NAME\",\"ADDRESS\",\"CAMERAID\",\"BUILDID\"],\n" +
                        "  \"query\": {\n" +
                        "    \"bool\" : {\n" +
                        "     \"must\" : { \n" +
                        "       \"match_all\" : {}\n" +
                        "              },\n" +
                        "      \"filter\": {\n" +
                        "        \"geo_distance\": {\n" +
                        "          \"distance\": \"" + range + "km\", \n" +
                        "          \"location\": { \n" +
                        "            \"lat\":  " + wd + ",\n" +
                        "            \"lon\": " + jd + "\n" +
                        "          }\n" +
                        "        }\n" +
                        "      }\n" +
                        "     }\n" +
                        "  }\n" +
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
