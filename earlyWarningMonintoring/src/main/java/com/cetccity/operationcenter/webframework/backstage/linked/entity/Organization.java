/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: Organization
 * Author:   YHY
 * Date:     2019/6/10 14:18
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

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈组织机构--联系人〉
 *
 * @author yhy
 * @create 2019/6/10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("组织机构--联系人")
public class Organization {
    @ApiModelProperty("组织机构名称")
    private String name;
    @ApiModelProperty("联系人列表")
    @JsonProperty("linked_man_list")
    private List<LinkedMan> linkedManList;
}