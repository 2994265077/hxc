package com.cetccity.operationcenter.webframework.core.tools;

import java.util.*;

public class MapUtil {

    public Map sortMapOfDescending(Map map){
        //降序排序
        List list = new LinkedList(map.entrySet());
        //sort list based on comparator
        Collections.sort(list, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        //put sorted list into map again
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
