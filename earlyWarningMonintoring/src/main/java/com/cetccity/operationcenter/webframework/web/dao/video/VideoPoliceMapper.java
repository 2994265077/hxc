package com.cetccity.operationcenter.webframework.web.dao.video;

import com.cetccity.operationcenter.webframework.web.model.video.VideoPoliceModel;
import com.cetccity.operationcenter.webframework.web.model.video.VideoPoliceModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoPoliceMapper {
    long countByExample(VideoPoliceModelExample example);

    int deleteByExample(VideoPoliceModelExample example);

    int insert(VideoPoliceModel record);

    int insertSelective(VideoPoliceModel record);

    List<VideoPoliceModel> selectByExample(VideoPoliceModelExample example);

    int updateByExampleSelective(@Param("record") VideoPoliceModel record, @Param("example") VideoPoliceModelExample example);

    int updateByExample(@Param("record") VideoPoliceModel record, @Param("example") VideoPoliceModelExample example);

    List<VideoPoliceModel> selectsummaryInfo(String ROWID);

}