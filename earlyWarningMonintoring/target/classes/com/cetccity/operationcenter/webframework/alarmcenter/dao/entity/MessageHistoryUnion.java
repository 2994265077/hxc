/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageHistoryUnion
 * Author:   YHY
 * Date:     2019/6/12 11:19
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.dao.entity;

import com.cetccity.operationcenter.webframework.backstage.alarm.domain.AlarmVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 〈一句话功能简述〉<br> 
 * 〈给前端显示的聚合短信历史记录〉
 *
 * @author yhy
 * @create 2019/6/12
 * @since 1.0.0
 */
@ApiModel("聚合短信历史记录")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistoryUnion extends AlarmVo {
    @ApiModelProperty("发送数量")
    @JsonProperty("send_num")
    private long sendNum;
}