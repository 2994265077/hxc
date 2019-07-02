package com.cetccity.operationcenter.webframework.core.tools;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.tools
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:55 2019-03-04
 * Updater:     heliangming
 * Update_Date：9:55 2019-03-04
 * 更新描述:    heliangming 补充
 **/
@Component
public class DsDictUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public List<LinkedHashMap> getDict(String sql){
        List<LinkedHashMap> map_list = oracleOperateService.querySql(sql);
        return map_list;
    }

}
