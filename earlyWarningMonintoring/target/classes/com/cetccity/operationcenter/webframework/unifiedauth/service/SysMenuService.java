package com.cetccity.operationcenter.webframework.unifiedauth.service;

import java.util.List;
import java.util.Set;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;

/**
 * 
 * @author ZHUTONGYU
 * Description:SysMenuService.java
 * 2019年3月26日
 */
public interface SysMenuService {

	/**
	 * 添加菜单
	 * @param menu
	 */
	void save(SysMenu menu);

	/**
	 * 更新菜单
	 * @param menu
	 */
	void update(SysMenu menu);

	/**
	 * 删除菜单
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 角色分配菜单
	 * @param roleId
	 * @param menuIds
	 */
	void setMenuToRole(String roleId, Set<Long> menuIds);

	/**
	 * 角色菜单列表
	 * @param roleIds
	 * @return
	 */
	List<SysMenu> findByRoles(Set<String> roleIds, Integer type);

	/**
	 * 菜单列表
	 * @return
	 */
	List<SysMenu> findAll();

	/**
	 * ID获取菜单
	 * @param id
	 * @return
	 */
	SysMenu findById(Long id);

	/**
	 * 角色ID获取菜单
	 * @param roleId
	 * @return
	 */
	Set<Long> findMenuIdsByRoleId(Long roleId);

	List<SysMenu> findOnes();


}
