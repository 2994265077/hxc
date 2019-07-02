package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.Data;

import java.util.List;

/**List<EMERGENCY_MATERIAL_MEM> eMERGENCY_MATERIAL_MEM_list = eMERGENCY_MATERIAL_MEMMapper.getEMERGENCY_MATERIAL_MEM(eMERGENCY_MATERIAL_MEM);
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
        return theadTbodyModel;
 */
@Data
public class TheadTbodyModel {

    private String thead[];

    private List<String[]> tbody;

}
