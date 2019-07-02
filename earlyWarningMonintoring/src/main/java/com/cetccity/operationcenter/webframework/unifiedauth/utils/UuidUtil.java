package com.cetccity.operationcenter.webframework.unifiedauth.utils;

import org.junit.Test;

import java.util.UUID;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.utils
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/5 17:10
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/5 17:10
 * @Update_Description: huangzezhou 补充
 **/
public class UuidUtil {
    @Test
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase(); //UUID去掉-, 刚好32位
    }
}
