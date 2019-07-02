package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cetccity.operationcenter.webframework.oa.model.DeptInfo;
/**
 * 
 * @author zhutongyu
 * @date 2019年6月11日
 * @desc 部门信息
 */
@Mapper
public interface DeptMapper {
   public List<DeptInfo> queryDeptInfo(String parentDeptId);
   
   public int deleteDept();
   
   public int batchInsertDeptInfo(List<DeptInfo> deptInfoList);
}
