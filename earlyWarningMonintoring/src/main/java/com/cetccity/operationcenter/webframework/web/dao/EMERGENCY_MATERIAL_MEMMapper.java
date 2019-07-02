package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.EMERGENCY_MATERIAL_MEM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EMERGENCY_MATERIAL_MEMMapper {

    List<EMERGENCY_MATERIAL_MEM> getEMERGENCY_MATERIAL_MEM(EMERGENCY_MATERIAL_MEM eMERGENCY_MATERIAL_MEM);

}
