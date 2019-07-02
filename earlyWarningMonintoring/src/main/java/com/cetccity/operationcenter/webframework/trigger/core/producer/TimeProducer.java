/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: TimeP    @Autowired
roducer
 * Author:   YHY
 * Date:     2019/5/20 15:33
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.core.producer;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 〈一句话功能简述〉<br> 
 * 〈根据时间本类型最后一次预警的时间查询源数据〉
 *
 * @author yhy
 * @create 2019/5/20
 * @since 1.0.0
 */
public interface TimeProducer<T> extends Producer<T> {

   /**
    * 默认最后一次预警时间为1970年
    */
   LocalDateTime DEFAULT_PREVIOUS_TIME = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());

   /**
    * 功能描述: <br>
    * 〈查询本来想最后一次预警的时间〉
    *
    * @return:java.time.LocalDateTime
    * @Author:yhy
    * @Date: 2019/5/22 14:59
    */
   LocalDateTime previousTime();


}