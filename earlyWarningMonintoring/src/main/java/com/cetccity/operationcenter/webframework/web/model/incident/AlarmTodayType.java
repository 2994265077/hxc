package com.cetccity.operationcenter.webframework.web.model.incident;

import lombok.Data;

@Data
public class AlarmTodayType {

    private String id;

    private String layerid;

    private String code;

    private String type;

    private String content;

    private String time;

    private String company;

    private String systemid;

    /**
     * needPush 跳转综合治理平台
     */
    private boolean hasPush;

    /**
     * showDetail 金融预警跳转
     */
    private boolean showDetail;

    private String alarmLevel;

}
