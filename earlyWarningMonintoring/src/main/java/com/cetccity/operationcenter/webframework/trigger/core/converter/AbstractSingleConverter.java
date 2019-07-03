/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AbstractConverter
 * Author:   YHY
 * Date:     2019/5/16 16:53
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.converter;

import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象单一型预警转换器〉
 *
 *  单一型预警转换器 -- 一条源数据记录->0条或1条预警记录；
 *  此生成的预警记录可为新增或修改 （有无id）， 持久化时会自动判断；
 *
 *  此类定义了一些公共预警规则，同时实现了一些可能能共用的几个方法；
 *
 *
 *
 *  公共预警规则（实现类可自行修改规则或自行实现SingleAlarmConverter接口）：
 *      1、查看是否触发阈值（isExceededThreshold），如果触发了阈值，查询上次预警记录(recentAlarm)；
 *          2.1、根据上次的预警记录判断是否需要新增预警（shouldAdd），如果需要新增预警，转换器返回新的预警记录；
 *          2.2、如果不需要新增预警，同理调用（shouldUpdate）方法判断是否需要修改， 如需修改，转换器返回新的修改预警记录；
 *          2.3、如若既不需要新增也不需要修改，转换器返回空预警对象（Optional.empty）,后续处理器程序会自动忽略此对象；
 *      3、在新的预警返回之前，转换器会填充一些可能不同预警都不需要修改的属性值（fillAlarmInformation）
 *  ps：新增预警和修改预警的区别在于 新增预警没有id值，而修改预警有id值
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
public abstract class AbstractSingleConverter<T> implements SingleAlarmConverter<T>{


    /**
     * 功能描述: <br>
     * 〈 源数据的上次预警对象 〉
     *
     * @param t 源数据记录
     * @return:java.util.Optional<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:dongxin
     * @Date: 2019/5/22 14:31
     */
    protected abstract Optional<AlarmInformation> recentAlarm(T t);

    /**
     * 功能描述: <br>
     * 〈将源数据记录 转换成预警对象〉
     *
     *  如果有必要的话（达到阈值并且[需要更新或者新增预警]） 将源数据记录 转换成预警对象
     *  如果 触发阈值 并且需要新增预警 则根据源数据生成一条不包含id的预警
     *  如果 阈值触发 并且需要修改（或取消）预警  则根据源数据生成一条包含id的预警
     *  新增和修改只会有一个会触发 同时触发时优先返回新生成的预警
     *  如果新增和修改都不触发 生成一个空的预警记录
     *
     * @param t
     * @return:java.util.Optional<com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation>
     * @Author:dongxin
     * @Date: 2019/5/22 14:31
     */
    @Override
    public Optional<AlarmInformation> convert(T t) {
        Optional<AlarmInformation> recentAlarmOption = recentAlarm(t);
        boolean isExceedThreshold = isExceededThreshold(t);
        if (isExceedThreshold && shouldAdd(recentAlarmOption)) {
            // 超过了阈值 且 需要新发布预警
            AlarmInformation newAlarmBean = newAlarmBean(t);
            newAlarmBean = fillAlarmInformation(newAlarmBean);
            return Optional.ofNullable(newAlarmBean);
        }
        if (!isExceedThreshold && shouldUpdate(recentAlarmOption)) {
            // 没有超过阈值 且 需要取消预警
            AlarmInformation cancelAlarmBean = updateAlarmBean(recentAlarmOption.get(), t);
            return Optional.ofNullable(cancelAlarmBean);
        }
        return Optional.empty();
    }

    /**
     * 功能描述: <br>
     * 〈生成一条更新预警的’有id‘预警信息〉
     *
     *  如果源数据记录触发了预警阈值（isExceededThreshold（））  并且 根据上次预警记录判断（shouldUpdate（）） 此次预警需要更新或取消
     *
     *  生成的预警id为上次预警记录的id
     *
     * @param lastAlarm 上次的预警记录 recentAlarm(T t) 方法生成的
     * @param t 源数据记录
     * @return:com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation
     * @Author:dongxin
     * @Date: 2019/5/22 14:27
     */
    protected abstract AlarmInformation updateAlarmBean(AlarmInformation lastAlarm, T t);

    /**
     * 功能描述: <br>
     * 〈获取预警来源表〉
     *
     * @param
     * @return:java.lang.String
     * @Author:yhy
     * @Date: 2019/5/22 14:25
     */
    protected abstract String getOriginTableName();

    /**
     * 功能描述: <br>
     * 〈填充一些同一类型每条预警记录都不变的值〉
     *
     * @param alarmInformation
     * @return:com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation
     * @Author:yhy
     * @Date: 2019/5/22 14:19
     */
    protected AlarmInformation fillAlarmInformation(AlarmInformation alarmInformation) {
        // 如果参数为null new 一个
        alarmInformation = Objects.nonNull(alarmInformation) ? alarmInformation : new AlarmInformation();
        alarmInformation.setRELEASE_PERSON("预警监测平台");
        alarmInformation.setDISPOSAL_STATE(0);
        alarmInformation.setSEND_STATE(0);
        alarmInformation.setALARM_STATE(1);
        alarmInformation.setSYSTEM_ID(UUID.randomUUID().toString().replace("-", ""));
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        return alarmInformation;
    }

    /**
     * 功能描述: <br>
     * 〈为每一个需要预警的源数据生成一条预警对象〉
     *  默认使用fastjson 序列化源数据，再反序列化为预警对象 以提取源数据中大量的公共字段 如地址 街道等信息
     *  使用fastjson 与其他地方的jackson区分开  避免需要定制字段名时影响到其他功能
     * @param t
     * @return:com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation
     * @Author:yhy
     * @Date: 2019/5/22 14:19
     */
    protected AlarmInformation newAlarmBean(T t) {
        String jsonString = JSONObject.toJSONString(t);
        AlarmInformation alarmInformation = JSONObject.parseObject(jsonString, AlarmInformation.class);
        alarmInformation.setALARM_LEVEL(getAlarmLevel(t));
        return alarmInformation;
    }

    protected abstract String getAlarmLevel(T t) ;

    /**
     * 功能描述: <br>
     * 〈判断源数据相关阈值项是否已经触发了预警阈值〉
     *
     * @param t
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/5/22 14:20
     */
    protected abstract boolean isExceededThreshold(T t);

    /**
     * 功能描述: <br>
     * 〈根据上次的预警记录 判断此次预警是否需要取消或者更新〉
     *
     * @param recentAlarmOption
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/5/22 14:20
     */
    protected abstract boolean shouldUpdate(Optional<AlarmInformation> recentAlarmOption);

    /**
     * 功能描述: <br>
     * 〈根据上次的预警记录 判断此次预警是否需要 新预警〉
     *
     * @param recentAlarmOption  本次源数据对应的上次预警记录  recentAlarm（）方法生成
     * @return:boolean
     * @Author:yhy
     * @Date: 2019/5/22 14:22
     */
    protected boolean shouldAdd(Optional<AlarmInformation> recentAlarmOption) {
        return !recentAlarmOption.isPresent() || Integer.valueOf(0).equals(recentAlarmOption.get().getALARM_STATE());
    }

}