package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_SURF_WATER_BASICINFO;
import java.util.List;
import java.util.Map;

public interface QHSJ_SURF_WATER_BASICINFOMapper {

    List<QHSJ_SURF_WATER_BASICINFO> getWaterBASICINFO(Map<String,String> map_time);
}
