package com.cetc.cloud.datasynch.provider.mapper;

import com.cetc.cloud.datasynch.api.model.DsOuterJobModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * PackageName:   com.cetc.cloud.datasynch.provider.mapper.input
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/12/14 17:59
 * Updater:     by luolinjie
 * Update_Date: 2018/12/14
 * Update_Description: luolinjie 补充
 **/
@Mapper
public interface DsOuterJobInfoMapper {
    DsOuterJobModel getModelByTableName(@Param("tableName")String tableName);

    DsOuterJobModel getModelByObjectId(@Param("objectId")int objectId);
}