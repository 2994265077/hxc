package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import com.cetccity.operationcenter.webframework.backstage.log.util.LogAnnotation;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.PermissionManagementApi;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionNode;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.PermissionManagementServiceImpl;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.controller
 * @Project: 31project-Apr4
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/9 11:42
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/9 11:42
 * @Update_Description: huangzezhou 补充
 **/
@RestController
public class PermissionManagementController implements PermissionManagementApi {

    @Autowired
    PermissionManagementServiceImpl permissionManagementService;

    @Override
    public HttpResponseModel<List<PermissionEntity>> queryAllPermissions() {
        return permissionManagementService.queryAllPermissions();
    }

    @Override
    public HttpResponseModel<List<PermissionNode>> queryAllPermissionsTree() {
        return HttpResponseModel.defaultSuccess(permissionManagementService.queryAllPermissionsTree());
    }

    @LogAnnotation(module="查询权限")
    @ApiOperation(value = "分页查询权限", notes = "分页查询权限")
    @GetMapping(value = "/permission/page", produces = "application/json")
    @ResponseBody
    public HttpResponseModel<PageInfo<PermissionEntity>> page(PageParam pageParam, HttpServletRequest request) {
        return HttpResponseModel.defaultSuccess(permissionManagementService.page(pageParam,request));
    }

    @LogAnnotation(module="新增权限")
    @ApiOperation(value = "新增权限", notes = "新增权限")
    @PostMapping(value = "/permission", produces = "application/json")
    @ResponseBody
    public HttpResponseModel<Boolean> save(@RequestBody @Validated PermissionEntity permissionEntity, HttpServletRequest request) {
        return HttpResponseModel.defaultSuccess(permissionManagementService.save(permissionEntity,request));
    }

    @LogAnnotation(module="修改权限")
    @ApiOperation(value = "修改权限", notes = "修改权限")
    @PutMapping(value = "/permission", produces = "application/json")
    @ResponseBody
    public HttpResponseModel<Boolean> update(@RequestBody @Validated PermissionEntity permissionEntity, HttpServletRequest request) {
        return HttpResponseModel.defaultSuccess(permissionManagementService.update(permissionEntity, request));
    }

    @ApiOperation(value = "删除权限", notes = "删除权限")
    @DeleteMapping(value = "/permission/{permission_id}", produces = "application/json")
    @ResponseBody
    public HttpResponseModel<Boolean> delete(@PathVariable("permission_id") String permissionId, HttpServletRequest request) {
        return HttpResponseModel.defaultSuccess(permissionManagementService.delete(permissionId, request));
    }

    @LogAnnotation(module="批量删除权限")
    @ApiOperation(value = "批量删除权限", notes = "批量删除权限")
    @DeleteMapping(value = "/permission/", produces = "application/json")
    @ResponseBody
    public HttpResponseModel<Boolean> deleteAll(HttpServletRequest request, @RequestBody String... permissionIds) {
        return HttpResponseModel.defaultSuccess(permissionManagementService.delete(permissionIds));
    }


}
