package com.cetccity.operationcenter.webframework.rundisplay.api.model;

import lombok.Data;

@Data
public class WaterDetailModel<T> {

    private String name;

    private String time;

    private T attr;

    private T data;

    private String url;

}
