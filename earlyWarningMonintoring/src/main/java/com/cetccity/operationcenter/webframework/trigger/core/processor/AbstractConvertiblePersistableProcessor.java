/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AbstractConvertiblePersistavleProcessor
 * Author:   YHY
 * Date:     2019/5/15 17:03
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.processor;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.core.producer.Producer;

import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象的 具有转换功能和持久化功能的处理器〉
 *
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
public abstract class AbstractConvertiblePersistableProcessor<S> extends AbstractPersistableProcessor implements ConvertibleProcessor<S, AlarmInformation> {
    /**
     *  源数据生产者
     */
    protected Producer<S> producer;

    /**
     * 功能描述: <br>
     * 〈设置源数据生产者〉
     *
     * @param producer
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:18
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     * 功能描述: <br>
     * 〈设置处理逻辑〉
     *
     * @param
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:19
     */
    @Override
    public void process() {
        Collection<S> sources = producer.produce();
        this.convertAndPersistenceSources(sources);
    }

}