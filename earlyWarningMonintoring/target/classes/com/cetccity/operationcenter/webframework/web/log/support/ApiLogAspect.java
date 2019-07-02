/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ApiLogAspect
 * Author:   YHY
 * Date:     2019/4/11 10:21
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.log.support;

import com.cetccity.operationcenter.webframework.web.log.consumer.ApiLogConsumer;
import com.cetccity.operationcenter.webframework.web.log.entity.ApiLog;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/11
 * @since 1.0.0
 */
@Aspect
@Component
@Slf4j
@ConditionalOnProperty("api.log.enable")
@ConfigurationProperties(prefix = "api.log")
@Data
public class ApiLogAspect {
    // 路径拦截优先级  include > exclude > includeStarts > excludeStarts
    // 一定要进行拦截的路径
    private String include;
    // 不进行日志拦截的路径
    private String exclude;
    // 路径以此开头的拦截
    private String includeStarts;
    // 路径以此开头的不拦截
    private String excludeStarts;
    // 耗时达到此阈值开始拦截 单位ms 小于等于0表示全拦截
    private long threshold;

    private HashSet<String> excludePaths;
    private HashSet<String> includePaths;
    private List<String> excludePrefix;
    private List<String> includePrefix;

    private final String BASE_MATCH_PATTERN_KEY = "org.springframework.web.servlet.HandlerMapping.bestMatchingPattern";
    private final String REAL_PATTERN_KEY = "org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping";

    @PostConstruct
    public void init() {
        initExcludeAndIncludes();
    }

    public void initExcludeAndIncludes() {
        excludePaths = new HashSet<>();
        includePaths = new HashSet<>();
        excludePrefix = new ArrayList<>();
        includePrefix = new ArrayList<>();

        fillCollection(exclude, excludePaths, ",");
        fillCollection(include, includePaths, ",");
        fillCollection(includeStarts, includePrefix, ",");
        fillCollection(excludeStarts, excludePrefix, ",");

    }

    public void fillCollection(String source, Collection target, String split) {
        if (StringUtils.isNotBlank(source)) {
            List<String> sourceList = Arrays.asList(source.split(split));
            target.addAll(sourceList);
        }
    }

    @Autowired
    private ApiLogConsumer apiLogConsumer;

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void pointCut() {}

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        ApiLog apiLog = genericApiLog();
        // 是否需要日志
        String realPath = apiLog.getRealPath();
        boolean ableToLog = ableToLog(realPath);
        if (!ableToLog) {
            return joinPoint.proceed();
        }

        long t1 = System.nanoTime();
        Object returnValue;
        try {
            returnValue = joinPoint.proceed();
            apiLog.setSuccess(true);
        } catch (Throwable throwable) {
            apiLog.setSuccess(false);
            throw throwable;
        } finally {
            long t2 = System.nanoTime();
            long timeConsumed = (t2 - t1);
            apiLog.setConsumedTime(timeConsumed);
            if (threshold <= 0 || !apiLog.isSuccess() || timeConsumed > threshold*1000000) {
                apiLogConsumer.consumeLog(apiLog);
            }
        }
        return returnValue;
    }

    public ApiLog genericApiLog() {
        ApiLog apiLog = new ApiLog();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String matchedPath = (String)request.getAttribute(BASE_MATCH_PATTERN_KEY);
        String queryString = request.getQueryString();
        String realPath = request.getRequestURI();
        if (Objects.nonNull(queryString) && !"null".equals(queryString)) {
            if (queryString.length() < 256) {
                realPath += "?" + queryString;
            }
        }
        apiLog.setMatchedPath(matchedPath);
        apiLog.setRealPath(realPath);
        apiLog.setHttpMethod(request.getMethod());
        return apiLog;
    }

    public boolean ableToLog(String path) {
        if (includePaths.contains(path)) {
            return true;
        } else if (excludePaths.contains(path)) {
            return false;
        }
        for (String prefix : includePrefix) {
            if (path.startsWith(prefix)) {
                return true;
            }
        }
        for (String prefix : excludePrefix) {
            if (path.startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }

}