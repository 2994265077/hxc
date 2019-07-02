package com.cetccity.operationcenter.webframework.backstage.interfaces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceParam;

/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理
 * 2019年5月8日
 *
 */
@Mapper
public interface SysServiceParamMapper {

    int save(SysServiceParam sysServiceParam);

    int update(SysServiceParam sysServiceParam);

    List<SysServiceParam> findByServiceId(Long serviceId);

    @Delete("delete from SYS_SERVICE_PARAM where PARAM_ID = #{paramId}")
    int delete(Long paramId);

    @Delete("delete from SYS_SERVICE_PARAM where SERVICE_ID = #{serviceId}")
    int deleteByServiceId(Long serviceId);

    /**
     * 删除当前服务无用的请求参数
     * @param serviceId
     * @param paramIdList 在用的请求参数
     * @return
     */
    int deleteOther(@Param("serviceId") Long serviceId,@Param("paramIdList") List<Long> paramIdList);
}
