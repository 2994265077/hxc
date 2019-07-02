package com.cetccity.operationcenter.webframework.web.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;

/**
 * Description：根据入参：表名+图层名 获取到所有落点，并组成相应格式
 * Created by luolinjie on 2018/4/28.
 */
public class GeoUtil {

    private static Logger logger = LoggerFactory.getLogger(GeoUtil.class);
    public static JSONArray getAllPoints(String tableName, String layerid) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";


        LinkedHashMap<String, Object> paramMap1 = new LinkedHashMap<String,Object>();

        ArrayList<String> list = new ArrayList<String>();
        list.add("id");
        list.add("location");
        paramMap1.put("_source", list);
        paramMap1.put("from", 0);
        paramMap1.put("size", 9999);

        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(paramMap1,true));

        JSONObject jsonResponse = JSON.parseObject(response);
        JSONArray array = jsonResponse.getJSONObject("hits").getJSONArray("hits");

        JSONArray resArr = new JSONArray();
        Iterator<Object> iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject source = next.getJSONObject("_source");
            String id = next.getString("_id");
            JSONObject location = source.getJSONObject("location");
            String jd = location.getString("lon");
            String wd = location.getString("lat");

            JSONObject resObj = new JSONObject();
            resObj.put("tableName", tableName);
            resObj.put("layerid", layerid);
            resObj.put("id", id);
            resObj.put("jd", jd);
            resObj.put("wd", wd);

            resArr.add(resObj);
        }


        return resArr;
    }

    /**
     *
     * @param tableName
     * @param layerid
     * @param sorted 是否进行排序 true：排序 false：不排序
     * @return
     */
    public static JSONArray getAllPoints(String tableName, String layerid,boolean sorted) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";


        LinkedHashMap<String, Object> paramMap1 = new LinkedHashMap<String,Object>();

        ArrayList<String> list = new ArrayList<String>();
        list.add("id");
        list.add("location");
        paramMap1.put("_source", list);
        paramMap1.put("from", 0);
        paramMap1.put("size", 9999);

        JSONArray sortParm = new JSONArray();
        JSONObject sortValue = new JSONObject();
        JSONObject sortValue_param = new JSONObject();
        sortValue_param.put("order", "asc");
        sortValue.put("_id",sortValue_param);
        sortParm.add(sortValue);
        paramMap1.put("sort", sortParm);

        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(paramMap1,true));

        JSONObject jsonResponse = JSON.parseObject(response);
        JSONArray array = jsonResponse.getJSONObject("hits").getJSONArray("hits");

        JSONArray resArr = new JSONArray();
        Iterator<Object> iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject source = next.getJSONObject("_source");
            String id = next.getString("_id");
            JSONObject location = source.getJSONObject("location");
            String jd = location.getString("lon");
            String wd = location.getString("lat");

            JSONObject resObj = new JSONObject();
            resObj.put("tableName", tableName);
            resObj.put("layerid", layerid);
            resObj.put("id", id);
            resObj.put("jd", jd);
            resObj.put("wd", wd);

            resArr.add(resObj);
        }


        return resArr;
    }

    public static String getListInfo(String tableName, List filedList) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";


        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();

        map.put("_source",filedList);
        map.put("from",0);
        map.put("size",9999);


        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map));

      return response;

    }


    public static String getListInfo(String tableName, List filedList,int size) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";


        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();

        map.put("_source",filedList);
        map.put("from",0);
        map.put("size",size);


        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map));

      return response;

    }
    public static String getListInfo(String tableName, List filedList,int pageNum,int pageSize) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";


        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();

        map.put("_source",filedList);
        map.put("from",(pageNum-1)*pageSize);
        map.put("size", pageSize);


        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map));

      return response;

    }
    /**
     * 查询列表
     * @param tableName
     * @param _sourceList
     * @param matchMap
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static String queryListInfoAndFilter(String tableName, List _sourceList,Map<String, String> matchMap, int pageNum, int pageSize) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";

        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
        if (pageNum<=0){
            pageNum=1;
        }
        /**
         * querys请求参数加载
         */
        JSONObject queryObj = new JSONObject();
        JSONObject boolObj = new JSONObject();
        JSONArray matchArr = new JSONArray();

        if (matchMap.size()>0) {

            Set<String> keySet = matchMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                JSONObject matchUnit = new JSONObject();
                JSONObject k_v = new JSONObject();
                k_v.put(next, matchMap.get(next));
                matchUnit.put("match", k_v);
                matchArr.add(matchUnit);
            }
            boolObj.put("must", matchArr);
            queryObj.put("bool", boolObj);
        }
        /**
         * filter请求参数加载
         */
        JSONObject filterObj = new JSONObject();

        map.put("_source",_sourceList);
        map.put("query",queryObj);
        map.put("from",(pageNum-1)*pageSize);
        map.put("size",pageSize);

        logger.info(JSON.toJSONString(map));
        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map));

      return response;

    }

    /**
     * 全字段匹配 matchPhrase
     * @param tableName
     * @param _sourceList
     * @param matchphraseMap
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static String getListInfoAndMatchphrase(String tableName, List _sourceList,Map<String, String> matchphraseMap, int pageNum, int pageSize) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";

        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
        if (pageNum<=0){
            pageNum=1;
            logger.error("param:getListInfoAndMatchphrase-pageNum must not less than 1! already change to 1");
        }
        /**
         * querys请求参数加载
         */
        JSONObject queryObj = new JSONObject();
        JSONObject boolObj = new JSONObject();
        JSONArray matchArr = new JSONArray();

        if (matchphraseMap.size()>0) {

            Set<String> keySet = matchphraseMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                JSONObject matchUnit = new JSONObject();
                JSONObject k_v = new JSONObject();
                k_v.put(next, matchphraseMap.get(next));
                matchUnit.put("match_phrase", k_v);
                matchArr.add(matchUnit);
            }
            boolObj.put("must", matchArr);
            queryObj.put("bool", boolObj);
        }
        /**
         * filter请求参数加载
         */
        JSONObject filterObj = new JSONObject();

        map.put("_source",_sourceList);
        map.put("query",queryObj);
        map.put("from",(pageNum-1)*pageSize);
        map.put("size",pageSize);

        logger.info(JSON.toJSONString(map));
        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map));

        return response;

    }
    public static List parseToGISArray(JSONArray array) {
        Iterator<Object> iterator = array.iterator();
        int len = array.size();
        List tableList = new ArrayList();
        int i = 0;
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            Set<String> keySet = next.keySet();
            Iterator<String> iterator_key = keySet.iterator();
            String keyName = iterator_key.next();
            String value = next.getString(keyName);
            //单个数组元素
            String[] unit = new String[2];
            unit[0] = keyName;
            unit[1] = value;
            tableList.add(unit);
        }

        return tableList;
    }
}
