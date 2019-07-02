package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.VIDEO_POLICE;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VIDEO_POLICEMapper {

    List<VIDEO_POLICE> getVIDEO_POLICE(VIDEO_POLICE VIDEO_POLICE);

}
