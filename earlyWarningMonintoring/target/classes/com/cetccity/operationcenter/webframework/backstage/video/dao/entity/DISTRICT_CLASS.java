package com.cetccity.operationcenter.webframework.backstage.video.dao.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 视频_标签
 * </p>
 *
 * @author LinHaiquan
 * @since 2019-04-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DISTRICT_CLASS {

    /**
     * id
     */
    private Integer OBJECT_ID;

    /**
     * 创建时间
     */

    private LocalDateTime YJJC_CREATE_TIME;

    /**
     * 修改时间
     */
    private LocalDateTime YJJC_UPDATE_TIME;

    /**
     * 类名
     */
    private String NAME;

    /**
     * 创建用户
     */
    private String USER_NAME;

    /**
     * tag
     */

    private Integer MAIN_ID;

    /**
     * 状态
     */
    private Integer STATUS;
}

