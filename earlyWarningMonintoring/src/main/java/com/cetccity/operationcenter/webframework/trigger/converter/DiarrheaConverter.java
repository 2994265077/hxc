/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: DiarrheaConverter
 * Author:   YHY
 * Date:     2019/5/22 17:50
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter;



/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/22
 * @since 1.0.0
 */
public class DiarrheaConverter extends WeijiAggregateConverter  {

    public DiarrheaConverter() {
        this.thresholdCount = 10;
        thresholdString = "流感";
    }

    @Override
    public String getAlarmTypeLv2() {
        return "007002";
    }
}