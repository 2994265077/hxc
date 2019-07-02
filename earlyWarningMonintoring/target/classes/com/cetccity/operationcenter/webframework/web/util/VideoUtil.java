package com.cetccity.operationcenter.webframework.web.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;

/**
 * Description：视频图像专用，精确查询 根据入参：表名+图层名 获取到所有落点，并组成相应格式
 * Created by LinHaiquan on 2018/4/28.
 * tableName 表名
 * filedList 要查询的字段
 * condition 查询的条件字段
 * conditionValue 条件字段的值
 */
public class VideoUtil {

    public static String conditionQuery(String tableName,List filedList,String condition,String conditionValue) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";

        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
        LinkedHashMap<String, Object> map1 = new LinkedHashMap<String,Object>();
        LinkedHashMap<String, Object> map2 = new LinkedHashMap<String,Object>();

        map2.put(condition,conditionValue);
        map1.put("match_phrase",map2);

        map.put("_source",filedList);
        map.put("query",map1);
        map.put("from",0);
        map.put("size",9999);

        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map,true));
        return response;
    }

    /**
     * Description：视频图像专用，得到所有的信息 根据入参：表名+图层名 获取到所有落点，并组成相应格式
     * Created by LinHaiquan on 2018/4/28.
     * tableName 表名
     * filedList 要查询的字段
     */
    public static String getAll(String tableName, List filedList) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";


        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();

        map.put("_source",filedList);
        map.put("from",0);
        map.put("size",999);


        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map));

        return response;

    }

    /**
     * Description：视频图像专用，得到指定数量的信息 根据入参：表名+图层名 获取到所有落点，并组成相应格式
     * Created by LinHaiquan on 2018/4/28.
     * tableName 表名
     * filedList 要查询的字段
     */

    public static String getAll(String tableName, List filedList,int size) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";


        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
        map.put("_source",filedList);
        map.put("size",size);
        map.put("from",0);


        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map));

        return response;

    }

    /**
     * Description：模糊查询 根据入参：表名+图层名 获取到所有落点，并组成相应格式
     * Created by LinHaiquan on 2018/4/28.
     * tableName 表名
     * filedList 要查询的字段
     */

    public static String likeQuery(String tableName,List filedList,List conditionList,String conditionValue) {

        String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/" + tableName + "@31project_april/_doc/_search";

        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
        LinkedHashMap<String, Object> multi_match = new LinkedHashMap<String,Object>();
        LinkedHashMap<String, Object> query = new LinkedHashMap<String,Object>();

        multi_match.put("query",conditionValue);
        multi_match.put("fields",conditionList);

        query.put("multi_match",multi_match);

        map.put("_source",filedList);
        map.put("query",query);
        map.put("from",0);
        map.put("size",9999);

        String response = UrlApiToSource.doJsonPost(urlStr, JSON.toJSONString(map,true));
        return response;
    }


    // 删除ArrayList中重复元素，保持顺序
    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);

    }

}
