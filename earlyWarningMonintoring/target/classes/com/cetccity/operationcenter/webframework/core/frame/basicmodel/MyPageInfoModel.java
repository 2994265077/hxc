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
public class MyPageInfoModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：总数
     *
     * 数据库字段信息:totle VARCHAR
     */
    private long total;
    /**
     * 字段名称：当前页
     *
     * 数据库字段信息:pageNum VARCHAR
     */
    private Integer pageNum;
    /**
     * 字段名称：总页数
     *
     * 数据库字段信息:pages VARCHAR
     */
    private Integer pages;
    /**
     * 字段名称：pagesize
     *
     * 数据库字段信息:pageSize VARCHAR
     */
    private Integer pageSize;
    /**
     * 字段名称：数据
     *
     * 数据库字段信息:datas VARCHAR
     */
    private T list;

}
