package com.cetc.cloud.datasynch.provider.mapper;

import com.cetc.cloud.datasynch.api.model.DsColumnMappingModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字段映射管理DAO层定义
 * Created by llj on 2018/10/14.
 */
@Mapper
public interface DsColumnMappingMapper {

    int add(DsColumnMappingModel model);

    int addList(List<DsColumnMappingModel> modelList);

    List<DsColumnMappingModel> getMappingByTargetTableName(String targetTbName);

    List<DsColumnMappingModel> getListInfoByTargetTableName(String targetTbName);

    List<DsColumnMappingModel> getListInfoByTargetColumnName(String targetColumnName);

    List<DsColumnMappingModel> getListInfoBySource(String source);

    int updateById(@Param("id")int id,@Param("targetTable")String targetTable,@Param("source")String source,
                   @Param("srcColumnName")String srcColumnName,@Param("targetColumnName")String targetColumnName);

    int deleteByTargetTbName(String targetTbName);

    int deleteById(int id);//删除多少条，返回数字就是多少
}
