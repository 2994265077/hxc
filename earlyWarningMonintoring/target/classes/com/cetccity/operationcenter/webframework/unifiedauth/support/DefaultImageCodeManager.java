/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: DefaultImageCodeManager
 * Author:   YHY
 * Date:     2019/4/22 11:01
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.support;

import com.cetccity.operationcenter.webframework.unifiedauth.entity.Code;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.ImageCode;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.ImageUtils;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/22
 * @since 1.0.0
 */
@Component
public class DefaultImageCodeManager implements CodeManager {

    private final Integer defaultWidth = 200;

    private final Integer defaultHeight = 80;

    @Autowired
    @Qualifier("codeCache")
    private Cache<String, Object> codeCache;

    @Override
    public ImageCode supplier() {
        String uuid = uuid();
        return supplier(uuid, defaultWidth, defaultHeight);
    }

    @Override
    public ImageCode supplier(String id) {
        return supplier(id, defaultWidth, defaultHeight);
    }

    @Override
    public ImageCode supplier(int width, int height) {
        String uuid = uuid();
        return supplier(uuid, width, height);
    }

    @Override
    public ImageCode supplier(String id, int width, int height) {
        ImageCode imageCode = ImageUtils.getImageCode(width, height);
        imageCode.getCode().setId(id);
        codeCache.put(id, imageCode.getCode().getValue());
        return imageCode;
    }

    @Override
    public boolean validate(Code code) {
        if (StringUtils.isBlank(code.getId()) || StringUtils.isBlank(code.getValue())) {
            return false;
        }
    	Object ifPresentCode = codeCache.getIfPresent(code.getId());
        if (ifPresentCode != null) {
            // code存在， 清除code， 只使用一次
            codeCache.invalidate(code.getId());
            if (String.valueOf(ifPresentCode).equalsIgnoreCase(code.getValue())) {
                return true;
            }
        }
        return false;
    }


    private String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}