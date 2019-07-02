package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.web.dao.EMERGENCY_MATERIAL_MEMMapper;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyModel;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.EMERGENCY_MATERIAL_MEM;
import com.cetccity.operationcenter.webframework.web.service.EMERGENCY_MATERIAL_MEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class EMERGENCY_MATERIAL_MEMServiceImpl implements EMERGENCY_MATERIAL_MEMService {

    @Autowired
    EMERGENCY_MATERIAL_MEMMapper eMERGENCY_MATERIAL_MEMMapper;

    public NameDataModel findEmergencyWarehouseDetails(String name){
        NameDataModel nameDataModel = new NameDataModel();
        EMERGENCY_MATERIAL_MEM eMERGENCY_MATERIAL_MEM = new EMERGENCY_MATERIAL_MEM();
        eMERGENCY_MATERIAL_MEM.setNAME(name);
        List<EMERGENCY_MATERIAL_MEM> eMERGENCY_MATERIAL_MEM_list = eMERGENCY_MATERIAL_MEMMapper.getEMERGENCY_MATERIAL_MEM(eMERGENCY_MATERIAL_MEM);
        String eMERGENCY_top[] = {"名称","数量","金额","主管单位","负责人","联系人","存储方式"};
        List<String[]> eMERGENCY_list = new ArrayList();
        for (EMERGENCY_MATERIAL_MEM eMERGENCY_MATERIAL_MEM_return:eMERGENCY_MATERIAL_MEM_list) {
            String eMERGENCY_tbody[] = {eMERGENCY_MATERIAL_MEM_return.getMATERIAL_NAME(),eMERGENCY_MATERIAL_MEM_return.getMATERIAL_NUM(),
                    eMERGENCY_MATERIAL_MEM_return.getTOTAL_MONEY(),eMERGENCY_MATERIAL_MEM_return.getMANAGE_DEPT(),
                    eMERGENCY_MATERIAL_MEM_return.getFZR(),eMERGENCY_MATERIAL_MEM_return.getCONTACTS(),eMERGENCY_MATERIAL_MEM_return.getSTORAGE_MODE()};
            eMERGENCY_list.add(eMERGENCY_tbody);
        }
        TheadTbodyModel theadTbodyModel = new TheadTbodyModel();
        theadTbodyModel.setThead(eMERGENCY_top);
        theadTbodyModel.setTbody(eMERGENCY_list);
        nameDataModel.setName(eMERGENCY_MATERIAL_MEM_list.get(0).getNAME());
        nameDataModel.setData(theadTbodyModel);
        return nameDataModel;
    }
}
