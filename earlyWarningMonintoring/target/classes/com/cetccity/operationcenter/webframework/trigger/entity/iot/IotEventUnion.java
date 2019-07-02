/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: IotEventBase
 * Author:   YHY
 * Date:     2019/5/13 17:57
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.entity.iot;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br> 
 * 〈iot事件 关联查询地址、社区、街道、设备名 〉
 *
 * @author yhy
 * @create 2019/5/13
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("iot 事件关联查询结果")
public class IotEventUnion {
    @ApiModelProperty("设备名称")
    private String deviceName;
    @ApiModelProperty("设备编号")
    private String deviceCode;
    @ApiModelProperty("纬度")
    @JSONField(name = "WD84")
    private String wd84;
    @ApiModelProperty("经度")
    @JSONField(name = "JD84")
    private String jd84;
    @ApiModelProperty("设备地址")
    @JSONField(name = "ADDRESS")
    private String address;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("区编号")
    @JSONField(name = "REGION_CODE")
    private String regionCode;
    @ApiModelProperty("街道编号")
    @JSONField(name = "STREET_CODE")
    private String streetCode;
    @ApiModelProperty("社区编号")
    @JSONField(name = "COMMUNITY_CODE")
    private String communityCode;
    @ApiModelProperty("街道名称")
    @JSONField(name = "STREET_NAME")
    private String streetName;
    @ApiModelProperty("社区名称")
    @JSONField(name = "COMMUNITY_NAME")
    private String communityName;
    @ApiModelProperty("随机id")
    private String uuid;
    @ApiModelProperty("设备类型编号")
    private String deviceType;
    @JSONField(name = "F_OBJECT_ROW_ID")
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("事件等级")
    private String eventLevel;
    @ApiModelProperty("数据编码")
    private String dataCode;
    @ApiModelProperty("事件编码")
    private String eventCode;
    @ApiModelProperty("发生时间")
    @JSONField(name = "RELEASE_TIME")
    private LocalDateTime produceTime;
    @ApiModelProperty("状态值")
    private Double dataValue;
    @JSONField(name = "F_ROW_ID")
    @ApiModelProperty("事件id")
    private String iotEventId;
    @ApiModelProperty("设备类型名称")
    private String deviceTypeName;

}