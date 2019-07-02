package com.cetccity.operationcenter.webframework.web.service.db;

/**
 * Created by dongxin on 2018/6/28.
 */
@Deprecated
public class DBOperateService {
    /**
     * 字段格式化大小写
     * @param col
     * @return
     */
    public String formateCol(String col){
        return col.toLowerCase();
    }
}
