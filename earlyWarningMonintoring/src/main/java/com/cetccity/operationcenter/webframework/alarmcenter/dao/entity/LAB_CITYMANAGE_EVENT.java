package com.cetccity.operationcenter.webframework.alarmcenter.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:28 2019-07-10
 * Updater:     heliangming
 * Update_Date：14:28 2019-07-10
 * 更新描述:    heliangming 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LAB_CITYMANAGE_EVENT {

    private String cameraId;

    private String eventType;

    private String imageUrl;

    private String videoUrl;

    private LocalDateTime logTime;

    private Long objectId;

    private LocalDateTime yjjcCreateTime;

    private LocalDateTime yjjcUpdateTime;
}
