/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: LinkedMan
 * Author:   YHY
 * Date:     2019/6/5 17:53
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.linked.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈联系人〉
 *
 * @author yhy
 * @create 2019/6/5
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("联系人")
public class LinkedMan {
    @ApiModelProperty("联系人id")
    @JsonProperty("linked_man_id")
    private String linkedManId;
    @ApiModelProperty("联系人名称")
    private String name;
    @ApiModelProperty("联系人电话号码")
    private String tel;
}