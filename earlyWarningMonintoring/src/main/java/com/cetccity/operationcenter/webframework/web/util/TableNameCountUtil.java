package com.cetccity.operationcenter.webframework.web.util;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class TableNameCountUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public String getCount(String tableName) {
        String sql = "select count(*) as COUNT from " + tableName;
        List<LinkedHashMap> map_list = oracleOperateService.querySql(sql);
        String count = (String)map_list.get(0).get("COUNT");
        return count;
    }

    public String getCount(String tableName,String column,String columnEntity) {
        String sql = "select count(*) as COUNT from " + tableName + " where "+column+" =" + "\'" + columnEntity + "\'";
        List<LinkedHashMap> map_list = oracleOperateService.querySql(sql);
        String count = (String)map_list.get(0).get("COUNT");
        return count;
    }

}
