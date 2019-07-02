package com.cetccity.operationcenter.webframework.web.util;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Description：用户自定义工具类  目前用于自定义排序
 * Created by luolinjie on 2018/5/8.
 */
public class CustomUtil {

    private static Properties properties = null;

    private static Logger logger = LoggerFactory.getLogger(CustomUtil.class);

    static {
        InputStream stream = CustomUtil.class.getClassLoader().getResourceAsStream("columnOrderRule.properties");
        properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
        	logger.error(e.toString());
        }
    }

    public static List SortByConfigFile(Map dataMap,String tableName) throws IOException {
        LinkedList<LinkedHashMap<String,String>> result = new LinkedList<LinkedHashMap<String,String>>();
        //TODO：1.根据传入tableName加载配置文件
        String colums = properties.getProperty(tableName);

        if (null == colums){
            logger.error("# '"+tableName+" # doesn't exists in config file:'columnOrderRule.properties',please add config corresponding to the table!");
        }

        String[] split = colums.split(",");
        for (int i=0;i<split.length;i++){
            LinkedHashMap<String,String> cell = new LinkedHashMap<String,String>();
            //TODO:2.从map中按顺序取数据，然后拼到List中
            String value = (String) dataMap.get(split[i]);
            if (null != value){
                String comment = ESOperate.dataDictionary(ESOperate.dbName, tableName, split[i]);
                cell.put(comment,value);
                result.add(cell);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        logger.info(properties.getProperty("jianzhuwu_001"));

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id","242");
        map.put("aqzs","安全指数");
        map.put("jzmc","建筑名称");
        map.put("jzdz","建筑地址");
        map.put("jzgc","建筑高度");
        map.put("zdmj","占地面积");
        map.put("jzqk","建筑情况");
        map.put("jzfl","建筑分类");

        List jianzhuwu_001 = SortByConfigFile(map, "jianzhuwu_001");
        logger.info(jianzhuwu_001.toString());
    }

}
