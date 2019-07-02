package com.cetccity.operationcenter.webframework.web.controller.incident;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotDeviceMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotEventMapper;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.dao.iot.IotSensordataMapper;
import com.cetccity.operationcenter.webframework.web.model.iot.*;
import com.squareup.okhttp.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value = "/iot")
@Api(tags = "物联网接口服务")
@Slf4j
public class IotController {
    @Autowired
    IotEventMapper iotEventMapper ;

    @Autowired
    IotSensordataMapper iotSensordataMapper;

    @Autowired
    IotDeviceMapper iotDeviceMapper;
    
    @Autowired
    private CommonInstance commonInstance;

    Logger logger = LoggerFactory.getLogger(IotController.class);

    Date nowdate = new Date(System.currentTimeMillis());

    // 创建一个数据库连接
    Connection connection = null;
    // 创建预编译语句对象，一般都是用这个而不用Statement
    PreparedStatement pstm = null;
    // 创建一个结果集对象
    ResultSet rs = null;

  //  @Autowired
  //  DataSource dataSource;


    //获取所有设备列表
    //参数为传感器类型
    @RequestMapping(value = "/getSensorListByType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getSensorListByType(String device_type) throws SQLException {

        JSONObject result = new JSONObject();
        String data= " ";
        String urlStr = "http://"+ commonInstance.getInternetIp()+":"+ commonInstance.getInternetPort()+"/wlgz-hqcgqlb/v1.0/deviceList";
        String token = "c94c0cea596342ae9d39fe06cc0660f9" ;
        String sys_id = "220";
        String content = "{\r\n\"device_type\":\"";
        content = content +device_type;
        content = content +"\"\r\n}\r\n";
        //------------------------

        if(device_type==null) {
            result.put("detail", "lack of para , device_type ");
            return result ;
        }

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(urlStr)
                .post(body)
                .addHeader("token", token)
                .addHeader("sys_id", sys_id)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .build();
        try {
            Response response = client.newCall(request).execute();
            data= response.body().string();
        }
        catch (IOException e) {
            log.error(e.toString());
            logger.error(" Class：IotController Method：getSensorListByType ：request iot platform service ");

        }
        //---------------------------
        JSONObject jsonResponse = JSON.parseObject(data);
        JSONArray array = jsonResponse.getJSONArray("devices");

        Iterator<Object> iterator = array.iterator();
        //设备存入
        while (iterator.hasNext()) {

            JSONObject next = (JSONObject) iterator.next();
            String device_code = next.getString("device_code");
            String device_name = next.getString("device_name");
            JSONArray address_infoArray = next.getJSONArray("address_info");
            JSONArray location = next.getJSONArray("location");

            String  address = new String() ;
            String  street =  new String();
            String district = new String();

            String latitude = new String();
            String longitude= new String();

            String latitudeSZ = new String();
            String longitudeSZ= new String();

            Iterator<Object> addressIterator = address_infoArray.iterator();
            while(addressIterator.hasNext())
            {
                JSONObject nextAddress_infoIterator = (JSONObject) addressIterator.next();
                  address =nextAddress_infoIterator.getString("address");
                  street =nextAddress_infoIterator.getString("street");
                  district =nextAddress_infoIterator.getString("district");
            }
            Iterator<Object> locationIterator = location.iterator();
            while(locationIterator.hasNext())
            {
                JSONObject nextLocationIterator = (JSONObject) locationIterator.next();
                String type= new String();
                if(type.equals("0")){
                    latitude =nextLocationIterator.getString("latitude");
                    longitude =nextLocationIterator.getString("longitude");
                }
                else
                {
                    latitudeSZ =nextLocationIterator.getString("latitude");
                    longitudeSZ =nextLocationIterator.getString("longitude");
                }
            }
            IotDeviceModelExample iotDeviceModelExample =new IotDeviceModelExample();
            IotDeviceModelExample.Criteria  criteria = iotDeviceModelExample.createCriteria();
            criteria.andDeviceCodeEqualTo(device_code);

            try{
                List<IotDeviceModel> iotDeviceModels = iotDeviceMapper.selectByExample(iotDeviceModelExample);
                if(iotDeviceModels.size()>0){
                }
                else{
                    //传感器不存在，增加传感器
                    IotDeviceModel tempModel =new IotDeviceModel() ;
                    tempModel.setStreet(street);
                    tempModel.setAddress(address);
                    tempModel.setDeviceCode(device_code);
                    tempModel.setName(device_name);
                    tempModel.setJd84(latitude);
                    tempModel.setWd84(longitude);
                    iotDeviceMapper.insertSelective(tempModel);
                }
            }catch (Exception e){
                log.error(e.toString());

            }
          //  addSensorHardware( address,device_code,device_name,latitude,longitude,street,device_type) ;

        }

        result.put("detail", jsonResponse);

        return result;
    }


