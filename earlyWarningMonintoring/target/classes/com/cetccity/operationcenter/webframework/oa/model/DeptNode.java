/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: DeptNode
 * Author:   YHY
 * Date:     2019/6/14 15:33
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.oa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/14
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class DeptNode extends NameBase {
    private String parent;
    private List<NameBase> datas = new LinkedList<>();
}