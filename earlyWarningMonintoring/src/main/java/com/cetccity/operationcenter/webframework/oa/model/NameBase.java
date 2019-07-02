/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: Dept
 * Author:   YHY
 * Date:     2019/6/12 16:19
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.oa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/12
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameBase implements Serializable {
    private String id;
    private String name;
}