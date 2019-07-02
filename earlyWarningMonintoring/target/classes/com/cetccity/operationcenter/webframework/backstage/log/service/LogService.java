package com.cetccity.operationcenter.webframework.backstage.log.service;

import com.cetccity.operationcenter.webframework.backstage.log.entity.SysLog;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @author ZHUTONGYU
 * Description: 日志服务
 * 2019年5月21日
 *
 */
public interface LogService {
	void save(SysLog log);
	
	/**
     * 分页查询
     * @param param 查询条件
     * @return
     */
	HttpResponseModel<PageInfo<SysLog>> findByPage(PageParam<SysLog> param);
}
