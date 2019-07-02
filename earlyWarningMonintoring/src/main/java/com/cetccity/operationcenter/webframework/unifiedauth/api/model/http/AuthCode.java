package com.cetccity.operationcenter.webframework.unifiedauth.api.model.http;

import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import org.springframework.stereotype.Component;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model.http
 * @Project: unified-auth
 * @Description: //登录模块状态码
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 10:53
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 10:53
 * @Update_Description: huangzezhou 补充
 **/
//10000~10100
@Component
public class AuthCode extends SysCode {

    public final static int CREATE_USER_ERROR_CODE = 10000;
    public final static String CREATE_USER_ERROR_MESSAGE = "创建用户失败";

    public final static int LOGIN_FAIL_CODE = 10001;
    public final static String LOGIN_FAIL_MESSAGE = "登录失败,账号或密码错误！";

    public final static int GENERATOR_TOKEN_ERROR = 10002;
    public final static String GENERATOR_TOKEN_ERROR_MESSAGE = "登录失败,用户令牌创建失败！";

    public final static int VALIDATE_TOKEN_ERROR = 10003;
    public final static String VALIDATE_TOKEN_ERROR_MESSAGE = "用户令牌无效！";

    public final static int USER_NO_LOGIN_ERROR = 10004;
    public final static String USER_NO_LOGIN_ERROR_MESSAGE = "用户未登录错误！";

    public final static int CREATE_ROLE_ERROR = 10005;
    public final static String CREATE_ROLE_ERROR_MESSAGE = "创建角色失败！";

    public final static int ACCOUNT_EXIST_ERROR = 10006;
    public final static String ACCOUNT_EXIST_ERROR_MESSAGE = "账号已存在";

    public final static int ROLE_NO_EXIST_ERROR = 10007;
    public final static String ROLE_NO_EXIST_ERROR_MESSAGE = "角色不存在";

    public final static int PERMISSION_DENIED_ERROR = 10008;
    public final static String PERMISSION_DENIED_ERROR_MESSAGE = "权限不足，请联系管理员";

    public final static int ACCOUNT_DISABLED_ERROR = 10009;
    public final static String ACCOUNT_DISABLED_ERROR_MESSAGE = "该账号已被禁用，请联系管理员";
    
    public final static int PWD_ERROR = 10010;
    public final static String PWD_ERROR_MSG = "密码必须为八位以上，且含大、小写字母、数字及特殊字符任意三种及以上";

    AuthCode() {
        map.put(CREATE_USER_ERROR_CODE, CREATE_USER_ERROR_MESSAGE);
        map.put(LOGIN_FAIL_CODE, LOGIN_FAIL_MESSAGE);
        map.put(GENERATOR_TOKEN_ERROR,GENERATOR_TOKEN_ERROR_MESSAGE);
        map.put(VALIDATE_TOKEN_ERROR, VALIDATE_TOKEN_ERROR_MESSAGE);
        map.put(USER_NO_LOGIN_ERROR, USER_NO_LOGIN_ERROR_MESSAGE);
        map.put(CREATE_ROLE_ERROR, CREATE_ROLE_ERROR_MESSAGE);
        map.put(ACCOUNT_EXIST_ERROR, ACCOUNT_EXIST_ERROR_MESSAGE);
        map.put(ROLE_NO_EXIST_ERROR, ROLE_NO_EXIST_ERROR_MESSAGE);
        map.put(PERMISSION_DENIED_ERROR, PERMISSION_DENIED_ERROR_MESSAGE);
        map.put(ACCOUNT_DISABLED_ERROR, ACCOUNT_DISABLED_ERROR_MESSAGE);
        map.put(PWD_ERROR, PWD_ERROR_MSG);
    }
}
