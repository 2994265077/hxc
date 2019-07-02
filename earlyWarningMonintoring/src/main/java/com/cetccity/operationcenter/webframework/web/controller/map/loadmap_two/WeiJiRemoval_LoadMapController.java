/*
package com.cetccity.operationcenter.webframework.web.controller.map.loadmap_two;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

*/
/**
 * 落图--卫计去重
 *//*

@Api(value = "落图--卫计去重", tags = "根据表名落图")
@Controller
public class WeiJiRemoval_LoadMapController {

    Logger logger = LoggerFactory.getLogger(WeiJiRemoval_LoadMapController.class);

    @Autowired
    LoadMapUtil loadMapUtil;

    @ApiOperation(value = "卫计去重YJJC_QWJJ_ORG_V落图", notes = "根据表名YJJC_QWJJ_ORG_V落图")
    @RequestMapping(value = "/YJJC_QWJJ_ORG_V/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadMap> findWeiJiRemovalByEs() throws IOException {
        String tableName;
        String layerid;
        InputStream inputStream =ESOperate.class.getResourceAsStream("/loadmap.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String tableNameUrl = "YJJC_QWJJ_ORG_V";
        String value = properties.getProperty(ESOperate.dbName+"."+tableNameUrl);
        logger.info("tableName-->" + tableNameUrl);

        if(tableNameUrl.contains("@")){
            //截取@之前的字符串
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            layerid = value.substring(0, value.indexOf("#"));
            logger.info("layerid-->" + layerid);
            String column = value.substring(value.indexOf("#")+1, value.indexOf("$"));
            String columnEntity = value.substring(value.indexOf("$")+1,value.length());
            List<LoadMap> LoadMap = loadMapUtil.loadMapRemovalByDb(tableName, layerid, column, columnEntity);
            return LoadMap;
        }else {
            tableName = tableNameUrl;
            layerid= value;
            logger.info("layerid-->" + layerid);
            List<LoadMap> LoadMap = loadMapUtil.loadMapRemovalByDb(tableName, layerid);
            return LoadMap;
        }
    }

    @ApiOperation(value = "根据YJJC_QWJJ_ORG_V@two二级医院落图", notes = "根据YJJC_QWJJ_ORG_V@two二级医院落图")
    @RequestMapping(value = "/YJJC_QWJJ_ORG_V@two/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadMap> findWeiJiRemovalTwoByEs() throws IOException {
        String tableName;
        String layerid;
        InputStream inputStream =ESOperate.class.getResourceAsStream("/loadmap.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String tableNameUrl = "YJJC_QWJJ_ORG_V@two";
        String value = properties.getProperty(ESOperate.dbName+"."+tableNameUrl);
        logger.info("tableName-->" + tableNameUrl);

        if(tableNameUrl.contains("@")){
            //截取@之前的字符串
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            layerid = value.substring(0, value.indexOf("#"));
            logger.info("layerid-->" + layerid);
            String column = value.substring(value.indexOf("#")+1, value.indexOf("$"));
            String columnEntity = value.substring(value.indexOf("$")+1,value.length());
            List<LoadMap> LoadMap = loadMapUtil.loadMapRemovalByDb(tableName, layerid, column, columnEntity);
            return LoadMap;
        }else {
            tableName = tableNameUrl;
            layerid= value;
            logger.info("layerid-->" + layerid);
            List<LoadMap> LoadMap = loadMapUtil.loadMapRemovalByDb(tableName, layerid);
            return LoadMap;
        }
    }

    @ApiOperation(value = "根据YJJC_QWJJ_ORG_V@two三级医院落图", notes = "根据YJJC_QWJJ_ORG_V@two三级医院落图")
    @RequestMapping(value = "/YJJC_QWJJ_ORG_V@three/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadMap> findWeiJiRemovalThreeByEs() throws IOException {
        String tableName;
        String layerid;
        InputStream inputStream =ESOperate.class.getResourceAsStream("/loadmap.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String tableNameUrl = "YJJC_QWJJ_ORG_V@three";
        String value = properties.getProperty(ESOperate.dbName+"."+tableNameUrl);
        logger.info("tableName-->" + tableNameUrl);

        if(tableNameUrl.contains("@")){
            //截取@之前的字符串
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            layerid = value.substring(0, value.indexOf("#"));
            logger.info("layerid-->" + layerid);
            String column = value.substring(value.indexOf("#")+1, value.indexOf("$"));
            String columnEntity = value.substring(value.indexOf("$")+1,value.length());
            List<LoadMap> LoadMap = loadMapUtil.loadMapRemovalByDb(tableName, layerid, column, columnEntity);
            return LoadMap;
        }else {
            tableName = tableNameUrl;
            layerid= value;
            logger.info("layerid-->" + layerid);
            List<LoadMap> LoadMap = loadMapUtil.loadMapRemovalByDb(tableName, layerid);
            return LoadMap;
        }
    }
}
*/
