package com.cetccity.operationcenter.webframework.backstage.log.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetccity.operationcenter.webframework.backstage.log.dao.LogMapper;
import com.cetccity.operationcenter.webframework.backstage.log.entity.SysLog;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ZHUTONGYU 
 * Description: 日志服务 
 * 2019年5月21日
 *
 */
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logDao;

	@Override
	public void save(SysLog log) {
		if (log.getCreateDate() == null) {
			log.setCreateDate(new Timestamp(new Date().getTime()));
		}
		if (log.getFlag() == null) {
			log.setFlag(Boolean.TRUE);
		}
		logDao.save(log);
	}

	public HttpResponseModel<PageInfo<SysLog>> findByPage(PageParam<SysLog> param) {
		 PageHelper.startPage(param.getPageNum(), param.getPageSize());
	     PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(logDao.findByPage(param));
	     pageInfo.setTotal(logDao.count(param));
	     return new HttpResponseModel<PageInfo<SysLog>>(0, pageInfo);
	}

}
