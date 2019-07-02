/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageSend
 * Author:   YHY
 * Date:     2019/6/6 11:09
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/6
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("短信发送实体")
public class MessageSend {
    @ApiModelProperty("联系人主键列表")
    @NotEmpty(message = "联系人主键列表不能为空")
    private List<String> userIds;
    @ApiModelProperty("短信内容")
    @NotBlank(message = "短信内容不能为空")
    private String content;
    @ApiModelProperty("预警对象id")
    @NotBlank(message = "预警对象不能为空")
    private String alarmId;
    private boolean success;
}