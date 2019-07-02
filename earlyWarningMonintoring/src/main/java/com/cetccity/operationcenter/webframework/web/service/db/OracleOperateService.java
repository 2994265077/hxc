package com.cetccity.operationcenter.webframework.web.service.db;

import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by dongxin on 2018/6/21.
 */
@Service
public class OracleOperateService extends DBOperateService{

    private static final Logger logger = LoggerFactory.getLogger(OracleOperateService.class);

    @Autowired
    DataSource dataSource;
    @Autowired
	private CommonInstance commonInstance;
    /**
     * 通过表名，查询该表所有数据
     * @param tbName
     * @return
     */
    public List<LinkedHashMap> queryAllDataByTableName(String tbName) {
        List<LinkedHashMap> list  = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "select * from \""+tbName+"\"";
            logger.debug("sql: "+sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnNumber= resultSetMetaData.getColumnCount();
                LinkedHashMap hashMap = new LinkedHashMap();
                for (int i = 1; i <= columnNumber; ++i){
                    String colName = resultSetMetaData.getColumnName(i);
                    String value = resultSet.getString(i);
                    hashMap.put(colName,value);
                }
                list.add(hashMap);
            }
        } catch (SQLException e) {
            logger.error("database connection error!\n",e);
        } finally {
            close(connection,statement);
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
     * 判断tableName是否存在
     * @return
     */
    public boolean tableNameIsExist(String tableName){
        String sql = "SELECT DISTINCT TABLE_NAME, OWNER FROM all_tab_columns WHERE OWNER= '"+commonInstance.getPrimaryDatasourceUsername()+"' and TABLE_NAME = '"+tableName+"'";
        boolean result;
        List<LinkedHashMap> map_list = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int len = rs.getMetaData().getColumnCount();
                LinkedHashMap row = new LinkedHashMap();
                for (int i = 1; i <= len; ++i) {
                    String colName = rs.getMetaData().getColumnName(i);
                    String value = rs.getString(colName);
                    row.put(colName, value);
                }
                map_list.add(row);
            }
            if (map_list.isEmpty()){
                result = false;
            }else{
                result = true;
            }
            logger.debug("sql: "+sql);
        } catch (SQLException e) {
            result = false;
            logger.error("query oracle error!\nsql="+sql,e);
        }finally {
            close(connection,statement);
        }
        return result;
    }
    /**
     * 执行查询sql语句
     * @return
     */
    public int queryCount(String sql){
        int result;
        Connection connection = null;
        Statement statement = null;
        try {
            int count = 0;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                 count = rs.getInt(1);
            }
            result = count;
            logger.debug("sql: "+sql);
        } catch (SQLException e) {
            result = 0;
            logger.error("query oracle error!\nsql="+sql,e);
        }finally {
            close(connection,statement);
        }
        return result;
    }

    /**
     * 执行查询sql语句
     * @return
     */
    public List<LinkedHashMap> querySql(String sql){
        List<LinkedHashMap> result = new ArrayList<LinkedHashMap>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int len = rs.getMetaData().getColumnCount();
                LinkedHashMap row = new LinkedHashMap();
                for (int i = 1; i <= len; ++i) {
                    String colName = rs.getMetaData().getColumnName(i);
                    String value = rs.getString(colName);
                    row.put(colName, value);
                }
                result.add(row);
            }
            logger.debug("sql: "+sql);
        } catch (SQLException e) {
            logger.error("query oracle error!\nsql="+sql,e);
        }finally {
            close(connection,statement);
        }
        return result;
    }

    /**
     * 执行查询sql语句--返回hashMap类型的List
     * @return
     */
    public List<HashMap> querySql2(String sql){
        List<HashMap> result = new ArrayList<HashMap>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int len = rs.getMetaData().getColumnCount();
                HashMap row = new HashMap();
                for (int i = 1; i <= len; ++i) {
                    String colName = rs.getMetaData().getColumnName(i);
                    String value = rs.getString(colName);
                    row.put(colName, value);
                }
                result.add(row);
            }
            logger.info("sql: " + sql);

        } catch (SQLException e) {
            logger.error("query oracle error!\nsql="+sql,e);
        }finally {
            close(connection,statement);
        }
        return result;
    }

    /**
     *  @Author huangzezhou
     *  @Description //执行sql
     *  @Date 2018/7/18 11:39
     *  @Param  [sql]
     *  @Return boolean
     *  @Throws
     * */
    public boolean excuteSql(String sql){

        Connection connection = null;
        Statement statement = null;
        boolean result = false;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            result = statement.execute(sql);
            logger.debug("sql: " + sql);
            return result;
        } catch (SQLException e) {
            logger.error("query oracle error!\nsql="+sql,e);
        }finally {
            close(connection,statement);
            return result;
        }
    }

    /**
     *  @Author huangzezhou
     *  @Description //创建视图，将异常抛出不做处理
     *  @Date 2018/7/18 11:38
     *  @Param
     *  @Return
     *  @Throws
     * */
    public boolean createView(String sql){

        Connection connection = null;
        Statement statement = null;
        boolean result = false;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            result = statement.execute(sql);
            logger.debug("sql: " + sql);
            return result;
        } catch (SQLException e) {
            if (!(e.getErrorCode() == 955)){
                logger.error("query oracle error!\nsql="+sql,e);
            }
        }finally {
            close(connection,statement);
            return result;
        }
    }

    private boolean close(Connection connection, Statement statement){
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
}
