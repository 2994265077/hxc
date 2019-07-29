package com.cetc.cloud.datasynch.provider.service.impl;
/**********************************************************************
 * Copyright (c) 2018 CETC Company
 * 中电科新型智慧城市研究院有限公司版权所有
 * <p>
 * PROPRIETARY RIGHTS of CETC Company are involved in the
 * subject matter of this material. All manufacturing, reproduction, use,
 * and sales rights pertaining to this subject matter are governed by the
 * license agreement. The recipient of this software implicitly accepts
 * the terms of the license.
 * 本软件文档资料是中电科新型智慧城市研究院有限公司的资产，任何人士阅读和
 * 使用本资料必须获得相应的书面授权，承担保密责任和接受相应的法律约束。
 *************************************************************************/

import com.baomidou.dynamic.datasource.annotation.DS;
import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.controller.SequenceManagerController;
import com.cetc.cloud.datasynch.provider.util.ListUtil;
import com.cetc.cloud.datasynch.provider.tools.DbTools;
import com.cetc.cloud.datasynch.provider.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Package: com.cetc.cloud.db_operate.provider.service.impl
 * Project: db_operate
 * Description: 执行SQL语句服务简单封装
 * Creator: huangzezhou
 * Create_Date: 2018/7/11 9:36
 * Updater: huangzezhou
 * Update_Date: 2018/7/11 9:36
 * Update_Description: huangzezhou 补充
 **/
@Service("dbOperateService")
@Slf4j
@DS("master")
public class DbOperateService {
    //    @Qualifier("jdbcTemplate")
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ColumnMappingService columnMappingService;

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String orclUsername;

    @Autowired
    SequenceManagerController sequenceManagerController;

    private Properties tbSeqMappingProp;

    /**
     * 获取 <表名,List<HashMap></><字段名,数据类型 > >组成的Map
     * 输出keyList：table_name,column_name,data_type
     *
     * @return
     * @throws SQLException
     */
    public HashMap<String, HashMap> queryTableStructure() throws SQLException {

        String SQL = "SELECT table_name,column_name,data_type \n" +
                "FROM user_tab_columns \n" +
                "WHERE table_name in(\n" +
                "SELECT table_name from user_all_tables \n" +
                ")";
        List<HashMap> list = oracleQuerySql(SQL);
        // 结果集 HashMap的Key是不能重复的，因此，每次put相同的key，都会将其value覆盖
        HashMap<String, HashMap> resMap = new HashMap<String, HashMap>();
        // 筛选存储当前业务库表的集合
        HashSet<String> tableNameSet = new HashSet<String>();

        for (HashMap<String, String> map : list) {
            String tableName = map.get("TABLE_NAME");
            String column_name = map.get("COLUMN_NAME");
            String data_type = map.get("DATA_TYPE");
            if (tableNameSet.contains(tableName)) {
                HashMap hashMap = resMap.get(tableName);
                hashMap.put(column_name, data_type);
            } else {
                tableNameSet.add(tableName);//添加到表名集合中，供下次检索
                HashMap<String, String> newMap = new HashMap();
                newMap.put(column_name, data_type);
                resMap.put(tableName, newMap);//第一次添加一条映射关系
            }
        }

        return resMap;
    }

    /**
     * 获取 <表名,List<HashMap></><字段名,数据类型 > >组成的Map
     * 输出keyList：table_name,column_name,data_type
     *
     * @return
     * @throws SQLException
     */
    public HashMap<String, HashMap> queryTableStructureByTableName(String tableName) {

        String SQL = "SELECT table_name,column_name,data_type \n" +
                "FROM user_tab_columns \n" +
                "WHERE table_name ='" + tableName + "'";
        List<HashMap> list = null;
        try {
            list = oracleQuerySql(SQL);
        } catch (Exception e) {
            log.error("SQL Error：" + SQL);
            e.printStackTrace();
        }
        // 结果集 HashMap的Key是不能重复的，因此，每次put相同的key，都会将其value覆盖
        HashMap<String, HashMap> resMap = new HashMap<String, HashMap>();
        // 筛选存储当前业务库表的集合
        HashSet<String> tableNameSet = new HashSet<String>();

        for (HashMap<String, String> map : list) {
            String tableName1 = map.get("TABLE_NAME");
            String column_name = map.get("COLUMN_NAME");
            String data_type = map.get("DATA_TYPE");
            if (tableNameSet.contains(tableName1)) {
                HashMap hashMap = resMap.get(tableName1);
                hashMap.put(column_name, data_type);
            } else {
                tableNameSet.add(tableName1);//添加到表名集合中，供下次检索
                HashMap<String, String> newMap = new HashMap();
                newMap.put(column_name, data_type);
                resMap.put(tableName1, newMap);//第一次添加一条映射关系
            }
        }

        return resMap;
    }

    public HashMap<String, String> queryTableStructureByTableName2(String tableName) throws SQLException {

        String SQL = "SELECT column_name,data_type \n" +
                "FROM user_tab_columns \n" +
                "WHERE table_name ='" + tableName + "'";
        List<HashMap> list = oracleQuerySql(SQL);
        // 结果集 HashMap的Key是不能重复的，因此，每次put相同的key，都会将其value覆盖
        HashMap<String, String> resMap = new HashMap<String, String>();

        for (HashMap<String, String> map : list) {
            String column_name = map.get("COLUMN_NAME");
            String data_type = map.get("DATA_TYPE");
            resMap.put(column_name, data_type);
        }

        return resMap;
    }

    public List<HashMap> oracleQueryTable(String tbName) throws SQLException {
        List<HashMap> list = new ArrayList<HashMap>();
        SqlRowSet resultSet = null;

        try {
            String sql = "select * from \"" + tbName + "\"";
            log.debug("sql: " + sql);
            resultSet = jdbcTemplate.queryForRowSet(sql);
            while (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
                int columnNumber = resultSetMetaData.getColumnCount();
                LinkedHashMap hashMap = new LinkedHashMap();
                for (int i = 1; i <= columnNumber; ++i) {
                    String colName = resultSetMetaData.getColumnName(i);
                    String colType = resultSetMetaData.getColumnTypeName(i);
                    String value = resultSet.getString(i);

                    hashMap.put(colName, value);
                }
                list.add(hashMap);
            }
        } catch (SQLException e) {
            log.error("database connection error!\n", e);
        }
        return list;
    }

