package com.cetccity.operationcenter.webframework.web.config;

import com.cetccity.operationcenter.webframework.core.tools.PropertiesLoadUtils;
import com.cetccity.operationcenter.webframework.urbansign.api.model.LoadMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.io.IOException;
import java.util.Properties;

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
