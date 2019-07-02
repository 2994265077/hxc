package com.cetccity.operationcenter.webframework.web.dao.video;

import com.cetccity.operationcenter.webframework.web.model.video.VideoUser;
import com.cetccity.operationcenter.webframework.web.model.video.VideoUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoUserMapper {
    long countByExample(VideoUserExample example);

    int deleteByExample(VideoUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoUser record);

    int insertSelective(VideoUser record);

    List<VideoUser> selectByExample(VideoUserExample example);

    VideoUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoUser record, @Param("example") VideoUserExample example);

    int updateByExample(@Param("record") VideoUser record, @Param("example") VideoUserExample example);

    int updateByPrimaryKeySelective(VideoUser record);

    int updateByPrimaryKey(VideoUser record);
}