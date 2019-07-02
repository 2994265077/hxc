package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.PageInfo_LoadMap;
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
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:26 2019-04-16
 * Updater:     heliangming
 * Update_Date：11:26 2019-04-16
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "安全隐患一张图--物联网落图")
@RequestMapping("/hiddendanger")
public interface IotMapApi {

    @ApiOperation(value = "根据表名DB落图<因为iot弹框样式不一样，所以iot落图url独立>", notes =
            "物联网-烟雾传感器--IOT_DEVICE@0003  \n"+
                    "物联网-电气火灾监测设备--IOT_DEVICE@0024  \n"+
                    "物联监测--气体传感器--IOT_DEVICE@0021  \n"+
                    "物联监测--地质灾害监测设备--IOT_DEVICE@0023  \n"+
                    "物联网-消防栓水压传感器--IOT_DEVICE@0025  \n"+
                    "物联网-内涝监测设备--易涝点--水位传感器--IOT_DEVICE@0026  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--IOT_DEVICE", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--id", name = "id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "报警--alarm=1", name = "alarm", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/iot/IconType/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<IconTypeLoadMap> findIotByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id, String alarm) throws IOException;

    @ApiOperation(value = "根据表名DB分页", notes = "物联网-烟雾传感器--IOT_DEVICE@0003  \n" +
            "物联网-气体传感器--IOT_DEVICE@0021  \n"+
            "物联网-地质灾害监测设备--IOT_DEVICE@0023  \n"+
            "物联网-电气火灾监测设备--IOT_DEVICE@0024  \n"+
            "物联网-消防水压传感器--IOT_DEVICE@0025  \n"+
            "物联网-内涝监测设备--IOT_DEVICE@0026  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "当前页", name = "pageNum", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "页码", name = "pageSize", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "街道", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/iot/IconType/pageInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo_LoadMap findPageInfoIotByDb(@PathVariable("tableNameUrl") String tableNameUrl, String pageNum, String pageSize, String street) throws IOException;

}
