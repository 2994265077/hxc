package com.cetccity.operationcenter.webframework.environment.api;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:36 2019-08-05
 * Updater:     heliangming
 * Update_Date：16:36 2019-08-05
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河排水设施详情")
@RequestMapping("/environment")
public interface CleanRiverDataArrangementApi {

    @ApiOperation(value = "清洁护河数据整理", notes = "清洁护河数据整理")
    @RequestMapping(value = "/data/arrangement",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String dataArrangement();
}
