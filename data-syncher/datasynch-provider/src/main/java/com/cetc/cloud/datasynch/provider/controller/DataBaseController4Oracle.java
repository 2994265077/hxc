package com.cetc.cloud.datasynch.provider.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.cetc.cloud.datasynch.provider.common.CommonCellStype;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.common.DesensitizationType;
import com.cetc.cloud.datasynch.provider.common.OrderedProperties;
import com.cetc.cloud.datasynch.provider.util.DesensitizationUtil;
import com.cetc.cloud.datasynch.provider.util.ExcelReaderUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description： 数据库交互接口
 * Created by luolinjie on 2018/5/8.
 */
@RestController
@DS("master")
@Slf4j
public class DataBaseController4Oracle {

    //    @Autowired
//    @Qualifier("primaryJdbcTemplate")
//    private JdbcTemplate primaryJdbcTemplate;
//
//    @Autowired
//    @Qualifier("readOnlyJdbcTemplate")
//    private JdbcTemplate readOnlyJdbcTemplate;
    @Autowired
    private JdbcTemplate currentJdbcTemplate;

    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String url_primary_datasource;
    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String username_primary_datasource;

    @Value("${spring.datasource.dynamic.datasource.readonly.url}")
    private String url_read_only_datasource;
    @Value("${spring.datasource.dynamic.datasource.readonly.username}")
    private String username_read_only_datasource;


    /**
     * 数据库连接初始化
     * <p>
     * 0：mysql
     * 1：oracle
     */

