package com.cetccity.operationcenter.webframework.backstage.interfaces.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysService;

/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理
 * 2019年5月8日
 *
 */
@Mapper
public interface SysServiceMapper {

    int save(SysService sysService);

    int update(SysService sysService);

    SysService findById(Long serviceId);

    @Delete("delete from SYS_SERVICE where SERVICE_ID = #{serviceId}")
    int delete(Long serviceId);

    @Select("select status from SYS_SERVICE where SERVICE_ID = #{serviceId}")
    Integer findStatusById(Long serviceId);

    @Update("update SYS_SERVICE set status = #{status} where SERVICE_ID = #{serviceId}")
    Integer updateStatusById(@Param("serviceId")Long serviceId, @Param("status")Integer status);
}
