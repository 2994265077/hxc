package com.cetccity.operationcenter.webframework.backstage.log.controller;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetccity.operationcenter.webframework.backstage.log.entity.SysLog;
import com.cetccity.operationcenter.webframework.backstage.log.service.LogService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;
import com.cetccity.operationcenter.webframework.web.util.NetworkUtil;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author ZHUTONGYU Description: 日志管理 2019年5月21日
 *
 */

@RestController
@Api(tags = "日志管理")
@RequestMapping("/log")
public class LogController {
	@Autowired
	private LogService logService;
	@Autowired
	private UserInfoUtils userInfoUtils;

	@ApiOperation(value = "服务分页查询")
	@PostMapping("/findLogByPage")
	public HttpResponseModel<PageInfo<SysLog>> findLogByPage(@RequestBody PageParam<SysLog> param) {
		return logService.findByPage(param);
	}

	@ApiOperation(value = "保存日志")
	@PostMapping("/save")
	public HttpResponseModel save(String module, String remark, HttpServletRequest request) throws Exception {
		SysLog log = new SysLog();
		log.setIp(NetworkUtil.getIpAddress(request));
		log.setModule(module);
		log.setRemark(remark);
		log.setUserName(userInfoUtils.getAccount(request));
		log.setCreateDate(new Timestamp(new Date().getTime()));
		logService.save(log);
		return new HttpResponseModel();
	}
}
