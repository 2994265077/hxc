package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoadMap implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：表名
     *
     * 数据库字段信息:tableName VARCHAR
     */
    private String tableName;
    /**
     * 字段名称：全局唯一ID
     *
     * 数据库字段信息:layerid VARCHAR
     */
    private String layerid;
    /**
     * 字段名称：全局唯一ID
     *
     * 数据库字段信息:uuid VARCHAR
     */
    private String id;
    /**
     * 字段名称：经度
     *
     * 数据库字段信息:jd VARCHAR
     */
    private String jd;

    /**
     * 字段名称：纬度
     *
     * 数据库字段信息:wd VARCHAR
     */
    private String wd;

}