    /**
     * 注意：只能指定返回4个字段
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    @DS("master")
    public List<List> oracleQueryList_4member(String sql) throws SQLException {
        List<List> data = new ArrayList<List>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {
            ArrayList<String> values = new ArrayList<>();
            try {
                String value1 = rs.getString(1);
                values.add(value1);
                String value2 = rs.getString(2);
                values.add(value2);
                String value3 = rs.getString(3);
                values.add(value3);
                int value4 = rs.getInt(4);
                values.add(String.valueOf(value4));
            } catch (Exception e) {
                e.printStackTrace();
            }
            data.add(values);
        }
        log.debug("sql: " + sql);

        return data;
    }

    public List<HashMap> oracleQuerySql(String sql) throws SQLException {
        List<HashMap> data = new ArrayList<HashMap>();
        SqlRowSet rs = null;
        rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {
            int len = rs.getMetaData().getColumnCount();
            LinkedHashMap row = new LinkedHashMap();
            for (int i = 1; i <= len; ++i) {
                String colName = rs.getMetaData().getColumnName(i);
                Object value = rs.getObject(colName);
                row.put(colName, value);
            }
            data.add(row);
        }
        log.debug("sql: " + sql);

        return data;
    }

    /**
     * 注意：只能指定返回一个字段
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public List<String> oracleQueryList(String sql) throws SQLException {
        List<String> data = new ArrayList<String>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {
            String value = rs.getString(1);
            data.add(value);
        }
        log.debug("sql: " + sql);

        return data;
    }

    public List<HashMap> oracleQuerySql(String sql, String urlOracle, String orclUsername, String orclPassword) throws SQLException {
        List<HashMap> data = new ArrayList<HashMap>();
        SqlRowSet rs = null;
        rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {
            int len = rs.getMetaData().getColumnCount();
            LinkedHashMap row = new LinkedHashMap();
            for (int i = 1; i <= len; ++i) {
                String colName = rs.getMetaData().getColumnName(i);
                String value = rs.getString(colName);
                row.put(colName, value);
            }
            data.add(row);
        }
        log.debug("sql: " + sql);

        return data;
    }

    public boolean oracleExecuteSql(String sql) throws SQLException {
//     execute 方法返回一个 boolean 值，以指示第一个结果的形式。
        jdbcTemplate.execute(sql);
        log.debug("sql: " + sql);
        return true;
    }

    public int oracleUpdateSql(String sql) throws SQLException {
        int update = jdbcTemplate.update(sql);
        log.debug("sql: " + sql);
        return update;
    }

//    /**
//     * 批量执行Insert语句
//     * @param sql
//     * @return
//     * @throws SQLException
//     */
//    public int oracleBatchInsertSql(String sql) throws SQLException {
//        int update = jdbcTemplate.update(sql);
//        log.debug("sql: " + sql);
//        return update;
//    }

    /**
     * 批量执行SQL语句
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public int[] oracleBatchSql(String[] sql) throws SQLException {
        int[] ints = jdbcTemplate.batchUpdate(sql);
        int successCount = 0;
        int failCount = 0;
        for (int i : ints) {
            if (i == 1) {
                successCount++;
            } else {
                failCount++;
            }
        }
//        log.info("batch insert res: " + ints);
        log.debug("sql: " + sql);
        return new int[]{successCount, failCount};
    }

    /**
     * 批量执行SQL语句
     *
     * @param sqls
     * @return
     * @throws SQLException
     */
    public int[] oracleBatchSql(List<String> sqls) {
        if (sqls.size() == 0) {
            log.warn("oracleBatchSql : Empty, Canceling invoking Method oracleBatchSql(List<String> sqls)");
            return new int[]{0, 0};
        }
        int[] ints = jdbcTemplate.batchUpdate(ListUtil.toStringArray(sqls));
        int successCount = 0;
        int failCount = 0;
        for (int i : ints) {
            if (i == 1) {
                successCount++;
            } else {
                failCount++;
            }
        }
        log.debug("sql: " + sqls);
        return new int[]{successCount, failCount};
    }


    public void oracleBatchSqlFile(String filePath) throws SQLException, IOException {
        ScriptRunner runner = new ScriptRunner(jdbcTemplate.getDataSource().getConnection());

        runner.setDelimiter(";");//每条命令间的分隔符
        Resources.setCharset(Charset.forName("UTF-8")); //设置字符集,不然中文乱码插入错误
        runner.setLogWriter(null);//设置是否输出日志

        Reader resourceReader = Resources.getResourceAsReader(filePath);
        runner.runScript(resourceReader);
    }


    public List<Integer> insertIntoTargetTable(List<HashMap> queryResult, DsScheduleModel dsScheduleModel) throws SQLException {

        if (tbSeqMappingProp == null) {
            try {
                tbSeqMappingProp = sequenceManagerController.loadMappingExcel();
            } catch (Exception e) {
                log.error("");
            }
        }
        String sequenceName = getSequenceName(dsScheduleModel.getTargetTableName());
        boolean exists = checkIfSequenceExists(sequenceName);
        if (exists == false) {
            createSequence(sequenceName);
        }
        List<Integer> resList = new ArrayList<Integer>();
        //获取目标表的"字段-字段类型" 映射map
        HashMap<String, HashMap> tbStructureMap = queryTableStructureByTableName(dsScheduleModel.getTargetTableName());

        //根据targetTable获取对应的字段映射表(需要过滤掉为null的字段)
        HashMap mapping = columnMappingService.getColumnMappingByTargetTableName(dsScheduleModel.getTargetTableName());
        int successCounter = 0;
        int failCounter = 0;
        List keyList_SQL = new ArrayList<String>();

        if (null == mapping || mapping.size() == 0) {
            resList.add(CommonInstance.FAIL);
            resList.add(successCounter);
            resList.add(failCounter);
            return resList;
        }

        /**要让映射过程可控，就需要以定义的mapping表为参考标准拼接SQL*/
        //遍历mapping，并根据mapping结果集中的key，将值通过映射表映射到数据库中
        Set set = mapping.keySet();//这里以mapping的keyset作为参考表，即使源表中多余的字段，也不会因mapping中没有对应的字段映射而报错
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String k = (String) iterator.next();//mapping的keyName
            keyList_SQL.add(mapping.get(k));
        }

