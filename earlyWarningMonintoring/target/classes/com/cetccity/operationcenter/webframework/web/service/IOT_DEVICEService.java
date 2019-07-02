package com.cetccity.operationcenter.webframework.web.service;

import com.cetccity.operationcenter.webframework.web.model.iot.IOT_DEVICE;

import java.util.List;

public interface IOT_DEVICEService {

    List<IOT_DEVICE> getIOT_DEVICE(String iOT_DEVICE);

    List<IOT_DEVICE> getIOT_DEVICE_OBJ(IOT_DEVICE iOT_DEVICE);

    int insertIOT_DEVICE(IOT_DEVICE iOT_DEVICE);

    int deleteIOT_DEVICE(IOT_DEVICE iOT_DEVICE);

}
