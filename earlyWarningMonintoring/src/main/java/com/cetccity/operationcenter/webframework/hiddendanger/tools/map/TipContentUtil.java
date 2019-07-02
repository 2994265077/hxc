package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.KeyValueHasDetailModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.google.gson.Gson;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:38 2019-05-23
 * Updater:     heliangming
 * Update_Date：9:38 2019-05-23
 * 更新描述:    heliangming 补充
 **/
@Slf4j
@UtilityClass
public class TipContentUtil {

    public KeyValueHasDetailModel getTipProperties(String tableNameUrl) {
        Boolean hasDetailInfo = null;
        String[] key = null;
        String[] value = null;
        try {
            InputStream inputStream = ESOperate.class.getResourceAsStream("/tip.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
            log.info("TipOf-->" + tableNameUrl);
            String tableKey = properties.getProperty(ESOperate.dbName + "." + tableNameUrl + "Key");
            String tableValue = properties.getProperty(ESOperate.dbName + "." + tableNameUrl + "Value").toUpperCase();
            String HasDetail = properties.getProperty(ESOperate.dbName + "." + tableNameUrl + "HasDetail");
            hasDetailInfo = Boolean.valueOf(HasDetail);
            key = tableKey.split(","); //注意分隔符是需要转译滴...
            value = tableValue.split(",");
            log.info("TipOfKey-->" + tableKey);
            log.info("TipOfValue-->" + tableValue);
        }catch (Exception e){
        	log.error(e.toString());
        }
        return KeyValueHasDetailModel.builder().key(key).value(value).hasDetailInfo(hasDetailInfo).build();
    }

    public List tipContent(String tableName, String[] key, String[] value, LinkedHashMap<String,String> map) {
        List result = new ArrayList();
        Gson gson = new Gson();
        for (int i = 0; i < key.length; i++) {
            if (i == 0) {
                result.add(key[i]);
                //result.add(value[i]);
                result.add(map.get(value[1]));
            } else {
                if(value[i].contains("*")){
                    result.add(key[i]);
                    value[i] = value[i].substring(0, value[i].indexOf("*"));
                    String stu = map.get(value[i]);
                    String columnValueDesc = LoadMyUtil.getPropertiesVauleOfKey("tip.properties", ESOperate.dbName + "." + tableName + "*"+value[i]);
                    if(columnValueDesc.contains(stu)){
                        Map<String, String> maps = new HashMap<String, String>();
                        maps = gson.fromJson(columnValueDesc, maps.getClass());
                        String statusValue = maps.get(stu);
                        result.add(statusValue);
                    }else {
                        result.add(map.get(value[i]));
                    }
                }else {
                    if("null".equals(map.get(value[i])) || map.get(value[i])==null){
                        result.remove(key[i]);
                    }else{
                        result.add(key[i]);
                        result.add(map.get(value[i]));
                    }
                }
            }
        }
        return result;
    }
}
