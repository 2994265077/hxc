package com.cetccity.operationcenter.webframework.core.chart.engine.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.constant.RegexCommonConstantsInterface;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Package: com.cetccity.operationcenter.webframework.core.chart.engine
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/4 11:44
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/4 11:44
 * @Update_Description: huangzezhou 补充
 * @Description: //防止sql注入攻击的工具类
 */

@Aspect
@Component
public class PreventSQLInjectionAttackAopService implements RegexCommonConstantsInterface {

/**
     * 单词过滤工具
     * @param tbNameOrCol 表名或列名
     * @return
*/

    public static boolean wordFilter(String tbNameOrCol){
        if (StringUtils.isEmpty(tbNameOrCol))
            throw new NullPointerException();
        return tbNameOrCol.matches(ONLY_NUMBER_AND_ENGLISH);
    }


    @Pointcut(value = "execution(* com.cetccity.operationcenter.webframework.core.chart.engine.dao.*.*(..))")
    public void cut(){

    }

    @Before("cut()")
    public void before(){
        System.out.println("123");
    }

    @Around("cut()")
    public void around(ProceedingJoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        String tbName = (String) objects[0];
        String computeCol = (String) objects[1];
        String dateCol = (String) objects[2];
        Integer decimal = (Integer) objects[3];
        if (!wordFilter(tbName)){
            objects[0] = "";
        }
        System.out.println();
    }
}
