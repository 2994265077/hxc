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

import com.cetccity.operationcenter.webframework.unifiedauth.api.SysMenuApi;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.SysMenuMapper;
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
public class SysMenuController implements SysMenuApi {

	@Autowired
	private SysMenuService menuService;

	@Autowired
	private UserInfoUtils userInfoUtils;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * 删除菜单
	 * @param id
	 */
	public Result<String> delete(@PathVariable Long id) {
		try {
			menuService.delete(id);
			return Result.succeed("操作成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			return Result.failed("操作失败");
		}
	}

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
				.collect(Collectors.toMap(SysMenu::getObjectId, SysMenu -> SysMenu));
		for (SysMenu sysMenu : allMenus) {
			Map<String, Object> authTree = new HashMap<>();
			authTree.put("id", sysMenu.getObjectId());
			authTree.put("name", sysMenu.getName());
			authTree.put("pId", sysMenu.getParentId());
			authTree.put("open", true);
			authTree.put("checked", false);
			if (roleMenusMap.get(sysMenu.getObjectId()) != null) {
				authTree.put("checked", true);
			}
			authTrees.add(authTree);
		}
		return authTrees;
	}

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
	public Result<String> setMenuToRole(@RequestBody SysMenu sysMenu) {
		menuService.setMenuToRole(sysMenu.getRoleId(), sysMenu.getMenuIds());
		return Result.succeed("操作成功");
	}

	public HttpResponseModel<List<SysMenu>> findAlls() {
		return new HttpResponseModel<List<SysMenu>>(SysCode.SYS_SUCCESS_CODE, TreeBuilder(menuService.findAll()));
	}

	public HttpResponseModel<List<SysMenu>> findOnes() {
		return new HttpResponseModel<List<SysMenu>>(SysCode.SYS_SUCCESS_CODE, TreeBuilder(menuService.findOnes()));
	}

	/**
	 * 添加菜单 或者 更新
	 * 
	 * @param menu
	 * @return
	 */
	public HttpResponseModel<String> saveOrUpdate(@RequestBody SysMenu menu) {
		try {
			if (menu.getObjectId() != null) {
				menuService.update(menu);
			} else {
				menu.setObjectId(sysMenuMapper.objectIdIncrement());
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
				if (menu.getParentId().equals(sysMenu.getObjectId())) {
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
