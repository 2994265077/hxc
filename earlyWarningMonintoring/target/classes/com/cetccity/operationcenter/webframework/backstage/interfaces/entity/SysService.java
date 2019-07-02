package com.cetccity.operationcenter.webframework.backstage.interfaces.entity;

import lombok.Data;
import com.cetccity.operationcenter.webframework.web.util.CommonPo;
/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理bean（主表）
 * 2019年5月8日
 *
 */
@Data
public class SysService extends CommonPo {
	private static final long serialVersionUID = 1L;
	private Long serviceId;
    private String url;
    private String path;
    private Integer type;
    private Long allowDeptId;
    private Integer status;
}