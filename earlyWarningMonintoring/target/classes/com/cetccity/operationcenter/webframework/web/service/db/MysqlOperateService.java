package com.cetccity.operationcenter.webframework.web.service.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class MysqlOperateService extends DBOperateService{

    private static final Logger logger = LoggerFactory.getLogger(MysqlOperateService.class);

    @Autowired
    DataSource dataSource;

    /**
     * 通过表名，查询该表所有数据
     * @param tbName
     * @return
     */
    public List<LinkedHashMap> queryAllDataByTableName(String tbName){

        List<LinkedHashMap> list  = new ArrayList<LinkedHashMap>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "select * from "+tbName;
            logger.debug("sql: "+sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnNumber= resultSetMetaData.getColumnCount();
                LinkedHashMap hashMap = new LinkedHashMap();
                for (int i = 1; i <= columnNumber; ++i){
                    String colName = resultSetMetaData.getColumnName(i);
                    String typeName = resultSetMetaData.getColumnTypeName(i);
                    String value = resultSet.getString(i);
//                    logger.debug("colName: "+colName);
//                    logger.debug("typeName: "+ typeName);
//                    logger.debug("value: "+ value);

                    hashMap.put(colName,value);
                }
                list.add(hashMap);
            }
        } catch (SQLException e) {
            logger.error("database connection error!\n",e);
        } finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("connection close error!\n",e);
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("statement close error!\n",e);
                }
            }
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("resultSet close error!\n",e);
                }
            }
        }
        return list;
    }

    /**
     * 新增表字段,默认类型为VARCHAR（32） NULL
     * @param tbName
     * @param colName
     */
    public boolean addColumn(String tbName, String colName, String comment){
        boolean result = false;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "alter table "+tbName+" add "+colName+" VARCHAR(32) NULL COMMENT '"+comment+"'";
            logger.debug("sql: "+sql);
            result = statement.execute(sql);
        } catch (SQLException e) {
            if (e.getMessage().split(" ")[0].equalsIgnoreCase("Duplicate")){
                result = true;
            }else{
                logger.error("update database error!\n",e);
            }
        }finally {
            close(connection,statement);
        }
        return result;
    }

    /**
     * 修改一条数据的一个字段的值
     * @param tbName
     * @param id
     * @param colName
     * @param colValue
     * @return
     */
    public boolean updateDataColValue(String tbName, String id, String colName, String colValue){
        boolean result = false;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "UPDATE "+tbName+" SET "+colName+" = '"+colValue+"' WHERE id = "+id;
            logger.debug("sql: "+sql);
            result = statement.execute(sql);
        } catch (SQLException e) {
            logger.error("update database error!\n",e);
        }finally {
            close(connection,statement);
        }
        return result;
    }

    /**
     * 执行sql语句
     * @return
     */
    public boolean executeSql(String sql){
        boolean result = false;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            logger.debug("sql: "+sql);
            result = statement.execute(sql);
        } catch (SQLException e) {
            logger.error("update database error!\n",e);
        }finally {
            close(connection,statement);
        }
        return result;
    }

    public boolean close(Connection connection, Statement statement){
        boolean result = false;
        boolean connectionResult = false;
        boolean statementResult = false;
        if (connection!=null){
            try {
                connection.close();
                connectionResult = true;
            } catch (SQLException e) {
                logger.error("connection close error!\n",e);
            }
        }
        if (statement!=null){
            try {
                statement.close();
                statementResult = true;
            } catch (SQLException e) {
                logger.error("statement close error!\n",e);
            }
        }
        if (connectionResult && statementResult)
            result = true;
        return result;
    }

    public Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}
