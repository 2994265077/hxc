package com.cetccity.operationcenter.webframework.web.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

/**
 * Created by luolinjie on 2018/6/28.
 */
@Component
public class DBOperateService2 {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    public SqlRowSet querySQL(String sql) {
        return primaryJdbcTemplate.queryForRowSet(sql);
    }
}
