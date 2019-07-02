package com.cetccity.operationcenter.webframework.backstage.interfaces.entity;

import com.cetccity.operationcenter.webframework.web.util.CommonPo;

import lombok.Data;
/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理（接口参数表）
 * 2019年5月8日
 *
 */
@Data
public class SysServiceParam extends CommonPo {
    private static final long serialVersionUID = -8175312434516069721L;
    private Long paramId;
    private Long parentParamId;
    private Long serviceId;
    private Integer mandatory;
    private String name;
    private String description;
    private Integer transferMethod;
    private Integer valueType;
    private Integer paramType;
}