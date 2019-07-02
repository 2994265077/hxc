/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ChenguanEventProcessor
 * Author:   YHY
 * Date:     2019/5/21 15:54
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.processor;

import com.cetccity.operationcenter.webframework.trigger.core.processor.SingleConvertiblePersistableProcessor;
import com.cetccity.operationcenter.webframework.trigger.core.producer.Producer;
import com.cetccity.operationcenter.webframework.trigger.entity.BlkChengguanEvent;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/21
 * @since 1.0.0
 */
public class ChenguanEventProcessor extends SingleConvertiblePersistableProcessor<BlkChengguanEvent> {
    public ChenguanEventProcessor(Producer<BlkChengguanEvent> producer) {
        this.setProducer(producer);
    }
}