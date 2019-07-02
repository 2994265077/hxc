/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: LinkedManController
 * Author:   YHY
 * Date:     2019/6/6 9:38
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.linked.controller;

import com.cetccity.operationcenter.webframework.backstage.linked.entity.LinkedMan;
import com.cetccity.operationcenter.webframework.backstage.linked.service.LinkedManService;
import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.oa.model.DeptNode;
import com.cetccity.operationcenter.webframework.oa.model.DeptUserInfo;
import com.cetccity.operationcenter.webframework.oa.model.Employee;
import com.cetccity.operationcenter.webframework.oa.service.EmployeeInfoService;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedList;
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
@Api(tags = "后台接口-联系人（通讯录）管理")
@RequestMapping("/backstage/linkedMan")
public class LinkedManController {
    @Autowired
    private EmployeeInfoService employeeInfoService;


    @ApiOperation("查询所有")
    @GetMapping("/")
    public HttpResponseModel<List<DeptNode>> get() {
        return HttpResponseModel.defaultSuccess(employeeInfoService.queryDeptWithUser());
    }

    @ApiOperation("根据IDs进行查询")
    @GetMapping("/page/")
    public HttpResponseModel<PageInfo<Employee>> page(String ids, PageParam page) {
        if (StringUtils.isBlank(ids)) {
            return HttpResponseModel.defaultSuccess(PageInfo.of(new LinkedList<>()));
        }
        List<String> strings = Arrays.asList(ids.split(","));
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Employee> employees = employeeInfoService.queryByUserIds(strings);
        PageInfo<Employee> pageInfo = PageInfo.of(employees);
        return HttpResponseModel.defaultSuccess(pageInfo);
    }

}