package com.cetccity.operationcenter.webframework.web.util;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
@Slf4j
public class DataOfProperityUtil {

    /**
     * 数据字段工具，将字段名称转换为中文名称
     * 配置文件实时加载，修改立马生效
     * @param colName
     * @return
     */
    public static String dataDictionary(String propertiesName,String tbName,String colName, String value) {

        InputStream inputStream = ESOperate.class.getResourceAsStream("/"+propertiesName+"");
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
        	log.error(e.toString());
        }
        return properties.getProperty(tbName + "." + colName + "." + value);
    }

}
