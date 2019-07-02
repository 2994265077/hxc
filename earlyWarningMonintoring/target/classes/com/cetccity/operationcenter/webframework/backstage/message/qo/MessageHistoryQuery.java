/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageHistoryQuery
 * Author:   YHY
 * Date:     2019/6/12 11:39
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.message.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/12
 * @since 1.0.0
 */
@ApiModel("用于查询聚合短信历史信息的条件对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHistoryQuery {
    @ApiModelProperty("预警类型代码")
    @JsonProperty("alarm_type_code")
    private String alarmTypeCode;
    @ApiModelProperty("联系人名称")
    @JsonProperty("staff_name")
    private String staffName;
    @ApiModelProperty("发件人账号")
    @JsonProperty("create_user")
    private String createUser;
    @ApiModelProperty("发送时间开始条件")
    @JsonProperty("send_time_begin")
    private LocalDateTime sendTimeBegin = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
    @ApiModelProperty("发送时间结束条件")
    @JsonProperty("send_time_end")
    private LocalDateTime sendTimeEnd;
    @ApiModelProperty("联系人id 前端忽略")
    @JsonProperty("staff_ids")
    private Set<String> staffIds;
    @ApiModelProperty("分页当前页码")
    private int pageNum = 1;
    @ApiModelProperty("分页每页大小")
    private int pageSize = 10;
}