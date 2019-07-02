package com.cetccity.operationcenter.webframework.oa.model;

import java.io.Serializable;
import java.util.Date;

import com.cetccity.operationcenter.webframework.web.util.Constant;

import lombok.Data;

/**
 * 
 * @author zhutongyu
 * @date 2019年6月10日
 * @desc 福田oa用户
 */
@Data
public class Employee implements Serializable {
	private static final long serialVersionUID = 7783727770942175166L;
	private String id;
	private String deptId;
	private String loginName;
	private String name;
	private String email;
	private String officePhone;//办公电话
	private String mobile;//手机
	private String jobTitles;//职务
	private String jobLevel;//职级
	private String pwd;
	private Date updatetime;//最后更新时间
	
	public String getPwd(){
		return Constant.INIT_PWD;
	}
	
}
