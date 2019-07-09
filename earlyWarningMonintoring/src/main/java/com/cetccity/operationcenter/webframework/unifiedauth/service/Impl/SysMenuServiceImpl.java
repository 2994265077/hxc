package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cetccity.operationcenter.webframework.unifiedauth.dao.SysMenuMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.SysRoleMenuMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;
import com.cetccity.operationcenter.webframework.unifiedauth.service.SysMenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper menuDao;
 	@Autowired
	private SysRoleMenuMapper roleMenuDao; 

	@Transactional
	@Override
	public void save(SysMenu menu) {
		menu.setCreateDate(new Date());
		menuDao.save(menu);
		log.info("新增菜单：{}", menu);
	}

	@Transactional
	@Override
	public void update(SysMenu menu) {
		menu.setUpdateDate(new Date());
		menuDao.updateByOps(menu);
		log.info("修改菜单：{}", menu);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		SysMenu menu = menuDao.findById(id);

		menuDao.deleteByParentId(menu.getId());
		menuDao.delete(id);

		log.info("删除菜单：{}", menu);
	}

	@Transactional
	@Override
	public void setMenuToRole(String roleId, Set<Long> menuIds) {
		roleMenuDao.delete(roleId, null);

		if (!CollectionUtils.isEmpty(menuIds)) {
			menuIds.forEach(menuId -> {
				roleMenuDao.save(roleId, menuId);
			});
		}
	}

	@Override
	public List<SysMenu> findByRoles(Set<String> roleIds, Integer type) {
		List<SysMenu> sysMenuList = roleMenuDao.findMenusByRoleIds(roleIds, type);
		if(!CollectionUtils.isEmpty(sysMenuList)){
			//递归查询所有菜单
			sysMenuList = roleMenuDao.recursionfindMenu(sysMenuList);
		}
		return sysMenuList;
	}

	@Override
	public List<SysMenu> findAll() {
		return menuDao.findAll();
	}

	@Override
	public SysMenu findById(Long id) {
		return menuDao.findById(id);
	}

	@Override
	public Set<Long> findMenuIdsByRoleId(Long roleId) {
		return roleMenuDao.findMenuIdsByRoleId(roleId);
	}

	@Override
	public List<SysMenu> findOnes() {
		return menuDao.findOnes();
	}

}
