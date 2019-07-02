package com.cetc.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

public class DataSyncherApplication{

    public static void main(String[] args)
    {
        SpringApplication.run(DataSyncherApplication.class, args);
//        new SpringApplicationBuilder(DataSyncherApplication.class).run(args);
    }
}


