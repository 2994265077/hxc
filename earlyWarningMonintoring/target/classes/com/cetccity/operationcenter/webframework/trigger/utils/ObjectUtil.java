package com.cetccity.operationcenter.webframework.trigger.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetc.cloud.alarm.trigger.utils
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/21 15:17
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/21 15:17
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
public class ObjectUtil {

    public static HashMap<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    public static List<HashMap> listObjectToListMap(List list) throws IllegalAccessException {

        List<HashMap> result = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i){
            result.add(objectToMap(list.get(i)));
        }
        return result;
    }

}
