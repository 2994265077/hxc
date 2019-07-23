package com.cetc.cloud.datasynch.api.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 字段解析映射model -- DS_COLUMN_MAPPING_INFO表
 * Created by llj on 2018/10/14.
 */
@Data
public class DsColumnMappingModel implements Serializable {
    private int id;
    private String targetTable;
    private String source;
    private String sourceColumnName;
    private String targetColumnName;
    private String colType;
    private String colLen;
    private Date createTime;
    private Date updateTime;

}
