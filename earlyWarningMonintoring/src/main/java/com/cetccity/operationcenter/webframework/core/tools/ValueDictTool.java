/*
package com.cetccity.operationcenter.webframework.core.tools;

import com.cetccity.operationcenter.webframework.core.frame.CetcCloudStaticResource;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.web.service.db.DBOperateService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

*/
/**
 * PackageName:   com.cetccity.operationcenter.webframework.core.tools
 * projectName:   futian-EarlyWarningMonitoring
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/12/6 19:00
 * Updater:     by luolinjie
 * Update_Date: 2018/12/6
 * Update_Description: luolinjie 补充
 **//*

@Component
public class ValueDictTool {
    @Autowired
    DBOperateService2 dbOperateService;

    public HttpResponseModel<String> getDictValue(String tableName, String columnName, String code) {
        HttpResponseModel responseModel = new HttpResponseModel<String>();
        if (tableName != null && !tableName.equals("") &&
                columnName != null && !columnName.equals("") &&
                code != null && !code.equals("")) {
            String SQL = "select CODE_IN_CHINESE from DS_VALUE_DICT " +
                    "where TABLE_NAME='" + tableName.toUpperCase() + "' " +
                    "and COLUMN_NAME='" + columnName.toUpperCase() + "' " +
                    "and CODE='" + code + "'";
            SqlRowSet rowSet = dbOperateService.querySQL(SQL);
            String codeInChinese = "";
            while (rowSet.next()) {
                codeInChinese = rowSet.getString(1);
            }
            if (!"".equals(codeInChinese)) {
                responseModel.setCode(CetcCloudStaticResource.VOS_INT_OK);
                responseModel.setData(codeInChinese);
                responseModel.setMessage("success");
                return responseModel;
            } else {
                responseModel.setCode(CetcCloudStaticResource.VOS_INT_NOK);
                responseModel.setMessage("failed! cannot found code explanation!");
                return responseModel;
            }
        } else {
            return new HttpResponseModel<String>(-1, "param cannot be null!", null);
        }
    }
}
*/
