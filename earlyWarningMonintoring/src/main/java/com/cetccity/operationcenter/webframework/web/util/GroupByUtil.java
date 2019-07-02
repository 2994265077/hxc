package com.cetccity.operationcenter.webframework.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Description：分组统计
 * Created by luolinjie on 2018/7/11.
 */
public class GroupByUtil {
    private static final Logger logger = LoggerFactory.getLogger(GroupByUtil.class);

    public List<HashMap> groupByTime(List<HashMap> originDataList,String timeFieldName,String valueFieldName) throws ParseException {

        ArrayList<HashMap> resultList = new ArrayList<HashMap>();
        for (int i = 0; i < originDataList.size(); i++) {
            String time = (String) originDataList.get(i).get(timeFieldName);
            int value = Integer.parseInt(String.valueOf(originDataList.get(i).get(valueFieldName)));

            boolean isExists = false;
            //遍历结果集中是否存在相同key的cell,若存在就对其value+1，若不存在就添加这个记录
            try {
                for (int j = 0; j < resultList.size(); j++) {
                    if (resultList.get(j).get(timeFieldName).equals(time)) {
                        isExists = true;
                        resultList.get(j).put("value", Integer.parseInt(String.valueOf(originDataList.get(j).get("value")) + value));
                        break;
                    }
                }
            }catch (Exception e){
                logger.error("",e);
            }
            if (!isExists) {
                HashMap linkedHashMap = new HashMap();
                linkedHashMap.put("time", time);
                linkedHashMap.put("value", value);
                resultList.add(linkedHashMap);
            }
        }
        return resultList;
    }
}
