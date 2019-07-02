/**
 *
 * Created by HZZ on 2018/3/15.
 *
 */
package com.cetccity.operationcenter.webframework.core.tools;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HZZ on 2018/3/15.
 */
public class Tooltip {

    public static ObjectMapper objectMapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true).configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    /**
     * 数组转为
     *
     * {
     title:xxx;
     value:[
     {xxx:"xxx"},
     {xxx:"xxx"}
     ],
     hasDetailInfo:true
     }
     * @param list
     * @return
     */
    public static Map toolTipListToMap(List list,Boolean hasDetailInfo){
        Map result = new HashMap();
        result.put(list.get(0),list.get(1));
        result.put("hasDetailInfo",hasDetailInfo);
        List tableList = new ArrayList();
        for(int i = 2; i < list.size(); i=i+2){
            List temp = new ArrayList();
            temp.add(list.get(i));
            temp.add(list.get(i+1));
            tableList.add(temp);
        }
        result.put("value",tableList);
        return result;
    }

    public static Map toolTipListToMap(List list){
        Map result = new HashMap();
        result.put(list.get(0),list.get(1));

        List tableList = new ArrayList();
        for(int i = 2; i < list.size(); i=i+2){
            Map map = new HashMap();
            map.put(list.get(i),list.get(i+1));
            tableList.add(map);
        }
        result.put("value",tableList);
        return result;
    }

    public static Map toolTipListToMapTwo(List list,Boolean hasDetailInfo){
        Map result = new HashMap();
        result.put(list.get(0),list.get(1));
        result.put(list.get(2),list.get(3));
        result.put(list.get(4),list.get(5));
        result.put("hasDetailInfo",hasDetailInfo);

        List tableList = new ArrayList();
        for(int i = 6; i < list.size(); i=i+2){
//            MapConfig map = new HashMap();
//            map.put(list.get(i),list.get(i+1));
//            tableList.add(map);
            List temp = new ArrayList();
            temp.add(list.get(i));
            temp.add(list.get(i+1));
            tableList.add(temp);
        }
        result.put("value",tableList);
        return result;
    }

    public static Map toolTipListToMapTwo(List list){
        Map result = new HashMap();
        result.put(list.get(0),list.get(1));

        List tableList = new ArrayList();
        for(int i = 2; i < list.size(); i=i+2){
            Map map = new HashMap();
            map.put(list.get(i),list.get(i+1));
            tableList.add(map);
        }
        result.put("value",tableList);
        return result;
    }
}
