package com.cetccity.operationcenter.webframework.hiddendanger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 12:05 2019-02-25
 * Updater:     heliangming
 * Update_Date：12:05 2019-02-25
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "安全隐患一张图--缓存管理")
@RequestMapping("/hiddendanger")
public interface CacheManagerApi {

    @ApiOperation(value = "缓存管理--清除缓存", notes = "缓存管理--清除缓存类型  \n" +
            "落图缓存--loadMap  \n" +
            "建筑详情--buildDetail  \n" +
            "安全隐患一张图图表--hiddenDangerChart  \n" +
            "生态环境图表--ecoEnvironmentChart  \n"+
            "所有--  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "缓存类型", name = "key", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/clean/cache",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    void clearAllCache(String key);

}
