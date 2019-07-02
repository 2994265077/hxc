package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cetccity.operationcenter.webframework.oa.model.DeptInfo;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.DeptMapper;
import com.cetccity.operationcenter.webframework.web.util.Constant;

@Service
@Transactional
public class DeptServiceImpl {
	@Autowired
	private DeptMapper DeptMapper;
    public List<DeptInfo> queryDeptInfo(String parentDeptId){
    	//当parentDeptId为null时，查询最顶层部门
    	if(StringUtils.isEmpty(parentDeptId))
    		parentDeptId = Constant.DEPT_PARENT_ID;
    	return DeptMapper.queryDeptInfo(parentDeptId);
    } 
}
