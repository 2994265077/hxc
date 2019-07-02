package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.oa.model.DeptInfo;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.DeptServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author zhutongyu
 * @date 2019年6月12日
 * @desc 部门组织结构
 */
@Api(tags = "后台管理－组织机构管理")
@RestController
@RequestMapping("org")
@Slf4j
public class OrgController {
	@Autowired
	private DeptServiceImpl deptService;
	/**
	 * 部门信息查询
	 * @param parentDeptId
	 * @return
	 */
    @ApiImplicitParam(name = "parentDeptId", value = "上级组织机构id,当为空时，查询一级组织机构", paramType = "query", required = true, dataType = "string")
	@PostMapping("query/{parentDeptId}")
	public HttpResponseModel<List<DeptInfo>> queryDeptInfo(@PathVariable("parentDeptId") String parentDeptId) {
		HttpResponseModel<List<DeptInfo>> model = new HttpResponseModel<List<DeptInfo>>(SysCode.SYS_SUCCESS_CODE);
		model.setData(deptService.queryDeptInfo(parentDeptId));
		return model;
	}
}
