package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(value = "城市体征--城市部件基本面", tags = "城市体征--城市部件基本面")
@RequestMapping("/urbansign")
@CacheConfig(cacheNames = "UrbanComponentApi")
public interface UrbanComponentApi {

    @ApiOperation(value = "楼栋基本面--城市部件数量", notes = "楼栋基本面--城市部件数量-所有BJ**表count")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/leftOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'leftOne' + #street")
    NameValueModel leftOne(String street);

    @ApiOperation(value = "楼栋基本面--城市部件种类", notes = "楼栋基本面--城市部件种类-BJ**表的数量")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/leftTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'leftTwo'")
    NameValueModel leftTwo();

    @ApiOperation(value = "楼栋基本面--柱状图-各街道部件数量", notes = "楼栋基本面--柱状图-各街道部件数量-所有BJ**表count")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/leftThree",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'leftThree' + #street")
    List<NameValueModel> leftThree(String street);

    @ApiOperation(value = "楼栋基本面--柱状图-各部门部件数量", notes = "楼栋基本面--柱状图-各部门部件数量-所有BJ**表count")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/rigthOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightOne' + #street")
    NameDataModel rightOne(String street);

    @ApiOperation(value = "楼栋基本面--柱状图-设备完好度--七大类", notes = "楼栋基本面--柱状图-设备完好度--七大类-mapconfig.xml配置文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/rigthTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightTwo' + #street")
    List<NameValueModel> rightTwo(String street)throws IOException;

    @ApiOperation(value = "楼栋基本面--柱状图-城市部件Top5", notes = "楼栋基本面--柱状图-城市部件Top5-所有BJ**表count")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/rigthThree",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightThree' + #street")
    Map rightThree(String street);
}
