package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.web.dao.QXFJ_BUILD_ZTZR_VMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.QXFJ_BUILD_ZTZR_V;
import com.cetccity.operationcenter.webframework.web.service.QXFJ_BUILD_ZTZR_VService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service("qXFJ_BUILD_ZTZR_VService")
public class QXFJ_BUILD_ZTZR_VServiceImpl implements QXFJ_BUILD_ZTZR_VService {

    @Autowired
    QXFJ_BUILD_ZTZR_VMapper qXFJ_BUILD_ZTZR_VMapper;

    public LinkedHashMap getQXFJ_BUILD_ZTZR_V(String buildid){
        QXFJ_BUILD_ZTZR_V qXFJ_BUILD_ZTZR_V = new QXFJ_BUILD_ZTZR_V();
        qXFJ_BUILD_ZTZR_V.setBUILD_ID(buildid);
        List<QXFJ_BUILD_ZTZR_V> qXFJ_BUILD_ZTZR_V_list= qXFJ_BUILD_ZTZR_VMapper.getQXFJ_BUILD_ZTZR_V(qXFJ_BUILD_ZTZR_V);
        LinkedHashMap map = new LinkedHashMap();
        if(qXFJ_BUILD_ZTZR_V_list.size()==0) {
            map.put("建筑编号", "未找到");
            map.put("单位名称", "未找到");
            map.put("更新时间", "未找到");
            map.put("责任人", "未找到");
            map.put("责任人联系方式", "未找到");
        }else{
            map.put("建筑编号", qXFJ_BUILD_ZTZR_V_list.get(0).getBUILD_ID());
            map.put("单位名称", qXFJ_BUILD_ZTZR_V_list.get(0).getUNIT_NAME());
            map.put("更新时间", qXFJ_BUILD_ZTZR_V_list.get(0).getADQ_UPDATE_TIME());
            map.put("责任人", qXFJ_BUILD_ZTZR_V_list.get(0).getZTZR_NAME());
            map.put("责任人联系方式", qXFJ_BUILD_ZTZR_V_list.get(0).getZTZR_TEL());
        }
        return map;
    }
}
