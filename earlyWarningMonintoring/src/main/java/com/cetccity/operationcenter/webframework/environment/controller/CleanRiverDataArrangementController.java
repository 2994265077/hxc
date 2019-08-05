package com.cetccity.operationcenter.webframework.environment.controller;

import com.cetccity.operationcenter.webframework.environment.api.CleanRiverDataArrangementApi;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverDataArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:38 2019-08-05
 * Updater:     heliangming
 * Update_Date：16:38 2019-08-05
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CleanRiverDataArrangementController implements CleanRiverDataArrangementApi {

    @Autowired
    CleanRiverDataArrangementService cleanRiverDataArrangementService;

    public String dataArrangement(){
        return cleanRiverDataArrangementService.dataArrangement();
    }
}
