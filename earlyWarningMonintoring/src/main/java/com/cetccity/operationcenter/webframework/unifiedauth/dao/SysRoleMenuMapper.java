package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;


/**
 * 
 * @author ZHUTONGYU
 * Description:角色菜单
 * 2019年3月26日
 */
@Mapper
public interface SysRoleMenuMapper {

	@Insert("insert into SYS_ROLE_MENU(roleId, menuId) values(#{roleId}, #{menuId})")
	int save(@Param("roleId") String roleId, @Param("menuId") Long menuId);

	int delete(@Param("roleId") String roleId, @Param("menuId") Long menuId);

	@Select("select t.menuId from SYS_ROLE_MENU t where t.roleId = #{roleId}")
	Set<Long> findMenuIdsByRoleId(Long roleId);

	List<SysMenu> findMenusByRoleIds(@Param("roleIds") Set<String> roleIds, @Param("type") Integer type);
	
	List<SysMenu> recursionfindMenu(@Param("ids") List<SysMenu> sysMenus);
}
