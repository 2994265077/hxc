/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmType
 * Author:   YHY
 * Date:     2019/5/16 10:54
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.type.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("报警类型")
public class AlarmCode {

    @ApiModelProperty(value = "一级类型编码", notes = "一级类型编码", dataType = "String")
    private String lv1;
    @ApiModelProperty(value = "一级类型名称", notes = "一级类型名称", dataType = "String")
    private String lv1Name;
    @ApiModelProperty(value = "二级类型编码", notes = "二级类型编码", dataType = "String")
    private String lv2;
    @ApiModelProperty(value = "二级类型名称", notes = "二级类型名称", dataType = "String")
    private String lv2Name;

}