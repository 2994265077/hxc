/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AbstractPersistableProcessor
 * Author:   YHY
 * Date:     2019/5/15 16:38
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.processor;

import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.utils.CheckData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象可持久化的处理器〉
 *
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
public abstract class AbstractPersistableProcessor implements PersistableProcessor<AlarmInformation> {
    @Autowired
    protected AlarmInformationV1Mapper alarmInformationMapper;

    /**
     * 功能描述: <br>
     * 〈将预警信息持久化〉
     *  分为两批持久化
     *      有id的更新
     *      无id的新增
     *
     * @param alarmInformations
     * @return:void
     * @Author:dongxin
     * @Date: 2019/5/15 16:44
     */
    @Override
    public void doPersistence(Collection<AlarmInformation> alarmInformations) {
        Assert.notNull(alarmInformations, "持久化的实体列表参数不能为null");
        // 有ID的修改
        List<AlarmInformation> updateList = alarmInformations.stream()
                .filter(hasId())
                .collect(Collectors.toList());
        // 没有ID的新增
        List<AlarmInformation> insertList = alarmInformations.stream()
                .filter(hasId().negate() )
                .collect(Collectors.toList());

        insertList.stream()
                .forEach(alarmInformationMapper::insert);
        if (CollectionUtils.isNotEmpty(updateList)) {
            alarmInformationMapper.updateBatchSelected(updateList);
        }
    }

    /**
     * 功能描述: <br>
     * 〈持久化预警对象〉
     *  分为两种情况持久化
     *      有id的更新
     *      无id的新增
     *
     * @param alarmInformation
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/22 15:21
     */
    @Override
    public void doPersistence(AlarmInformation alarmInformation) {
        if (hasId().negate().test(alarmInformation)) {
            if (CheckData.checkFieldNotNull(alarmInformation)) {
                alarmInformationMapper.insert(alarmInformation);
            }
        } else {
            alarmInformationMapper.updateBatchSelected(Arrays.asList(alarmInformation));
        }
    }

    /**
     *功能描述: <br>
     * 〈 生成判断AlarmInformation 是否含有id的断言〉
     *  如果 AlarmInformation 改成String类型 第二个Objects.nonNull 改成StringUtil.isNotBlank
     * @param
     * @return:java.util.function.Predicate<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:yhy
     * @Date: 2019/5/15 16:42
     */
    protected Predicate<AlarmInformation> hasId() {
        return alarmInformation ->  Objects.nonNull(alarmInformation) && Objects.nonNull(alarmInformation.getOBJECT_ID());
    }

    public abstract void process();
}