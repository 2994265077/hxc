package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.hiddendanger.api.CacheManagerApi;
import com.cetccity.operationcenter.webframework.hiddendanger.service.CacheManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:11 2019-02-25
 * Updater:     heliangming
 * Update_Date：14:11 2019-02-25
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CacheManagerController implements CacheManagerApi {

    @Autowired
    CacheManagerService cacheManagerService;

    /**落图缓存--loadMap
     建筑详情--buildDetail
     安全隐患一张图图表--hiddenDangerChart
     生态环境图表--ecoEnvironmentChart*/
    public void clearAllCache(String key) {
        switch(key) {
            case "loadMap":
                cacheManagerService.clearAllCacheOfLoadMap();
                break;
            case "buildDetail":
                cacheManagerService.clearAllCacheOfBuildDetail();
                break;
            case "hiddenDangerChart":
                cacheManagerService.clearAllCacheOfHiddenDangerChart();
                break;
            case "ecoEnvironmentChart":
                cacheManagerService.clearAllCacheOfEcoEnvironmentChart();
                break;
            case "":
                cacheManagerService.clearAllCacheOfLoadMap();
                cacheManagerService.clearAllCacheOfBuildDetail();
                cacheManagerService.clearAllCacheOfHiddenDangerChart();
                cacheManagerService.clearAllCacheOfEcoEnvironmentChart();
                break;
        }
    }
}
