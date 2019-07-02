package com.cetccity.operationcenter.webframework.backstage.interfaces.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceInfo;
import com.cetccity.operationcenter.webframework.backstage.interfaces.vo.SysServiceInfoVo;
import com.cetccity.operationcenter.webframework.web.util.PageParam;

/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理
 * 2019年5月8日
 *
 */
@Mapper
public interface SysServiceInfoMapper {

    Long createServiceId();

    int save(SysServiceInfo sysServiceInfo);

    int update(SysServiceInfo sysServiceInfo);

    SysServiceInfo findById(Long serviceId);

    @Delete("delete from SYS_SERVICE_INFO where SERVICE_ID = #{serviceId}")
    int delete(Long serviceId);

    @Delete("delete from SYS_USER_SERVICE where SERVICE_ID = #{serviceId}")
    int deleteUserService(Long serviceId);

    Long count(PageParam<SysServiceInfoVo> param);

    List<SysServiceInfoVo> findByPageParam(PageParam<SysServiceInfoVo> param);
}
