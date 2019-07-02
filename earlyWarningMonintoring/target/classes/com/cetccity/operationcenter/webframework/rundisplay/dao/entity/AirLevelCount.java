/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AirLevelCount
 * Author:   YHY
 * Date:     2019/6/5 14:36
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.rundisplay.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用于接收数据库查询空气质量等级计数，不返回给前端，如有需要可加字段〉
 *
 * @author yhy
 * @create 2019/6/5
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirLevelCount {

    private String aqiLevel;

    private String monitorTime;

    private String count;

}