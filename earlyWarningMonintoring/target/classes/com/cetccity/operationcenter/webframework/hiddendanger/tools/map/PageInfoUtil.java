package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.PageInfo_LoadMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:09 2019-06-06
 * Updater:     heliangming
 * Update_Date：17:09 2019-06-06
 * 更新描述:    heliangming 补充
 **/
@Component
public class PageInfoUtil {

    @Autowired
    PageInfoDbUtil pageInfoDbUtil;

    public PageInfo_LoadMap getPageInfoDbUtil(String tableNameUrl, String pageNum, String pageSize, String street){
        String tableName;
        Integer size = Integer.parseInt(pageSize);
        Integer page = (Integer.parseInt(pageNum)-1)*size;
        String value = LoadMyUtil.getPropertiesVauleOfKey("pageInfo.properties", ESOperate.dbName+"."+tableNameUrl);
        String sql_column = value.substring(0, value.indexOf("*")).toUpperCase();
        if(tableNameUrl.contains("@")) {
            //截取@之前的字符串
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            String column_comment = value.substring(value.indexOf("*")+1,value.indexOf("#")).toUpperCase();
            String col = value.substring(value.indexOf("#")+1, value.indexOf("$")).toUpperCase();
            String columnEntity = value.substring(value.indexOf("$")+1,value.length()).toUpperCase();
            if(StringUtils.isEmpty(street)){
                return pageInfoDbUtil.getPageInfoDbHasType(tableName,page,size,sql_column,column_comment,col,columnEntity);
            }else{
                String street_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties",street).split(",")[0];
                return pageInfoDbUtil.getPageInfoDbHasType(tableName,page,size,sql_column,column_comment,col,columnEntity,street_code);
            }
        }else{
            tableName = tableNameUrl;
            String column_comment = value.substring(value.indexOf("*")+1,value.length()).toUpperCase();
            if(StringUtils.isEmpty(street)){
                return pageInfoDbUtil.getPageInfoDb(tableName,page,size,sql_column,column_comment);
            }else {
                String street_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties",street).split(",")[0];
                return pageInfoDbUtil.getPageInfoDb(tableName, page, size, sql_column, column_comment, street_code);
            }
        }
    }
}
