/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageController
 * Author:   YHY
 * Date:     2019/6/6 11:04
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.service.impl.MessageService;
import com.cetccity.operationcenter.webframework.alarmcenter.vo.MessageSend;
import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.oa.model.Dept;
import com.cetccity.operationcenter.webframework.oa.model.DeptNode;
import com.cetccity.operationcenter.webframework.oa.model.NameBase;
import com.cetccity.operationcenter.webframework.oa.model.DeptUserInfo;
import com.cetccity.operationcenter.webframework.oa.service.EmployeeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/6
 * @since 1.0.0
 */
@RestController
@Api(tags = "预警中心--短信操作和发送接口")
@RequestMapping("/alarmcenter")
@CacheConfig(cacheNames = {"message"})
public class MessageController {
    @Autowired
    private EmployeeInfoService employeeInfoService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/linkedMans")
    @ApiOperation("获取联系人树")
    @Cacheable(key = "'linkedMans'")
    public HttpResponseModel<List<DeptNode>> linkedMans() {
        return HttpResponseModel.defaultSuccess(employeeInfoService.queryDeptWithUser());
    }

    @GetMapping("/dept")
    @ApiOperation("获取组织结构列表")
    @Cacheable(key = "'depts'+#deptId")
    public HttpResponseModel<List<? extends NameBase>> depts(String deptId) {
        if (StringUtils.isBlank(deptId)) {
            deptId = "{00000000-0000-0000-0000-000000000000}";
        }
        List<Dept> deptList = employeeInfoService.queryDeptList(deptId);
        if (CollectionUtils.isNotEmpty(deptList)) {
            return HttpResponseModel.defaultSuccess(deptList);
        }
        return HttpResponseModel.defaultSuccess(employeeInfoService.queryUserByDeptId(deptId));
    }

    @GetMapping("/linkedMan/{name}")
    @ApiOperation("根据联系人名称查询联系人")
    @Cacheable(key = "'linkedMansByName' + #name")
    public HttpResponseModel<List<DeptNode>> deptByUserName(@PathVariable("name") String name) {
        return HttpResponseModel.defaultSuccess(employeeInfoService.deptByUserName(name));
    }


    @PostMapping("/message")
    @ApiOperation("发短信")
    public HttpResponseModel<Boolean> sendMessage(@RequestBody MessageSend messageSend) {
        List<String> userIds = messageSend.getUserIds().stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(userIds)) {
            messageSend.setUserIds(userIds);
            return HttpResponseModel.defaultSuccess(messageService.sendMessage(messageSend));
        }
        throw CetcCommonException.defaultException("发送失败，不存在有效联系人");
    }

}