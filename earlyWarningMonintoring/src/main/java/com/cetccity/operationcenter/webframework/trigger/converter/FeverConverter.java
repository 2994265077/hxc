/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: WeijiAggregateConverter
 * Author:   YHY
 * Date:     2019/5/22 17:02
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
public class FeverConverter extends WeijiAggregateConverter {

    @Override
    protected String getAlarmLevel() {
        return "二级-橙";
    }

    public FeverConverter() {
        this.thresholdCount = 56;
        thresholdString = "发热";
    }

    @Override
    public String getAlarmTypeLv2() {
        return "007001";
    }
}

