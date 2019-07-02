package com.cetc.cloud.datasynch.provider.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/6/25.
 */
@Slf4j
public class RegexUtil {
    public static String regexExtract(String regex, String target) {
        //把规则编译成模式对象
        Pattern p = Pattern.compile(regex);
        //通过模式对象得到匹配器对象
        Matcher m = p.matcher(target);
        String extractedDateText = null;
        //查找下一个匹配的字符串，进入循环则表示查找到
        while (m.find()) {
            extractedDateText = m.group();//获取并打印该匹配的字符串
            return extractedDateText;
        }
        return extractedDateText;
    }
}
