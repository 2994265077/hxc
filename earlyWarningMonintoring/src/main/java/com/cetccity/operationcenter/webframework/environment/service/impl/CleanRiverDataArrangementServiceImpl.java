package com.cetccity.operationcenter.webframework.environment.service.impl;

import com.cetccity.operationcenter.webframework.environment.dao.CleanRiverDataArrangementMapper;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverDataArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:39 2019-08-05
 * Updater:     heliangming
 * Update_Date：16:39 2019-08-05
 * 更新描述:    heliangming 补充
 **/
@Service
public class CleanRiverDataArrangementServiceImpl implements CleanRiverDataArrangementService {

    @Autowired
    CleanRiverDataArrangementMapper cleanRiverDataArrangementMapper;

    public String dataArrangement(){
        //查询排水户对应的数量并去重
        List<HashMap> list = cleanRiverDataArrangementMapper.operationOne();
        list.stream().forEach(u->{
            //根据排水户名称获取排水户id
            List<String> listUid = cleanRiverDataArrangementMapper.operationTwo((String) u.get("NAME"));
            String uid = listUid.get(0);//排水户唯一id
            for (int i = 0; i<listUid.size(); i++) {
                String uid_current = listUid.get(i);//当前排水户id
                //修改排水设施关联的排水户id
                cleanRiverDataArrangementMapper.operationThree(uid,uid_current);
                //修改巡查记录关联的排水户id
                cleanRiverDataArrangementMapper.operationFour(uid,uid_current);
                //删除重复的排水户
                cleanRiverDataArrangementMapper.deleteRepeatHold(uid_current);
            }
        });
        return "success";
    }

}
