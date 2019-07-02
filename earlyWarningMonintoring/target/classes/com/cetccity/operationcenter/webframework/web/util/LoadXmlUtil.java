package com.cetccity.operationcenter.webframework.web.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Slf4j
public class LoadXmlUtil {

    public Resource loadXmlFileOBJ() {
        Resource resource = new ClassPathResource("countries.xml");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
        	log.error(e.toString());
        }
        return resource;
    }
}
