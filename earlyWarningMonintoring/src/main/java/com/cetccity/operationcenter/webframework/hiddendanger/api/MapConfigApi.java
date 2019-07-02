package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

@Api(tags = "安全隐患一张图--落图Api")
@RequestMapping("/hiddendanger")
public interface MapConfigApi {

    @ApiOperation(value = "安全隐患一张图--落图列表", notes = "安全隐患一张图--落图列表-加载(mapconfig.xml)")
    @RequestMapping(value = "/mapConfig/list",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MapConfig loadXmlOfMapConfig() throws Exception;

    @ApiOperation(value = "王熙--落图列表", notes = "王熙--落图列表-加载(mapconfig.xml)")
    @RequestMapping(value = "/mapConfig/mapdata",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MapConfig loadXmlOfMapConfigWangxi() throws Exception;

    @ApiOperation(value = "周边搜索--落图列表", notes = "王熙--落图列表-加载(mapconfig.xml)")
    @RequestMapping(value = "nearly/mapConfig/mapdata",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MapConfig loadXmlOfMapConfigNearly() throws Exception;

    @ApiOperation(value = "mapconfig.xml", notes = "赵李明--落图列表-加载(mapconfig.xml)")
    @RequestMapping(value = "mapconfig.xml",method = RequestMethod.GET,produces = "application/xml;charset=utf-8")
    @ResponseBody
    Resource loadXmlOfMapConfig_xml() throws Exception;

}
