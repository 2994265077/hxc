package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.web.dao.HiddenDangerChartMapper;
import com.cetccity.operationcenter.webframework.web.service.HiddenDangerChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hiddenDangerChartService")
public class HiddenDangerChartServiceImpl implements HiddenDangerChartService {

    @Autowired
    HiddenDangerChartMapper hiddenDangerChartMapper;

    public int getSanfangTB_ROAD_DISEASECount(String street_code){
        int rOAD_DISEASECount = hiddenDangerChartMapper.getSanfangTB_ROAD_DISEASECount(street_code);
        return rOAD_DISEASECount;
    }

    public int getBLK_SANXIAO_PLACECount(String street_code){
        int sANXIAO_PLACECount = hiddenDangerChartMapper.getBLK_SANXIAO_PLACECount(street_code);
        return sANXIAO_PLACECount;
    }

    public int getTB_YINHUANFENBU_002Count(String street_code){
        int qAJJ_REPACCIDENT_VCount = hiddenDangerChartMapper.getTB_YINHUANFENBU_002Count(street_code);
        return qAJJ_REPACCIDENT_VCount;
    }
}
