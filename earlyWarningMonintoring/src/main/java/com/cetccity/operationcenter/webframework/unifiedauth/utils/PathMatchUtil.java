package com.cetccity.operationcenter.webframework.unifiedauth.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.utils
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 10:33
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 10:33
 * @Update_Description: huangzezhou 补充
 **/
public class PathMatchUtil {

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static boolean match(Collection<String> patterns, String url) {
        if (!CollectionUtils.isEmpty(patterns)) {
            for (String pattern : patterns) {
                if (match(pattern, url)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean match(String pattern, String url) {

        return antPathMatcher.match(pattern, url);
    }

}
