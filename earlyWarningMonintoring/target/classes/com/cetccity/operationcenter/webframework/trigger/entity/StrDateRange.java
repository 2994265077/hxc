/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: StrDateRange
 * Author:   YHY
 * Date:     2019/4/25 15:31
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/25
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StrDateRange {

    private String begin;

    private String end;
}