/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: PermissionNode
 * Author:   YHY
 * Date:     2019/5/27 10:25
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.LinkedHashSet;

import static com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.PermissionManagementServiceImpl.ROOT_PARENT_ID;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/27
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation("权限树节点")
public class PermissionNode {

    @ApiModelProperty(value = "权限编号")
    String permission_id;
    @ApiModelProperty(value = "权限名")
    @NotBlank(message = "权限名不能为空")
    String permission_name;
    @ApiModelProperty(value = "权限字符串")
    String permission_str;
    @ApiModelProperty(value = "url")
    @NotBlank(message = "权限url不能为空")
    String permission_url;
    @ApiModelProperty(value = "描述")
    String description;
    @ApiModelProperty(value = "父节点id", notes = "不传值表示此节点为根节点, 默认值为-1.表示此节点为根节点", example = "-1")
    @NotBlank(message = "父节点可以不传，但必须不为空字符串")
    private String parent_id = ROOT_PARENT_ID;
    @ApiModelProperty(value = "是否是父节点", notes = "新增和修改不用传此值，传值将会被后端忽略")
    private boolean parent;

    private Collection<PermissionNode> children = new LinkedHashSet<>();;

}