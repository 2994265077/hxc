package com.cetccity.operationcenter.webframework.urbansign.tools;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.tools
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:47 2019-07-08
 * Updater:     heliangming
 * Update_Date：16:47 2019-07-08
 * 更新描述:    heliangming 补充
 **/
@Component
public class RightThirteenDrillDownUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public List<HashMap> getBarNoStreet(String TableName, String name){
        List<HashMap> data = new ArrayList<>();
        String sql = "SELECT STREET_NAME NAME_X,count(*) "+name+" from "+TableName+" where REGION_CODE = '440304' GROUP BY STREET_NAME";
        List<LinkedHashMap> list = oracleOperateService.querySql(sql);
        list.stream().forEach(u-> data.add(u));
        return data;
    }

    public List<HashMap> getBarHasStreet(String TableName, String streetCode, String name){
        List<HashMap> data = new ArrayList<>();
        String sql = "SELECT COMMUNITY_NAME NAME_X,count(*) "+name+" from "+TableName+" where STREET_CODE = '"+streetCode+"' GROUP BY COMMUNITY_NAME";
        List<LinkedHashMap> list = oracleOperateService.querySql(sql);
        list.stream().forEach(u->data.add(u));
        return data;
    }



}