    @ApiOperation(value = "重新初始化oracle连接", notes = "需提供IP,serviceName,username,password")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "source", value = "0:primary 1:readOnly", required = true, dataType = "int", paramType = "query",example = "0"),
    })
    public List<HashMap> base_oracleQuerySql(String sql) {
        List<HashMap> data = new ArrayList<HashMap>();
        try {
            SqlRowSet rs = currentJdbcTemplate.queryForRowSet(sql);
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
        } catch (Exception e) {
            log.error("query oracle error!\nsql=" + sql, e);
        }
        return data;
    }

    public int base_oracleUpdateSql(String sql) {
        int rs = 0;
        try {
            rs = currentJdbcTemplate.update(sql);
        } catch (Exception e) {
            log.error("query oracle error!\nsql=" + sql, e);
        }
        return rs;
    }

    /**
     * 批量添加字段
     *
     * @param tableNames    表名集合，默认以","作为分隔符
     * @param dbName
     * @param newColumnName
     * @param columnType
     * @param comment
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/batchAddColumn", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String batchAddColumn(String tableNames, String dbName, String newColumnName, String columnType, String defaultValue, String comment) throws SQLException {
        JSONObject result = new JSONObject();

        String[] tables = tableNames.split(",");
        for (String table : tables) {
            String s = addColumn(dbName, table, newColumnName, columnType, defaultValue, comment);
            JSONObject res1 = new JSONObject();
            res1.put("table", table);
            res1.put("return", s);
            result.put("table", res1);
        }
        return result.toJSONString();
    }


    @RequestMapping(value = "/batchAddCommentByDict", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map batchAddCommentByDict() throws Exception {

        //0.加载数据字典文件
        HashMap res = new HashMap();
        // 读取properties文件 加载数据字典
        InputStream inputStream = DataBaseController4Oracle.class.getResourceAsStream("/dataDictionary_sanxiao_place.properties");
        Properties properties = new OrderedProperties();
        properties.load(inputStream);
        if (properties == null) {
            res.put("error", "FileNameError");
            return res;
        }
        //1.获取所有表List
        List tableList_oracle = getDBAllTableList();
        //2.遍历每张表
        for (int i = 0; i < tableList_oracle.size(); i++) {

        }
        //3.为单张表添加column

        return res;

    }


    /**
     * 检查表中是否包含该字段
     *
     * @param tableNames
     * @param targetColumnNames
     * @return
     * @throws SQLException
     */
    @ApiOperation(value = "检查表中是否包含该字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableNames", value = "多个表以\",\"分隔", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "targetColumnNames", value = "多个字段以\",\"分隔", required = true, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "/checkIfTableHasColumn_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkIfTableContainsColumn_oracle(String tableNames, String targetColumnNames) throws SQLException, IOException {
        JSONObject result = new JSONObject();

        String[] tableArr = tableNames.split(",");
        String[] columnsArr = targetColumnNames.split(",");

        for (int i = 0; i < tableArr.length; i++) {

            //获取目标表的字段组成的集合
            String SQL_getAllColumns = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '" + tableNames + "'";
            SqlRowSet rs1 = currentJdbcTemplate.queryForRowSet(SQL_getAllColumns);

            while (rs1.next()) {
                String columnName = rs1.getString(1);
                if (targetColumnNames.equals(columnName)) {
                    result.put("has column:" + targetColumnNames, "true");
                    return result.toJSONString();
                }
            }
            result.put("has column:" + targetColumnNames, "false");
            return result.toJSONString();
        }
        return result.toJSONString();
    }

    /**
     * 将指定数据库的所有表名由全部转换 大写/小写  -- oracle版本
     *
     * @param caseType 选择大写还是小写
     *                 upper:大写
     *                 lower:小写
     * @return
     * @throws SQLException
     */
    @ApiOperation(value = "将指定数据库的所有表名由全部转换 大写/小写", notes = "选择大写还是小写:upper:大写/lower:小写", httpMethod = "POST", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "要改变的表名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "caseType", value = "选择大写还是小写:upper-大写/lower-小写", required = true, dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/parseSingleTbNameAndColNameCase_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String parseSingleTbNameAndColNameCase_oracle(String tableName, String caseType) throws Exception {
        JSONObject res = new JSONObject();
        //   1 先修改表字段
        // 获取当前表所有字段名组成的列表
        String SQL_getAllColumns = "select COLUMN_NAME " +
                "from user_tab_columns " +
                "where table_name='" + tableName + "'";
        SqlRowSet ps2 = currentJdbcTemplate.queryForRowSet(SQL_getAllColumns);
        JSONArray array = new JSONArray();

        if (null == caseType || "lower".equals(caseType)) {
            while (ps2.next()) {
                String OLD_COLUMN_NAME = ps2.getString(1);
                //如果字段名称已经是小写的话，就不做修改
                if (OLD_COLUMN_NAME.equals(OLD_COLUMN_NAME.toLowerCase())) {
                    continue;
                }
                String sql2 = "alter table \"" + tableName + "\" rename column \"" + OLD_COLUMN_NAME + "\" to \"" + OLD_COLUMN_NAME.toLowerCase() + "\"";
                currentJdbcTemplate.execute(sql2);
                log.info("colunmName --> " + tableName + "." + OLD_COLUMN_NAME + " -- " + OLD_COLUMN_NAME.toLowerCase());
                array.add(OLD_COLUMN_NAME + "-->" + OLD_COLUMN_NAME.toLowerCase());
            }
        } else if ("upper".equals(caseType)) {
            while (ps2.next()) {
                String OLD_COLUMN_NAME = ps2.getString(1);
                //如果字段名称已经是大写的话，就不做修改
                if (OLD_COLUMN_NAME.equals(OLD_COLUMN_NAME.toUpperCase())) {
                    continue;
                }
                String sql2 = "alter table \"" + tableName + "\" rename column \"" + OLD_COLUMN_NAME + "\" to \"" + OLD_COLUMN_NAME.toUpperCase() + "\"";
                currentJdbcTemplate.execute(sql2);
                log.info("colunmName --> " + tableName + "." + OLD_COLUMN_NAME + " -- " + OLD_COLUMN_NAME.toUpperCase());
                array.add(OLD_COLUMN_NAME + "-->" + OLD_COLUMN_NAME.toUpperCase());
            }
        }

        //2.修改表名
        String newTableName = "";
        if (null == caseType || caseType.equals("lower")) {
            // 如果表名称已经是小写的话，就不做修改
            if (tableName.equals(tableName.toLowerCase())) {
                res.put("warning", "Table Already be LowerCase");
                return res.toJSONString();
            } else {
                newTableName = tableName.toLowerCase();
            }

        } else if (caseType.equals("upper")) {
            // 如果表名称已经是小写的话，就不做修改
            if (tableName.equals(tableName.toUpperCase())) {
                res.put("warning", "Table Already be UpperCase");
                return res.toJSONString();
            } else {
                newTableName = tableName.toUpperCase();
            }
        }
        // 执行操作：修改表名
        String SQL_renameTableName = "ALTER TABLE \"" + tableName + "\" RENAME TO \"" + newTableName + "\"";
        currentJdbcTemplate.execute(SQL_renameTableName);

        res.put(tableName, newTableName);
        log.info(tableName + "-->" + newTableName);
        log.info("函数： parseSingleTableName_oracle()执行完成！");
        return res.toJSONString();
    }


    /**
     * 将指定数据库的所有表字段由大写转换为小写-- oracle版本
     *
     * @param type 要转换的类型
     *             upper：转大写
     *             lower: 转小写
     * @return
     * @throws SQLException
     */
    @ApiOperation(value = "将指定数据库的所有字段名由全部转换 大写/小写  -- oracle版本", notes = "选择大写还是小写:upper:大写/lower:小写", httpMethod = "POST", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "转换目标：upper-大写 lower-小写", required = true, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/parseDBAllColumnNameToLowerCase_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String parseDBAllColumnNameToLowerCase_oracle(String type) throws Exception {
        JSONObject res = new JSONObject();
        //1 获取当前数据库的所有表名组成的列表
        List list = getDBAllTableList();
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            String tableName = (String) iterator.next();
            //2 获取当前表所有字段名组成的列表
            String SQL_getAllColumns = "select COLUMN_NAME " +
                    "from user_tab_columns " +
                    "where table_name='" + tableName + "'";
            SqlRowSet rs1 = currentJdbcTemplate.queryForRowSet(SQL_getAllColumns);
            JSONArray array = new JSONArray();

            if (null == type || "lower".equalsIgnoreCase(type)) {
                while (rs1.next()) {
                    String OLD_COLUMN_NAME = rs1.getString(1);
                    //如果字段名称已经是小写的话，就不做修改
                    if (OLD_COLUMN_NAME.equals(OLD_COLUMN_NAME.toLowerCase())) {
                        continue;
                    }
                    String sql2 = "alter table \"" + tableName + "\" rename column \"" + OLD_COLUMN_NAME + "\" to \"" + OLD_COLUMN_NAME.toLowerCase() + "\"";
                    currentJdbcTemplate.execute(sql2);
                    log.info("colunmName --> " + tableName + "." + OLD_COLUMN_NAME + " -- " + OLD_COLUMN_NAME.toLowerCase());
                    array.add(OLD_COLUMN_NAME + "-->" + OLD_COLUMN_NAME.toLowerCase());
                }
            } else if ("upper".equalsIgnoreCase(type)) {
                while (rs1.next()) {
                    String OLD_COLUMN_NAME = rs1.getString(1);
                    //如果字段名称已经是大写的话，就不做修改
                    if (OLD_COLUMN_NAME.equals(OLD_COLUMN_NAME.toUpperCase())) {
                        continue;
                    }
                    String sql2 = "alter table \"" + tableName + "\" rename column \"" + OLD_COLUMN_NAME + "\" to " + OLD_COLUMN_NAME.toUpperCase();
                    currentJdbcTemplate.execute(sql2);
                    log.info("colunmName --> " + tableName + "." + OLD_COLUMN_NAME + " -- " + OLD_COLUMN_NAME.toUpperCase());
                    array.add(OLD_COLUMN_NAME + "-->" + OLD_COLUMN_NAME.toUpperCase());
                }
            }
            res.put(tableName, array);
        }
        log.info("finished! methodName: parseDBAllColumnNameToLowerCase_oracle() ");
        return res.toJSONString();
    }

    /**
     * 将指定数据库的所有表名由全部转换 大写/小写  -- oracle版本
     *
     * @param caseType 选择大写还是小写
     *                 upper:大写 / lower:小写
     * @return
     * @throws SQLException
     */
    @ApiOperation(value = "将指定数据库的所有'表名'由全部转换 大写/小写  -- oracle版本", notes = "选择大写还是小写:upper:大写/lower:小写", httpMethod = "POST", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "要改变的表名", required = true, dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/parseDBAllTableNameCASE_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String parseDBAllTableNameCASE_oracle(String caseType) throws Exception {
        JSONObject res = new JSONObject();
        //1 获取当前数据库的所有表名组成的列表
        List list = getDBAllTableList();
        Iterator iterator = list.iterator();

        if (null == caseType || caseType.equals("lower")) {
            while (iterator.hasNext()) {
                String oldTbName = (String) iterator.next();
                //2 如果表名称已经是小写的话，就不做修改
                if (oldTbName.equals(oldTbName.toLowerCase())) {
                    continue;
                }
                //3 执行操作：修改表名
                String SQL_getAllColumns = "ALTER TABLE \"" + oldTbName + "\" RENAME TO \"" + oldTbName.toLowerCase() + "\"";
                try {
                    currentJdbcTemplate.execute(SQL_getAllColumns);
                    res.put(oldTbName, oldTbName.toLowerCase());
                    log.info(oldTbName + "-->" + oldTbName.toLowerCase());
                } catch (Exception e) {
                    log.error("tableName" + oldTbName);
                }
            }
        } else if (caseType.equals("upper")) {
            while (iterator.hasNext()) {
                String oldTbName = (String) iterator.next();
                //2 如果表名称已经是小写的话，就不做修改
                if (oldTbName.equals(oldTbName.toUpperCase())) {
                    continue;
                }
                //3 执行操作：修改表名
                String SQL_getAllColumns = "ALTER TABLE \"" + oldTbName + "\" RENAME TO \"" + oldTbName.toUpperCase() + "\"";
                try {
                    currentJdbcTemplate.execute(SQL_getAllColumns);
                    res.put(oldTbName, oldTbName.toUpperCase());
                    log.info(oldTbName + "-->" + oldTbName.toUpperCase());
                } catch (Exception e) {
                    log.error("tableName:" + oldTbName);
                }
            }
        }
        log.info("函数： parseDBAllTableName_oracle()执行完成！");
        return res.toJSONString();
    }

//    @RequestMapping(value = "/compare2UserTables", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public JSONObject compare2UserTables() throws Exception {
//        JSONObject res = new JSONObject();
//        String s = reInitConnectionProperties_oracle(0);
//        log.info(s);
//        List<String> list0 = getDBAllTableList();
//
//        String s1 = reInitConnectionProperties_oracle(1);
//        log.info(s1);
//        List<String> list1 = getDBAllTableList();
//        List<String> otherTable = new ArrayList<String>();
//        List<String> otherTable1 = new ArrayList<String>();
//        for (int i = 0; i < list0.size(); i++) {
//            String tableName = list0.get(i);
//            if (!list1.contains(tableName)) {
//                otherTable.add(tableName);
//            }
//        }
//
//        for (int i = 0; i < list1.size(); i++) {
//            String tableName = list1.get(i);
//            if (!list0.contains(tableName)) {
//                otherTable1.add(tableName);
//            }
//        }
//        res.put(url_primary_datasource.split("@")[1] + "'stables not in" + url_read_only_datasource.split("@")[1], otherTable);
//        res.put(url_read_only_datasource.split("@")[1] + "'stables not in" + url_primary_datasource.split("@")[1], otherTable1);
//
//        return res;
//    }


    /**
     * 统计每张表中的记录总数 以 table_name,table_rows,table_comment形式打印 -- oracle版本
     *
     * @param dbName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/countDBAllTables_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject countDBAllTables_oracle(String dbName) throws Exception {

        JSONObject result = new JSONObject();

        String SQL = "SELECT \n" +
                "table_name,table_rows,table_comment\n" +
                "FROM \n" +
                "information_schema.tables \n" +
                "WHERE \n" +
                "TABLE_SCHEMA = '" + dbName + "' \n" +
                "ORDER BY \n" +
                "table_rows DESC;";
        SqlRowSet resultSet = currentJdbcTemplate.queryForRowSet(SQL);
        System.out.println("|--table_name--|--table_rows--|--table_comment--|");
        int i = 1;
        int column_count_all = 0;
        int rows_count_all = 0;
        while (resultSet.next()) {
            String table_name = resultSet.getString(1);
            int table_rows = resultSet.getInt(2);
            String table_comment = resultSet.getString(3);
//            log.info(table_name + "\t" + table_rows + "\t" + table_comment);
            JSONObject cell = new JSONObject();
            cell.put("table_name", table_name);
            cell.put("table_rows", table_rows);
            rows_count_all += table_rows;
            cell.put("table_comment", table_comment);
            log.info(table_comment);
            String SQL2 = "SELECT count(COLUMN_NAME) FROM `information_schema`.`columns` \n" +
                    "WHERE `table_schema` = '" + dbName + "'\n" +
                    "AND `table_name` = '" + table_name + "'";
            SqlRowSet resultSet2 = currentJdbcTemplate.queryForRowSet(SQL2);
            resultSet2.next();
            int count = resultSet2.getInt(1);
            column_count_all += count;
            result.put(String.valueOf(i++), cell);
        }
        log.info("column_count_all:      " + column_count_all);
        log.info("rows_count_all:      " + rows_count_all);

        Set<String> keySet = result.keySet();
        for (String s : keySet) {

        }
        return result;
    }


    /**
     * 统计每张表中的记录字段总数--oracle
     *
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/countAllTableColums_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String countAllTableColums_oracle() throws Exception {

        JSONObject result = new JSONObject();

        List<String> tableNameList = getDBAllTableList();

        for (String tableName : tableNameList) {

            String SQL = "select COLUMN_NAME from user_tab_columns where table_name='" + tableName + "' ";
            SqlRowSet rs = currentJdbcTemplate.queryForRowSet(SQL);
            int counter = 0;
            while (rs.next()) {
                if (null != rs.getString(1)) {
                    counter++;
                }
            }

            result.put(tableName, counter);
        }
        return result.toJSONString();
    }

    /**
     * 统计每张表中的记录字段总数--oracle
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/countRows_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String countRows_oracle(String tables) throws Exception {

        JSONObject result = new JSONObject();

        String[] split = tables.split(",");
        int counter = 0;
        for (String tableName : split) {

            log.info("tableName: " + tableName);
            String SQL = "select count(1) from \"" + tableName + "\" ";
            SqlRowSet rs = currentJdbcTemplate.queryForRowSet(SQL);

            while (rs.next()) {
                int count = rs.getInt(1);
                counter += count;
            }

        }
        log.info("Finished!");
        result.put("total", counter);
        return result.toJSONString();
    }

    /**
     * 为目标表增加指定字段
     *
     * @param dbName
     * @param tableName
     * @param newColumnName
     * @return
     */
    @RequestMapping(value = "/addColumn", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addColumn(String dbName, String tableName, String newColumnName, String columnType, String
            defaultValue, String comment) throws SQLException {
        JSONObject result = new JSONObject();

        try {
            String SQL = "alter table " + tableName + " add "
                    + newColumnName + " "
                    + columnType
                    + " DEFAULT " + defaultValue
                    + " comment '" + comment + "'";
            currentJdbcTemplate.execute(SQL);
            result.put("result", "success! SQL:" + SQL);
            return result.toJSONString();
        } catch (Exception e) {
            result.put("result", "fail!");
            log.error(e.toString());
            return result.toJSONString();
        }
    }


    /**
     * 为目标表增加指定字段 oracle版本
     *
     * @param owner
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/exportTableStructure2Excel_oracle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String exportTableStructure2Excel_oracle(String owner, String tableName, String exportFilePath, String exceptTables) throws
            Exception {
        if (null == exportFilePath) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String formatTimeStr = format.format(Calendar.getInstance().getTime());
            exportFilePath = "C:\\Users\\Administrator\\Desktop\\31project_tableStructure\\" + owner + "_" + formatTimeStr;
        }
        List<String> exceptTableList = new ArrayList<String>();
        if (null != exceptTables) {
            String[] split = exceptTables.split(",");
            for (int i = 0; i < split.length; i++) {
                exceptTableList.add(split[i]);
            }
        }
        if (null != tableName) {//指定打印某张表
            //创建excel
            File file = new File(exportFilePath + "_" + tableName + ".xls");
            boolean newFile = file.createNewFile();
            if (newFile) {
                log.info("create newFile:" + file.getAbsolutePath() + " Success!");
            } else {
                log.info("create newFile:" + file.getAbsolutePath() + " Failed!");
            }

            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(owner);

            sheet.setDefaultColumnWidth(15);
            //获取当前用户所管的所有表组成的列表
            List<List> tableDDLInfo = getSingleTableDDLInfo(tableName);
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            short o = 200;
            font.setFontHeight(o);
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            Row rows0 = sheet.createRow(0);
            Cell cell0 = rows0.createCell(0);
            cell0.setCellStyle(cellStyle);
            cell0.setCellValue("字段名称");
            Cell cell1 = rows0.createCell(1);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue("sql数据类型/长度");
            Cell cell2 = rows0.createCell(2);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue("字段含义");
            for (int row = 0; row < tableDDLInfo.size(); row++) {
                ArrayList rowlist = (ArrayList) tableDDLInfo.get(row);
                Row rows1 = sheet.createRow(row + 1);
                for (int col = 0; col < rowlist.size(); col++) {
                    // 向工作表中添加数据
                    rows1.createCell(col).setCellValue((String) rowlist.get(col));
                }
            }

            FileOutputStream xlsStream = new FileOutputStream(file);
            workbook.write(xlsStream);
            xlsStream.close();
            HashMap res = new HashMap();
            res.put("success", "finished  filepath is:" + exportFilePath + ".xls");
            return res.toString();
        } else {/**未指定打印某张表，默认输出所有表 （创建多个excel，并放在同一个文件夹下）*/
            //创建一个文件夹
            File folder = new File(exportFilePath);
            boolean mkdir = folder.mkdirs();
            if (mkdir) {
                log.info("create folder ： " + exportFilePath + " success!");
            }
            //获取当前用户所管的所有表组成的列表
            List<String> dbAllTableList = getDBAllTableList();

            for (String tbName : dbAllTableList) {
                if (null != exceptTableList && exceptTableList.size() != 0) {
                    for (String exceptTb : exceptTableList) {
                        if (exceptTb.equals(tableName)) {
                            continue;
                        }
                    }
                }
                //根据tbName获取其comment
                String tableComment = getTableComment(tbName);
                //创建excel文件
                File file = new File(exportFilePath + "/" + tbName + "_" + tableComment + ".xls");
                boolean newFile = file.createNewFile();
                if (newFile) {
                    log.info("create newFile:" + file.getAbsolutePath() + " Success!");
                } else {
                    log.info("create newFile:" + file.getAbsolutePath() + " Failed!");
                }
                //读取excel文件
                HSSFWorkbook workbook = new HSSFWorkbook();
                Sheet sheet = workbook.createSheet(owner);
                sheet.setDefaultColumnWidth(15);
                Row rows0 = sheet.createRow(0);
                HSSFFont font = workbook.createFont();
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                short o = 200;
                font.setFontHeight(o);
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setFont(font);

                Cell cell0 = rows0.createCell(0);
                cell0.setCellStyle(cellStyle);
                cell0.setCellValue("字段名称");
                Cell cell1 = rows0.createCell(1);
                cell1.setCellStyle(cellStyle);
                cell1.setCellValue("数据类型");
                Cell cell2 = rows0.createCell(2);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue("字段含义");
                List tableDDLInfo = getTableDDLInfo_oracle(tbName);
                for (int row = 0; row < tableDDLInfo.size(); row++) {
                    ArrayList rowlist = (ArrayList) tableDDLInfo.get(row);
                    Row rows = sheet.createRow(row + 1);
                    for (int col = 0; col < rowlist.size(); col++) {
                        // 向工作表中添加数据
                        Cell cell = rows.createCell(col);
                        cell.setCellValue((String) rowlist.get(col));
                    }
                }

                FileOutputStream xlsStream = new FileOutputStream(file);
                workbook.write(xlsStream);
                xlsStream.close();
            }

            // 输出汇总信息
            List<List> dbAllTableCount = getDBAllTableCount_oracle();

            //创建一个xls文件
            File summary = new File(exportFilePath + "/" + owner + "_summary.xls");
            boolean newFile1 = summary.createNewFile();
            if (newFile1 == false) {
                log.error("create file error:" + folder.getAbsolutePath());
                System.exit(0);
            }
            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(owner);

            sheet.setDefaultColumnWidth(15);


            Row rows0 = sheet.createRow(0);
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            short o = 200;
            font.setFontHeight(o);
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);

            Cell cell0 = rows0.createCell(0);
            cell0.setCellStyle(cellStyle);
            cell0.setCellValue("表名");
            Cell cell1 = rows0.createCell(1);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue("数据量");
            Cell cell2 = rows0.createCell(2);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue("备注");
            for (int i = 0; i < dbAllTableCount.size(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < dbAllTableCount.get(i).size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue((String) dbAllTableCount.get(i).get(j));
                }

            }
            FileOutputStream xlsStream = new FileOutputStream(summary);
            workbook.write(xlsStream);
            xlsStream.close();

        }

        return "finished! folder path is:" + exportFilePath;
    }

    private List<List> getSingleTableDDLInfo(String tableName) throws Exception {

        List<List> res = new ArrayList<List>();
        String SQL = "SELECT COLUMN_NAME,COLUMN_TYPE,DATA_LENGTH FROM user_all_tables WHERE TABLE_NAME ='" + tableName + "'";
        SqlRowSet rowSet = currentJdbcTemplate.queryForRowSet(SQL);
        Map columnName_CommentMap = getColumnCommentMap(tableName);
        while (rowSet.next()) {
            ArrayList<String> list = new ArrayList<String>();
            String colName = rowSet.getString(1);
            String colLenth = rowSet.getString(2);
            String colComment = rowSet.getString(3);
            list.add(colName);
            list.add(colLenth);
            list.add(colComment);
            res.add(list);
        }

        return res;
    }

    /**
     * 单表汇总当前数据库每张表的数据量
     *
     * @param owner
     * @return
     */
    @RequestMapping(value = "/exportTableStructure2Excel_oracle_summary", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String exportTableStructure2Excel_oracle_summary(String owner, String exportFilePath) throws
            Exception {
        String formatTimeStr = null;
        if (null == exportFilePath) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            formatTimeStr = format.format(Calendar.getInstance().getTime());
            exportFilePath = "C:\\Users\\Administrator\\Desktop\\31project_tableStructure\\" + owner + "_tbSum_" + formatTimeStr;
        }
        File folder = new File(exportFilePath);
        boolean mkdir = folder.mkdirs();
        if (mkdir) {
            log.info("create folder ： " + exportFilePath + " success!");
        }

        //创建一个xls文件
        File summary = new File(exportFilePath + "/" + owner + "_summary" + formatTimeStr + ".xls");
        boolean newFile1 = summary.createNewFile();
        if (newFile1 == false) {
            System.exit(0);
        }


        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(owner);

        sheet.setDefaultColumnWidth(15);
        int rowCounter = 0;
        Row rows0 = sheet.createRow(rowCounter++);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        short o = 200;
        font.setFontHeight(o);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        List<String> tbName_count_comment = null;
        Cell cell0 = rows0.createCell(0);
        cell0.setCellStyle(cellStyle);
        cell0.setCellValue("表名");
        Cell cell1 = rows0.createCell(1);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("数据量");
        Cell cell2 = rows0.createCell(2);
        cell2.setCellStyle(cellStyle);
        cell2.setCellValue("备注");
        Cell cell3 = rows0.createCell(3);
        cell3.setCellStyle(cellStyle);
        cell3.setCellValue("字段数");
        Cell cell4 = rows0.createCell(4);
        cell4.setCellStyle(cellStyle);
        cell4.setCellValue("最后更新时间");
        try {
            List<String> dbAllTableList_oracle = getDBAllTableList();
            for (String table : dbAllTableList_oracle) {
                // 输出汇总信息
                tbName_count_comment = getTableCountByTableName(table);
                log.info(tbName_count_comment.toString());
                Row row = sheet.createRow(rowCounter++);
                for (int j = 0; j < tbName_count_comment.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(tbName_count_comment.get(j));
                }
                //输出单表字段个数-下标为3
                Cell cell_s3 = row.createCell(3);
                cell_s3.setCellValue(getTableColumnNumSum_oracle(table));
                //输出单表字段个数-下标为3
                Cell cell_s4 = row.createCell(4);
                cell_s4.setCellValue(getTableLastUpdateTime_oracle(table));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileOutputStream xlsStream = new FileOutputStream(summary);
            workbook.write(xlsStream);
            xlsStream.close();
        }

        log.info("finished! folder path is:" + exportFilePath);
        return "finished! folder path is:" + exportFilePath;
    }


    /**
     * 为目标表增加指定字段 oracle版本,输出到一个文件
     *
     * @param owner
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/exportTableStructure2Excel_oracle_asOneFile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public HashMap<String, String> exportTableStructure2Excel_oracle_asOneFile(String owner, String
            tableName, String exportFilePath, String exceptTables) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatTimeStr = format.format(Calendar.getInstance().getTime());
        if (null == exportFilePath) {
            exportFilePath = "C:\\Users\\Administrator\\Desktop\\31project_tableStructure\\" + owner + "_" + formatTimeStr;
        }

        List<String> exceptTableList = new ArrayList<String>();
        if (null != exceptTables) {
            String[] split = exceptTables.split(",");
            for (int i = 0; i < split.length; i++) {
                exceptTableList.add(split[i]);
            }
        }
        if (null != tableName) {//指定打印某张表
            //创建excel
            File file = new File(exportFilePath + "_" + tableName + formatTimeStr + ".xls");
            boolean newFile = file.createNewFile();
            if (newFile) {
                log.info("create newFile:" + file.getAbsolutePath() + " Success!");
            } else {
                log.info("create newFile:" + file.getAbsolutePath() + " Failed!");
            }

            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(owner);

            sheet.setDefaultColumnWidth(15);
            //获取当前用户所管的所有表组成的列表
            List<String> allTableList = getDBAllTableList();
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            short o = 200;
            font.setFontHeight(o);
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            Row rows0 = sheet.createRow(0);
            HashMap<Integer, Integer> indexMapping = new HashMap<Integer, Integer>();
            indexMapping.put(0, 1);
            indexMapping.put(1, 2);
            indexMapping.put(2, 3);
            indexMapping.put(3, 8);
            ArrayList list = new ArrayList();
            list.add("序号");
            list.add("字段名称");
            list.add("数据类型");
            list.add("PK");
            list.add("可空");
            list.add("缺省值");
            list.add("唯一");
            list.add("字段描述");
            createCells_dif_name(list, rows0, cellStyle, font);
            for (int row_i = 0; row_i < allTableList.size(); row_i++) {
                List<List> tableDDLInfo_oracle = getTableDDLInfo_oracle_2(allTableList.get(row_i));
                List rowlist = tableDDLInfo_oracle.get(row_i);
                Row rows1 = sheet.createRow(row_i + 1);
                for (int col = 0; col < rowlist.size(); col++) {
                    // 向工作表中添加数据
                    rows1.createCell(indexMapping.get(col)).setCellValue((String) rowlist.get(indexMapping.get(col)));
                }
            }

            FileOutputStream xlsStream = new FileOutputStream(file);
            workbook.write(xlsStream);
            xlsStream.close();
            HashMap res = new HashMap();
            res.put("success", "finished  filepath is:" + exportFilePath + ".xls");
            return res;
        } else {/**未指定打印某张表，默认输出所有表 （创建多个excel，并放在同一个文件夹下）*/
            //创建一个文件夹
            File folder = new File(exportFilePath);
            boolean mkdir = folder.mkdirs();
            if (mkdir) {
                log.info("create folder ： " + exportFilePath + " success!");
            }
            //获取当前用户所管的所有表组成的列表
            List<String> dbAllTableList = getDBAllTableList();
            //创建excel文件
            File file = new File(exportFilePath + "/" + owner + "_" + formatTimeStr + ".xls");
            boolean newFile = file.createNewFile();
            if (newFile) {
                log.info("create newFile:" + file.getAbsolutePath() + " Success!");
            } else {
                log.info("create newFile:" + file.getAbsolutePath() + " Failed!");
            }
            FileOutputStream xlsStream = new FileOutputStream(file);
            int row = 0;
            //读取excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(owner);
            //分别设置表格列宽
//            sheet.setDefaultColumnWidth(12);
//            sheet.setDefaultRowHeight((short) (2 * 256));
            sheet.setColumnWidth(0, (short) (4 * 256));
            sheet.setColumnWidth(1, (short) (12 * 256));
            sheet.setColumnWidth(2, (short) (12 * 256));
            sheet.setColumnWidth(3, (short) (10 * 256));
            sheet.setColumnWidth(4, (short) (6 * 256));
            sheet.setColumnWidth(5, (short) (8 * 256));
            sheet.setColumnWidth(6, (short) (8 * 256));
            sheet.setColumnWidth(7, (short) (8 * 256));
            sheet.setColumnWidth(8, (short) (12 * 256));
            //表名样式
            HSSFFont font_tbName = workbook.createFont();
            font_tbName.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font_tbName.setFontHeight((short) 220);
            HSSFCellStyle cellStyle_tbName = workbook.createCellStyle();
            cellStyle_tbName.setFont(font_tbName);

            //表头样式
            HSSFFont font_tbHead = workbook.createFont();
//                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font_tbHead.setFontHeight((short) 200);
            HSSFCellStyle cellStyle_tbHead = workbook.createCellStyle();
            cellStyle_tbHead.setFont(font_tbHead);


            //cell样式
            HSSFFont font2 = workbook.createFont();
            font2.setFontHeight((short) 200);
            HSSFCellStyle cellStyle_content = workbook.createCellStyle();
            cellStyle_content.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle_content.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle_content.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle_content.setBorderRight(HSSFCellStyle.BORDER_THIN);

            log.info("正在执行导出：共" + dbAllTableList.size() + "张表");
            int countNum = 0;
            for (String tbName : dbAllTableList) {

                //友情提示
                DecimalFormat df = new DecimalFormat("0.00");
                log.info("\n--正在导出第" + (countNum + 1) + "/" + dbAllTableList.size() + "张表  --   总进度：" + df.format(((countNum + 1) / new Double(dbAllTableList.size())) * 100) + "%\n");
                countNum++;
                if (null != exceptTableList) {
                    for (String exceptTb : exceptTableList) {
                        if (exceptTb.equals(tableName)) {
                            continue;
                        }
                    }
                }
                //根据tbName获取其comment
                String tableComment = getTableComment(tbName);


                //1.添加表名
                Row rows_tb = sheet.createRow(++row);
                Cell cell_tb = rows_tb.createCell(1);
                cell_tb.setCellValue(tableComment + " " + tbName);
                cell_tb.setCellStyle(cellStyle_tbName);
//                Cell cell_tb1 = rows_tb.createCell(2);
//                cell_tb1.setCellValue(tbName);

                //2.添加表头
                Row rows0 = sheet.createRow(++row);
                ArrayList list = new ArrayList();
                list.add("序号");
                list.add("字段名称");
                list.add("数据类型");
                list.add("长(精)度, 小数位数");
                list.add("PK");
                list.add("可空");
                list.add("缺省值");
                list.add("唯一");
                list.add("字段描述");
                createCells_dif_name(list, rows0, cellStyle_tbHead, font_tbHead);

                List tableDDLInfo = getTableDDLInfo_oracle_2(tbName);
                //3.插入DDL数据
                for (int i = 0; i < tableDDLInfo.size(); i++) {
                    ArrayList<String> rowlist = (ArrayList) tableDDLInfo.get(i);
                    Row rows = sheet.createRow(++row);
                    HashMap<Integer, Integer> indexMapping = new HashMap<Integer, Integer>();
                    //key--DDL中对应的序号 value--Excel中对应的
                    indexMapping.put(0, 0);
                    indexMapping.put(1, 1);
                    indexMapping.put(2, 2);
                    indexMapping.put(3, 3);
                    indexMapping.put(4, 5);
                    indexMapping.put(5, 6);
                    indexMapping.put(6, 8);
                    indexMapping.put(7, 4);
                    for (int col = 0; col < list.size(); col++) {
                        Integer index = 0;
                        //选择只在指定列插入数据
                        if (null == indexMapping.get(col)) {
                            continue;
                        } else {
                            index = indexMapping.get(col);
                        }
                        // 向工作表中添加数据
                        Cell cell = rows.createCell(index);
                        cell.setCellValue(null == rowlist.get(col) && index == 6 ? "NULL" : rowlist.get(col));
                        cell.setCellStyle(cellStyle_content);
                    }

                    //补全4,7两列border
//                    Cell cell4 = rows.createCell(4);
//                    cell4.setCellStyle(cellStyle_content);
                    Cell cell7 = rows.createCell(7);
                    cell7.setCellStyle(cellStyle_content);
                }
                Row rows_null = sheet.createRow(++row);

            }
            workbook.write(xlsStream);

            xlsStream.close();


        }

        log.info("finished! folder path is:" + exportFilePath);
        HashMap<String, String> res = new HashMap<String, String>();
        res.put("result", "finished! folder path is:" + exportFilePath);
        return res;
    }


    /**
     * 获取单表comment -- oracle版本
     */
    public String getTableComment(String tableName) {
        try {
            String SQL = "select COMMENTS from user_tab_comments where table_name='" + tableName + "'";
//            log.info(SQL);
            SqlRowSet execute = currentJdbcTemplate.queryForRowSet(SQL);
            String comment = "";
            while (execute.next()) {
                comment = execute.getString(1);
            }

            return comment;
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取单张表DDL列表 -- oracle版本
     */
    public List getTableDDLInfo_oracle(String tableName) {

        List result = new ArrayList();
        try {
            String SQL = "SELECT COLUMN_NAME,DATA_TYPE,CHAR_LENGTH,DATA_LENGTH FROM user_tab_columns where table_name='" + tableName + "'";
            log.info(SQL);
            SqlRowSet execute = currentJdbcTemplate.queryForRowSet(SQL);

            //获取当前表的comments Map
            Map commentMap = getColumnCommentMap(tableName);

            while (execute.next()) {
                ArrayList arrayList = new ArrayList();
                String COLUMN_NAME = execute.getString(1);
                String DATA_TYPE = execute.getString(2);
                String CHAR_LENGTH = execute.getString(3);
                String DATA_LENGTH = execute.getString(4);
                String colType_Len = "";
                if ("VARCHAR2".equals(DATA_TYPE) || "NVARCHAR2".equals(DATA_TYPE)) {
                    colType_Len = DATA_TYPE + "(" + CHAR_LENGTH + ")";
                } else if ("NUMBER".equals(DATA_TYPE)) {
                    colType_Len = DATA_TYPE + "(" + DATA_LENGTH + ")";
                } else if ("DATE".equals(DATA_TYPE)) {
                    colType_Len = DATA_TYPE;
                }

                arrayList.add(COLUMN_NAME);//字段名称
                arrayList.add(colType_Len);//字段类型描述
                arrayList.add(commentMap.get(COLUMN_NAME));//字段注释
                result.add(arrayList);
            }
            return result;
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    /**
     * 获取单张表DDL列表 -- oracle版本2--将数据类型和字段长度分开返回
     * list 下标对应字段：
     * 0.字段序列号
     * 1.字段名称
     * 2.字段类型
     * 3.字段长度
     * 4.可空：NULLABLE
     * 5.缺省值：DATA_DEFAULT
     * 6.字段注释
     */
    public List getTableDDLInfo_oracle_2(String tableName) {

        List result = new ArrayList();
        try {
            String SQL = "SELECT COLUMN_ID,COLUMN_NAME,DATA_TYPE,CHAR_LENGTH,DATA_LENGTH,NULLABLE,DATA_DEFAULT " +
                    "FROM user_tab_columns " +
                    " WHERE table_name='" + tableName + "'" +
                    " ORDER BY COLUMN_ID ASC";
//            log.info(SQL);
            SqlRowSet execute = currentJdbcTemplate.queryForRowSet(SQL);

            //获取当前表的comments Map
            Map commentMap = getColumnCommentMap(tableName);
            String primaryKey = getPrimaryKeyByTableName(tableName);
            while (execute.next()) {
                ArrayList arrayList = new ArrayList();
                String COLUMN_ID = execute.getString(1);
                String COLUMN_NAME = execute.getString(2);
                String DATA_TYPE = execute.getString(3);
                String CHAR_LENGTH = execute.getString(4);
                String DATA_LENGTH = execute.getString(5);
                String NULLABLE = execute.getString(6);
                String DATA_DEFAULT = execute.getString(7);
                String colLen = "";
                if ("VARCHAR2".equals(DATA_TYPE) || "NVARCHAR2".equals(DATA_TYPE)) {
                    colLen = CHAR_LENGTH;
                } else if ("NUMBER".equals(DATA_TYPE) || "DATE".equals(DATA_TYPE)) {
                    colLen = DATA_LENGTH;
                }

                arrayList.add(COLUMN_ID);//0.序号：COLUMN_ID
                arrayList.add(COLUMN_NAME);//1.字段名称
                arrayList.add(DATA_TYPE);//2.字段类型
                arrayList.add(colLen);//3.字段长度
                arrayList.add(NULLABLE);//4.可空：NULLABLE
                arrayList.add(DATA_DEFAULT);//5.缺省值：DATA_DEFAULT
                arrayList.add(commentMap.get(COLUMN_NAME));//6.字段注释
                if (primaryKey.equals(COLUMN_NAME)) {
                    arrayList.add("Y");//7.当前字段为主键
                } else {
                    arrayList.add("");//7.当前字段为非主键
                }
                result.add(arrayList);
            }
            return result;
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    String getPrimaryKeyByTableName(String tableName) {
        String column_name = "";
        String SQL = "select a.column_name " +
                "from user_cons_columns a, user_constraints b " +
                "where a.constraint_name = b.constraint_name " +
                "and b.constraint_type = 'P' " +
                "and a.table_name='" + tableName.toUpperCase() + "'";
//        log.info(SQL);
        SqlRowSet execute = currentJdbcTemplate.queryForRowSet(SQL);
        while (execute.next()) {
            column_name = execute.getString("COLUMN_NAME");
        }
        return column_name;
    }

    /**
     * 获取单张表DDL列表 -- oracle版本3--
     * sheet1:表名、字段名、字段释义
     * sheet2:表名、表注释
     * list 下标对应字段：
     */
    public List getTableDDLInfo_oracle_3(String tableName, String tableColumn) {

        List result = new ArrayList();
        try {
            String SQL = "SELECT COLUMN_NAME " +
                    " FROM user_tab_columns " +
                    " WHERE table_name='" + tableName + "'" +
                    " ORDER BY COLUMN_ID ASC";
            log.info(SQL);
            SqlRowSet executeRs = currentJdbcTemplate.queryForRowSet(SQL);

            //获取当前表的comments Map
            Map commentMap = getColumnCommentMap(tableName);

            while (executeRs.next()) {
                String COLUMN_NAME = executeRs.getString(1);
                ArrayList arrayList = new ArrayList();

                arrayList.add(tableName);//0.表名
                arrayList.add(COLUMN_NAME);//1.字段名称
                arrayList.add(commentMap.get(COLUMN_NAME));//6.字段注释
                result.add(arrayList);
            }
            return result;
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    /**
     * 获取单张表字段个数 -- oracle版本
     */
    public int getTableColumnNumSum_oracle(String tableName) {

        try {
            String SQL = "SELECT count(*) COUNT FROM user_tab_columns where table_name='" + tableName + "'";
//            log.info(SQL);
            SqlRowSet execute = currentJdbcTemplate.queryForRowSet(SQL);

            //获取当前表的comments Map
            Map commentMap = getColumnCommentMap(tableName);

            while (execute.next()) {
                ArrayList arrayList = new ArrayList();
                int count = execute.getInt(1);
                return count;
            }

        } catch (Exception e) {
            log.error(e.toString());
        }
        return 0;
    }

    /**
     * 获取单张表最后更新时间 -- oracle版本
     */
    public Date getTableLastUpdateTime_oracle(String tableName) {

        try {
            String SQL = "select LAST_DDL_TIME from user_objects where object_type='TABLE' and object_name='" + tableName + "'";
//            log.info(SQL);
            SqlRowSet execute = currentJdbcTemplate.queryForRowSet(SQL);

            //获取当前表的comments Map
            Map commentMap = getColumnCommentMap(tableName);

            while (execute.next()) {
                ArrayList arrayList = new ArrayList();
                Date date = execute.getDate(1);
                return date;
            }

        } catch (Exception e) {
            log.error(e.toString());
        }
        return new Date();
    }

    /**
     * 获取单张表columnName-columnComment组成的map
     *
     * @param tbName
     * @return
     * @throws Exception
     */
    public Map getColumnCommentMap(String tbName) throws SQLException {
        String query_col_comment_SQL = "select COLUMN_NAME,COMMENTS from user_col_comments where table_name='" + tbName + "'";
//        log.info(query_col_comment_SQL);
        SqlRowSet execute = currentJdbcTemplate.queryForRowSet(query_col_comment_SQL);
        HashMap map = new HashMap();
        while (execute.next()) {
            String COLUMN_NAME = execute.getString(1);
            String COLUMN_COMMENT = execute.getString(2);
            map.put(COLUMN_NAME, COLUMN_COMMENT);
        }
        return map;
    }

    private List<List> getColumnCommentList(String tbName) throws SQLException {
        String query_col_comment_SQL = "select COLUMN_NAME,COMMENTS from user_col_comments where table_name='" + tbName + "'";
        log.info(query_col_comment_SQL);
        SqlRowSet execute = currentJdbcTemplate.queryForRowSet(query_col_comment_SQL);
        List list = new ArrayList();
        while (execute.next()) {
            List list1 = new ArrayList();
            String COLUMN_NAME = execute.getString(1);
            String COLUMN_COMMENT = execute.getString(2);
            list1.add(COLUMN_NAME);
            list1.add(COLUMN_COMMENT);
            list.add(list1);
        }
        return list;
    }

    /**
     * 获取单张表columnName-columnComment组成的map
     *
     * @param tbName
     * @return
     * @throws Exception
     */
    public String getColunmCommentMapByColumnName(String tbName, String columnName) throws SQLException {
        String query_col_comment_SQL = "select COMMENTS from user_col_comments where table_name='" + tbName + "'" +
                "AND COLUMN_NAME='" + columnName + "'";
        log.info(query_col_comment_SQL);
        SqlRowSet execute = currentJdbcTemplate.queryForRowSet(query_col_comment_SQL);
        HashMap map = new HashMap();
        String COLUMN_COMMENT = "";
        while (execute.next()) {
            COLUMN_COMMENT = execute.getString(1);
        }
        return COLUMN_COMMENT;
    }


    /*获取整个数据库的所有表*/
    public List<String> getDBAllTableList() throws Exception {
        List<String> list = new ArrayList();
        String SQL = null;

        SQL = "select table_name from user_tables ORDER BY table_name";
        SqlRowSet resultSet = currentJdbcTemplate.queryForRowSet(SQL);
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }


    /**
     * 汇总信息：获取整个数据库的每张表的统计数 oracle版本
     */
    public List<List> getDBAllTableCount_oracle_1() throws Exception {


        List list = new ArrayList();

        List<String> tableList = getDBAllTableList();

        for (String table : tableList) {
            String SQL = "select count(1) from \"" + table + "\"";
            log.info("TB_name: " + table);
            SqlRowSet resultSet = currentJdbcTemplate.queryForRowSet(SQL);
            String count = null;
            while (resultSet.next()) {
                count = resultSet.getString(1);

            }
            String table_comment = getTableComment(table);
            ArrayList list1 = new ArrayList();
            list1.add(table);
            list1.add(count);
            list1.add(table_comment);
            list.add(list1);
        }


        return list;
    }

    /**
     * 汇总信息：获取整个数据库的每张表的统计数 oracle版本 使用系统统计表
     */
    public List<List> getDBAllTableCount_oracle() throws Exception {

        List list = new ArrayList();
        String SQL = "select TABLE_NAME,NUM_ROWS from user_all_tables";

        SqlRowSet resultSet = currentJdbcTemplate.queryForRowSet(SQL);

        while (resultSet.next()) {
            String table = resultSet.getString(1);
            String count = resultSet.getString(2);

            String table_comment = getTableComment(table);
            ArrayList list1 = new ArrayList();
            list1.add(table);
            list1.add(count);
            list1.add(table_comment);
            list.add(list1);
        }
        return list;
    }

    /**
     * 汇总信息：获取整个数据库的每张表的统计数 oracle版本 使用系统统计表
     */
    public List<String> getTableCountByTableName(String tableName) throws Exception {

        ArrayList list1 = new ArrayList();
        String SQL = "select count(*) from \"" + tableName + "\"";

        SqlRowSet resultSet = currentJdbcTemplate.queryForRowSet(SQL);

        while (resultSet.next()) {
            String COUNT = resultSet.getString(1);

            String table_comment = getTableComment(tableName);

            list1.add(tableName);
            list1.add(COUNT);
            list1.add(table_comment);
        }

        return list1;
    }

    @RequestMapping(value = "/generateCommentSQLIntoFile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String generateCommentSQLIntoFile(String exporRootPath) throws Exception {
        JSONObject res = new JSONObject();
//        currentJdbcTemplate = readOnlyJdbcTemplate;
        if (null == exporRootPath || "".equals(exporRootPath)) {
            exporRootPath = CommonInstance.DEFAULT_FILE_OUTPUTPATH;
        }

        StringBuilder commentBuilder = new StringBuilder();
        // 1.获取所有表list
        List<String> allTableList = getDBAllTableList();
        // 2.遍历获取columnComment: tableName+name+comment
        for (String singleTable : allTableList) {
            //生成table comment SQL 语句
            String tableComment = getTableComment(singleTable);
            commentBuilder.append("COMMENT ON TABLE \"" + username_read_only_datasource + "\".\"" + singleTable + "\" IS '" + tableComment + "';\r\n");
            List<List> list = getColumnCommentList(singleTable);
            for (List subList : list) {
                String columnName = (String) subList.get(0);
                String columnComment = (String) subList.get(1);
                //生成column comment SQL 语句
                commentBuilder.append("COMMENT ON COLUMN \"" + username_read_only_datasource + "\".\"" + singleTable + "\".\"" + columnName + "\" IS '" + columnComment + "';\r\n");
            }
        }
        String formatTimeStr = null;
        // 5.写入文件
        String exportFileDirectory = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        formatTimeStr = format.format(Calendar.getInstance().getTime());
        exportFileDirectory = exporRootPath + "futianProject_DBComment\\" + username_read_only_datasource + "_" + formatTimeStr;

        File folder = new File(exportFileDirectory);
        if (!folder.exists()) {
            boolean mkdir = folder.mkdirs();
            if (mkdir) {
                log.info("create folder ： " + exportFileDirectory + " success!");
            }
        }

        //创建一个txt文件
        File commentFile = new File(exportFileDirectory + "\\" + url_read_only_datasource.split("@")[1].split(":")[0] + "_" + formatTimeStr + ".sql");
        boolean newFile1 = commentFile.createNewFile();
        if (newFile1 == false) {
            res.put("fail", "file:" + commentFile + " already exists!");
            return res.toJSONString();
        }

        FileWriter fileWriter = new FileWriter(commentFile);
        fileWriter.write(commentBuilder.toString());
        fileWriter.flush();
        fileWriter.close();
        log.info("create file ： " + commentFile.getAbsolutePath() + " success!");
        res.put("success", "created file ： " + commentFile.getAbsolutePath());
        return res.toJSONString();
    }

//    /**
//     * 优化：指定 源-目的 , 为目的库中的表添加comment,增加异常处理,返回没有comment的表和字段
//     *
//     * @param exporRootPath
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/transmitCommentToTarget", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public String transmitCommentToTarget(String ipSrc, String ipTarget, String exporRootPath) throws Exception {
//        JdbcTemplate srcJdbcTemplate = null;
//        //url_read_only_datasource --> url_primary_datasource
//        if (url_read_only_datasource.contains(ipSrc) && url_primary_datasource.contains(ipTarget)) {
//            currentJdbcTemplate = primaryJdbcTemplate;
//            srcJdbcTemplate = readOnlyJdbcTemplate;
//            //url_primary_datasource --> url_read_only_datasource
//        } else if (url_primary_datasource.contains(ipSrc) && url_read_only_datasource.contains(ipTarget)) {
//            currentJdbcTemplate = readOnlyJdbcTemplate;
//            srcJdbcTemplate = primaryJdbcTemplate;
//        }
//
//        //从目标库中读取表的列表，以及每张表的字段列表
//        JSONObject res = new JSONObject();
//        if (null == exporRootPath || "".equals(exporRootPath)) {
//            exporRootPath = CommonInstance.DEFAULT_FILE_OUTPUTPATH;
//        }
//
//        StringBuilder commentBuilder = new StringBuilder();
//        //1.获取所有表list
//        List<String> allTableList = getDBAllTableList();
//        //2.遍历获取columnComment: tableName+name+comment
//        for (String singleTable : allTableList) {
//            //.生成table comment SQL 语句
//            String tableComment = getTableComment(singleTable);
//            commentBuilder.append("COMMENT ON TABLE \"" + username_read_only_datasource + "\".\"" + singleTable + "\" IS '" + tableComment + "';\r\n");
//            List<List> list = getColumnCommentList(singleTable);
//            for (List subList : list) {
//                String columnName = (String) subList.get(0);
//                String columnComment = (String) subList.get(1);
//                //.生成column comment SQL 语句
//                commentBuilder.append("COMMENT ON COLUMN \"" + username_read_only_datasource + "\".\"" + singleTable + "\".\"" + columnName + "\" IS '" + columnComment + "';\r\n");
//            }
//        }
//        String formatTimeStr = null;
//        //5.写入文件
//        String exportFileDirectory = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//        formatTimeStr = format.format(Calendar.getInstance().getTime());
//        exportFileDirectory = exporRootPath + "futianProject_DBComment\\" + username_read_only_datasource + "_" + formatTimeStr;
//
//        File folder = new File(exportFileDirectory);
//        if (!folder.exists()) {
//            boolean mkdir = folder.mkdirs();
//            if (mkdir) {
//                log.info("create folder ： " + exportFileDirectory + " success!");
//            }
//        }
//
//        //创建一个txt文件
//        File commentFile = new File(exportFileDirectory + "\\" + url_read_only_datasource.split("@")[1].split(":")[0] + "_" + formatTimeStr + ".sql");
//        boolean newFile1 = commentFile.createNewFile();
//        if (newFile1 == false) {
//            res.put("fail", "file:" + commentFile + " already exists!");
//            return res.toJSONString();
//        }
//
//        FileWriter fileWriter = new FileWriter(commentFile);
//        fileWriter.write(commentBuilder.toString());
//        fileWriter.flush();
//        fileWriter.close();
//        log.info("create file ： " + commentFile.getAbsolutePath() + " success!");
//        res.put("success", "created file ： " + commentFile.getAbsolutePath());
//        return res.toJSONString();
//    }

    @RequestMapping(value = "/createTablesByDataDict2", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public HashMap createTablesByDataDict2() throws IOException, SQLException {

        HashMap res = new HashMap();
        //: 读取properties文件 加载数据字典
//        InputStream inputStream = DataBaseController.class.getResourceAsStream("/dataDictionary_xiaoan.properties");
        InputStream inputStream = DataBaseController4Oracle.class.getResourceAsStream("/dataDictionary_sanxiao_place.properties");
        Properties properties = new OrderedProperties();
        properties.load(inputStream);
        if (properties == null) {
            res.put("error", "FileNameError");
            return res;
        }

        String tableList = properties.getProperty("tableList");
        String[] tableArr = tableList.split(",");

        // : 遍历 创建表
        for (int i = 0; i < tableArr.length; i++) {
            dropTable(tableArr[i]);
            boolean createRes = createTable(tableArr[i], properties);
            if (createRes)
                log.info("\ncreate table :" + tableArr[i] + "  success!\n");
            res.put(tableArr[i], createRes);
        }

        return res;
    }


    /**
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/createColumnNameDesc", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public HashMap createColumnNameDesc() throws IOException {

        HashMap res = new HashMap();
        //: 读取properties文件 加载数据字典
        InputStream inputStream = DataBaseController4Oracle.class.getResourceAsStream("/apiDictionary2.properties");
        Properties properties = new OrderedProperties();
        properties.load(inputStream);

        Set<String> keySet = properties.stringPropertyNames();
        String tableList = properties.getProperty("tableList");
        String[] tableArr = tableList.split(",");

//        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("/columnNameList.properties"));

        for (int i = 0; i < tableArr.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();

//            log.info(tableArr[i]);
            // : 遍历 创建表
            for (String key : keySet) {
                if (key.contains("columnType") && key.contains(tableArr[i])) {
                    String[] split = key.split("\\.");
                    String columnName = split[1];
                    stringBuilder.append(columnName + ",");
//                    fileWriter.write(tableArr[i]+".columnNameList="+columnName+"\n");
                }
            }

            System.out.println(tableArr[i] + ".columnList=" + stringBuilder.toString().substring(0, stringBuilder.length() - 1));
//            System.out.println(tableArr[i] + ".columnNameList=" + columnName);
        }
//        fileWriter.close();
        return res;
    }


    /**
     * 数据脱敏 -- 通过读取脱敏Excel文件描述
     *
     * @return
     */
    @RequestMapping(value = "/desensitization", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String desensitization(String sheetName, int startRow, int endRow, String startColStr, String endColStr) throws Exception {
        String filePath = "D:\\GitCode\\futianpro_docs2\\7.数据脱敏\\ZHFTYJJCPT_summary_0829脱敏需求整理.xls";
        int startCol = 1;
        int endCol = 12;
        int columnStartIndex = 3;//目标列开始下标
        if ((null != startColStr) && (!"".equals(startColStr))) {
            startCol = Integer.valueOf(startColStr);
        }
        if ((null != endColStr) && (!"".equals(endColStr))) {
            endCol = Integer.valueOf(endColStr);
        }
        List<ArrayList> list = ExcelReaderUtil.readExcel2List(filePath, sheetName, startRow, endRow, startCol, endCol);

        for (ArrayList<String> oneRow : list) {
            //对单张表进行处理,考虑col为空的情况
            String tablename = oneRow.get(0); //表名

            //需要用一个hashmap存当前列所对应的下标
            HashMap<String, Integer> columnIndexMap = new HashMap<String, Integer>();

            //遍历Excel中的一行，获取需要处理的字段，并通过SQL查询出来
            List queryColumns = new ArrayList();
            for (int i = 0; i < oneRow.size(); i++) {
                String targetColumn = oneRow.get(i);
                if (null == targetColumn) continue;
                String[] split = targetColumn.split("\\\\");
                for (int j = 0; j < split.length; j++) {
                    if (split[j].length() == 0) continue;//ORA-01741: 非法的零长度标识符\n
                    columnIndexMap.put(split[j], i);
                    if (i >= columnStartIndex) {
                        queryColumns.add(split[j]);
                    }
                }
            }
            String columns = printColumnList(queryColumns);
            if (columns.length() == 0) continue;
            String querySQL = "SELECT ROWID as ROW_ID," + columns + " FROM \"" + tablename + "\"";
            List<HashMap> queryResultList = base_oracleQuerySql(querySQL);

            //遍历每行
            for (HashMap<String, Object> map : queryResultList) {
                // 进来就是获取到的单行数据
                Set<String> set = map.keySet();
                for (String columnName : set) {
                    if ("ROW_ID".equals(columnName)) {
                        continue;
                    }
                    // 获取列下标，确定当前列所对应的处理函数
                    Integer index = columnIndexMap.get(columnName);
                    String value = (String) map.get(columnName);
                    //对应类型的值进行单独处理
                    Object newValue = "";
                    switch (index - 3) {
                        case DesensitizationType.PERSON_NAME:
                            newValue = DesensitizationUtil.personNameTransformer(value);
                            break;
                        case DesensitizationType.ENTERPRISE_NAME:
                            newValue = DesensitizationUtil.enterpriseNameTransformer(value);
                            break;
                        case DesensitizationType.TELEPHONE_NUM:
                            newValue = DesensitizationUtil.telephoneNumformer(value);
                            break;
                        case DesensitizationType.QQ_NUMBER:
                            newValue = DesensitizationUtil.qqNumberTransformer(value);
                            break;
                        case DesensitizationType.IDCARD_NUM:
                            newValue = DesensitizationUtil.idCardnumtransformer(value);
                            break;
                        case DesensitizationType.DETAIL_ADDRESS:
                            newValue = DesensitizationUtil.detailAddressTransformer(value);
                            break;
                        case DesensitizationType.STANDARD_ADDRESS:
                            newValue = DesensitizationUtil.standardAddressTransformer(value);
                            break;
                        case DesensitizationType.COORDINATE_VALUE:
                            if ("jd84".equals(columnName) || "wd84".equals(columnName)) continue;
                            if ("LON".equals(columnName) || "LAT".equals(columnName)
                                    || "ABSY".equals(columnName) || "ABSX".equals(columnName)) {
                                newValue = new Integer(0);
                                break;
                            } else {
                                newValue = DesensitizationUtil.coordinateValueTransformer(value);
                                break;
                            }
                        default:
                            newValue = "*";
                    }
                    // 赋给每个col新的值
                    map.put(columnName, newValue);
                }

                // 组装新的SQL：columnName=newValue
                Set<String> keySet = map.keySet();
                int counterIndex = 0;
                String newSetSQLs = "";
                for (String colName : keySet) {
                    if ("ROW_ID".equals(colName)) continue;
                    if (null == map.get(colName)) {
                        log.info("null value");
                        newSetSQLs += "\"" + colName + "\"=NULL,";
//                        continue;
                    } else if (map.get(colName).getClass().equals(Integer.class)) {
                        newSetSQLs += "\"" + colName + "\"=" + map.get(colName) + ",";
                    } else {
                        newSetSQLs += "\"" + colName + "\"='" + map.get(colName) + "',";
                    }

                }
                if (newSetSQLs.length() == 0) continue;
                if (',' == newSetSQLs.toCharArray()[newSetSQLs.length() - 1]) {
                    newSetSQLs = newSetSQLs.substring(0, newSetSQLs.length() - 1);
                }

                // 更新回原表
//                String updateSQL = "UPDATE \"" + tablename + "\" SET " + newSetSQLs + " WHERE ROWID='" + map.get("ROW_ID") + "'";
                //创建新表并insert
                String updateSQL = "UPDATE \"" + tablename + "\" SET " + newSetSQLs + " WHERE ROWID='" + map.get("ROW_ID") + "'";
                log.info("-----------------UPDATE SQL!" + updateSQL);
                int i = base_oracleUpdateSql(updateSQL);
                if (i > 0) {
                    log.info("success!" + updateSQL);
                }
            }
        }
        log.info(" Finished!!!  public String desensitization()!");
        return "{\"res\":\"FINISHED\"}";
    }


    @RequestMapping(value = "/exportJson", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String exportJson() throws SQLException {
        List resList = new ArrayList<HashMap<String, String>>();
        String sql = "SELECT distinct FWDM,FH,FWSSLC,SYYT,SYQK " +
                "FROM \"blk_house\"";
        List<HashMap> houseList = base_oracleQuerySql(sql);
        for (HashMap<String, String> map : houseList) {
            Map rowmap = new HashMap<String, String>();
            String fwdm = map.get("FWDM");
            rowmap.put("code", fwdm);
            rowmap.put("num", map.get("FH"));

            HashMap<String, Object> house = new HashMap<String, Object>();
            // 单个房间的信息
            ArrayList<HashMap<String, String>> houseInfoList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> oneHouseInfo1 = new HashMap<String, String>();
            oneHouseInfo1.put("name", "房屋编码");
            oneHouseInfo1.put("value", fwdm);
            houseInfoList.add(oneHouseInfo1);
            HashMap<String, String> oneHouseInfo2 = new HashMap<String, String>();
            oneHouseInfo2.put("name", "房屋性质");
            oneHouseInfo2.put("value", map.get("SYYT"));
            houseInfoList.add(oneHouseInfo2);
            HashMap<String, String> oneHouseInfo3 = new HashMap<String, String>();
            oneHouseInfo3.put("name", "房屋用途");
            oneHouseInfo3.put("value", map.get("SYQK"));
            houseInfoList.add(oneHouseInfo3);
            house.put("name", "房屋信息");
            house.put("data", houseInfoList);

            String sql2 = "SELECT XM,LXFS,SFZH " +
                    "FROM \"blk_population\" " +
                    "WHERE FWDM='" + fwdm + "'";
            HashMap<String, Object> peopleInfo = new HashMap<String, Object>();
            List<HashMap> polulationList = base_oracleQuerySql(sql);
            ArrayList<HashMap> peopleList = new ArrayList<HashMap>();
            HashMap<String, String> peopleInfoMap = new HashMap<String, String>();
            for (HashMap<String, String> m : polulationList) {
                Set<String> set = m.keySet();
                for (String k : set) {
                    peopleInfoMap.put(k, m.get(k));
                }
                peopleList.add(peopleInfoMap);
            }
            peopleInfo.put("name", "人口信息");
            peopleInfo.put("data", peopleList);
            ArrayList data = new ArrayList<HashMap>();


            data.add(house);
            data.add(peopleInfo);

            rowmap.put("data", data);
            return null;
        }
        return null;
    }

    //查询该表是否有comment
    private boolean checkIfTableHasComment(String tableName) throws SQLException {
        boolean res = false;
        String SQL1 = "select COMMENTS from user_tab_comments where table_name='" + tableName + "'";

        SqlRowSet i1 = currentJdbcTemplate.queryForRowSet(SQL1);
        while (i1.next()) {
            String table_comment = i1.getString(1);
            if ("null".equals(table_comment) || "".equals(table_comment) || null == table_comment) {
                return false;
            } else {
                return true;
            }
        }
        return res;
    }

    //查询该表的字段是否有comment
    private boolean checkIfCollumnHasComment(String tableName, String columnName) throws SQLException {
        boolean res = false;
        String SQL1 = "select comments from user_col_comments where table_name='" + tableName + "' and column_name='" + columnName + "'";
        SqlRowSet rowSet = currentJdbcTemplate.queryForRowSet(SQL1);
        while (rowSet.next()) {
            String column_comment = rowSet.getString(1);
            if ("null".equals(column_comment) || "".equals(column_comment) || null == column_comment) {
                return false;
            } else {
                return true;
            }
        }
        return res;
    }

    private void createCells_dif_name(ArrayList<String> list, Row row, HSSFCellStyle cellStyle, HSSFFont font) {

        for (int i = 0; i < list.size(); i++) {
            Cell cell = row.createCell(i);
                cell.setCellStyle(CommonCellStype.setCellStyle1(cellStyle, font));
            cell.setCellValue(list.get(i));
        }
    }


    private boolean createTable(String tableName, Properties properties) {

        boolean res = false;

        //:1.准备SQL
        StringBuilder SQL_createTable = new StringBuilder("CREATE TABLE \"ZHFTYJJCPT\".\"" + tableName + "\" \n" +
                "(");

        //1.1 获取表字段列表
        List columnList = getTableColumnList(tableName, properties);
//        String columnList = properties.getProperty(tableName + ".columnList");

//        String[] columnArr = columnList.split(",");

        // 1.2 遍历添加column 描述
        for (int i = 0; i < columnList.size(); i++) {
            if (i == columnList.size() - 1) {
                SQL_createTable.append("\t\t\"" + columnList.get(i) + "\" " + properties.getProperty(tableName + "." + columnList.get(i) + ".columnType") + " NULL \n");
            } else {
                SQL_createTable.append("\t\t\"" + columnList.get(i) + "\" " + properties.getProperty(tableName + "." + columnList.get(i) + ".columnType") + " NULL ,\n");
            }
        }

        SQL_createTable.append("\n )");

        //:2.执行创建
        log.info("\n-----executing DDL ---------\n" + SQL_createTable.toString());
        PreparedStatement statement = null;
        try {
            currentJdbcTemplate.execute(SQL_createTable.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    private boolean dropTable(String tableName) {

        boolean res = false;

        //:1.准备SQL
        StringBuilder SQL_dropTable = new StringBuilder("DROP TABLE \"ZHFTYJJCPT\".\"" + tableName + "\"");

        //:2.执行创建
        log.info("\n-----executing DDL ---------\n" + SQL_dropTable.toString());
        PreparedStatement statement = null;
        try {
            statement.execute(SQL_dropTable.toString());
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 955) {  // ORA-00955: 名称已由现有对象使用
                log.error(e.getLocalizedMessage());
                return false;
            } else if (e.getErrorCode() == 2258) {
                log.error(e.getLocalizedMessage());//冲突的Null值
                return false;
            } else {
                e.printStackTrace();
            }
        }

        return res;
    }

    /**
     * 通过读取数据字典，获取单张表的所有字段名称组成的集合
     *
     * @param tableName
     * @param properties
     * @return
     */
    private List getTableColumnList(String tableName, Properties properties) {
        ArrayList<String> columnList = new ArrayList<String>();
        Set<String> keySet = properties.stringPropertyNames();
        for (Object key : keySet) {
            String keyName = (String) key;
            if (keyName.contains(tableName) && !keyName.contains("columnType") && !keyName.contains("tableComment")) {
                String[] split = keyName.split("\\.");
                int length = split.length;
                if (length == 2) {
                    columnList.add(split[1]);
                }
            }
        }

        return columnList;
    }

    private String printColumnList(List queryColumns) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < queryColumns.size(); i++) {
            if (i == queryColumns.size() - 1) {
                builder.append("\"" + queryColumns.get(i) + "\"");
            } else {
                builder.append("\"" + queryColumns.get(i) + "\",");
            }
        }

        return builder.toString();
    }

}
