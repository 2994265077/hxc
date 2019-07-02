package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BLK_CG_EVT_ATTS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlkCgEvtAttsMapper {

    List<BLK_CG_EVT_ATTS> getImgUrlBLK_CG_EVT_ATTS(@Param("id") String id);

}
