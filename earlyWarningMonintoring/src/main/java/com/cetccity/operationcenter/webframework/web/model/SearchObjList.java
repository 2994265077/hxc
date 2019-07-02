package com.cetccity.operationcenter.webframework.web.model;

import lombok.Data;

@Data
public class SearchObjList<T> {

    private String name;

    private String address;

    private String tableName;

    private String tag;

    private String layerid;

    private String id;

    private String jd;

    private String wd;

    private T details;

}
