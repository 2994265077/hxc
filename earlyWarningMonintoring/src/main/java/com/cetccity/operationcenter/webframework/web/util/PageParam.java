package com.cetccity.operationcenter.webframework.web.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 
 * @author ZHUTONGYU
 * Description: PageParam.java
 * 2019年5月8日
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageParam<T> implements Serializable {

	private static final long serialVersionUID = 4067232661065395143L;

	//当前第几页
	private Integer pageNum;
	//每页显示几条
	private Integer pageSize;
	//具体查询条件
	private T param;
	
	//时间查询参数
	private String beginDate;
	private String endDate;
	
}
