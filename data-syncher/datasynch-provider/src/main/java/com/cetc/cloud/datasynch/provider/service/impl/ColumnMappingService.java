package com.cetc.cloud.datasynch.provider.service.impl;

import com.cetc.cloud.datasynch.api.model.DsColumnMappingModel;
import com.cetc.cloud.datasynch.provider.mapper.DsColumnMappingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 字段映射关系维护
 * Created by llj on 2018/10/14.
 */
@Service("columnMappingService")
@Slf4j
public class ColumnMappingService {

    @Autowired
    DsColumnMappingMapper dsColumnMappingMapper;

    /**
     * 根据目标表名称获取映射关系Map
     * @param targetTable
     * @return
     */
    public HashMap<String, String> getColumnMappingByTargetTableName(String targetTable) {
        List<DsColumnMappingModel> list = dsColumnMappingMapper.getMappingByTargetTableName(targetTable);
        if (list == null)
            return null;
        HashMap<String, String> columnMap = new LinkedHashMap<String, String>();

        for (int i = 0; i < list.size(); i++) {
            DsColumnMappingModel model = list.get(i);
            columnMap.put(model.getSourceColumnName(), model.getTargetColumnName());
        }
        if (columnMap.size() == 0) {
            log.error("\n>>> >>>> >>>> >>>> >>>> >>>> >>>> >>>> >>>>");
            log.error("\n>>> >>> >>>>  Empty column mapping for table:" + targetTable +"Please import Excel Mapping First");
            return null;
        }
        return columnMap;
    }
//    public HashMap<String, String> getColumnMappingByTargetTableName(String targetTable) {
//        List<DsColumnMappingModel> list = dsColumnMappingMapper.getMappingByTargetTableName(targetTable);
//        if (list == null)
//            return null;
//        HashMap<String, String> columnMap = new LinkedHashMap<String, String>();
//
//        for (int i = 0; i < list.size(); i++) {
//            DsColumnMappingModel model = list.get(i);
//            columnMap.put(model.getSourceColumnName(), model.getTargetColumnName());
//        }
//        if (columnMap.size() == 0) {
//            log.error("\n>>> empty column mapping result for table:" + targetTable);
//            return null;
//        }
//        return columnMap;
//    }

    /**
     * 新增一条映射
     *
     * @param model
     * @return
     */

    public int add(DsColumnMappingModel model) {
        int count = dsColumnMappingMapper.add(model);
        if (count > 0) {
            return model.getId();
        } else {
            return -1;
        }
    }

    /**
     * 批量添加，用于辅助Excel导入功能
     *
     * @param modelList
     * @return
     */

    public int addList(List<DsColumnMappingModel> modelList) {
        return dsColumnMappingMapper.addList(modelList);
    }

    /**
     * 获取列表查询信息--通过源表名称/URL名称
     *
     * @param source
     * @return
     */

    public List<DsColumnMappingModel> getListInfoBySource(String source) {
        return dsColumnMappingMapper.getListInfoBySource(source);
    }

    /**
     * 获取列表查询信息--通过目标表名称
     *
     * @param targetTbName
     * @return
     */

    public List<DsColumnMappingModel> getListInfoByTargetTableName(String targetTbName) {
        return dsColumnMappingMapper.getListInfoByTargetTableName(targetTbName);
    }

    /**
     * 获取列表查询信息--通过目标字段-用于模糊检索
     *
     * @param targetColumnName
     * @return
     */

    public List<DsColumnMappingModel> getListInfoByTargetColumnName(String targetColumnName) {
        return dsColumnMappingMapper.getListInfoByTargetColumnName(targetColumnName);
    }

    /**
     * 通过id更新该条信息
     *
     * @return
     */

    public int updateById(int id, String targetTable, String source, String srcColumnName, String targetColumnName) {
        return dsColumnMappingMapper.updateById(id, targetTable, source, srcColumnName, targetColumnName);
    }

    /**
     * 删除一条映射 byId
     *
     * @param id
     * @return
     */

    public int deleteById(int id) {
        return dsColumnMappingMapper.deleteById(id);
    }

    /**
     * 删除目标表相关的所有映射规则
     *
     * @param targetTbName
     * @return
     */

    public int deleteByTargetTbName(String targetTbName) {
        return dsColumnMappingMapper.deleteByTargetTbName(targetTbName);
    }

}
