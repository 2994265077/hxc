package com.cetccity.operationcenter.webframework.unifiedauth.api.model;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api.model
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/13 17:50
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/13 17:50
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
public class SimplePageInfoModel<T> {

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
    private List<T> list;

    public SimplePageInfoModel(){

    }

    public SimplePageInfoModel(PageInfo<T> pageInfo){
        this.total = pageInfo.getTotal();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pages = pageInfo.getPages();
        this.list = pageInfo.getList();
    }
}

