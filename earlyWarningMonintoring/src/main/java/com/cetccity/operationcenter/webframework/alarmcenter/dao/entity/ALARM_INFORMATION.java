package com.cetccity.operationcenter.webframework.alarmcenter.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * PackageName:   com.cetc.cloud.datasynch.api.model
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/11/27 23:06
 * Updater:     by luolinjie
 * Update_Date: 2018/12/11 11:00
 * Update_Description: huangzezhou 补充
 **/

/**
 *
 value–字段说明
 name–重写属性名字
 dataType–重写属性类型
 required–是否必填
 example–举例说明
 hidden–隐藏
 */
@ApiModel("预警模型")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ALARM_INFORMATION implements Serializable {

    private String ID;

    @ApiModelProperty(value = "全表自增id,mybatis自动生成",hidden = true)
    private String OBJECT_ID;

    @ApiModelProperty(value = "预警对象", required = true)
    private String ALARM_OBJECT;

    @ApiModelProperty(value = "外键关联主体的object_id", required = true)
    private Integer fRowId;

    @ApiModelProperty("来源表名")
    private String originTableName;

    @ApiModelProperty(value = "事件编号，预警平台生成，推送给综治", required = true)
    private String SYSTEM_ID;

    @ApiModelProperty(value = "发布时间", required = true)
    private String RELEASE_TIME;

    @ApiModelProperty(value = "取消发布的时间")
    private LocalDate CANCEL_TIME;

    @ApiModelProperty(value = "发布人", example = "预警监测平台", required = true)
    private String RELEASE_PERSON;

    //1:正在预警中;0:预警已结束
    @ApiModelProperty(value = "预警状态", example = "1", required = true)
    private Integer ALARM_STATE;

    private String ALARM_NAME;

    @ApiModelProperty(value = "预警内容", required = true)
    private String CONTENTS;

    @ApiModelProperty(value = "预警等级", example = "红、橙、黄、蓝;1、2、3级;高、中、低;物联网:一般、重要、紧急", required = true)
    private String ALARM_LEVEL;

    @ApiModelProperty(value = "一级预警类型编码", required = true)
    private String ALARM_TYPE_LV1;

    @ApiModelProperty(value = "二级预警类型编码", required = true)
    private String ALARM_TYPE_LV2;

    //0:未处置;1:已分拨;2:已办结;3:已归档;50:挂起;80:作废
    @ApiModelProperty(value = "处置状态", example = "0")
    private Integer DISPOSAL_STATE;

    private String disposalStatusStr;

    private String DISPOSAL_NAME;

    //0:未发送;1:已发送
    @ApiModelProperty(value = "发送状态", example = "0")
    private Integer SEND_STATE;

    @ApiModelProperty(value = "渠道", example = "物联网平台")
    private String CHANNEL;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "街道名")
    private String streetName;

    @ApiModelProperty(value = "社区名")
    private String communityName;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date yjjcCreateTime;
    @ApiModelProperty(value = "更新时间",hidden = true)
    private String YJJC_UPDATE_TIME;
    @ApiModelProperty(value = "楼栋代码",hidden = true)
    private String lddm;
    @ApiModelProperty(value = "84坐标经度",hidden = true)
    private String JD84;
    @ApiModelProperty(value = "84坐标维度",hidden = true)
    private String WD84;
    @ApiModelProperty(value = "区代码",hidden = true)
    private String REGION_CODE;
    @ApiModelProperty(value = "街道代码",hidden = true)
    private String STREET_CODE;
    @ApiModelProperty(value = "社区代码",hidden = true)
    private String COMMUNITY_CODE;

    private Integer pageNum;

    private Integer pageSize;

    private String startTime;

    private String endTime;

    private String LV_1_NAME;

    private String LV_2_NAME;

    private String street;

    private String condition;

}
