/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: DateRange
 * Author:   YHY
 * Date:     2019/5/16 10:10
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.qo;

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
@ApiModel("日期时间区间类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateTimeRange {

    @ApiModelProperty(value = "开始时间 yyyy-MM-dd HH:mm:Ss ", notes = "开始时间 yyyy-MM-dd HH:mm:Ss ", name = "begin", example = "2019-05-01 00:00:00", dataType = "java.time.LocalDateTime")
    private LocalDateTime begin;

    @ApiModelProperty(value = "结束时间 yyyy-MM-dd HH:mm:Ss ", notes = "结束时间 yyyy-MM-dd HH:mm:Ss", name = "end", example = "2019-05-15 00:00:00", dataType = "java.time.LocalDateTime")
    private LocalDateTime end;
}