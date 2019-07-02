package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.core.tools.ImageFileTool;
import com.cetccity.operationcenter.webframework.web.dao.QXFJ_BUILD_IMAGE_VMapper;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.QXFJ_BUILD_IMAGE_V;
import com.cetccity.operationcenter.webframework.web.service.QXFJ_BUILD_IMAGE_VService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class QXFJ_BUILD_IMAGE_VServiceImpl implements QXFJ_BUILD_IMAGE_VService {

    @Autowired
    QXFJ_BUILD_IMAGE_VMapper qXFJ_BUILD_IMAGE_VMapper;

    public NameDataModel getBUILD_IMAGE(String buildid){
        QXFJ_BUILD_IMAGE_V qXFJ_BUILD_IMAGE_V = new QXFJ_BUILD_IMAGE_V();
        qXFJ_BUILD_IMAGE_V.setBUILD_ID(buildid);
        List<QXFJ_BUILD_IMAGE_V> qXFJ_BUILD_IMAGE_V_list = qXFJ_BUILD_IMAGE_VMapper.getQXFJ_BUILD_IMAGE_V(qXFJ_BUILD_IMAGE_V);
        NameDataModel nameDataModel = new NameDataModel();
        LinkedHashMap map = new LinkedHashMap();
        if(qXFJ_BUILD_IMAGE_V_list.isEmpty()){
            map.put("图纸名称", "未找到");
            map.put("图纸类型", "未找到");
            map.put("图纸描述", "未找到");
            map.put("建筑编号", buildid);
            map.put("创建人", "");
            nameDataModel.setName("未找到");
            nameDataModel.setData(map);
        }else {
            //图纸类型--1、主题外观图   2、楼层图
            String imageType;
            if("1".equals(qXFJ_BUILD_IMAGE_V_list.get(0).getTYPE())){
                imageType = "主题外观图";
            }else{
                imageType = "楼层图";
            }
            String Url = qXFJ_BUILD_IMAGE_V_list.get(0).getFILEPATH();
            map.put("图纸名称", qXFJ_BUILD_IMAGE_V_list.get(0).getNAME());
            map.put("图纸类型", imageType);
            map.put("图纸描述", qXFJ_BUILD_IMAGE_V_list.get(0).getDESCRIBE());
            map.put("建筑编号", buildid);
            map.put("创建人", "");
            nameDataModel.setName(ImageFileTool.getBuildImageFileUrl(Url).getData());
            nameDataModel.setData(map);
        }
        return nameDataModel;
    }
}
