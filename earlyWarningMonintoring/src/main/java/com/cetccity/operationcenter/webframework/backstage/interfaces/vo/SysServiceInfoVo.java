package com.cetccity.operationcenter.webframework.backstage.interfaces.vo;

import lombok.Data;

import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceInfo;

/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理－接口查询
 * 2019年5月8日
 *
 */
@Data
public class SysServiceInfoVo extends SysServiceInfo {

    private static final long serialVersionUID = 6245479902033369333L;

    private Integer status;
}
