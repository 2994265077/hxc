package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotDeviceMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotEventMapper;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.IOT_EVENT;
import com.cetccity.operationcenter.webframework.web.model.iot.IOT_DEVICE;
import com.cetccity.operationcenter.webframework.web.model.iot.YilaodianAlarmModel;
import com.cetccity.operationcenter.webframework.web.service.IOT_EVENTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("iOT_EVENTService")
public class IOT_EVENTServiceImpl implements IOT_EVENTService {

    @Autowired
    IotEventMapper iOT_EVENTMapper;

    @Autowired
    IotDeviceMapper iOT_DEVICEMapper;

    public YilaodianAlarmModel  getIOT_EVENT_list(String EVENT_CODE){
        YilaodianAlarmModel yilaodianAlarmModel = new YilaodianAlarmModel();
        LinkedHashMap<String,Integer> map_name = new LinkedHashMap();
        LinkedHashMap<String,Integer> map = new LinkedHashMap();
        Integer total = 0;
        List<IOT_EVENT> iOT_EVENT_list = iOT_EVENTMapper.getIOT_EVENT_list(EVENT_CODE);
        for (IOT_EVENT iOT_EVENT:iOT_EVENT_list) {
            if(map.get(iOT_EVENT.getDEVICE_CODE())==null) {
                map.put(iOT_EVENT.getDEVICE_CODE(), 1);
            }else{
                map.put(iOT_EVENT.getDEVICE_CODE(), map.get(iOT_EVENT.getDEVICE_CODE())+1);
            }
        }
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            total += entry.getValue();
            String DEVICE_CODE = entry.getKey();
            List<IOT_DEVICE> iOT_DEVICE_list = iOT_DEVICEMapper.getIOT_DEVICE_list(DEVICE_CODE);
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(iOT_DEVICE_list.get(0).getADDRESS());
            nameValueModel.setValue(String.valueOf(entry.getValue()));
            nameValueModel_list.add(nameValueModel);*/
            nameValueModel_list.add(NameValueModel.builder().name(iOT_DEVICE_list.get(0).getADDRESS()).value(String.valueOf(entry.getValue())).build());
        }
        yilaodianAlarmModel.setName("三防隐患");
        yilaodianAlarmModel.setSubTitle("易涝点预警频次排行");
        yilaodianAlarmModel.setTotal(total);
        yilaodianAlarmModel.setData(nameValueModel_list);
        return yilaodianAlarmModel;
    }

}
