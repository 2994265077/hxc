/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmInformationCodeMapper
 * Author:   YHY
 * Date:     2019/5/16 10:57
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.type.dao;

import com.cetccity.operationcenter.webframework.backstage.alarm.type.entity.AlarmCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@Mapper
public interface AlarmCodeMapper {

    /**
     * 功能描述: <br>
     * 〈查询所有预警等级〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.alarm.type.entity.AlarmCode>
     * @Author:yhy
     * @Date: 2019/5/16 11:06
     */
    List<AlarmCode> queryAll();

    /**
     * 功能描述: <br>
     * 〈查询所有一级预警等级〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.alarm.type.entity.AlarmCode>
     * @Author:yhy
     * @Date: 2019/5/16 11:07
     */
    List<AlarmCode> queryLv1s();

}