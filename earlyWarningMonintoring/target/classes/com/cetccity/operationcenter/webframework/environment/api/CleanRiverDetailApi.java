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
 * Create_Date: 17:27 2019-05-27
 * Updater:     heliangming
 * Update_Date：17:27 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河排水设施详情")
@RequestMapping("/environment")
public interface CleanRiverDetailApi {

    @ApiOperation(value = "排水设施详情", notes = "清洁护河排水设施查看巡查记录--QJHH_PATROL")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "排水设施id=LHZJYSK1989", name = "id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "页码--pageNum", name = "pageNum", dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(value = "页行数--pageSize", name = "pageSize", dataType = "int", paramType = "query",example = "10")
    })
    @RequestMapping(value = "/find/patrol/record",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo findPatrolRecord(String id, Integer pageNum, Integer pageSize);

}
