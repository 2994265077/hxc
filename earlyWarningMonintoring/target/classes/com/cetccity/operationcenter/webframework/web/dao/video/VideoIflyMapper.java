package com.cetccity.operationcenter.webframework.web.dao.video;


import java.math.BigDecimal;
import java.util.List;

import com.cetccity.operationcenter.webframework.web.model.video.VideoIflyModel;
import com.cetccity.operationcenter.webframework.web.model.video.VideoIflyModelExample;
import org.apache.ibatis.annotations.Param;

public interface VideoIflyMapper {
    long countByExample(VideoIflyModelExample example);

    int deleteByExample(VideoIflyModelExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(VideoIflyModel record);

    int insertSelective(VideoIflyModel record);

    List<VideoIflyModel> selectByExample(VideoIflyModelExample example);

    VideoIflyModel selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") VideoIflyModel record, @Param("example") VideoIflyModelExample example);

    int updateByExample(@Param("record") VideoIflyModel record, @Param("example") VideoIflyModelExample example);

    int updateByPrimaryKeySelective(VideoIflyModel record);

    int updateByPrimaryKey(VideoIflyModel record);
}