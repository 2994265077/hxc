package com.cetccity.operationcenter.webframework.backstage.interfaces.entity;

import com.cetccity.operationcenter.webframework.web.util.CommonPo;

import lombok.Data;
/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理（详情表）
 * 2019年5月8日
 *
 */
@Data
public class SysServiceInfo extends CommonPo {
	private static final long serialVersionUID = 1L;
	private Long serviceId;
    private String name;
    private String description;
    private String imgPath;
    private Long sort;
    private Long cataId;
    private Long serId;
    private Long sysId;
    private Long companyId;
    private Float schedule;
}