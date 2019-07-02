package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotDeviceMapper;
import com.cetccity.operationcenter.webframework.web.model.iot.IOT_DEVICE;
import com.cetccity.operationcenter.webframework.web.service.IOT_DEVICEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOT_DEVICEServiceImpl implements IOT_DEVICEService {

    @Autowired
    IotDeviceMapper iOT_DEVICEMapper;

    public List<IOT_DEVICE> getIOT_DEVICE(String device_type){
        List<IOT_DEVICE> iOT_DEVICE_list = iOT_DEVICEMapper.getIOT_DEVICE_list(device_type);
        return iOT_DEVICE_list;
    }

    public List<IOT_DEVICE> getIOT_DEVICE_OBJ(IOT_DEVICE iOT_DEVICE){
        List<IOT_DEVICE> iOT_DEVICE_list = iOT_DEVICEMapper.getIOT_DEVICE_OBJ(iOT_DEVICE);
        return iOT_DEVICE_list;
    }

    public int insertIOT_DEVICE(IOT_DEVICE iOT_DEVICE){
        iOT_DEVICE.setOBJECT_ID(iOT_DEVICEMapper.getIOT_DEVICE_Increment());
        int result = iOT_DEVICEMapper.insertIOT_DEVICE(iOT_DEVICE);
        return result;
    }

    public int deleteIOT_DEVICE(IOT_DEVICE iOT_DEVICE){
        int result = iOT_DEVICEMapper.deleteIOT_DEVICE(iOT_DEVICE);
        return result;
    }
}
