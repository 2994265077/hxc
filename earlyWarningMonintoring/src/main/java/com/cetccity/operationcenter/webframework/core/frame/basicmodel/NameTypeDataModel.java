package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.Data;

@Data
public class NameTypeDataModel<T> {

    private String name;

    private String type;

    private String smooth;

    private T data;
}