        for (int i = 0; i < queryResult.size(); i++) {
            List valueList_SQL = new ArrayList<String>();
            HashMap valueObj = queryResult.get(i);
            Set set1 = mapping.keySet();//这里以mapping的keyset作为参考表，即使源表中多余的字段，也不会因mapping中没有对应的字段映射而报错
            Iterator iterator1 = set1.iterator();
            while (iterator1.hasNext()) {
                String k = (String) iterator1.next();//mapping的keyName
                valueList_SQL.add(valueObj.get(k));
            }


            String targetTableName = dsScheduleModel.getTargetTableName();
            //根据表名、字段名称集合,与表结构 获取 组装后的SQL值String
            String tableValues = getTableValuesSQLString(targetTableName, keyList_SQL, valueList_SQL, tbStructureMap);
            //异常情况处理：如果不能在业务库中找到这张目标表对应的表结构,则放弃执行该任务
            if (StringUtils.isEmpty(tableValues)) {
                log.error("》》》》》》》》》》》》》》》\n" +
                        "cannot find target table:不能在业务库中找到这张目标表对应的表结构\"" + targetTableName + "\" in targetDB\n");
                resList.add(CommonInstance.ERROR);
                resList.add(0);
                resList.add(0);
                return resList;
            }

            //获取序列nextval值
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT " + sequenceName + ".NEXTVAL FROM DUAL");
            int nextValue = 0;
            while (sqlRowSet.next()) {
                nextValue = sqlRowSet.getInt(1);
            }

            String sql = "INSERT INTO \"" + targetTableName + "\"(" +
                    CommonInstance.GLOBAL_COLNAME_INCRE_ID + "," +
                    //组装key列表
                    ListUtil.getSQLColumnsListWithQuotes(keyList_SQL) + ")" +
                    //组装value列表
                    " VALUES (" + nextValue + "," + tableValues + ")";

            log.debug("sql: " + sql);
            int count = -1;
            try {
                count = jdbcTemplate.update(sql);
            } catch (Exception e) {
                String localizedMessage = e.getLocalizedMessage();
                if (e instanceof UncategorizedSQLException) {
                    UncategorizedSQLException esql = (UncategorizedSQLException) e;
                    int errorCode = esql.getSQLException().getErrorCode();
                    log.error("errorCode:[{}]", errorCode);
                    Throwable cause = esql.getCause();
                    String message = cause.getLocalizedMessage();
                    log.error("errorMessage:[{}]", message);
                    if (errorCode == 12899) {
                        String columnDefine = RegexUtil.regexExtract("(\\\"\\w+\\\".){2}\\\"\\w+\\\"", message);
                        String[] split = columnDefine.replaceAll("\"", "").split("\\.");
                        String ownerName = split[0];
                        String tableName = split[1];
                        String columnName = split[2];
                        String realLen = RegexUtil.regexExtract("实际值: \\d+", message);
                        int realLength = Integer.valueOf(realLen.replace("实际值: ", ""));
                        long expandLen = expandColumnLength(ownerName, tableName, columnName, realLength);
                        log.info("Finished expandColumnLength: Column:[{}] for Table[{}],\n from{} -->to{}", columnName, tableName,
                                realLength, expandLen);
                        log.info("Trying to execute insert SQL again:{}", sql);
                        count = jdbcTemplate.update(sql);
                    }
                } else {
                    log.error(localizedMessage);
                }
            }
            if (count > 0) {
                log.debug("insert successful！");
                successCounter++;
            } else {
                log.debug("insert failed！");
                failCounter++;
            }
        }


