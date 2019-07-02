package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import lombok.Data;

@Data
public class IOT_EVENT {
    
    private String ID;

    private String EVENT_ID;

    private String EVENT_CODE;

    private String EVENT_NAME;

    private String EVENT_LEVEL;

    private String EVENT_STATE;

    private String EVENT_DESC;

    private String PRODUCE_TIME;

    private String END_TIME;

    private String DURATION;

    private String UPDATE_TIME;

    private String UPDATE_STATE;

    private String UUID;

    private String DEVICE_CODE;

    private String DATA_CODE;

    private String DATA_VALUE;

    private String PARA_CODE;

    private String PARA_VALUE;

    private String IS_PROCESSED;

    private String PROCESSOR;

    private String PROCESS_UNIT;

    private String PROCESS_SYSTEM;

    private String PROCESS_TIME;

    private String PROCESS_WAY;

}
