/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: RiskPoint
 * Author:   YHY
 * Date:     2019/4/23 10:54
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
 * @create 2019/4/23
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("危险源")
public class RiskPoint {

    @ApiModelProperty("主键")
    @JSONField(name = "F_ROW_ID")
    private String OBJECT_ID;

    @ApiModelProperty("经度")
    private String LON;

    @ApiModelProperty("维度")
    private String LAT;

    @ApiModelProperty("街道代码")
    private String STREET_CODE;

    @ApiModelProperty("社区代码")
    private String COMMUNITY_CODE;

    @ApiModelProperty("街道名称")
    private String STREET_NAME;

    @ApiModelProperty("社区名称")
    private String COMMUNITY_NAME;

    @ApiModelProperty("地址")
    @JSONField(name = "ADDRESS")
    private String DETAIL_ADDRESS;

    @ApiModelProperty("经度")
    private String JD84;

    @ApiModelProperty("维度")
    private String WD84;

    @ApiModelProperty("区代码")
    private String REGION_CODE;

    @ApiModelProperty("创建时间  event(推送时间)")
    @JSONField(name = "RELEASE_TIME")
    private String YJJC_CREATE_TIME;

    @ApiModelProperty("存在的隐患情况 event（事件内容）")
    @JSONField(name = "CONTENTS")
    private String PARAMS;

    @ApiModelProperty("单位名称  event(事件主体)")
    @JSONField(name = "ALARM_OBJECT")
    private String UNIT_NAME;

    @ApiModelProperty("危险等级")
    @JSONField(name = "ALARM_LEVEL")
    private String DANGER_LEVEL;

}