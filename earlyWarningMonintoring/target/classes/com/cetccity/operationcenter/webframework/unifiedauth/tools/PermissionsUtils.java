package com.cetccity.operationcenter.webframework.unifiedauth.tools;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.utils
 * @Project: futian1
 * @Description: //权限字符串处理工具
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 11:49
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 11:49
 * @Update_Description: huangzezhou 补充
 **/

/**
 * 权限名：系统:子系统:操作名。  system:module:operate
 * 支持通配符：*
 */
public class PermissionsUtils {
    public static boolean matchPermission(String pattern, String permission){
        String[] str1 = pattern.split(":");
        String s1 = str1[0];
        String m1 = str1[1];
        String o1 = str1[2];

        String[] str2 = permission.split(":");



        return false;
    }


}
