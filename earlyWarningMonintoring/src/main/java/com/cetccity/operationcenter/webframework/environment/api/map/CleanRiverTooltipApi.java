package com.cetccity.operationcenter.webframework.environment.api.map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:16 2019-05-21
 * Updater:     heliangming
 * Update_Date：10:16 2019-05-21
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河排水户弹框")
@RequestMapping("/environment")
public interface CleanRiverTooltipApi {

    @ApiOperation(value = "根据表名DB弹框", notes = "排水户--QJHH_SEWERAGE_INFO"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--QJHH_SEWERAGE_INFO", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--AAAUrkAAEAABzdjAAB", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{tableNameUrl}/drainHold/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map summaryInfoToDrainHold(@PathVariable("tableNameUrl") String tableNameUrl, String id);

    @ApiOperation(value = "根据表名DB弹框", notes = "排水户--QJHH_FACILITY_INFO"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--QJHH_FACILITY_INFO", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--AAAUrkAAEAABzdjAAB", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{tableNameUrl}/drainFacilities/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map summaryInfoDrainFacilities(@PathVariable("tableNameUrl") String tableNameUrl,String id);
}
