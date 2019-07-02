package com.cetccity.operationcenter.webframework.backstage.iot.api;

import com.cetccity.operationcenter.webframework.backstage.iot.dao.entity.ALARM_PUSH_AVILABLE;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.iot
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:32 2019-06-04
 * Updater:     heliangming
 * Update_Date：10:32 2019-06-04
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "后台管理--物联网管理")
@RequestMapping("/backstage")
public interface IotDeviceApi {

    /**
     * 物联网管理top
     */
    @ApiOperation(value = "物联网管理top", notes = "物联网管理top")
    @RequestMapping(value = "iot/top", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel iotDeviceTop();

    /**
     * 接入传感器数量变化
     */
    @ApiOperation(value = "接入传感器数量变化", notes = "接入传感器数量变化")
    @RequestMapping(value = "iot/count/one", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel iotDeviceTrendOne();

    /**
     * 接入各种类型的传感器数量
     */
    @ApiOperation(value = "接入各种类型的传感器数量", notes = "接入各种类型的传感器数量")
    @RequestMapping(value = "iot/count/two", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel iotDeviceTrendTwo();

    /**
     * 触发报警+推送+处理数量
     */
    @ApiOperation(value = "触发报警+推送+处理数量", notes = "触发报警+推送+处理数量")
    @RequestMapping(value = "iot/count/three", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel iotDeviceTrendThree();

    /**
     * 物联网传感器设置列表
     */
    @ApiOperation(value = "物联网传感器设置列表", notes = "物联网传感器设置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "pageNum", name = "pageNum", dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(value = "pageSize", name = "pageSize", dataType = "int", paramType = "query",example = "1")
    })
    @RequestMapping(value = "iot/device/attribute", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel iotDeviceAttributeList(Integer pageNum, Integer pageSize);
}
