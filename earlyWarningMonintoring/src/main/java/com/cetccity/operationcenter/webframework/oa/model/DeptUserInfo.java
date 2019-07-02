package com.cetccity.operationcenter.webframework.oa.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author zhutongyu
 * @date 2019年6月10日
 * @desc 区委部门用户信息（部门对应用户）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptUserInfo implements Serializable {

	private static final long serialVersionUID = -3002242316978106109L;
	private String id;
	private String name;
	private List<Employee> employeeList;
}
