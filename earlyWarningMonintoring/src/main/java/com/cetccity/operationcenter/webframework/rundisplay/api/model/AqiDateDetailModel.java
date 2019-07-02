package com.cetccity.operationcenter.webframework.rundisplay.api.model;

import lombok.Data;

@Data
public class AqiDateDetailModel<T> {

    private String name;

    private T data;

}
