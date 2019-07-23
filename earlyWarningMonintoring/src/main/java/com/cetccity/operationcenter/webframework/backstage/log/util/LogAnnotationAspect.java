package com.cetccity.operationcenter.webframework.backstage.log.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.cetccity.operationcenter.webframework.backstage.log.entity.SysLog;
import com.cetccity.operationcenter.webframework.backstage.log.service.LogService;
import com.cetccity.operationcenter.webframework.backstage.log.service.LogServiceImpl;
import com.cetccity.operationcenter.webframework.core.tools.BeanUtils;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;
import com.cetccity.operationcenter.webframework.web.util.NetworkUtil;
 
/**
 * 
 * @author ZHUTONGYU
 * Description:保存日志
 * 2019年3月28日
 */
@Component
@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
public class LogAnnotationAspect {
	@Autowired
	private UserInfoUtils userInfoUtils;
    private static final Logger logger = LoggerFactory.getLogger(LogAnnotationAspect.class);
    @Around("@annotation(ds)")
    public Object logSave (ProceedingJoinPoint joinPoint, LogAnnotation ds) throws Throwable {
    	 SysLog log = new SysLog();
         log.setCreateDate(new Timestamp(new Date().getTime()));
         MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
         LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
         log.setModule(logAnnotation.module());
         if (logAnnotation.recordRequestParam()) {
        	 Object[] args = joinPoint.getArgs();// 参数值
             for (Object object : args) {
            	 if(object instanceof HttpServletRequest){
            		 HttpServletRequest request = (HttpServletRequest)object;
            		 //获取请求IP
            		 log.setIp(NetworkUtil.getIpAddress(request));
            		 //获取访问账号
            		 UserEntity user = userInfoUtils.getUserInfo(request);
            		 log.setUserName(user == null ?"admin":user.getAccount());
            		 break;
            	 }
             }
             
             String[] paramNames = methodSignature.getParameterNames();// 参数名
             if (paramNames != null && paramNames.length > 0) {
                 Map<String, Object> params = new HashMap<>();
                 for (int i = 0; i < paramNames.length; i++) {
                	 if(paramNames[i] instanceof Serializable){
                		 params.put(paramNames[i], args[i]);
                	 }
                 }
                 try {
                     log.setParams(params.toString());
                 } catch (Exception e) {
                     logger.error("记录参数失败：{}", e.getMessage());
                 }
             }
         }
         
         try {
        	// 调用原来的方法
        	 Object object = joinPoint.proceed();
             log.setFlag(Boolean.TRUE);

             return object;
         } catch (Exception e) {
             log.setFlag(Boolean.FALSE);
             log.setRemark(e.getMessage());
             throw e;
         } finally {
             CompletableFuture.runAsync(() -> {
            	 try {
     				LogService logService = BeanUtils.getBean(LogServiceImpl.class);
     				logService.save(log);
     			} catch (Exception e) {
     				 logger.error("记录参数失败：{}", e.getMessage());
     			}
             });
         }
    }
}