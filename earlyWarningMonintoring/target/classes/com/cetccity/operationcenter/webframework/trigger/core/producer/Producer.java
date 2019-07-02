/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: P    @Autowired
roducer
 * Author:   YHY
 * Date:     2019/5/20 15:31
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.producer;

import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈源数据生产者〉
 *
 * @author yhy
 * @create 2019/5/20
 * @since 1.0.0
 */
public interface Producer<T> {

    /**
     * 功能描述: <br>
     * 〈生产（查询）源数据〉
     *
     * @param
     * @return:java.util.Collection<T>
     * @Author:yhy
     * @Date: 2019/5/22 14:54
     */
    Collection<T> produce();

    /**
     * 功能描述: <br>
     * 〈数据来源表〉
     *
     * @return:java.lang.String
     * @Author:dongxin
     * @Date: 2019/5/22 14:59
     */
    String getOriginTableName();


}