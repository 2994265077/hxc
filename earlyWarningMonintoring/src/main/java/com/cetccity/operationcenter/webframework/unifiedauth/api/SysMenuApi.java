package com.cetccity.operationcenter.webframework.unifiedauth.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.cetccity.operationcenter.webframework.web.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.unifiedauth.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:30 2019-07-08
 * Updater:     heliangming
 * Update_Date：15:30 2019-07-08
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "菜单模块api")
@RequestMapping("/menus")
public interface SysMenuApi {

    /**
     * 删除菜单
     *
     * @param id
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{id}")
    Result<String> delete(@PathVariable Long id);

    @ApiOperation(value = "根据roleId获取对应的菜单")
    @GetMapping("/{roleId}/menus")
    List<Map<String, Object>> findMenusByRoleId(@PathVariable String roleId);

    @ApiOperation(value = "根据roleId获取对应的菜单列表")
    @GetMapping("/{roleId}/listMenus")
    HttpResponseModel<List<SysMenu>> findMenuListByRoleId(@PathVariable String roleId);

    /**
     * 给角色分配菜单
     */
    @ApiOperation(value = "角色分配菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "1", name = "roleId", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "[1,2]", name = "menuIds", dataType = "long", paramType = "query", required = true, defaultValue="1")
    })
    @PostMapping("/granted")
    Result<String> setMenuToRole(@RequestBody SysMenu sysMenu);

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/findAlls")
    HttpResponseModel<List<SysMenu>> findAlls();

    @ApiOperation(value = "获取菜单以及顶级菜单")
    @GetMapping("/findOnes")
    HttpResponseModel<List<SysMenu>> findOnes();

    /**
     * 添加菜单 或者 更新
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("saveOrUpdate")
    HttpResponseModel<String> saveOrUpdate(@RequestBody SysMenu menu);

    /**
     * 当前登录用户的菜单
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/current")
    @ApiOperation(value = "查询当前用户菜单")
    HttpResponseModel<List<SysMenu>> findMyMenu(HttpServletRequest request, Integer type) throws Exception;
}
