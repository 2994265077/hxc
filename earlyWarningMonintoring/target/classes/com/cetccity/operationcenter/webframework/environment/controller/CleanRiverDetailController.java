package com.cetccity.operationcenter.webframework.environment.controller;

import com.cetccity.operationcenter.webframework.environment.api.CleanRiverDetailApi;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverDetailService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:31 2019-05-27
 * Updater:     heliangming
 * Update_Date：17:31 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CleanRiverDetailController implements CleanRiverDetailApi {

    @Autowired
    CleanRiverDetailService cleanRiverDetailService;

    public PageInfo findPatrolRecord(String id, Integer pageNum, Integer pageSize){
        return cleanRiverDetailService.findPatrolRecord(id, pageNum, pageSize);
    }
}
