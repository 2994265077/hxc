/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ApiLog
 * Author:   YHY
 * Date:     2019/4/11 11:07
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.log.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/11
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("日志实体")
public class ApiLog {

    private long id;

    @ApiModelProperty("请求方法")
    private String httpMethod;

    @ApiModelProperty("mvc映射到的路径 如：/api/{path}")
    private String matchedPath;

    @ApiModelProperty("浏览器实际请求路径")
    private String realPath;

    @ApiModelProperty("是否请求成功")
    private boolean success;

    @ApiModelProperty("请求耗时 ns")
    private long consumedTime;

    @ApiModelProperty("失败原因")
    private String failedCause;

    @ApiModelProperty("请求时间")
    private LocalDateTime requestTime = LocalDateTime.now();
}