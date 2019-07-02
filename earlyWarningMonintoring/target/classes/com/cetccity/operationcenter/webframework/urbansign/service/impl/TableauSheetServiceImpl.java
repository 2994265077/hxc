package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.tools.HttpClientUtil;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.urbansign.code.TableauCode;
import com.cetccity.operationcenter.webframework.urbansign.dao.TableauSheetMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.TableauEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.service.impl
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/10 9:26
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/10 9:26
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
@Slf4j
public class TableauSheetServiceImpl {

    @Autowired
    TableauSheetMapper tableauSheetMapper;

    public HttpResponseModel<String> getTicket(){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        NameValuePair nameValuePair = new BasicNameValuePair("username","zhb");
        list.add(nameValuePair);
        String result = "";
        try {
            result = HttpClientUtil.postFormData("http://10.190.65.33:80/trusted", list);
            if ("-1".equals(result)){
                return new HttpResponseModel<String>(TableauCode.SERVER_IP_NOT_IN_WHITE_SHEET_CODE,
                        TableauCode.SERVER_IP_NOT_IN_WHITE_SHEET_MESSAGE, "");
            }
        } catch (IOException e) {
            log.error("Request tableau ticket ", e);
            return new HttpResponseModel<String>(TableauCode.REQUEST_TABLEAU_SERVER_ERROR_CODE,
                    TableauCode.REQUEST_TABLEAU_SERVER_ERROR_MESSAGE, "");
        }
        return new HttpResponseModel<String>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, result);
    }

    public HttpResponseModel<List<TableauEntity>> getList() {
        List<TableauEntity> list = tableauSheetMapper.queryAll();
        return new HttpResponseModel<List<TableauEntity>>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, list);
    }

    public HttpResponseModel<Boolean> insertSheet(TableauEntity model) {
        if (tableauSheetMapper.insert(model) == 1)
            return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, true);
        return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, false);
    }

    public HttpResponseModel<Boolean> deleteSheet(int object_id) {
        if (tableauSheetMapper.delete(object_id) == 1)
            return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, true);
        return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, false);
    }

}
