package com.cetccity.operationcenter.webframework.web.util;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @author ZHUTONGYU
 * Description: 分页实体类
 * total 总数
 * code  是否成功
 * data 当前页结果集
 * 2019年5月8日
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = -275582248840137389L;
	private Long total;
	private int code;
	private List<T> data;
}
