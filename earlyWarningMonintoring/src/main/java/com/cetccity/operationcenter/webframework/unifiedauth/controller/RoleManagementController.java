/**
 *
 * Created by HZZ on 2018/10/31.
 *
 */
package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import com.cetccity.operationcenter.webframework.backstage.log.util.LogAnnotation;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.RoleManagementApi;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.RoleUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RolePermissionRelationVo;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.RoleManagementServiceImpl;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HZZ on 2018/10/31.
 */
@RestController
public class RoleManagementController implements RoleManagementApi {

    @Autowired
    RoleManagementServiceImpl roleManagementService;

    @LogAnnotation(module="添加角色")
    @Override
    public HttpResponseModel<Boolean> addRole(RoleEntity role, String[] permission_ids) {
        return roleManagementService.addRole(role, permission_ids);
    }

    @LogAnnotation(module="查询角色")
    @Override
    public HttpResponseModel<List<RoleEntity>> queryAllRoles(HttpServletRequest request) {
        return roleManagementService.queryAllRoles();
    }

    @LogAnnotation(module="查询角色")
    @ApiOperation(value = "分页查询所有角色", notes = "分页查询所有角色")
    @GetMapping(value = "/role/page", produces = "application/json")
    @ResponseBody
    public HttpResponseModel<PageInfo<RoleEntity>> pageAllRoles(PageParam page, HttpServletRequest request) {
        return HttpResponseModel.defaultSuccess(roleManagementService.pageAllRoles(page));
    }

    @Override
    public HttpResponseModel<List<PermissionEntity>> queryRoleInfo(String role_id) {
        return roleManagementService.queryRoleInfo(role_id);
    }

    @LogAnnotation(module="查询角色所有权限")
    @ApiOperation(value = "查询角色所有权限id", notes = "查询角色所有权限id")
    @ApiImplicitParam(name = "role_id", value = "角色名", paramType = "query", required = true, dataType = "string")
    @RequestMapping(value = "/role/permission", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public HttpResponseModel<List<String>> queryPermissionIds(String role_id, HttpServletRequest request) {
        return HttpResponseModel.defaultSuccess(roleManagementService.queryPermissionIds(role_id));
    }

    @Override
    public HttpResponseModel<Boolean> updateRole(RoleUpdateModel role) {
        return roleManagementService.updateRole(role);
    }

    @LogAnnotation(module="授权")
    @ApiOperation(value = "授权", notes = "授权")
    @PostMapping(value = "/role/relation", produces = "application/json")
    @ResponseBody
    public HttpResponseModel relationChange(@RequestBody @Validated RolePermissionRelationVo permissionRelationVo, HttpServletRequest request) {
        roleManagementService.updateRelation(permissionRelationVo);
        return HttpResponseModel.defaultSuccess("操作成功");
    }

}
