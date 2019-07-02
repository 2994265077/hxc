/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: DefaultJdbcApiLogConsumer
 * Author:   YHY
 * Date:     2019/4/11 11:55
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.log.consumer;

import com.cetccity.operationcenter.webframework.web.dao.log.ApiLogMapper;
import com.cetccity.operationcenter.webframework.web.log.entity.ApiLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/11
 * @since 1.0.0
 */

@Slf4j
public class DefaultJdbcApiLogConsumer implements ApiLogConsumer{

    @Autowired
    private ApiLogMapper apiLogMapper;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Override
    public void consumeLog(ApiLog apiLog) {
        threadPoolTaskExecutor.execute(() -> apiLogMapper.insert(apiLog));
    }


}