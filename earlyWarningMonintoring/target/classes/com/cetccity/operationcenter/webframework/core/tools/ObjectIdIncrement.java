package com.cetccity.operationcenter.webframework.core.tools;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.tools
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:20 2019-02-26
 * Updater:     heliangming
 * Update_Date：9:20 2019-02-26
 * 更新描述:    heliangming 补充
 **/
@Component
public class ObjectIdIncrement {

    @Autowired
    OracleOperateService oracleOperateService;

    public int increment(String tableName){
        String sql = "select max(OBJECT_ID) from "+tableName;
        int num = oracleOperateService.queryCount(sql);
        return num+1;
    }
}
