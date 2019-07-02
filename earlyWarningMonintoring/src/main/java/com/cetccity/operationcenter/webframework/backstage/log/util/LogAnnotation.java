package com.cetccity.operationcenter.webframework.backstage.log.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author ZHUTONGYU
 * Description: 日志注解
 * 2019年5月21日
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

	/**
	 * 模块
	 * @return
	 */
	String module();

	/**
	 * 记录执行参数
	 * @return
	 */
	boolean recordRequestParam() default true;
}
