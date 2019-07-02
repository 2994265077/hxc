package com.cetc.cloud.datasynch.api.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * PackageName:   com.cetc.cloud.datasynch.api.service
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/12/5 15:29
 * Updater:     by luolinjie
 * Update_Date: 2018/12/5
 * Update_Description: luolinjie 补充
 **/
@Api(tags = "数据字典管理")
public interface ValueDictRemoteService {

    @RequestMapping(value = "/valueDict/importExcel", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "导入Excel", notes = "", produces = "application/json")
    String importExcel(MultipartFile file, String sheetName);


    @RequestMapping(value = "/valueDict/getDictValue", produces = "application/json", method = RequestMethod.GET)
    @ApiOperation(value = "通过表名、字段名、编码获取对应中文释义", notes = "", produces = "application/json")
    String getDictValue(String tableName, String columnName, String code);
}
