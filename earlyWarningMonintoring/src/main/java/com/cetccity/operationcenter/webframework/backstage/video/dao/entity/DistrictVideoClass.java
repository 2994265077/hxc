package com.cetccity.operationcenter.webframework.backstage.video.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author LinHaiquan
 * @since 2019-03-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictVideoClass {

    private static final long serialVersionUID = 1L;

    private Integer OBJECT_ID;

    private String GB_CODE;

    private Integer CLASS_ID;

    private String CAMERAID;

    private String USER_NAME;

    private LocalDateTime YJJC_CREATE_TIME;

    private LocalDateTime YJJC_UPDATE_TIME;
}
