/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: NewAlaramController
 * Author:   YHY
 * Date:     2019/8/2 16:31
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.service.impl.NewAlarmService;
import com.cetccity.operationcenter.webframework.alarmcenter.vo.AlarmLevelCount;
import com.cetccity.operationcenter.webframework.alarmcenter.vo.AlarmTypeModel;
import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo;
import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/8/2
 * @since 1.0.0
 */
@RestController
@Api(tags = "新版预警查询 -- by 杨鸿远")
@RequestMapping("/alarmcenter")
public class NewAlarmController {

    @Autowired
    private CommunityInfoService communityInfoService;
    @Autowired
    private NewAlarmService newAlarmService;

    @GetMapping("/level")
    @ApiOperation("预警等级列表")
    public HttpResponseModel<List<AlarmLevelCount>> levels(String date, String type, String typeV1, String typeV2) {
        LocalDate localDate = null;
        if (StringUtils.isNotBlank(date)) {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return HttpResponseModel.defaultSuccess(newAlarmService.levels(localDate, type, typeV1, typeV2));
    }

    @GetMapping("/typeV1")
    @ApiOperation("一级类型列表")
    public HttpResponseModel<List<NameValueDataModel<Integer>>> typeV1s(String date, String type, String level) {
        LocalDate localDate = null;
        if (StringUtils.isNotBlank(date)) {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return HttpResponseModel.defaultSuccess(newAlarmService.alarmTypeLv1s(localDate, type, level));
    }

    @GetMapping("/typeV2")
    @ApiOperation("二级类型列表")
    public HttpResponseModel<List<AlarmTypeModel>> typeV2s(String date, String type, String level, String typeV1) {
        LocalDate localDate = null;
        if (StringUtils.isNotBlank(date)) {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return HttpResponseModel.defaultSuccess(newAlarmService.alarmTypeLv2s(localDate, type, level, typeV1));
    }

    @GetMapping("/type")
    @ApiOperation("预警类型")
    public Object types() {
        return HttpResponseModel.defaultSuccess(newAlarmService.types());
    }

//    @GetMapping("/street")
//    @ApiOperation("街道列表")
//    public HttpResponseModel<List<CommunityInfo>> streets() {
//        return HttpResponseModel.defaultSuccess(communityInfoService.queryStreets());
//    }
//
//    @GetMapping("/community/{streetCode}")
//    @ApiOperation("街道列表 传街道代码")
//    public Object communities(@PathVariable String streetCode) {
//        return HttpResponseModel.defaultSuccess(communityInfoService.queryCommunities(streetCode));
//    }

//    @GetMapping("/map")
//    @ApiOperation("落图")
//    public Object map(String date, String type, String level, String typeV1, String typeV2) {
//        return null;
//    }
//
//    @GetMapping("/")
//    @ApiOperation("预警查询")
//    public Object alarms() {
//        return null;
//    }

}