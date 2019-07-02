/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: TheadTbodyPlus
 * Author:   YHY
 * Date:     2019/6/18 15:12
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/18
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheadTbodyPlus {
    private List<String> thead = new LinkedList<>();
    private List<List<Object>> tbody = new LinkedList<>();
}