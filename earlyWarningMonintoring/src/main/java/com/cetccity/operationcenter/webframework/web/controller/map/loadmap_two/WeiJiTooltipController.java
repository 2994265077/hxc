/*
package com.cetccity.operationcenter.webframework.web.controller.map.loadmap_two;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import com.google.gson.Gson;
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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

*/
/**
 * 弹框，卫计概要信息
 *//*

@Controller
@Api(value = "卫计二级医院弹框", tags = "卫计二级医院弹框")
@RequestMapping("/yjjc_qwjj_org_v@two")
@Slf4j
public class WeiJiTooltipController {

    Logger logger = LoggerFactory.getLogger(WeiJiTooltipController.class);

    @Autowired
    TipUtil tipUtil;

    @ApiOperation(value = "卫计二级医院弹框", notes = "卫计二级医院弹框")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--yjjc_qwjj_org_v@two", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--1222776", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    public Map summaryInfo( String id) {
        String tableName;
        try {
            InputStream inputStream =ESOperate.class.getResourceAsStream("/tip.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream,"UTF-8"));
            String tableNameUrl = "yjjc_qwjj_org_v@two";
            logger.info("TipOf-->"+tableNameUrl);
            String tableKey = properties.getProperty(ESOperate.dbName + "." + tableNameUrl + "Key").toLowerCase();
            String tableValue = properties.getProperty(ESOperate.dbName + "." + tableNameUrl + "Value").toLowerCase();
            String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
            String[] value = tableValue.split(",");
            logger.info("TipOfKey-->" + tableKey);
            logger.info("TipOfValue-->" + tableValue);
            if(tableNameUrl.contains("@")){
                tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            }else {
                tableName = tableNameUrl;
            }
            String source = tipUtil.loadMapTip(tableName, id);
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<String, String>();
            map = gson.fromJson(source, map.getClass());
            List result = new ArrayList();
            for (int i = 0; i < key.length; i++) {
                if (i == 0) {
                    result.add(key[i]);
                    result.add(value[i]);
                } else {
                    if(value[i].contains("*")){
                        result.add(key[i]);
                        value[i] = value[i].substring(0, value[i].indexOf("*"));
                        String stu = map.get(value[i]);
                        String columnValueDesc = properties.getProperty(ESOperate.dbName + "." + tableName + "*"+value[i]).toLowerCase();
                        if(columnValueDesc.contains(stu)){
                            Map<String, String> maps = new HashMap<String, String>();
                            maps = gson.fromJson(columnValueDesc, maps.getClass());
                            String statusValue = maps.get(stu);
                            //String statusValue = columnValueDesc.substring(columnValueDesc.indexOf(stu)+1, columnValueDesc.indexOf("；"));
                            result.add(statusValue);
                        }else {
                            result.add(map.get(value[i]));
                        }
                    }else {
                        result.add(key[i]);
                        if("null".equals(map.get(value[i])) || map.get(value[i])==null){
                            map.put(value[i],"无");
                        }
                        result.add(map.get(value[i]));
                    }
                }
            }
            return Tooltip.toolTipListToMap(result, true);
        }catch (Exception e){
        	log.error(e.toString());
            return null;
        }
    }
}
*/
