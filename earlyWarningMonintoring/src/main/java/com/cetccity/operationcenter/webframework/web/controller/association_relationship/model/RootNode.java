package com.cetccity.operationcenter.webframework.web.controller.association_relationship.model;

import java.util.List;

/**
 * 源节点
 */
public class RootNode {
    String name;    /*中文名称*/
    String table;   /*表名*/
    List<String> basic_cols;  /*基本属性字段*/
    List<Relationship> relationships; /*关联关系*/

    public List<String> getBasic_cols() {
        return basic_cols;
    }

    public void setBasic_cols(List<String> basic_cols) {
        this.basic_cols = basic_cols;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
