package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ObjectUtils;
import org.intellij.lang.annotations.JdkConstants.AdjustableOrientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;
import com.cetccity.operationcenter.webframework.unifiedauth.service.SysMenuService;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.cetccity.operationcenter.webframework.web.util.PageResult;
import com.cetccity.operationcenter.webframework.web.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author ZHUTONGYU Description:SysMenuController.java 2019年3月26日
 */
@RestController
@Api(tags = "菜单模块api")
@RequestMapping("/menus")
public class SysMenuController {

	@Autowired
	private SysMenuService menuService;
	@Autowired
	private UserInfoUtils userInfoUtils;
	private static Logger log = LoggerFactory.getLogger(SysMenuController.class);

	/**
	 * 删除菜单
	 * 
	 * @param id
	 */
	@ApiOperation(value = "删除菜单")
	@DeleteMapping("/{id}")
	public Result<String>  delete(@PathVariable Long id) {
		try {
			menuService.delete(id);
			return Result.succeed("操作成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			return Result.failed("操作失败");
		}

	}

	@ApiOperation(value = "根据roleId获取对应的菜单")
	@GetMapping("/{roleId}/menus")
	public List<Map<String, Object>> findMenusByRoleId(@PathVariable String roleId) {
		Set<String> roleIds = new HashSet<String>() {
			{
				add(roleId);
			}
		};
		List<SysMenu> roleMenus = menuService.findByRoles(roleIds, null); // 获取该角色对应的菜单
		List<SysMenu> allMenus = menuService.findAll(); // 全部的菜单列表
		List<Map<String, Object>> authTrees = new ArrayList<>();

		Map<Long, SysMenu> roleMenusMap = roleMenus.stream()
				.collect(Collectors.toMap(SysMenu::getId, SysMenu -> SysMenu));

		for (SysMenu sysMenu : allMenus) {
			Map<String, Object> authTree = new HashMap<>();
			authTree.put("id", sysMenu.getId());
			authTree.put("name", sysMenu.getName());
			authTree.put("pId", sysMenu.getParentId());
			authTree.put("open", true);
			authTree.put("checked", false);
			if (roleMenusMap.get(sysMenu.getId()) != null) {
				authTree.put("checked", true);
			}
			authTrees.add(authTree);
		}
		return authTrees;
	}
	
	@ApiOperation(value = "根据roleId获取对应的菜单列表")
	@GetMapping("/{roleId}/listMenus")
	public HttpResponseModel<List<SysMenu>> findMenuListByRoleId(@PathVariable String roleId){
		Set<String> roleIds = new HashSet<String>() {
			{
				add(roleId);
			}
		};
		return new HttpResponseModel<List<SysMenu>>(SysCode.SYS_SUCCESS_CODE, menuService.findByRoles(roleIds, null));
	}

	/**
	 * 给角色分配菜单
	 */
	@ApiOperation(value = "角色分配菜单")
	@ApiImplicitParams({
         @ApiImplicitParam(value = "1", name = "roleId", dataType = "string", paramType = "query", required = true),
         @ApiImplicitParam(value = "[1,2]", name = "menuIds", dataType = "long", paramType = "query", required = true, defaultValue="1")
    })
	@PostMapping("/granted")
	public Result<String> setMenuToRole(@RequestBody SysMenu sysMenu) {
		menuService.setMenuToRole(sysMenu.getRoleId(), sysMenu.getMenuIds());
		return Result.succeed("操作成功");
	}

	@ApiOperation(value = "查询所有菜单")
	@GetMapping("/findAlls")
	public HttpResponseModel<List<SysMenu>> findAlls() {
		return new HttpResponseModel<List<SysMenu>>(SysCode.SYS_SUCCESS_CODE, TreeBuilder(menuService.findAll()));
	}

	@ApiOperation(value = "获取菜单以及顶级菜单")
	@GetMapping("/findOnes")
	public HttpResponseModel<List<SysMenu>> findOnes() {
		return new HttpResponseModel<List<SysMenu>>(SysCode.SYS_SUCCESS_CODE, TreeBuilder(menuService.findOnes()));
	}

	/**
	 * 添加菜单 或者 更新
	 * 
	 * @param menu
	 * @return
	 */
	@ApiOperation(value = "新增菜单")
	@PostMapping("saveOrUpdate")
	public HttpResponseModel<String> saveOrUpdate(@RequestBody SysMenu menu) {
		try {
			if (menu.getId() != null) {
				menuService.update(menu);
			} else {
				menuService.save(menu);
			}
			return new HttpResponseModel<String>(SysCode.SYS_SUCCESS_CODE, "操作成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new HttpResponseModel<String>(SysCode.OPERATE_FAILED, "操作失败");
		}

	}

	/**
	 * 当前登录用户的菜单
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/current")
	@ApiOperation(value = "查询当前用户菜单")
	public HttpResponseModel<List<SysMenu>> findMyMenu(HttpServletRequest request, Integer type) throws Exception {
		UserRolePermissionEntity userRoleEntity = userInfoUtils.getUserInfo(request);
		Set<RoleEntity> roles = userRoleEntity.getRoleEntities();
		if (CollectionUtils.isEmpty(roles)) {
			return new HttpResponseModel<List<SysMenu>>(SysCode.SYS_SUCCESS_CODE, Collections.emptyList());
		}

		List<SysMenu> menus = menuService
				.findByRoles(roles.parallelStream().map(RoleEntity::getRole_id).collect(Collectors.toSet()), type == null ? Constant.BACK_MENU_TYPE: type);

		return new HttpResponseModel<List<SysMenu>>(SysCode.SYS_SUCCESS_CODE, TreeBuilder(menus));
	}

	/**
	 * 两层循环实现建树
	 * 
	 * @param sysMenus
	 * @return
	 */
	public static List<SysMenu> TreeBuilder(List<SysMenu> sysMenus) {

		List<SysMenu> menus = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : sysMenus) {
			if (ObjectUtils.equals(-1L, sysMenu.getParentId())) {
				menus.add(sysMenu);
			}
			for (SysMenu menu : sysMenus) {
				if (menu.getParentId().equals(sysMenu.getId())) {
					if (sysMenu.getSubMenus() == null) {
						sysMenu.setSubMenus(new ArrayList<>());
					}
					sysMenu.getSubMenus().add(menu);
				}
			}
		}
		return menus;
	}
}