    //获取指定设备的传感器数据
    // 参数 device_code 统一设备编码
    //用于显示整数等等
    @ApiOperation(value = "获取指定设备的传感器数据", notes = "获取指定设备的传感器数据", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_code", value = "统一设备编码", paramType = "query")

    })
    @RequestMapping(value = "/getData", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getData( String device_code) {
        JSONObject result = new JSONObject();
        if(device_code==null)
        {
            result.put("error", "empty device_code");
            return result;
        }

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String content = "{\r\n  \"device_code\":\"";
        content=content +device_code;
        content=content+"\"\r\n}\r\n" ;

        RequestBody body = RequestBody.create(mediaType,content );
        Request request = new Request.Builder()
                .url("http://10.190.55.62:8080/wlgz-sjcx/v1.0/dataQuery")
                .post(body)
                .addHeader("token", "c94c0cea596342ae9d39fe06cc0660f9")
                .addHeader("sys_id", "220")
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "a7893140-b27e-92c8-ffff-ec52e61d0f9d")
                .build();
        Response response;
        try {
              response = client.newCall(request).execute();
        } catch (Exception e) {
            logger.error("remote response exception,can not request iot platform data");
            result.put("error", "can not request iot platform data ");
            return result;

        }
        String data ;

        try {
            data= response.body().string();
        } catch (Exception e) {
            logger.error("error ,response.body().string()");
            result.put("error", "response.body().string() ");
            return result;

        }
        JSONObject jsonObj ;

        try {
            jsonObj = JSON.parseObject(data);
        } catch (Exception e) {
            logger.error("JSON.parseObject(data)");
            result.put("error","JSON.parseObject(data)");
            return result;

        }

        JSONObject realData ;
        try {
            realData = jsonObj.getJSONObject("realData");
        } catch (Exception e) {
            logger.error(" jsonObj.getJSONObject(\"realData\")");
            result.put("error"," jsonObj.getJSONObject(\"realData\")");
            return result;

        }



                if (null == realData) {
                    result.put("info", "empty response:realData");
                    return result;
                }



                device_code =  realData.getString("device_code");
                int data_count = realData.getInteger("data_count");
                if( data_count==0){
                    result.put("info", "empty response: data is zero");
                    return result;
                }else{
                    JSONArray array = realData.getJSONArray("datas");
                    result.put("detail", array);
                    Iterator<Object> iterator = array.iterator();
                    while (iterator.hasNext()) {

                        JSONObject next = (JSONObject) iterator.next();
                        String data_code = next.getString("data_code");
                        String data_value = next.getString("data_value");
                        addSensorData( device_code, data_code,  data_value);
                        logger.info(device_code + " " + data_code + " " + data_value);

                    }

                }






        return result;
    }


    /**
     * 循环得到传感器数据
     */
    /**
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
     */

    /**
     * 定时卡点计算。每天凌晨 02:00 执行一次
     */
  //  @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
//    @Scheduled(cron = "* 1 * * * *")
    public void timerGetSensorData() {
        int i=0 ;
        logger.error("timer ");
        ArrayList resultList= getSensorList();

        for(Object result:resultList){
            i++ ;
            logger.info(String.valueOf(i));
            getData(result.toString());

        }
    }

    /**
     * 测试本地数据库
     */
 //   @RequestMapping(value = "/datasql", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
