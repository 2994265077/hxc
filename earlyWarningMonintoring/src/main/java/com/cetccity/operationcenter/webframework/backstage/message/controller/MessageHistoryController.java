/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageHistoryController
 * Author:   YHY
 * Date:     2019/6/6 14:53
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.message.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistory;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistoryUnion;
import com.cetccity.operationcenter.webframework.backstage.message.qo.MessageHistoryQuery;
import com.cetccity.operationcenter.webframework.backstage.message.service.MessageHistoryService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/6
 * @since 1.0.0
 */
@RestController
@RequestMapping("/backstage/message")
@Api(tags = "后台管理--短信历史")
public class MessageHistoryController {
    @Autowired
    private MessageHistoryService messageHistoryService;

    @GetMapping("/")
    @ApiOperation("查询所有短信历史")
    public HttpResponseModel<List<MessageHistory>> select() {
        return HttpResponseModel.defaultSuccess(messageHistoryService.select());
    }

    @GetMapping("/page")
    @ApiOperation("短信历史分页查询")
    public HttpResponseModel<PageInfo<MessageHistory>> page(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<MessageHistory> select = messageHistoryService.select();
        return HttpResponseModel.defaultSuccess(PageInfo.of(select));
    }

    @GetMapping("/num")
    @ApiOperation("查询短信历史和事件数量")
    public HttpResponseModel<List<NameValueTypeModel<Long>>> nums() {
        return HttpResponseModel.defaultSuccess(messageHistoryService.nums());
    }

    @PostMapping("/union")
    @ApiOperation("按条件查询短信事件聚合数据")
    public HttpResponseModel<PageInfo<MessageHistoryUnion>> pageMessageHistoryAlarm(@RequestBody MessageHistoryQuery messageHistoryQuery) {
        return HttpResponseModel.defaultSuccess(messageHistoryService.selectUnion(messageHistoryQuery));
    }

    @GetMapping("/detail/{alarm_id}")
    @ApiOperation("根据预警对象id查询短信历史详情")
    public HttpResponseModel<List<MessageHistory>> detail(@PathVariable("alarm_id") String alarmId) {
        return HttpResponseModel.defaultSuccess(messageHistoryService.selectDetailByAlarm(alarmId));
    }

}