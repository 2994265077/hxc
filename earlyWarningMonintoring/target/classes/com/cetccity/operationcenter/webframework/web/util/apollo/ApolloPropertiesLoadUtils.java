package com.cetccity.operationcenter.webframework.web.util.apollo;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
/**
 * 
 * @author zhutongyu
 * @date 2019年6月3日
 * @desc 读取apollo配置
 */
public class ApolloPropertiesLoadUtils {
    /**
     * @param xmlName 文件名
     * @desc 从apollo中读取xml
     * @return
     */
    public static Resource readApolloToResource(String xmlName) {
        return new ByteArrayResource(readApollo(xmlName).getBytes());
    }
    
    /**
     * 
     * @param xmlName
     * @return
     */
    public static String readApollo(String xmlName){
    	return ConfigService.getConfigFile(xmlName, ConfigFileFormat.XML).getContent();
    }
    
    
    /**
     * 根据namespace, key获取properties的值
     * @param namespace
     * @param key
     * @return
     */
    public static String readProperties(String namespace, String key){
    	Config config = ConfigService.getConfig(namespace); //config instance is singleton for each namespace and is never null
    	String someDefaultValue = "someDefaultValueForTheKey";
    	return config.getProperty(key, someDefaultValue);
    }
}
