package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.RIVER_INFO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RIVER_INFOMapper {

    List<RIVER_INFO> getRIVER_INFO(RIVER_INFO rIVER_INFO);

}
