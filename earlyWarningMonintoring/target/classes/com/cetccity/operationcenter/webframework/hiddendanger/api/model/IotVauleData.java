package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import lombok.Data;

@Data
public class IotVauleData<T> {

    private String iotVaule;

    private T Data;

}
