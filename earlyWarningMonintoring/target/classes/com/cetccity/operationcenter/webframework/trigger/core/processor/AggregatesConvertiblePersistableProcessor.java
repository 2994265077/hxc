/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AbstractAggregatesConvertiblePersistableProcessor
 * Author:   YHY
 * Date:     2019/5/15 17:48
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.processor;

import com.cetccity.operationcenter.webframework.trigger.core.converter.AggregatesAlarmConverter;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
@Slf4j
public abstract class AggregatesConvertiblePersistableProcessor<S> extends AbstractConvertiblePersistableProcessor<S> {

    protected Set<AggregatesAlarmConverter<S>> converterChain = new HashSet<>();

    public void addConverter(AggregatesAlarmConverter<S> iotEventConverter) {
        converterChain.add(iotEventConverter);
    }

    @Override
    public Collection<AlarmInformation> convertSources(Collection<S> sources) {
        if (CollectionUtils.isNotEmpty(sources)) {
            return converterChain.stream()
                    .filter(aggregatesAlarmConverter -> aggregatesAlarmConverter.isSupport(sources))
                    .flatMap(aggregatesAlarmConverter -> doConvertSafely(aggregatesAlarmConverter, sources).stream())
                    .collect(Collectors.toList());
        }
        return new LinkedList<>();
    }

    private Collection<AlarmInformation> doConvertSafely(AggregatesAlarmConverter<S> aggregatesAlarmConverter, Collection<S> collection) {
        try {
            return aggregatesAlarmConverter.convert(collection);
        } catch (Throwable throwable) {
            log.error("聚合型预警转换失败", throwable);
        }
        return new LinkedList<>();
    }

    @Override
    public void convertAndPersistenceSources(Collection<S> sources) {
        Collection<AlarmInformation> alarmInformations = convertSources(sources);
        doPersistence(alarmInformations);
    }

    @Override
    public void process() {
        super.process();
    }
}