package com.cetccity.operationcenter.webframework.backstage.log.util;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 
 * @author ZHUTONGYU
 * Description:装配bean
 * 2019年3月28日
 */
public class LoggingConfigurationSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] { 
				"com.cetccity.operationcenter.webframework.backstage.log.util.LogAnnotationAspect"
		};
	}

}
