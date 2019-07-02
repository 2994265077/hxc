package com.cetccity.operationcenter.webframework.unifiedauth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.AuthClient;
/**
 * 
 * @author zty
 * @date 2019年6月26日
 * @desc 第三方系统单点登录平台认证
 */
@Mapper
public interface AuthClientMapper {
	AuthClient selectByClientId(@Param("clientId") String clientId,  @Param("clientSecret") String clientSecret);
}
