package com.cetccity.operationcenter.webframework.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetccity.operationcenter.webframework.oa.service.EmployeeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author zhutongyu
 * @date 2019年6月11日
 * @desc 此接口只用于OA用户同步到预警监测（由于OA用户较稳定，所以暂时不启用定时任务）
 */
@Api(tags = "OA数据同步")
@RestController
@RequestMapping("oa")
public class EmployeeInfoController {
	@Autowired
	private EmployeeInfoService employeeInfoService;

	@ApiOperation("同步OA用户")
	@GetMapping("synEmployee")
	public int synEmployee() {
		return employeeInfoService.synEmployee();
	}

	@ApiOperation("同步OA组织机构")
	@GetMapping("synDept")
	public int synDept(){
		return employeeInfoService.synDept();
   }
}
