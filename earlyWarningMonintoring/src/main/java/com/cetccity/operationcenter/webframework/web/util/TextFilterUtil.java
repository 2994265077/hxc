package com.cetccity.operationcenter.webframework.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Description：
 * Created by luolinjie on 2018/7/12.
 */
public class TextFilterUtil {
    /**
     * 空值操作，将所有的null，转换为中文的“无”
     * @param input
     * @return
     */
    public static List<HashMap> emptyOperate(List<HashMap> input){
        for (int i = 0; i < input.size(); ++i){
            HashMap temp = input.get(i);
            Set set = temp.keySet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()){
                String key = it.next();
                if (temp.get(key)==null){
                    temp.put(key,"无");
                }
            }
        }
        return input;
    }
}
