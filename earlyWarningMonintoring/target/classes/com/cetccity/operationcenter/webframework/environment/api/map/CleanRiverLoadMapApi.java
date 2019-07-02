package com.cetccity.operationcenter.webframework.environment.api.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:34 2019-05-20
 * Updater:     heliangming
 * Update_Date：14:34 2019-05-20
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河排水户落图")
@RequestMapping("/environment")
public interface CleanRiverLoadMapApi {

    @ApiOperation(value = "落图", notes = "清洁护河排水户--排水户落图--QJHH_SEWERAGE_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--QJHH_SEWERAGE_INFO@1", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--id", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/drainHold/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> LoadMapToDrainHold(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id) throws IOException;


    @ApiOperation(value = "落图", notes = "清洁护河排水户--排水设施落图--QJHH_FACILITY_INFO")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--QJHH_FACILITY_INFO@1", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/drainFacilities/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<IconTypeLoadMap> LoadMapDrainFacilities(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id) throws IOException;

}
