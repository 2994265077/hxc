package com.cetc.cloud.datasynch.provider.controller;

import com.cetc.cloud.datasynch.api.model.DsOuterJobModel;
import com.cetc.cloud.datasynch.api.service.OuterUrlsRemoteService;
import com.cetc.cloud.datasynch.provider.service.impl.OuterUrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * PackageName:   com.cetc.cloud.datasynch.provider.controller
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/12/14 17:30
 * Updater:     by luolinjie
 * Update_Date: 2018/12/14
 * Update_Description: luolinjie 补充
 **/
@Controller
public class OuterUrlsController implements OuterUrlsRemoteService {
    @Autowired
    OuterUrlsService outerUrlsService;

    @Override
    public DsOuterJobModel getModelByTableName(String tableName) {
        return outerUrlsService.getModelByTableName(tableName);
    }
}
