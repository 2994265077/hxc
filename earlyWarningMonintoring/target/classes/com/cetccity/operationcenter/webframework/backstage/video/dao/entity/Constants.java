package com.cetccity.operationcenter.webframework.backstage.video.dao.entity;

public interface Constants {
	// 响应code
	String HTTP_RES_CODE_NAME = "code";
	// 响应msg
	String HTTP_RES_CODE_MSG = "msg";
	// 响应data
	String HTTP_RES_CODE_DATA = "data";
	// 响应请求成功
	String HTTP_RES_CODE_200_VALUE = "success";
	// 系统错误
	Integer HTTP_RES_CODE_10004 = 10004;
	// 响应请求成功code
	Integer HTTP_RES_CODE_0 = 0;
	// 系统错误
	Integer HTTP_RES_CODE_500 = 500;
	// 账户禁用
	Integer ACCOUNT_IS_FORBIDDEN_1 = 1;
	// 城区新闻
	 String DISTRICT_NEWS_05 = "05";
	// 专题报告
	String DISTRICT_SUBJECT_06 = "06";
	// 消息
	String DISTRICT_MESSAGE_07 = "07";

	// 周报综述
	String DISTRICT_WEEKLY_REPORT_08 = "08";

	// 行业综述
	String DISTRICT_INDUSTRY_REPORT_09 = "09";

	 // 主菜单
	Integer MENU_FIRST_LENGTH_2 = 2;
	// 二级菜单
	Integer MENU_SECOND_LENGTH_4 = 4;
	// 文章被收藏
	Integer IS_FAVORITES_1 = 1;

	// 文章被收藏
	Integer IS_NOT_FAVORITES_0 = 0;
	// 有授权的权限
	Integer have_permission_1 = 1;
	//文件服务器内网地址
	String FILE_INTERNAL = "file_internal";
	//文件服务器外网地址
	String FILE_PUBLIC = "file_public";
}