package com.cetccity.operationcenter.webframework.core.frame.model;

import java.util.HashMap;

/**
 * @Package: com.cetccity.operationcenter.baseframework.http
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/2 14:46
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/2 14:46
 * @Update_Description: huangzezhou 补充
 **/
/**
 * AuthCode  10000~10100
 * HiddenDangerCode 10101~10200
 * TableauCode 10201~10300
 */
public class SysCode {

    public static HashMap<Integer,String> map = new HashMap<Integer,String>();

    public static final int SYS_SUCCESS_CODE = 0;
    public static final String SYS_SUCCESS_MESSAGE = "正常响应请求";

    public static final int UNKNOWN_ERROR_CODE = -1;
    public static final String UNKNOWN_ERROR_MESSAGE = "系统发生未知错误";

    public SysCode(){
        map.put(SYS_SUCCESS_CODE, SYS_SUCCESS_MESSAGE);
        map.put(UNKNOWN_ERROR_CODE,UNKNOWN_ERROR_MESSAGE);
    }

    public String message(int code){
        return map.get(code);
    }
}
