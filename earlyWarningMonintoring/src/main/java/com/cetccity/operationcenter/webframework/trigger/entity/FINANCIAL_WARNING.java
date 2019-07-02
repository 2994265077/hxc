package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;
import java.util.Date;

/**
 * 工程包名:   com.cetc.cloud.alarm.trigger.dao.entity
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:19 2019-03-11
 * Updater:     heliangming
 * Update_Date：16:19 2019-03-11
 * 更新描述:    heliangming 补充
 **/
@Data
public class FINANCIAL_WARNING{

	private Double OBJECT_ID;


	private String NAME;


	private String MASS_SOCRE;


    private String REGISTER_ADDRESS;


	private String OPREATE_SCOPE;
	/**
	 * 创建时间
	 */

	private Date YJJC_CREATE_TIME;

	/**
	 * 更新时间
	 */

	private Date YJJC_UPDATE_TIME;

	/**
	 * 推送时间
	 */

	private Date PUSH_TIME;

	/**
	 * 经度84
	 */

	private String JD84;

	/**
	 * 纬度84
	 */

	private String WD84;

	/**
	 * 区代码
	 */

	private String REGION_CODE;

	/**
	 * 街道代码
	 */

	private String STREET_CODE;

	/**
	 * 社区代码
	 */

	private String COMMUNITY_CODE;


	private String ADDRESS;


	private String STREET_NAME;


	private String COMMUNITY_NAME;

	/**
	 * 楼栋代码
	 */

	private String LDDM;
}
