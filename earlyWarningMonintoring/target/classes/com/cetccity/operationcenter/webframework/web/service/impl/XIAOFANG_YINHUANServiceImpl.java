package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.web.dao.XIAOFANG_YINHUANMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.XIAOFANG_YINHUAN;
import com.cetccity.operationcenter.webframework.web.service.XIAOFANG_YINHUANService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xIAOFANG_YINHUANService")
public class XIAOFANG_YINHUANServiceImpl implements XIAOFANG_YINHUANService {

    @Autowired
    XIAOFANG_YINHUANMapper xIAOFANG_YINHUANMapper;

    public int getXIAOFANG_YINHUANTypeContent(String type){
        int XIAOFANG_YINHUAN_Count = xIAOFANG_YINHUANMapper.getXIAOFANG_YINHUANTypeContent(type);
        return XIAOFANG_YINHUAN_Count;
    }
}
