/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: IotEventProcessor
 * Author:   YHY
 * Date:     2019/5/14 15:40
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.converter;




/**
 * 〈一句话功能简述〉<br> 
 * 〈预警转换器〉
 *  源数据转换为预警对象
 * @author yhy
 * @create 2019/5/14
 * @since 1.0.0
 */
public interface Converter<T, S> {

    /**
     * 功能描述: <br>
     * 〈将当前记录转换为预警对象〉
     *
     * @param t
     * @return:java.util.Optional<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:yhy
     * @Date: 2019/5/15 10:20
     */
    S convert(T t);

    /**
     * 功能描述: <br>
     * 〈转换器是否支持当前记录的转换〉
     *
     * @param t
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/5/15 10:19
     */
    boolean isSupport(T t);

}