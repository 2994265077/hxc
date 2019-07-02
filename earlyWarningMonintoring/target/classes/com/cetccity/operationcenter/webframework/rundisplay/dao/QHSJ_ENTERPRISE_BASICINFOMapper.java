package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_ENTERPRISE_BASICINFO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface QHSJ_ENTERPRISE_BASICINFOMapper {

    List<QHSJ_ENTERPRISE_BASICINFO> getWaterBASICINFO(Map<String,String> map_time);

}
