package com.cetccity.operationcenter.webframework.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Json处理
 */
@Slf4j
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			log.error(e.toString());
		}
    	return null;
    }
    /**
     *
     *实体转为json
     */
    public static String beanToJson(Object data){
        String json = JSONObject.toJSONString(data);
        return json;
    }
    /**
     *List<>集合转json
     */
    public static String listToJson(List list) {
        String json = JSON.toJSONString(list);
        return json;
    }
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	log.error(e.toString());
        }
        return null;
    }
    /**
     *json转为实体
     */
    public static <T> T jsonToBean(String jsonData, Class<T> beanType){
        return JSONObject.parseObject(jsonData,beanType);
    }
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			log.error(e.toString());
		}
    	return null;
    }
    /**
    * List<实体>转为list<Object>
    * @param t
    * @return
    */
    public static List<Object> beanToObject(List<?> t) {
        List<Object> o = new ArrayList<Object>();
        Iterator<?> it = t.iterator();
        while (it.hasNext()) {
            o.add(it.next());
        }
        return o;
    }

    /**
     * List<objcet> 转为List<MapConfig>
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static List<Map> beanToMap(List<Object> object) throws Exception {
        List<Map> maps = new ArrayList<Map>();
        for (Object o : object) {
            String s = JSON.toJSONString(o);
            Map map = JSONObject.parseObject(s, Map.class);
            maps.add(map);
        }
        return maps;
    }

    /**
     * map   转   实体Bean
     */
    public static <T> T mapToBean(Class<T> clazz, Map map) {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            obj = clazz.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if ("".equals(value)) {
                        value = null;
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (InvocationTargetException e) {
                        System.out.println("字段映射失败");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("实例化 JavaBean 失败");
        } catch (IntrospectionException e) {
            System.out.println("分析类属性失败");
        } catch (IllegalArgumentException e) {
            System.out.println("映射错误");
        } catch (InstantiationException e) {
            System.out.println("实例化 JavaBean 失败");
        }
        return obj;
    }
}
