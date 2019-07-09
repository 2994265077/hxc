package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;

/**
 * 
 * @author ZHUTONGYU
 * Description:菜单
 * 2019年3月26日
 */
@Mapper
public interface SysMenuMapper {


	@Insert("insert into SYS_MENU(OBJECT_ID, parentId, name, url, path, css, sort, type) "
			+ "values (SEQ_SYS_MENU.nextVal,#{parentId, jdbcType=VARCHAR}, #{name, jdbcType=VARCHAR}, #{url, jdbcType=VARCHAR} , #{path, jdbcType=VARCHAR} , #{css, jdbcType=VARCHAR}, #{sort, jdbcType=INTEGER}, #{type, jdbcType=INTEGER})")
	int save(SysMenu menu);

	int updateByOps(SysMenu menu);

	@Select("select t.OBJECT_ID id, t.* from SYS_MENU t where t.OBJECT_ID = #{id}")
	SysMenu findById(Long id);

	@Delete("delete from SYS_MENU where OBJECT_ID = #{id}")
	int delete(Long id);

	@Delete("delete from SYS_MENU where parentId = #{id}")
	int deleteByParentId(Long id);

	@Select("select t.OBJECT_ID id, t.* from SYS_MENU t order by t.sort")
	List<SysMenu> findAll();

	@Select("select t.OBJECT_ID id, t.* from SYS_MENU t order by t.sort")
	List<SysMenu> findOnes();
}
