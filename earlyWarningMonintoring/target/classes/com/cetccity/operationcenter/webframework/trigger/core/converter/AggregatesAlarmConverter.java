/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AggregatesAlarmConverter
 * Author:   YHY
 * Date:     2019/5/16 17:18
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.converter;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;

import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
public interface AggregatesAlarmConverter<T> extends Converter<Collection<T>, Collection<AlarmInformation>> {

}