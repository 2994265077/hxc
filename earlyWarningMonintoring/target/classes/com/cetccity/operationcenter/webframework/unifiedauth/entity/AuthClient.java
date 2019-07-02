package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author zty
 * @date 2019年6月26日
 * @desc 第三方系统单点登录平台认证
 */
@Data
public class AuthClient implements Serializable{
	private static final long serialVersionUID = -8554937427404290833L;
	private Long id;
	private String clientId; // 应用标识
	private String clientSecret;// 应用密钥(bcyt) 加密
	private String clientSecretStr;// 应用密钥(明文)
	private String scope;// 范围
	private String reamrk;
}
