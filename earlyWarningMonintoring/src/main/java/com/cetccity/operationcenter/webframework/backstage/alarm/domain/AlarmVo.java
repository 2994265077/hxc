/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmVo
 * Author:   YHY
 * Date:     2019/5/17 14:30
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/17
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmVo {
    /**
     * 预警对象
     */
    @JsonProperty(value = "alarm_object" )
    private String alarmObject;

    /**
     * 预警对象的唯一主键
     */
    @JsonProperty(value = "f_object_row_id" )
    private String fObjectRowId;

    /**
     * 经度84
     */
    @JsonProperty(value = "jd84" )
    private String jd84;

    /**
     * 纬度84
     */
    @JsonProperty(value = "wd84" )
    private String wd84;

    /**
     * 区代码
     */
    @JsonProperty(value = "region_code" )
    private String regionCode;

    /**
     * 街道代码
     */
    @JsonProperty(value = "street_code" )
    private String streetCode;

    /**
     * 社区代码
     */
    @JsonProperty(value = "community_code" )
    private String communityCode;

    @JsonProperty(value = "address" )
    private String address;

    @JsonProperty(value = "street_name" )
    private String streetName;

    @JsonProperty(value = "community_name" )
    private String communityName;

    /**
     * 楼栋代码
     */
    @JsonProperty(value = "lddm" )
    private String lddm;


    /**
     * 全表自增id
     */
    @JsonProperty(value = "object_id" )
    private String objectId;


    /**
     * 外键关联行数据的主键，事件id
     */
    @JsonProperty(value = "f_row_id" )
    private String fRowId;

    /**
     * 源表名
     */
    @JsonProperty(value = "origin_table_name" )
    private String originTableName;

    /**
     * 综治业务编号
     */
    @JsonProperty(value = "system_id" )
    private String systemId;

    /**
     * 发布时间
     */
    @JsonProperty(value = "release_time" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime releaseTime;

    /**
     * 取消时间
     */
    @JsonProperty(value = "cancel_time" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelTime;

    /**
     * 发布人
     */
    @JsonProperty(value = "release_person" )
    private String releasePerson;

    /**
     * 预警状态（1:正在预警中；0:预警已结束）
     */
    @JsonProperty(value = "alarm_state" )
    private String alarmState;

    /**
     * 预警内容
     */
    @JsonProperty(value = "contents" )
    private String contents;

    /**
     * 预警等级
     */
    @JsonProperty(value = "alarm_level" )
    private String alarmLevel;

    /**
     * 一级预警等级编码
     */
    @JsonProperty(value = "alarm_type_lv1" )
    private String alarmTypeLv1;

    /**
     * 二级预警等级编码
     */
    @JsonProperty(value = "alarm_type_lv2" )
    private String alarmTypeLv2;

    /**
     * 处置状态（0.未处置、2.办结、3.已评价、3.归档、50.挂起、80.作废）
     */
    @JsonProperty(value = "disposal_state" )
    private String disposalState;

    /**
     * 发送状态（0.未发送；1.已发送）
     */
    @JsonProperty(value = "send_State" )
    private String sendState;

    /**
     * 渠道
     */
    @JsonProperty(value = "channel" )
    private String channel;

    /**
     * 创建日期
     */
    @JsonProperty(value = "yjjc_create_time" )
    private LocalDateTime yjjcCreateTime;

    /**
     * 更新时间
     */
    @JsonProperty(value = "yjjc_update_time" )
    private LocalDateTime yjjcUpdateTime;

    @JsonProperty("alarm_type_lv2_name")
    private String alarmTypeLv2Name;

    @JsonProperty("alarm_type_lv1_name")
    private String alarmTypeLv1Name;

    @JsonProperty("send_state_name")
    private String sendStateName;

    @JsonProperty("disposal_state_name")
    private String disposalStateName;

    @JsonProperty("alarm_state_name")
    private String alarmStateName;

}