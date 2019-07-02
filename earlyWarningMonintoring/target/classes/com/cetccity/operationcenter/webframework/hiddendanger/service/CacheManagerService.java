package com.cetccity.operationcenter.webframework.hiddendanger.service;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:15 2019-02-25
 * Updater:     heliangming
 * Update_Date：14:15 2019-02-25
 * 更新描述:    heliangming 补充
 **/
public interface CacheManagerService {

    String clearAllCacheOfLoadMap();

    String clearAllCacheOfBuildDetail();

    String clearAllCacheOfHiddenDangerChart();

    String clearAllCacheOfEcoEnvironmentChart();
}
