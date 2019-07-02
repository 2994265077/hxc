/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: Code
 * Author:   YHY
 * Date:     2019/4/22 11:23
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 〈一句话功能简述〉<br> 
 * 〈验证码〉
 *
 * @author yhy
 * @create 2019/4/22
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("验证码")
public class Code {
    @ApiModelProperty(value = "验证码id", required = true)
    @NotBlank(message = "验证码id不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String id;
    @ApiModelProperty(value = "验证码值", required = true)
    @NotBlank(message = "请输入验证码")
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String value;
}