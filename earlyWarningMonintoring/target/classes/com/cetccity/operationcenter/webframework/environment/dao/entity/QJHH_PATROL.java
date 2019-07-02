package com.cetccity.operationcenter.webframework.environment.dao.entity;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:35 2019-05-27
 * Updater:     heliangming
 * Update_Date：17:35 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QJHH_PATROL {

    private String UID;
	private String SEWERATE_ID;
	private String FACILITY_ID;
	private String STATUS;
	private LocalDateTime CHECK_TIME;
    private String WARN_TYPE;
	private String PHOTO;
	private String PERSON_NAME;
	private String MOBILE;
	private String REMARK;
	private String CREATOR;
	private String CREATE_TIME;
    private String LAST_EDITOR;
	private String LAST_EDIT_TIME;
    private String OBJECT_ID;
    private LocalDateTime YJJC_CREATE_TIME;
    private LocalDateTime YJJC_UPDATE_TIME;

}
