/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageHistory
 * Author:   YHY
 * Date:     2019/6/6 14:30
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.dao.entity;

import com.cetccity.operationcenter.webframework.oa.model.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/6
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistory {
    private String objectId;
    private String content;
    private String createUser;
    private String alarmId;
    private LocalDateTime createTime;
    @ApiModelProperty("短信接收方")
    @JsonProperty("accepter")
    private List<Employee> accepter;
}