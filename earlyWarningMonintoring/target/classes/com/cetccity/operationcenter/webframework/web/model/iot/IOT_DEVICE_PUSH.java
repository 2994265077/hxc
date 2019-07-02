package com.cetccity.operationcenter.webframework.web.model.iot;

import lombok.Data;

import java.util.List;

@Data
public class IOT_DEVICE_PUSH<T> {

    private String device_name;

    private List<ADDRESS_INFO> address_info;

    private String flag;

    private String device_code;

    private List<LAT_LON_TYPE> location;

}
