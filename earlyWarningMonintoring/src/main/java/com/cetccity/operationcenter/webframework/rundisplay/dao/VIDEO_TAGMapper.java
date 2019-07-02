package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.VIDEO_TAG;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VIDEO_TAGMapper {

    List<VIDEO_TAG> getVIDEO_TAG(VIDEO_TAG vIDEO_TAG);
}
