/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: NewAlarmMapper
 * Author:   YHY
 * Date:     2019/8/2 16:55
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/8/2
 * @since 1.0.0
 */
@Mapper
public interface NewAlarmMapper {

    List<NameValueTypeModel<Integer>> countByLevel(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("alarm_condition")String alarmCondition, @Param("type_v1") String typeV1, @Param("type_v2") String typeV2);

    List<NameValueDataModel<Integer>> countByTypeLv1s(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("alarm_condition")String alarmCondition, @Param("level")String level);

    List<NameValueDataModel<Integer>> countByTypeLv2s(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("alarm_condition")String alarmCondition, @Param("level")String level, @Param("type_v1") String typeV1);

    List<NameValueModel> types();
}