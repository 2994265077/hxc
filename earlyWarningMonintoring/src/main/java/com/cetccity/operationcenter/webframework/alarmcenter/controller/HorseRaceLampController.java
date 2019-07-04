package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.HorseRaceLampApi;
import com.cetccity.operationcenter.webframework.alarmcenter.service.HorseRaceLampService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:46 2019-03-22
 * Updater:     heliangming
 * Update_Date：16:46 2019-03-22
 * 更新描述:    heliangming 补充
 **/
@RestController
@CacheConfig(cacheNames = "HorseRaceLampController")
public class HorseRaceLampController implements HorseRaceLampApi {

    @Autowired
    HorseRaceLampService horseRaceLampService;

    @Cacheable(key = "'HorseRaceLamp'")
    public List<LoadMap> findLamp(){
        List<LoadMap> loadMap_list = horseRaceLampService.findLamp();
        return loadMap_list;
    }
}
