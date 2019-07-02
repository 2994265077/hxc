package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjSdmInfoV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 卫计_特殊病种监控表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-03
 */
@Mapper
public interface YjjcQwjjSdmInfoVMapper {

    List<YjjcQwjjSdmInfoV> querySourceData(Map map);

    List<YjjcQwjjSdmInfoV> querySourceDataByDateRange(@Param("begin") String begin, @Param("end") String end);

    List<YjjcQwjjSdmInfoV> queryByMinId(@Param("min_object_id") String minObjectId);

}
