package com.cetccity.operationcenter.webframework.trigger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@Data
public class AlarmInformation extends ObjectBaseInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 全表自增id
     */
    @JsonProperty
    private Integer OBJECT_ID;


    /**
     * 外键关联行数据的主键，事件id
     */
    @JsonProperty
    private Double F_ROW_ID;

    /**
     * 源表名
     */
    @JsonProperty
    private String ORIGIN_TABLE_NAME;

    /**
     * 综治业务编号
     */
    @JsonProperty
    private String SYSTEM_ID;

    /**
     * 发布时间
     */
    @JsonProperty()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date RELEASE_TIME;

    /**
     * 取消时间
     */
    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date CANCEL_TIME;

    /**
     * 发布人
     */
    @JsonProperty
    private String RELEASE_PERSON;

    /**
     * 预警状态（1:正在预警中；0:预警已结束）
     */
    @JsonProperty
    private Integer ALARM_STATE;

    /**
     * 预警内容
     */
    @JsonProperty
    private String CONTENTS;

    /**
     * 预警等级
     */
    @JsonProperty
    private String ALARM_LEVEL;

    /**
     * 一级预警等级编码
     */
    @JsonProperty
    private String ALARM_TYPE_LV1;

    /**
     * 二级预警等级编码
     */
    @JsonProperty
    private String ALARM_TYPE_LV2;

    /**
     * 处置状态（0.未处置、2.办结、3.已评价、3.归档、50.挂起、80.作废）
     */
    @JsonProperty
    private Integer DISPOSAL_STATE;

    /**
     * 发送状态（0.未发送；1.已发送）
     */
    @JsonProperty
    private Integer SEND_STATE;

    /**
     * 渠道
     */
    @JsonProperty
    private String CHANNEL;

    /**
     * 创建日期
     */
    @JsonProperty
    private Date YJJC_CREATE_TIME;

    /**
     * 更新时间
     */
    @JsonProperty
    private Date YJJC_UPDATE_TIME;


    public String errorLocation(){
        return "originTableName="+ORIGIN_TABLE_NAME+",systemId="+SYSTEM_ID;
    }


}