        resList.add(CommonInstance.SUCCESS);
        resList.add(successCounter);
        resList.add(failCounter);
        return resList;
    }

    public boolean insertBySQL() throws SQLException {
        String tableValues = "收到十点多发挂号费大概的发过火电饭锅好地方更好的发挂号费的挂号费打工皇帝发过火发过火";
        String sql = "INSERT INTO TEST_INSERT(id,name) VALUES ('" + 1 + "','" + tableValues + "')";

        int count = -1;
        try {
            count = jdbcTemplate.update(sql);
        } catch (Exception e) {
            String localizedMessage = e.getLocalizedMessage();
            if (e instanceof UncategorizedSQLException) {
                UncategorizedSQLException esql = (UncategorizedSQLException) e;
                int errorCode = esql.getSQLException().getErrorCode();
                log.error("errorCode:[{}]", errorCode);
                Throwable cause = esql.getCause();
                String message = cause.getLocalizedMessage();
                log.error("errorMessage:[{}]", message);
                if (errorCode == 12899) {
                    //todo 修改字段长度为合适的长度
                    String columnDefine = RegexUtil.regexExtract("(\\\"\\w+\\\".){2}\\\"\\w+\\\"", message);
                    String[] split = columnDefine.replaceAll("\"", "").split("\\.");
                    String ownerName = split[0];
                    String tableName = split[1];
                    String columnName = split[2];
                    String realLen = RegexUtil.regexExtract("实际值: \\d+", message);
                    int realLength = Integer.valueOf(realLen.replace("实际值: ", ""));
                    long expandLen = expandColumnLength(ownerName, tableName, columnName, realLength);
                    log.info("Finished expandColumnLength: Column:[{}] for Table[{}],\n from{} -->to{}", columnName, tableName,
                            realLength, expandLen);
                    log.info("Trying to execute insert SQL again:{}", sql);
                    count = jdbcTemplate.update(sql);
                }
            } else {
                log.error(localizedMessage);
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private long expandColumnLength(String ownerName, String tableName, String columnName, int reallength) {
        long expandLen = Math.round(reallength * 1.2);
        if (expandLen > 2048) {
            expandLen = 2048;
        }
        String expandSQL = "ALTER TABLE \"" + ownerName + "\".\"" + tableName + "\"\n" +
                "MODIFY ( \"" + columnName + "\" VARCHAR2(" + expandLen + " BYTE) ) ";
        jdbcTemplate.execute(expandSQL);
        return expandLen;
    }

    public List<Integer> insertIntoTargetTableByTableName(List<HashMap> queryResult, String tableName) throws SQLException {

        if (tbSeqMappingProp == null) {
            try {
                tbSeqMappingProp = sequenceManagerController.loadMappingExcel();
            } catch (Exception e) {
                log.error("load file:\"TableSeqNameMapping.properties\" error!");
            }
        }
        List<Integer> resList = new ArrayList<Integer>();
        //获取 "字段-字段类型" 映射map
        HashMap<String, HashMap> tbStructureMap = queryTableStructureByTableName(tableName);

        //根据targetTable获取对应的字段映射表(需要过滤掉为null的字段)
        HashMap mapping = columnMappingService.getColumnMappingByTargetTableName(tableName);
        int successCounter = 0;
        int failCounter = 0;
        List keyList_SQL = new ArrayList<String>();

        if (mapping.size() == 0) {
            log.error("Empty mapping!tableName:" + tableName);
            resList.add(CommonInstance.FAIL);
            resList.add(successCounter);
            resList.add(failCounter);
            return resList;
        }

        /**要让映射过程可控，就需要以定义的mapping表为参考标准拼接SQL*/
        //遍历mapping，并根据mapping结果集中的key，将值通过映射表映射到数据库中
        Set set = mapping.keySet();//这里以mapping的keyset作为参考表，即使源表中多余的字段，也不会因mapping中没有对应的字段映射而报错
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String k = (String) iterator.next();//mapping的keyName
            keyList_SQL.add(mapping.get(k));
        }

        for (int i = 0; i < queryResult.size(); i++) {
            List valueList_SQL = new ArrayList<String>();
            HashMap valueObj = queryResult.get(i);
            Set set1 = mapping.keySet();//这里以mapping的keyset作为参考表，即使源表中多余的字段，也不会因mapping中没有对应的字段映射而报错
            Iterator iterator1 = set1.iterator();
            while (iterator1.hasNext()) {
                String k = (String) iterator1.next();//mapping的keyName
                valueList_SQL.add(valueObj.get(k));
            }


            String targetTableName = tableName;
            //根据表名、字段名称集合,与表结构 获取 组装后的SQL值String
            String tableValues = getTableValuesSQLString(targetTableName, keyList_SQL, valueList_SQL, tbStructureMap);
            //异常情况处理：如果不能在业务库中找到这张目标表对应的表结构,则放弃执行该任务
            if (null == tableValues || "".equals(tableValues)) {
                log.error("cannot find target table:\"" + targetTableName + "\" in targetDB");
                resList.add(CommonInstance.ERROR);
                resList.add(0);
                resList.add(0);
                return resList;
            }

            //获取序列nextval值
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT " + tbSeqMappingProp.getProperty(targetTableName) + ".NEXTVAL FROM DUAL");
            int nextValue = 0;
            while (sqlRowSet.next()) {
                nextValue = sqlRowSet.getInt(1);
            }

            String sql = "INSERT INTO \"" + targetTableName + "\"(" +
                    CommonInstance.GLOBAL_COLNAME_INCRE_ID + "," +
                    //组装key列表
                    ListUtil.getSQLColumnsListWithQuotes(keyList_SQL) + ")" +
                    //组装value列表
                    " VALUES (" + nextValue + "," + tableValues + ")";

            log.debug("sql: " + sql);
            int count = jdbcTemplate.update(sql);
            if (count > 0) {
//                alarmMsgService.pushAlaramInfo(targetTableName, valueObj);
                log.debug("insert successful！");
                successCounter++;
            } else {
                log.debug("insert failed！");
                failCounter++;
            }
        }


        resList.add(CommonInstance.SUCCESS);
        resList.add(successCounter);
        resList.add(failCounter);
        return resList;
    }


    /**
     * 优化版：利用批处理命令，执行insert语句
     *
     * @param queryResult
     * @param tableName
     * @return
     */
    public int[] batchInsertIntoTargetTableByTableName(List<HashMap> queryResult, String tableName) {
        if (null == queryResult) {
            return new int[]{0, 0};
        }
        if (tbSeqMappingProp == null) {
            try {
                tbSeqMappingProp = sequenceManagerController.loadMappingExcel();
            } catch (Exception e) {
                log.error("Error: cannot Execute : DbOperateService：sequenceManagerController.loadMappingExcel(); ");
            }
        }
        List<Integer> resList = new ArrayList<Integer>();
        //获取 "字段-字段类型" 映射map
        HashMap<String, HashMap> tbStructureMap = queryTableStructureByTableName(tableName);

        //根据targetTable获取对应的字段映射表(需要过滤掉为null的字段)
        HashMap mapping = columnMappingService.getColumnMappingByTargetTableName(tableName);
        if (mapping.size() == 0) {
            log.error("Empty mapping!tableName:" + tableName);
            return new int[]{0, queryResult.size()};
        }

        int successCounter = 0;
        int failCounter = 0;
        List keyList_SQL = new ArrayList<String>();
        /**要让映射过程可控，就需要以定义的mapping表为参考标准拼接SQL*/
        //遍历mapping，并根据mapping结果集中的key，将值通过映射表映射到数据库中
        Set set = mapping.keySet();//这里以mapping的keyset作为参考表，即使源表中多余的字段，也不会因mapping中没有对应的字段映射而报错
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String k = (String) iterator.next();//mapping的keyName
            keyList_SQL.add(mapping.get(k));
        }

        ArrayList<String> insertSQLList = new ArrayList<>();

        for (int i = 0; i < queryResult.size(); i++) {
            List valueList_SQL = new ArrayList<String>();
            HashMap valueObj = queryResult.get(i);
            Set set1 = mapping.keySet();//这里以mapping的keyset作为参考表，即使源表中多余的字段，也不会因mapping中没有对应的字段映射而报错
            Iterator iterator1 = set1.iterator();
            while (iterator1.hasNext()) {
                String k = (String) iterator1.next();//mapping的keyName
                valueList_SQL.add(valueObj.get(k));
            }
            String targetTableName = tableName;
            //根据表名、字段名称集合,与表结构 获取 组装后的SQL值String
            String tableValues = getTableValuesSQLString(targetTableName, keyList_SQL, valueList_SQL, tbStructureMap);
            //异常情况处理：如果不能在业务库中找到这张目标表对应的表结构,则放弃执行该任务
            if (null == tableValues || "".equals(tableValues)) {
                log.error("cannot find target table:\"" + targetTableName + "\" in targetDB");
                return new int[]{0, queryResult.size()};
            }

            String sequenceName = getSequenceName(targetTableName);
            boolean seq_blk_cg_evt_atts = checkIfSequenceExists(sequenceName);
            if (seq_blk_cg_evt_atts == false) {
                createSequence(sequenceName);
            }
            String sql = "INSERT INTO \"" + targetTableName + "\"(" +
                    CommonInstance.GLOBAL_COLNAME_INCRE_ID + "," +
                    //组装key列表
                    ListUtil.getSQLColumnsListWithQuotes(keyList_SQL) + ")" +
                    //组装value列表
                    " VALUES (" + sequenceName + ".NEXTVAL," + tableValues + ")";
            insertSQLList.add(sql);
        }

        int[] batchSqlRes = null;
        log.info("ListToInsert.size():" + insertSQLList.size());
        try {
            batchSqlRes = oracleBatchSql(ListUtil.toStringArray(insertSQLList));
            log.info("\nInsert:SuccessCount:" + batchSqlRes[0]);
            log.info("\nInsert:FailCount:" + batchSqlRes[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return batchSqlRes;
    }

    /**
     * 获取序列名称的策略：
     * 优先级1:支持自定义序列名称，需要在\datasynch_provider\src\main\resources\TableSeqNameMapping.properties
     * 路径下面指定表名和序列名的映射关系
     * 优先级2:当在使用默认序列生成规则，sequenceName = SEQ_ + tablename ,当长度超过30时将自动截断
     *
     * @param targetTableName
     * @return
     */
    public String getSequenceName(String targetTableName) {
        String property = null;
        try {
            property = tbSeqMappingProp.getProperty(targetTableName);
        } catch (Exception e) {
            log.info("find no mapping in \\datasynch_provider\\src\\main\\resources\\TableSeqNameMapping.properties \r\n" +
                    "trying to create new SequenceName:");
        }
        if (null == property) {
            property = "SEQ_" + targetTableName;
            if (property.length() > 30) {
                property = property.substring(0, 30);
            }
            log.info("new Sequence Name:" + property);
        }
        return property;
    }

    /**
     * 根据表名、字段名称、字段类型组装SQL插入值
     *
     * @param tableName      表名
     * @param keyList_SQL    目标表的字段列表
     * @param valueList_sql  值列表
     * @param tbStructureMap 字段名称-字段类型 映射表
     * @return 组装后的SQL值部分
     */
    private String getTableValuesSQLString(String tableName, List keyList_SQL, List<String> valueList_sql, HashMap<String, HashMap> tbStructureMap) {

        List<String> valueList = new ArrayList<String>();
        for (int i = 0; i < keyList_SQL.size(); i++) {
            try {
                //通过当前key获取对应的字段类型
                String column_type = (String) tbStructureMap.get(tableName).get(keyList_SQL.get(i));
                //根据字段类型判断输出值的形式（加""或者to_date()）, 拼接至值列表中
                String decoratedColumn = DbTools.getDecoratedColumn(column_type, valueList_sql.get(i));
                if (null == decoratedColumn) {
                    log.error("\n》》》》》》》》》》》》》\n" +
                            "DbOperateService.getTableValuesSQLString():\"tbStructureMap\" cannot find :\""
                            + tableName + "\"corresponding column\"" + keyList_SQL.get(i)
                            + "\"correct column type ,please complete method getDecoratedColumn() ");
                    log.info("relative value:" + valueList_sql.get(i));
                    return null;
                } else {
                    valueList.add(decoratedColumn);
                }
            } catch (Exception e) {
                log.error("column type of " + keyList_SQL.get(i) + " is null! value:" + valueList_sql.get(i));
                return null;
            }
        }
        return ListUtil.toStringWithoutBracket(valueList);
    }


    public boolean checkIfTableExists(String tbName) {
        String sql = "SELECT COUNT(*) COUNT FROM " + tbName;
        try {
            List<HashMap> hashMaps = oracleQuerySql(sql);
            if (hashMaps.size() > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("DbOperateService.checkIfTableExists_readOnly(),SQL:" + sql);
//            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int getTableRowCounts(String tbName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM \"" + tbName + "\"";
        log.debug("sql: " + sql);

        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql);
        String count = null;
        while (resultSet.next()) {
            count = resultSet.getString(1);
        }
        return Integer.parseInt(count);
    }

    public boolean checkIfSequenceExists(String sequenceName) {
        String SQL = "select count(1) COUNT \n" +
                "from dba_sequences \n" +
                "where sequence_owner='" + orclUsername + "' and SEQUENCE_NAME='" + sequenceName + "'";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);
        int count = 0;
        while (sqlRowSet.next()) {
            count = sqlRowSet.getInt("COUNT");
            if (count > 0) {
                log.info("\ncheckIfSequenceExists(" + sequenceName + "):" + true);
            } else {
                log.info("\ncheckIfSequenceExists(" + sequenceName + "):" + false);
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

//    public boolean checkIfSequenceExists_pure(String targetTableName) {
//        String SQL = "select count(1) COUNT \n" +
//                "from dba_sequences \n" +
//                "where sequence_owner='" + orclUsername + "' and SEQUENCE_NAME='" + targetTableName + "'";
//        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);
//        int count = 0;
//        log.info("\nsql:" + SQL);
//        while (sqlRowSet.next()) {
//            count = sqlRowSet.getInt("COUNT");
//            log.info("\nCOUNT:" + count);
//        }
//        if (count > 0) {
//            return true;
//        }
//        return false;
//    }

    public boolean checkIfSequenceExists_prefix_seq(String targetTableName) {
        String SQL = "select count(1) COUNT \n" +
                "from dba_sequences \n" +
                "where sequence_owner='" + orclUsername + "' and SEQUENCE_NAME='SEQ_" + targetTableName + "'";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);
        int count = 0;
        log.info("\nsql:" + SQL);
        while (sqlRowSet.next()) {
            count = sqlRowSet.getInt("COUNT");
            log.info("\nCOUNT:" + count);
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

    public boolean createSequence(String sequenceName) {
        String sql = "create sequence " + sequenceName + "\n" +
                "minvalue 1\n" +
                "maxvalue 999999999999\n" +
                "start with 1\n" +
                "increment by 1\n" +
                "cache 50";
        jdbcTemplate.execute(sql);
        return true;
    }

    public boolean dropSequence(String sequenceName) {
        String sql = "drop sequence " + sequenceName;
        jdbcTemplate.execute(sql);
        return true;
    }

    public boolean checkIfColumnExists(String targetTableName, String columnName) {

        String sql = "SELECT count(*) COUNT " +
                "FROM user_tab_columns" +
                " WHERE TABLE_NAME ='" + targetTableName + "'" +
                " AND column_name= '" + columnName + "'";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
        int count = 0;
        while (sqlRowSet.next()) {
            count = sqlRowSet.getInt(1);
        }

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addColumn(String targetTableName, String columnName, String columnType_len) {

        String sql = "alter table " + targetTableName + " add " + columnName + " " + columnType_len;
        jdbcTemplate.execute(sql);
        return true;
    }

    public boolean addColumnComment(String targetTableName, String columnName, String columnComment) {

        String sql = "COMMENT ON COLUMN \"" + orclUsername + "\".\"" + targetTableName + "\".\"" + columnName + "\" IS \'" + columnComment + "\'";
        jdbcTemplate.execute(sql);
        return true;
    }

    public boolean addTableComment(String targetTableName, String tableComment) {

        String sql = "COMMENT ON TABLE \"" + orclUsername + "\".\"" + targetTableName + "\" IS '" + tableComment + "'";
        jdbcTemplate.execute(sql);
        return true;
    }

    public boolean checkIfExistsTable(String targetTableName) {
        String sql = "SELECT count(1) from user_all_tables " +
                "WHERE table_name ='" + targetTableName + "'";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
        int count = 0;
        while (sqlRowSet.next()) {
            count = sqlRowSet.getInt(1);
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 执行备份
     */
    public String backUpTable(String tableName) {

        //1.在所有拷贝表基础上获取最大数字
        int maxCopyNum = renameCopiesNameAndGetMaxCopyNum(tableName);
        int count = maxCopyNum + 1;
        String newCopyName = tableName + "_CP" + count;

        //oracle表名长度规范：不能超过30字符
        if (newCopyName.length() > 30) {
            newCopyName = newCopyName.replaceAll("_", "");
        }

        boolean ifExistsTable = false;
        while (true) {
            ifExistsTable = checkIfExistsTable(newCopyName);
            if (ifExistsTable == true) {
                newCopyName = tableName + "_CP" + count;
                count++;

            } else {
                log.info("success! Do backup by TableName:" + tableName + "_CP" + count);
                break;
            }
        }
        backUpTable(tableName, newCopyName);
        //删除多余的备份
        try {
            deleteRedundantTableCopies(tableName);
        } catch (Exception e) {
            log.error("" + e.getMessage());
        }


        return newCopyName;
    }

    private void reNameAllCopys(List<String> tableCpNameList, Integer cpMinVersionNum, Integer cpMaxVersionNum) {
        //检查是否是连续的
        for (String cpTbName : tableCpNameList) {


        }

    }

    /**
     * 对目标表副本进行重新命名，然后返回最大副本排序int值
     *
     * @param tableName
     * @return
     */
    private int renameCopiesNameAndGetMaxCopyNum(String tableName) {

        /**
         * 获取所有副本集合
         */
        String SQL = "SELECT TABLE_NAME FROM USER_ALL_TABLES " +
                "WHERE TABLE_NAME LIKE '" + tableName + "_CP%'";
        try {
            List<String> list = oracleQueryList(SQL);
            list.sort(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    String obj1 = ((String) o1).replaceAll(tableName + "_CP", "");
                    String obj2 = ((String) o2).replaceAll(tableName + "_CP", "");
                    if (Integer.valueOf(obj1) > Integer.valueOf(obj2)) {
                        return 1;
                    } else if (Integer.valueOf(obj1) < Integer.valueOf(obj2)) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            //获取最小值
            if (list.size() >= 2) {
                String minTableCpName = list.get(0);
                Integer cpMinVersionNum = Integer.valueOf(minTableCpName.replaceAll(tableName + "_CP", ""));
                String maxTableCpName = list.get(list.size() - 1);
                Integer cpMaxVersionNum = Integer.valueOf(maxTableCpName.replaceAll(tableName + "_CP", ""));
                reNameAllCopys(list, cpMinVersionNum, cpMaxVersionNum);
                //返回cp最大值
                return Integer.valueOf(maxTableCpName.replaceAll(tableName + "_CP", ""));
            } else if (list.size() == 1) {
                String maxTableCpName = list.get(0);
                Integer cpMaxVersionNum = Integer.valueOf(maxTableCpName.replaceAll(tableName + "_CP", ""));
                reNameAllCopys(list, cpMaxVersionNum, cpMaxVersionNum);
                return 0;
            } else {
                return 0;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 执行copy至新表
     *
     * @param srcTableName
     * @param bkTableName
     */
    public void backUpTable(String srcTableName, String bkTableName) {

        //执行备份
        if (checkIfExistsTable(bkTableName) == false) {
            String SQL = "create TABLE \"" + orclUsername + "\".\"" + bkTableName + "\" as  SELECT * FROM " + srcTableName;
            jdbcTemplate.execute(SQL);
        }
        //添加表注释
        copyTableComments(srcTableName, bkTableName);
        //添加default值
        copyTableDefault(srcTableName, bkTableName);
        //添加主键约束，与check(跳过default)、unique约束
        copyTableConstraint(srcTableName, bkTableName);
    }

    /**
     * 为字段添加default值
     *
     * @param srcTableName
     * @param bkTableName
     */
    private void copyTableDefault(String srcTableName, String bkTableName) {
        //增加字段约束
        String SQL2 = "select COLUMN_NAME,DATA_DEFAULT\n" +
                "from user_tab_columns\n" +
                "where table_name='" + srcTableName + "'\n" +
                "and data_default is not null";
        List<HashMap> hashMaps = null;
        try {
            hashMaps = oracleQuerySql(SQL2);
            if (null == hashMaps) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (HashMap map : hashMaps) {
            String column_name = (String) map.get("COLUMN_NAME");
            String data_default = (String) map.get("DATA_DEFAULT");
            int i = executeAlterTableSQL(bkTableName, column_name, data_default);
            if (i > 0) {
                log.info("Add Table Constraint:executeAlterTableSQL(bkTableName, column_name, data_default)" + bkTableName + column_name + data_default);
            }
        }
    }

    private void copyTableConstraint(String srcTableName, String bkTableName) {
        //获取主键约束
        String getPrimaryKeySQL = "select  COLUMN_NAME,POSITION\n" +
                "from user_constraints con,user_cons_columns col\n" +
                "where\n" +
                "con.constraint_name=col.constraint_name and con.constraint_type='P'\n" +
                "and col.table_name='" + srcTableName + "'";
        List<Map<String, Object>> primaryKeyColumnList = jdbcTemplate.queryForList(getPrimaryKeySQL);
        ArrayList primaryKeyList = new ArrayList();
        int count = primaryKeyColumnList.size();
        while (count-- > 0) {
            primaryKeyList.add("");
        }
        if (null != primaryKeyColumnList) {
            for (Map<String, Object> map : primaryKeyColumnList) {
                String primaryKeyColumn = (String) map.get("COLUMN_NAME");
                BigDecimal position = (BigDecimal) map.get("POSITION");
                primaryKeyList.set(Integer.valueOf(position.intValue() - 1), primaryKeyColumn);
            }
        }

        if (null != primaryKeyList && primaryKeyList.size() >= 1) {

            String sqlColumnsListWithQuotes = ListUtil.getSQLColumnsListWithQuotes(primaryKeyList);
            //添加主键
            String addPrimaryKey = "ALTER TABLE \"" + bkTableName + "\" ADD PRIMARY KEY (" + sqlColumnsListWithQuotes + ")";
            jdbcTemplate.execute(addPrimaryKey);
            log.info("added PRIMARY KEY as:" + primaryKeyList);
        } else {
            log.info("sourceTable:" + srcTableName + "doesn't has PRIMARY KEY !");
        }

        //2.获取除 NOT NULL 之外的约束条件
        String getCheckConstraintSQL = "select  SEARCH_CONDITION\n" +
                "from user_constraints con,user_cons_columns col\n" +
                "where\n" +
                "con.constraint_name=col.constraint_name and con.constraint_type='C'\n" +
                "and col.table_name='" + srcTableName + "'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(getCheckConstraintSQL);
        if (CollectionUtils.isNotEmpty(maps)) {
            for (Map<String, Object> checkMap : maps) {
                String checkCondition = (String) checkMap.get("SEARCH_CONDITION");
                if (checkCondition.contains("IS NOT NULL")) continue; //跳过对IS NOT NULL的检查 因为前面已经对默认值做了赋值
                String addChecksql = "ALTER TABLE " + bkTableName + " ADD CHECK (" + checkCondition + ")";
                jdbcTemplate.execute(addChecksql);
                log.info("added CHECK constraint:" + checkCondition);
            }
        } else {
            log.info("source table\"" + srcTableName + "\" has no CHECK constraints to log!");
        }
        //3. 获取Unique约束
        String getUniqueConstraintSQL = "select  COLUMN_NAME,POSITION \n" +
                "from user_constraints con,user_cons_columns col\n" +
                "where\n" +
                "con.constraint_name=col.constraint_name and con.constraint_type='U'\n" +
                "and col.table_name='" + srcTableName + "'";
        List<Map<String, Object>> uniqueMapsList = jdbcTemplate.queryForList(getUniqueConstraintSQL);
        ArrayList uniqueKeyList = new ArrayList();
        int uniqueCount = uniqueMapsList.size();
        while (uniqueCount-- > 0) {
            uniqueKeyList.add("");
        }
        if (CollectionUtils.isNotEmpty(uniqueMapsList)) {
            for (Map<String, Object> map : uniqueMapsList) {
                String uniqueKeyColumn = (String) map.get("COLUMN_NAME");
                BigDecimal position = (BigDecimal) map.get("POSITION");
                uniqueKeyList.set(Integer.valueOf(position.intValue() - 1), uniqueKeyColumn);
            }
        }
        if (CollectionUtils.isNotEmpty(uniqueKeyList)) {

            String uniqueKeyListWithQuotes = ListUtil.getSQLColumnsListWithQuotes(uniqueKeyList);
            String addUniqueSql = "ALTER TABLE " + bkTableName + " ADD UNIQUE (" + uniqueKeyListWithQuotes + ")";
            jdbcTemplate.execute(addUniqueSql);
            log.info("added UNIQUE constraint:" + uniqueKeyListWithQuotes);
        } else {
            log.info("source table\"" + srcTableName + "\" has no UNIQUE constraints to log!");
        }
    }

    public String getTableComments(String srcTableName) {
        if (checkIfExistsTable(srcTableName) == false) {
            return "";
        }
        String tableCommentSQL = "select TABLE_NAME from ALL_TAB_COMMENTS WHERE table_name='" + srcTableName + "'";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(tableCommentSQL);
        if (MapUtils.isNotEmpty(stringObjectMap)) {
            return (String) stringObjectMap.get("TABLE_NAME");
        } else {
            return "";
        }
    }

    private void copyTableComments(String srcTableName, String bkTableName) {
        //1.表名称注释
        String table_comment = getTableComments(srcTableName);
        String formatedDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        // 添加表名称注释
        if (StringUtils.isNotBlank(table_comment)) {
            addTableComment(bkTableName, table_comment + "_" + formatedDate);
        }
        List<HashMap> list = getColumnCommentsByTableName(srcTableName);
        //2.表字段注释
        for (HashMap col : list) {
            //获取表字段注释
            String colName = (String) col.get("COLUMN_NAME");
            String comments = (String) col.get("COMMENTS");
            boolean b = addColumnComment(bkTableName, colName, comments);
            if (b) {
                log.info("add comment on column " + bkTableName + "." + colName + " success!");
            }
        }
    }

    public List<HashMap> getColumnCommentsByTableName(String srcTableName) {
        //获取当前表的所有字段
        String getColList = "SELECT COLUMN_NAME,COMMENTS from ALL_COL_COMMENTS WHERE table_name='" + srcTableName + "'";
        try {
            return oracleQuerySql(getColList);
        } catch (Exception e) {
            log.error("copyTableComments: comment on table error");
        }
        return null;
    }

    public void deleteRedundantTableCopies(String tableName) throws SQLException {

        String SQL = "SELECT TABLE_NAME FROM USER_ALL_TABLES " +
                "WHERE TABLE_NAME LIKE '" + tableName + "_CP%'";
        List<String> list = oracleQueryList(SQL);
        log.info(SQL);
        log.info("\nCopy counts:" + list.size());
        if (list.size() >= 5) { //只留4个备份
            log.info("\nPrepare Delete Redundant Copies");

            list.sort(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    String obj1 = ((String) o1).replaceAll(tableName + "_CP", "");
                    String obj2 = ((String) o2).replaceAll(tableName + "_CP", "");
                    if (Integer.valueOf(obj1) > Integer.valueOf(obj2)) {
                        return 1;
                    } else if (Integer.valueOf(obj1) < Integer.valueOf(obj2)) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            for (int i = 0; i < list.size() - 5; i++) {
                boolean b = dropTable(list.get(i));
                if (b) {
                    log.info("\nSuccessfully Dropped Table:" + list.get(i));
                } else {
                    log.error("\nFailed Dropping Table:" + list.get(i));
                }
            }
        } else {
            return;
        }
    }

    public boolean truncateTableByTbName(String targetTbName) {
        boolean ifExistsTable = checkIfExistsTable(targetTbName);
        if (ifExistsTable) {
            log.info("prepare truncate table:" + targetTbName);
            String sql = "truncate TABLE \"" + orclUsername + "\".\"" + targetTbName + "\"";
            jdbcTemplate.execute(sql);
            log.info("Done! truncate table:" + targetTbName);
            return true;
        } else {
            log.error("\n >>> Table does not exists! tableName:" + targetTbName);
            return false;
        }
    }

    public boolean dropTable(String tableName) {
        boolean ifExistsTable = checkIfExistsTable(tableName);
        if (ifExistsTable) {
            String sql = "DROP TABLE \"" + orclUsername + "\".\"" + tableName + "\"";
            jdbcTemplate.execute(sql);
            return true;
        } else {
            log.error("\n >>> Table does not exists! tableName:" + tableName);
            return false;
        }
    }

    /**
     * 校准序列值
     */
    public boolean correctSequence(String tableName) {
        // 1.获取最大ObjectId
        int maxObjectId = getMaxObjectId(tableName);
        //2.获取nextVal
        int nextSeqVal = getNextSeqVal(tableName);
        //3.获取差值 objectID：215  nextVal：315 目标值：215   215-315= -100
        int differNum = maxObjectId - nextSeqVal;
        String SQL1 = "alter sequence \"" + orclUsername + "\".\"SEQ_" + tableName + "\" increment by " + differNum;
        jdbcTemplate.execute(SQL1);
        int nextSeqVal2 = getNextSeqVal(tableName);
        if (maxObjectId == nextSeqVal2) {
            String SQL2 = "alter sequence SEQ_" + tableName + " increment by 1";
            jdbcTemplate.execute(SQL2);
            return true;
        }

        return false;

    }

    public int getMaxObjectId(String tableName) {
        int maxObjId = -1;
        String sql = "select max(OBJECT_ID) FROM \"" + orclUsername + "\".\"" + tableName + "\"";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        if (!rowSet.wasNull()) {
            rowSet.next();
            maxObjId = rowSet.getInt(1);
        }
        return maxObjId;
    }

    public int getNextSeqVal(String sequenceName) {
        int nextVal = -1;
        String sql = "select " + sequenceName + ".nextval from dual";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        try {
            rowSet.next();
            return rowSet.getInt(1);
        } catch (Exception e) {
            return nextVal;
        }
    }


    private int executeAlterTableSQL(String tableName, String column_name, String data_default) {
        String sql = "alter table " + tableName + " modify " + column_name + " default " + data_default;
        int update = jdbcTemplate.update(sql);
        return update;
    }


    public List getAllSequenceNameList() {
        List<String> list = new ArrayList<String>();
        String SQL = "SELECT sequence_name from user_sequences";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL);
        while (rowSet.next()) {
            String seq_name = rowSet.getString(1);
            list.add(seq_name);
        }
        return list;
    }

    public List getAllTableList() {
        List list = new ArrayList();
        String sql = "SELECT TABLE_NAME FROM USER_TABLES";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            String table_name = rowSet.getString(1);
            list.add(table_name);
        }

        return list;
    }

}
