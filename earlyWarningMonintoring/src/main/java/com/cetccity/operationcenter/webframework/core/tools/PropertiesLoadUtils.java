package com.cetccity.operationcenter.webframework.core.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;

import java.io.*;
import java.util.Properties;

/**
 * @Package: com.justplay1994.github.db2es.util
 * @Project: db2es
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/22 15:33
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/22 15:33
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Slf4j
@Component
public class PropertiesLoadUtils {

    /**
     * 容器环境 env
     */
    @Autowired
    private Environment env;

    /**
     * @param key key
     * @return
     */
    public String getProperty(String key) {
        return env.getProperty(key);
    }

    /**
     * properties文件读取优先级：
     * 1.相对路径 config/xx.properties
     * 2.相对路径 xx.properties
     * 3.类路径 classpath:xx.properties
     * @param propertiesName 文件名
     * @return
     */
    public Properties readProperties(String propertiesName) {
        Properties properties = null;
        try {
            File file = new File("config/" + propertiesName);
            if (file.exists()) {
                InputStream inputStreamReader = new FileInputStream(file);
                InputStreamReader in = new InputStreamReader(inputStreamReader, "utf-8");
                properties = new Properties();
                properties.load(in);
                in.close();
                //log.info("Read properties finished: config/" + propertiesName);
            } else {
                file = new File(propertiesName);
                if (file.exists()) {
                    InputStream inputStreamReader = new FileInputStream(file);
                    InputStreamReader in = new InputStreamReader(inputStreamReader, "utf-8");
                    properties = new Properties();
                    properties.load(in);
                    in.close();
                    //log.info("Read properties finished: " + propertiesName);
                } else {
                    InputStream stream = getClass().getClassLoader().getResourceAsStream(propertiesName);
                    InputStreamReader in = new InputStreamReader(stream, "utf-8");
                    properties = new Properties();
                    properties.load(in);
                    in.close();
                    //log.info("Read properties finished: classpath: " + propertiesName);
                }
            }
        } catch (Exception e) {
            log.error("read properties error: " + propertiesName, e);
        }
        return properties;
    }

    /**
     * @param xmlName 文件名
     * @return
     */
    public StringBuffer readXml(String xmlName) {
        StringBuffer buffer = new StringBuffer();
        String line;
        try {
            // 读取XML文件
            File file = new File("config/" + xmlName);
            if (file.exists()) {
                Resource resource = new FileSystemResource(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
                while ((line = br.readLine()) != null) {
                    buffer.append(line);
                }
                br.close();
                log.info("Read properties finished: config/" + xmlName);
            } else {
                file = new File(xmlName);
                if (file.exists()) {
                    Resource resource = new FileSystemResource(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
                    while ((line = br.readLine()) != null) {
                        buffer.append(line);
                    }
                    br.close();
                    log.info("Read properties finished: " + xmlName);
                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(xmlName), "utf-8"));
                    while ((line = br.readLine()) != null) {
                        buffer.append(line);
                    }
                    br.close();
                    log.info("Read properties finished: classpath: " + xmlName);
                }
            }
        } catch (Exception e) {
            log.error("read properties error: " + xmlName, e);
        }
        return buffer;
    }

    /**
     * @param xmlName 文件名
     * @return
     */
    public Resource readXmlReturnResource(String xmlName) {
        Resource resource = null;
        try {
            // 读取XML文件
            File file = new File("config/" + xmlName);
            if (file.exists()) {
                resource = new FileSystemResource(file);
                log.info("Read properties finished: config/" + xmlName);
            } else {
                 file = new File(xmlName);
                if (file.exists()) {
                    resource = new FileSystemResource(file);
                    log.info("Read properties finished: " + xmlName);
                } else {
                    resource = new ClassPathResource(xmlName);
                    log.info("Read properties finished: classpath: " + xmlName);
                }
            }
            return resource;
        } catch (Exception e) {
            log.error("read properties error: " + xmlName, e);
            return resource;
        }
    }

}
