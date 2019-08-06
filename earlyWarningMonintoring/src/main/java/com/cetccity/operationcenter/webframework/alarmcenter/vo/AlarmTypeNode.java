/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmTypeNode
 * Author:   YHY
 * Date:     2019/8/5 16:52
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.vo;

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
 * @create 2019/8/5
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmTypeNode {
    private String name;
    private String code;
    private String layerid;
    private List<AlarmTypeNode> children = new LinkedList<>();
}