package com.cetccity.operationcenter.webframework.oa.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @author zhutongyu
 * @date 2019年6月11日
 * @desc 福田OA部门信息
 */
@Data
public class DeptInfo implements Serializable {
	private static final long serialVersionUID = -6408734588564420771L;
	private String deptId;
	private String parentId;
	private String fullName;
	private String twoName; // 二字简称
	private String threeName;// 三字简称
	private String description;// 描述
	private String dn;// 部门的DN
	private String officeAddress;// 部门地址
	private String phone;
	private String fax;//传真
	private Integer deptLevel;// 增加部门类型：0：其他，1：司局级，部门类型可以根据需要进一步扩展
	private Integer deptType;//机构类别
	private Integer deptIndex;//排序
	private Date updatetime;//更新时间（同步数据时间可以根据此字段判断）
	private Integer isChild;//是否有子部门（大于0为有子部门）

}
