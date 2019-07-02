/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ApiLogConsumer
 * Author:   YHY
 * Date:     2019/4/11 11:48
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.log.consumer;

import com.cetccity.operationcenter.webframework.web.log.entity.ApiLog;

/**
 * 〈一句话功能简述〉<br> 
 * 〈请求日志消费者〉
 *
 * @author yhy
 * @create 2019/4/11
 * @since 1.0.0
 */
public interface ApiLogConsumer {

    /**
     * 功能描述: <br>
     * 〈消费请求日志〉
     *  处理生成的请求日志，依照具体实现，可以选择存数据库，也可以选择存es，或者其他存储介质
     * @param apiLog 请求日志
     * @return:void
     * @Author: yhy
     * @Date: 2019/4/11 11:49
     */
    void consumeLog(ApiLog apiLog);
}