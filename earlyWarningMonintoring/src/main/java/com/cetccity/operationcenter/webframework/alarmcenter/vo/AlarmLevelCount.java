/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmLevelCount
 * Author:   YHY
 * Date:     2019/8/2 17:05
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/8/2
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("预警等级统计实体")
public class AlarmLevelCount {
    @ApiModelProperty("预警等级名称")
    private String name;
    @ApiModelProperty("查询时传的名称")
    private String value;
    @ApiModelProperty("查询到的数量")
    private long count;
    @ApiModelProperty("今日统计数量")
    private long todayCount;
}