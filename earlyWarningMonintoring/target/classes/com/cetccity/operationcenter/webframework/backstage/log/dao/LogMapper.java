package com.cetccity.operationcenter.webframework.backstage.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cetccity.operationcenter.webframework.backstage.log.entity.SysLog;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
/**
 * 
 * @author ZHUTONGYU
 * Description:保存日志
 * 2019年5月21日
 */
@Mapper
public interface LogMapper {

	int save(SysLog log);
	
	Long count(PageParam<SysLog> param);
	
	List<SysLog>  findByPage(PageParam<SysLog> param);
	
}
