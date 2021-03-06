package com.mg.swagger.framework.service;

/**
 * 实现此类才能使用服务器端的存贮功能
 * 
 * @since 2018年8月19日
 */
public interface MgStorageService {
	
	/**
	 * 获取存储的值
	 * 
	 * @since 2018年8月19日
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 存储数据
	 * 
	 * @since 2018年8月19日
	 * @param key
	 * @param value
	 */
	void put(String key, String value);
	
}