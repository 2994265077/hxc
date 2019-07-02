package com.cetc.cloud.datasynch.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cetc.cloud.datasynch.api.model.DsValueDictModel;
import com.cetc.cloud.datasynch.provider.mapper.DsValueDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackageName:   com.cetc.cloud.datasynch.provider.service.impl
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/12/5 15:55
 * Updater:     by luolinjie
 * Update_Date: 2018/12/5
 * Update_Description: luolinjie 补充
 **/
@Service("valueDictService")
public class ValueDictService {
    @Autowired
    DsValueDictMapper dsValueDictMapper;

    public int addList(List<DsValueDictModel> modelList){
       return dsValueDictMapper.addList(modelList);
    }

    public String getCodeInChineseByCode(String tableName, String columnName, String code) {
        QueryWrapper<DsValueDictModel> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(DsValueDictModel::getTableName,tableName)
                .eq(DsValueDictModel::getColumnName,columnName)
                .eq(DsValueDictModel::getCode,code);
        DsValueDictModel dsValueDictModel = dsValueDictMapper.selectOne(wrapper);
        return dsValueDictModel.getCodeInChinese();
    }
}
