package com.cetccity.operationcenter.webframework.rundisplay.api;

import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Api(tags = "运行展现")
@RequestMapping("/rundisplay")
public interface RunDisplayApi {

    @ApiOperation(value = "运行展现--读取xml视列", notes = "运行展现--读取xml视列-加载(city.xml)")
    @RequestMapping(value = "/load/xml",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<City> loadXmlOfTest() throws Exception;

    @ApiOperation(value = "运行展现--功能列表", notes = "运行展现--功能列表-加载(rundisplaymap.xml)")
    @RequestMapping(value = "/mapConfig/mapdata",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MapConfig loadXmlOfFunction() throws Exception;

    /*@ApiOperation(value = "faren.txt", notes = "赵李明--落图列表-加载(faren.txt) url中不能出现 .txt")
    @RequestMapping(value = "faren",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Resource loadTxtOfFunction();*/
}
