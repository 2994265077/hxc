package com.cetccity.operationcenter.webframework.web.model.iot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IOT_DEVICE {

    private String ID;

    private String NAME;

    private String DEVICE_CODE;

    private String JD84;

    private String WD84;

    private String ADDRESS;

    private String CREATE_TIME;

    private String UPDATE_TIME;

    private String UPDATE_STATUS;

    private String UUID;

    private String REGION;

    private String STREET;

    private String DEVICE_TYPE;

    private String JDSZ;

    private String WDSZ;

    private String REGION_CODE;

    private String STREET_CODE;

    private String COMMUNITY_CODE;

    private Integer OBJECT_ID;

}
