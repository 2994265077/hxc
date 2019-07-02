package com.cetccity.operationcenter.webframework.backstage.log.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ZHUTONGYU
 * Description:日志实体
 * 2019年5月21日
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {

	private static final long serialVersionUID = -5398795297842978376L;

	private Long id;
	private String ip;
	
    //用户名
	private String userName;
    //归属模块
	private String module;
    //执行方法的参数值
	private String params;
	private String remark;
    //是否执行成功
	private Boolean flag;
	
	private Timestamp createDate;
}
