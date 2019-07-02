package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.PermissionManagementServiceImpl.ROOT_PARENT_ID;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao.entity
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 14:21
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 14:21
 * @Update_Description: huangzezhou 补充
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity {
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
}
