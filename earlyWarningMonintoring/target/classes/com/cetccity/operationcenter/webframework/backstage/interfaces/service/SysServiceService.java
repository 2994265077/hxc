package com.cetccity.operationcenter.webframework.backstage.interfaces.service;

import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceInfo;
import com.cetccity.operationcenter.webframework.backstage.interfaces.vo.SysServiceInfoVo;
import com.cetccity.operationcenter.webframework.backstage.interfaces.vo.SysServiceVo;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.cetccity.operationcenter.webframework.web.util.PageResult;

/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理
 * 2019年5月8日
 *
 */
public interface SysServiceService {

    /**
     * 创建或修改服务详情
     * @param sysServiceInfo
     * @return serviceId
     */
    Long saveOrUpdateServiceInfo(SysServiceInfo sysServiceInfo);
    /**
     * 创建或修改服务请求信息
     * @param sysServiceView
     * @return serviceId
     */
    SysServiceVo saveOrUpdateService(SysServiceVo sysServiceView);

    /**
     * 删除服务
     * @param serviceId
     * @return
     */
    Integer deleteService(Long serviceId);

    /**
     * 修改服务状态
     * @param serviceId
     * @param status
     * @return
     */
    Integer updateServiceStatus(Long serviceId, Integer status);

    /**
     * 分页查询
     * @param param 查询条件
     * @return
     */
    PageResult<SysServiceInfoVo> findServiceByPage(PageParam<SysServiceInfoVo> param);
}
