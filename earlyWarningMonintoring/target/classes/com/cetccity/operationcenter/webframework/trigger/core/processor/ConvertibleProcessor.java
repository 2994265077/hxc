package com.cetccity.operationcenter.webframework.trigger.core.processor;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 功能描述: <br>
 * 〈具有转换功能的处理器〉
 *
 * @Author:yhy
 * @Date: 2019/5/22 15:06
 */
public interface ConvertibleProcessor<S,T> extends Processor {

    /**
     * 功能描述: <br>
     * 〈批量转换并持久化对象〉
     *
     * @param s
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:07
     */
    default void convertAndPersistenceSources(Collection<S> s){

    }

    /**
     * 功能描述: <br>
     * 〈批量转换对象〉
     *
     * @param s
     * @return:java.util.Collection<T>
     * @Author:yhy
     * @Date: 2019/5/22 15:08
     */
    default Collection<T> convertSources(Collection<S> s){
        return new LinkedList<>();
    }

}
