/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: BlkChengguanEvent
 * Author:   YHY
 * Date:     2019/5/9 10:24
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br> 
 * 〈  〉
 *
 * @author yhy
 * @create 2019/5/9
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlkChengguanEvent {
    @ApiModelProperty("自增主键")
    @JSONField(name = "F_ROW_ID")
    private String OBJECT_ID;

    @ApiModelProperty("uuid")
    @JSONField(name = "SYSTEMID")
    private String systemId;

    @ApiModelProperty("事件编号")
    @JSONField(name = "EVENT_CODE")
    private String eventCode;

    @ApiModelProperty("事件名称")
    @JSONField(name = "ALARM_OBJECT")
    private String eventName;

    @ApiModelProperty("事件内容")
    @JSONField(name = "CONTENTS")
    private String eventContent;

    @ApiModelProperty("事件发生时间")
    @JSONField(name = "RELEASE_TIME")
    private LocalDateTime eventTime;

    @ApiModelProperty("事件来源")
    @JSONField(name = "EVENT_SOURCE")
    private String eventSource;

    @ApiModelProperty("事件地址")
    @JSONField(name = "ADDRESS")
    private String address;

    @ApiModelProperty("状态(0、受理；1、分拨；2、办结；3、已评价；9、归档(字典项)；50、挂起；80、作废；)")
    @JSONField(name = "DISPOSAL_STATE")
    private String state;

    @ApiModelProperty("创建者id")
    @JSONField(name = "CREATER_ID")
    private String createrId;

    @ApiModelProperty("创建者部门id")
    @JSONField(name = "CREATER_DEPTID")
    private String createrDeptid;

    @ApiModelProperty("创建者部门名称")
    @JSONField(name = "CREATER_DEPT")
    private String createrDept;

    @ApiModelProperty("上报人名称")
    @JSONField(name = "REPORT_NAME")
    private String reportName;

    @ApiModelProperty("工作流状态")
    @JSONField(name = "FLOWSTATE")
    private String flowState;

    @ApiModelProperty("工作流状态id")
    @JSONField(name = "FLOWSTATEID")
    private String flowStateId;

    @ApiModelProperty("事件类型")
    @JSONField(name = "EVENT_TYPE")
    private String eventType;

    @ApiModelProperty("事件类型")
    @JSONField(name = "ALARM_TYPE")
    private String eventLV;

    @ApiModelProperty("街道")
    @JSONField(name = "JD")
    private String JD;

    @ApiModelProperty("街道代码")
    @JSONField(name = "JDDM")
    private String JDDM;

    @ApiModelProperty("社区")
    @JSONField(name = "SQ")
    private String SQ;

    @ApiModelProperty("社区代码")
    @JSONField(name = "SQDM")
    private String SQDM;

    @ApiModelProperty("网格")
    @JSONField(name = "WG")
    private String WG;

    @ApiModelProperty("网格代码")
    @JSONField(name = "WGDM")
    private String WGDM;

    @ApiModelProperty("经度")
    @JSONField(name = "JD84")
    private String JD84;

    @ApiModelProperty("纬度")
    @JSONField(name = "WD84")
    private String WD84;

    @ApiModelProperty("上报")
    @JSONField(name = "CREATER_NAME")
    private String CREATER_NAME;
}