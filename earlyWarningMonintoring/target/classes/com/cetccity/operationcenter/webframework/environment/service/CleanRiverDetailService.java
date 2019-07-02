package com.cetccity.operationcenter.webframework.environment.service;

import com.github.pagehelper.PageInfo;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:32 2019-05-27
 * Updater:     heliangming
 * Update_Date：17:32 2019-05-27
 * 更新描述:    heliangming 补充
 **/
public interface CleanRiverDetailService {

    PageInfo findPatrolRecord(String id, Integer pageNum, Integer pageSize);

}
