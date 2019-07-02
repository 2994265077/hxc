package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.hiddendanger.service.CacheManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:16 2019-02-25
 * Updater:     heliangming
 * Update_Date：14:16 2019-02-25
 * 更新描述:    heliangming 补充
 **/
@Slf4j
@Service
public class CacheManagerServiceImpl implements CacheManagerService {

    /**
     * allEntries = true: 清空loadMap里的所有缓存
     */
    @CacheEvict(cacheNames="loadMap", allEntries=true)
    public String clearAllCacheOfLoadMap(){
        log.info("clearCache-->loadMap");
        return "clearCache-->loadMap";
    }

    /**
     * allEntries = true: 清空loadMap里的所有缓存
     */
    @CacheEvict(cacheNames="buildDetail", allEntries=true)
    public String clearAllCacheOfBuildDetail(){
        log.info("clearCache-->buildDetail");
        return "clearCache-->buildDetail";
    }

    /**
     * allEntries = true: 清空hiddenDangerChart里的所有缓存
     */
    @CacheEvict(cacheNames="hiddenDangerChart", allEntries=true)
    public String clearAllCacheOfHiddenDangerChart(){
        log.info("clearCache-->hiddenDangerChart");
        return "clearCache-->hiddenDangerChart";
    }

    /**
     * allEntries = true: 清空ecoEnvironmentChart里的所有缓存
     */
    @CacheEvict(cacheNames="ecoEnvironmentChart", allEntries=true)
    public String clearAllCacheOfEcoEnvironmentChart(){
        log.info("clearCache-->ecoEnvironmentChart");
        return "clearCache-->ecoEnvironmentChart";
    }
}
