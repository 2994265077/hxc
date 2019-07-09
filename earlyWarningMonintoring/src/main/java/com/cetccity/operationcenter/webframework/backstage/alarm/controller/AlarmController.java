/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmController
 * Author:   YHY
 * Date:     2019/5/16 11:46
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.controller;

import com.cetccity.operationcenter.webframework.backstage.alarm.domain.AlarmVo;
import com.cetccity.operationcenter.webframework.backstage.alarm.qo.AlarmQueryObject;
import com.cetccity.operationcenter.webframework.backstage.alarm.service.AlarmService;
import com.cetccity.operationcenter.webframework.common.alarm.Enums;
import com.cetccity.operationcenter.webframework.common.qo.DateTimeRange;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/alarm")
@Api(tags = "后台管理--预警管理接口")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @ApiOperation(value = "按条件查询预警", notes = "按条件查询预警")
    @GetMapping("/")
    public HttpResponseModel<PageInfo<AlarmVo>> query(AlarmQueryObject queryVo) {
        DateTimeRange dateTimeRange = new DateTimeRange();
        if (StringUtils.isNotBlank(queryVo.getBegin())) {
            dateTimeRange.setBegin(LocalDateTime.parse(queryVo.getBegin(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (StringUtils.isNotBlank(queryVo.getEnd())) {
            dateTimeRange.setEnd(LocalDateTime.parse(queryVo.getEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        queryVo.setReleaseTimeRange(dateTimeRange);
        return HttpResponseModel.defaultSuccess(alarmService.query(queryVo));
    }

    @ApiOperation(value = "根据主键查询预警", notes = "按条件查询预警")
    @GetMapping("/{id}")
    public HttpResponseModel<AlarmVo> query(@PathVariable("id") String id) {
        return HttpResponseModel.defaultSuccess(alarmService.queryById(id));
    }

    @ApiOperation(value = "推送状态列表", notes = "推送状态列表")
    @GetMapping("/state/send")
    public HttpResponseModel<Enums.SendStateEnum[]> sendState() {
        return HttpResponseModel.defaultSuccess(Enums.SendStateEnum.values());
    }

    @ApiOperation(value = "处置状态列表", notes = "处置状态列表")
    @GetMapping("/state/disposal")
    public HttpResponseModel<Enums.DisposalStateEnum[]> disposalState() {
        return HttpResponseModel.defaultSuccess(Enums.DisposalStateEnum.values());
    }

    @ApiOperation(value = "预警状态", notes = "预警状态")
    @GetMapping("/state/alarm")
    public HttpResponseModel<Enums.AlarmStateEnum[]> alarmState() {
        return HttpResponseModel.defaultSuccess(Enums.AlarmStateEnum.values());
    }
    
}