/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmQueryVo
 * Author:   YHY
 * Date:     2019/5/16 10:18
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.qo;

import com.cetccity.operationcenter.webframework.common.qo.DateTimeRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@ApiModel("预警查询条件对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmQueryObject {
    @ApiModelProperty(value = "街道编码", notes = "街道编码", name = "streetCode" ,dataType = "String")
    private String streetCode;
    @ApiModelProperty(value = "预警类型（二级）编码", notes = "预警类型（二级）编码", name = "alarmTypeLv2" ,dataType = "String")
    private String alarmTypeLv2;
    @ApiModelProperty(value = "预警状态", notes = "预警状态", name = "alarmState" ,dataType = "String")
    private String alarmState;
    @ApiModelProperty(value = "推送状态", notes = "推送状态", name = "sendState" ,dataType = "String")
    private String sendState;
    @ApiModelProperty(value = "处置状态", notes = "处置状态", name = "disposalState" ,dataType = "String")
    private String disposalState;
    @ApiModelProperty(value = "来源渠道", notes = "来源渠道", name = "channel" ,dataType = "String")
    private String channel;
    @ApiModelProperty(value = "时间区间", notes = "时间区间", name = "releaseTimeRange" ,dataType = "com.cetccity.operationcenter.webframework.backstage.common.DateTimeRange")
    private DateTimeRange releaseTimeRange;
    @ApiModelProperty(value = "开始时间 yyyy-MM-dd HH:mm:Ss ", notes = "开始时间 yyyy-MM-dd HH:mm:Ss ", name = "begin", example = "2019-05-01 00:00:00", dataType = "java.time.LocalDateTime")
    private String begin;
    @ApiModelProperty(value = "结束时间 yyyy-MM-dd HH:mm:Ss ", notes = "结束时间 yyyy-MM-dd HH:mm:Ss", name = "end", example = "2019-05-15 00:00:00", dataType = "java.time.LocalDateTime")
    private String end;
    @ApiModelProperty(value = "页码", notes = "页码", name = "pageNum" ,dataType = "int", example = "1")
    private int pageNum = 1;
    @ApiModelProperty(value = "页面大小", notes = "时间区间", name = "pageSize" ,dataType = "int", example = "10")
    private int pageSize = 10;

}