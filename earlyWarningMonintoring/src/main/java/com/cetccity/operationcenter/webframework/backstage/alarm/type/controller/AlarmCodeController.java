/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmCodeController
 * Author:   YHY
 * Date:     2019/5/16 11:10
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.type.controller;

import com.cetccity.operationcenter.webframework.backstage.alarm.type.entity.AlarmCode;
import com.cetccity.operationcenter.webframework.backstage.alarm.type.service.AlarmCodeService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@Api(tags = "后台管理--预警等级管理接口")
@RequestMapping("/alarm/type")
@RestController
public class AlarmCodeController {
    @Autowired
    private AlarmCodeService alarmCodeService;

    @ApiOperation(value = "查询所有预警等级", notes = "查询所有预警等级")
    @GetMapping("/")
    public HttpResponseModel<List<AlarmCode>> all() {
        return HttpResponseModel.defaultSuccess(alarmCodeService.queryAll());
    }

    @ApiOperation(value = "查询所有一级预警等级", notes = "查询所有一级预警等级")
    @GetMapping("/lv1")
    public HttpResponseModel<List<AlarmCode>> allLv1s() {
        return HttpResponseModel.defaultSuccess(alarmCodeService.queryLv1s());
    }
}