package com.cetccity.operationcenter.webframework.trigger.core.converter;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;

import java.util.Optional;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.converter
 * @Project: futian-EarlyWarningMonitoring
 * @Creator: yhy
 * @Create_Date: 2019/5/16 17:16
 * @Description: 确认converter处理和生成的信息类型  一条源数据转换成0条或一条预警记录
 **/
public interface SingleAlarmConverter<T> extends Converter<T, Optional<AlarmInformation>> {
}
