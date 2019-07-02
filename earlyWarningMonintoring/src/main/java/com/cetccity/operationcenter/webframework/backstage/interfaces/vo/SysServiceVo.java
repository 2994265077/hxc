package com.cetccity.operationcenter.webframework.backstage.interfaces.vo;

import java.util.List;

import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysService;
import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceParam;

import lombok.Data;

/**
 * 
 * @author ZHUTONGYU
 * Description: 用于接口管理－新增接口配置
 * 2019年5月8日
 *
 */
@Data
public class SysServiceVo extends SysService {

    private static final long serialVersionUID = 2815839745897908438L;

    private List<SysServiceParam> paramList;
}
