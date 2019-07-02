package com.cetccity.operationcenter.webframework.rundisplay.service.impl;

import com.cetccity.operationcenter.webframework.rundisplay.dao.RIVER_INFOMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.RIVER_INFO;
import com.cetccity.operationcenter.webframework.rundisplay.service.RIVER_INFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class RIVER_INFOServiceImpl implements RIVER_INFOService {

    @Autowired
    RIVER_INFOMapper rIVER_INFOMapper;

    public List<RIVER_INFO> getRIVER_INFO(){
        RIVER_INFO rIVER_INFO = new RIVER_INFO();
        List<RIVER_INFO> rIVER_INFO_list = rIVER_INFOMapper.getRIVER_INFO(rIVER_INFO);
        for (RIVER_INFO rIVER_INFO_return:rIVER_INFO_list) {
            LinkedHashMap map = new LinkedHashMap();
            map.put("河段",rIVER_INFO_return.getRIVER_NAME());
            map.put("河长",rIVER_INFO_return.getSTREET_MANAGER_NAME());
            map.put("联系电话","");
        }
        return rIVER_INFO_list;
    }

}
