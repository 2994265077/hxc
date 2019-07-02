package com.cetccity.operationcenter.webframework.web.service.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Service
public class OracleOperateIoTService {
    private static final Logger logger = LoggerFactory.getLogger(OracleOperateIoTService.class);
    // 创建一个数据库连接
    Connection connection = null;
    // 创建预编译语句对象，一般都是用这个而不用Statement
    PreparedStatement pstm = null;
    // 创建一个结果集对象
    ResultSet rs = null;


    // 增加传感器数据
    public void addSensorData(String device_code,String data_code, String data_value) {

        connection = getConnection();
        String sql = "select max(\"id\") as max from  \"iot_sensordata\"  where 1=1 \n";
        String sqlStr = "INSERT INTO \"iot_sensordata\" VALUES (?,?,?,?,?,?,?,?,?,?,?)" ;//11个
        int id= 0 ;
        try {
            // 计算数据库student表中数据总数
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id=rs.getInt("max") +1;
            }
        } catch (SQLException e) {

        }

        try {

            pstm = connection.prepareStatement(sqlStr);
            pstm.setInt(1, id); //id
            pstm.setString(2, device_code);//device_code
            pstm.setString(3, data_code);//data_code
            pstm.setString(4, data_value);//data_value
            Date nowdate = new Date(System.currentTimeMillis());
            pstm.setDate(5, nowdate);//create_time
            pstm.setDate(6, nowdate);//update_time
            pstm.setString(7,"1");//update_status
            pstm.setString(8, "0");//reserved2
            pstm.setString(9, "0");//reserved3
            pstm.setString(10, "0");//reserved4
            pstm.setString(11, "0");//reserved5
            pstm.executeUpdate();


        } catch (SQLException e) {
            logger.error(" Class:IotController Method:AddData:Get sensor list fail");
        } finally {
            releaseResource();
        }


    }





    /**
     * 获取Connection对象
     *
     * @return
     */
    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 开发库 connection = DriverManager.getConnection("jdbc:oracle:thin:@database:1521/orclcetc", "ZHFTYJJCPT","ToKreDi*nJ" );
            //生产库
            connection = DriverManager.getConnection("jdbc:oracle:thin:@database:1521/coreora", "ZHFTYJJCPT","ToKreDi*nJ" );


            //   logger.error(" Class:IotController Method:getConnection:getConnection Oracle success");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * 释放资源
     */
    public void releaseResource() {
        if (rs != null) {
            try {
                rs.close();
                //      logger.error(" Class:IotController Method:ReleaseResource:ReleaseResource Oracle success");
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }
}
