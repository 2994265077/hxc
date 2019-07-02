/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CommunityInfo
 * Author:   YHY
 * Date:     2019/5/16 10:50
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("社区、街道、区信息实体")
public class CommunityInfo {
    @ApiModelProperty("主键")
    private String objectId;
    @ApiModelProperty("区代码")
    private String regionCode;
    @ApiModelProperty("区名称")
    private String regionName;
    @ApiModelProperty("街道代码")
    private String streetCode;
    @ApiModelProperty("街道名称")
    private String streetName;
    @ApiModelProperty("社区名称")
    private String communityName;
    @ApiModelProperty("社区代码")
    private String communityCode;
}