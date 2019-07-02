package com.cetccity.operationcenter.webframework.trigger.core.processor;

import java.util.Collection;

/**
 * 功能描述: <br>
 * 〈具有持久化能力的处理器〉
 *
 * @Author:yhy
 * @Date: 2019/5/22 15:03
 */
public interface PersistableProcessor<T> extends Processor{

    /**
     * 功能描述: <br>
     * 〈批量持久化数据〉
     *  自动识别需要更新还是新增
     * @param t
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:04
     */
    void doPersistence(Collection<T> t);

    /**
     * 功能描述: <br>
     * 〈持久化单个数据〉
     *  自动识别需要更新还是新增
     * @param t
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:05
     */
    void doPersistence(T t);

}