//    @Scheduled(cron = "* 1 * * * *")
    public void datasql() {
        String id ;

        connection = getConnection();
        String sql = "SELECT * FROM \"ZHFTYJJCPT\".\"IOT_EVENT\"";
        try {
            // 计算数据库student表中数据总数
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while(rs.next()) {

                 id = rs.getString("ID");
                id=id ;

            }


        } catch (SQLException e) {
            logger.error(" Class:IotController Method:GetSensorList");
        } finally {
            releaseResource();
        }

    }


    /**
     * 测试example
     */
    @RequestMapping(value = "/example", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
//    @Scheduled(cron = "* 1 * * * *")
    public void example() {
        int a ;

        IotEventModelExample example= new IotEventModelExample();
     //   IotEventModelExample.Criteria criteria = example.createCriteria();
      //  criteria.andDeviceCodeIsNull();
       // example.setOrderByClause("username asc,email desc");
        BigDecimal max ;
        example.setOrderByClause("ID desc"); //ASC desc
        //example.setOrderByClause("字段名 ASC"); //升序排列，desc为降序排列
        List<IotEventModel> lists = iotEventMapper.selectByExample(example);
        for(IotEventModel list:lists){
            max=list.getId();
            break;
        }

    }


    /**
     * 测试example
     */
    @RequestMapping(value = "/example2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
//    @Scheduled(cron = "* 1 * * * *")
    public void example2() {
        int a ;

        IotSensordataModelExample example= new IotSensordataModelExample();


        List<IotSensordataModel> lists = iotSensordataMapper.selectByExample(example);
        for(IotSensordataModel list:lists){

            break;
        }

    }


    /**
     * 测试example
     */
  //  @RequestMapping(value = "/select", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
//    @Scheduled(cron = "* 1 * * * *")
    public String select() {
        IotSensordataModelExample iotSensordataModelExample = new IotSensordataModelExample();
        IotEventModel iotEventModel= new IotEventModel();
    //    List<Io> list = iotEventMapper.selectByExample(iotSensordataModelExample);
      //  return list;
        return "string" ;
    }




    public ArrayList  getSensorList()
    {
        ArrayList result=new ArrayList();
        connection = getConnection();
        String sql = "select \"device_code\" from \"iot_device\" where \"device_type\" ='0024' and \"update_status\"='2'";

        try {
            // 计算数据库student表中数据总数
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String data =rs.getString("device_code") ;
                result.add(data);
            }
        } catch (SQLException e) {
            logger.error(" Class:IotController Method:GetSensorList");
        } finally {
            releaseResource();
        }


        return result;


    }

    /**
     * 向数据库中增加数据传感器-硬件
     */

    public void addSensorHardware(String address,
                                  String device_code, String device_name , String latitude,String longitude,String street,String device_type) throws SQLException {

        connection = getConnection();
        String sql = "select max(\"id\") as max from  \"iot_device\"  where 1=1 \n";
        String sqlStr = "INSERT INTO \"iot_device\" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;//18个

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

            // 执行插入数据操作
            nowdate =nowdate ;
            pstm = connection.prepareStatement(sqlStr);
            pstm.setInt(1, id); //id
            pstm.setString(2, device_name);//name
            pstm.setString(3, device_code);//device_code
            pstm.setString(4, "0.0");//jd84
            pstm.setString(5, "0.0");//wd84
            pstm.setString(6, address);//address
            pstm.setDate(7, nowdate);
            pstm.setDate(8, nowdate);
            pstm.setString(9, "1");//update_status
            pstm.setString(10, "1");//uuid
            pstm.setString(11, "1");//region
            pstm.setString(12, "0");//reserved3
            pstm.setString(13, "0");//reserved4
            pstm.setString(14, "0");//reserved5
            pstm.setString(15, street);//street
            pstm.setString(16, device_type);//device_type
            pstm.setString(17, longitude);//jd84
            pstm.setString(18, latitude);//wd84
            pstm.executeUpdate();


        } catch (SQLException e) {
            logger.error(" Class:IotController Method:AddData:Get sensor list fail");
        } finally {
            releaseResource();
        }

    }


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
        //    connection = DriverManager.getConnection("jdbc:oracle:thin:@database:1521/coreora", "ZHFTYJJCPT","ToKreDi*nJ" );
            //本地库
            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.192.19.107:1521/coreora", "ZHFTYJJCPT","ToKreDi*nJ" );
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
                log.error(e.toString());
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
    }


}
