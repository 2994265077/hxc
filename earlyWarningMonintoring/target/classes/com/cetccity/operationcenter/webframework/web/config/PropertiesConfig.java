package com.cetccity.operationcenter.webframework.web.config;

import com.cetccity.operationcenter.webframework.core.tools.PropertiesLoadUtils;
import com.cetccity.operationcenter.webframework.urbansign.api.model.LoadMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Properties;

/**
 * @Package: com.justplay1994.github.db2es.config
 * @Project: db2es
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/22 15:48
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/22 15:48
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Configuration
@PropertySource(value = {"classpath:street.properties","classpath:tip.properties"},encoding = "utf-8")
public class PropertiesConfig {

    @Bean
    public LoadMap setLoadMap() throws IOException {
        Properties properties = new PropertiesLoadUtils().readProperties("loadmap.properties");
        LoadMap loadMap = new LoadMap(properties);
        return loadMap;
    }

}
