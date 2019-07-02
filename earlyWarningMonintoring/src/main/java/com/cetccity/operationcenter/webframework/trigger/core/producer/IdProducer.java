package com.cetccity.operationcenter.webframework.trigger.core.producer;


/**
 * 功能描述: <br>
 * 〈根据本类型最后一次预警的源数据object_id 生产（查询）源数据〉
 *
 * @Author:yhy
 * @Date: 2019/5/22 14:54
 */
public interface IdProducer<T> extends Producer<T> {

    String DEFAULT_ID_VALUE = "0";

    /**
     * 功能描述: <br>
     * 〈查询上次预警的源数据object_id〉
     *
     * @return:java.lang.String
     * @Author:yhy
     * @Date: 2019/5/22 14:55
     */
    String previousId();

}
