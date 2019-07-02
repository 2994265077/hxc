/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AbstractSingleConvertiblePersistableProcessor
 * Author:   YHY
 * Date:     2019/5/15 17:49
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.processor;

import com.cetccity.operationcenter.webframework.trigger.core.converter.SingleAlarmConverter;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈单一型可转换可持久化处理器〉
 *
 *  单一型 ： 一条记录经处理转换成一条或零条预警记录 并持久化到数据库
 *
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
@Slf4j
public abstract class SingleConvertiblePersistableProcessor<S> extends AbstractConvertiblePersistableProcessor<S> {

    /**
     *  转换器链  单一型处理器处理时 遇到一个support的预警对象就直接转换并返回
     */
    protected Set<SingleAlarmConverter<S>> converterChain = new HashSet<>();

    /**
     * 功能描述: <br>
     * 〈添加预警对象〉
     *
     * @param iotEventConverter
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:12
     */
    public void addConverter(SingleAlarmConverter<S> iotEventConverter) {
        converterChain.add(iotEventConverter);
    }

    /**
     * 功能描述: <br>
     * 〈单条源数据转换成一条或零条预警对象〉
     *
     * @param s
     * @return:java.util.Optional<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:yhy
     * @Date: 2019/5/22 15:12
     */
    private Optional<AlarmInformation> convert(S s) {
        Assert.notNull(s, "alarm can't convert from a null source");
        // 转换成预警对象
        for (SingleAlarmConverter<S> singleAlarmConverter : converterChain) {
            if (singleAlarmConverter.isSupport(s)) {
                Optional<AlarmInformation> informationOptional = singleAlarmConverter.convert(s);
                return informationOptional;
            }
        }
        return Optional.empty();
    }

    /**
     * 功能描述: <br>
     * 〈转换单条源数据对象并持久化生成的预警对象〉
     *
     * @param s
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:14
     */
    protected void convertAndPersistence(S s) {
        try {
            Optional<AlarmInformation> convert = convert(s);
            convert.ifPresent(this::doPersistence);
        } catch (Throwable e) {
            log.error("生成预警对象出现异常", e);
        }

    }

    /**
     * 功能描述: <br>
     * 〈批量转换源数据对象 并将生成的预警对象持久化〉
     *
     * @param sources
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:15
     */
    @Override
    public void convertAndPersistenceSources(Collection<S> sources) {
        if (CollectionUtils.isNotEmpty(sources)) {
            sources.stream()
                    .forEach(this::convertAndPersistence);
        }
    }

}