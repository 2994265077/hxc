/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: RolePermissionRelationVo
 * Author:   YHY
 * Date:     2019/5/27 15:21
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/27
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionRelationVo {
    @NotBlank(message = "角色id不能为空")
    private String roleId;
    private List<String> permissionIds = new LinkedList<>();
}