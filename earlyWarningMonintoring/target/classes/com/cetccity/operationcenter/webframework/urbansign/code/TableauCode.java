package com.cetccity.operationcenter.webframework.urbansign.code;

import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.code
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/10 9:52
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/10 9:52
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
public class TableauCode extends SysCode {

    public static final int REQUEST_TABLEAU_SERVER_ERROR_CODE = 10201;
    public static final String REQUEST_TABLEAU_SERVER_ERROR_MESSAGE = "请求tableau服务器时发送错误";

    public static final int SERVER_IP_NOT_IN_WHITE_SHEET_CODE = 10202;
    public static final String SERVER_IP_NOT_IN_WHITE_SHEET_MESSAGE = "该服务器ip未设置为tableau白名单";

}
