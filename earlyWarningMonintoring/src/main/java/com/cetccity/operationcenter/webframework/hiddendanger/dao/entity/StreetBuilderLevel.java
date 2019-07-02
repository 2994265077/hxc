/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: StreetBuilderLevel
 * Author:   YHY
 * Date:     2019/4/18 13:13
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 〈一句话功能简述〉<br> 
 * 〈街道建筑风险统计domain〉
 *  用于统计每个街道、社区 建筑风险等级（低风险，中低，中...）数量
 * @author yhy
 * @create 2019/4/18
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreetBuilderLevel implements Serializable {
    private String dangerLevel;
    private String name;
    private Integer count;
}