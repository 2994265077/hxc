package com.cetccity.operationcenter.webframework.backstage.video.dao;

import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASS;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASS_GROUP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频_标签 Mapper 接口
 * </p>
 *
 * @author LinHaiquan
 * @since 2019-03-28
 */
@Mapper
public interface DistrictClassMapper {

    @Select("select SEQ_DISTRICT_CLASS.nextval from dual")
    Integer maxId();

    List<DISTRICT_CLASS> isExistTagName(Map map);

    List<DISTRICT_CLASS> findVideoList(DISTRICT_CLASS dISTRICT_CLASS);

    List<DISTRICT_CLASS_GROUP> findVideoAsListNo(DISTRICT_CLASS dISTRICT_CLASS);

    List<DISTRICT_CLASS_GROUP> findVideoAsList(DISTRICT_CLASS dISTRICT_CLASS);

    boolean updateById(DISTRICT_CLASS dISTRICT_CLASS);

    boolean removeClassById(@Param("id")Integer id);

    boolean removeVideoTagById(@Param("id")Integer id);

    int save(DISTRICT_CLASS dISTRICT_CLASS);

}
