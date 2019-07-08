package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;

/**
 * 
 * @author ZHUTONGYU
 * Description:菜单
 * 2019年3月26日
 */
@Mapper
public interface SysMenuMapper {

	@Insert("insert into SYS_MENU(ID, parentId, name, url, path, css, sort, type) "
			+ "values (SEQ_SYS_MENU_ID.nextval,#{parentId, jdbcType=VARCHAR}, #{name, jdbcType=VARCHAR}, #{url, jdbcType=VARCHAR} , #{path, jdbcType=VARCHAR} , #{css, jdbcType=VARCHAR}, #{sort, jdbcType=INTEGER}, #{type, jdbcType=INTEGER})")
	int save(SysMenu menu);

	int updateByOps(SysMenu menu);

	@Select("select * from SYS_MENU t where t.ID = #{id}")
	SysMenu findById(Long id);

	@Delete("delete from SYS_MENU where ID = #{id}")
	int delete(Long id);

	@Delete("delete from SYS_MENU where parentId = #{id}")
	int deleteByParentId(Long id);

	@Select("select * from SYS_MENU t order by t.sort")
	List<SysMenu> findAll();

	@Select("select * from SYS_MENU t order by t.sort")
	List<SysMenu> findOnes();
}
