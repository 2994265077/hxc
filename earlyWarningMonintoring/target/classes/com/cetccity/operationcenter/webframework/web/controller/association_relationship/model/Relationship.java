package com.cetccity.operationcenter.webframework.web.controller.association_relationship.model;

/**
 * 源节点之间的关联关系
 */
public class Relationship {
    String node;/*关联节点 key*/
    String rootCol; /*源节点 关联字段*/
    String rootColLable;/*源节点 关联字段 备注*/
    String relationCol; /*关联节点 关联字段*/
    String relationColLable;/*关联接点 关联字段 备注*/

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getRootCol() {
        return rootCol;
    }

    public void setRootCol(String rootCol) {
        this.rootCol = rootCol;
    }

    public String getRootColLable() {
        return rootColLable;
    }

    public void setRootColLable(String rootColLable) {
        this.rootColLable = rootColLable;
    }

    public String getRelationCol() {
        return relationCol;
    }

    public void setRelationCol(String relationCol) {
        this.relationCol = relationCol;
    }

    public String getRelationColLable() {
        return relationColLable;
    }

    public void setRelationColLable(String relationColLable) {
        this.relationColLable = relationColLable;
    }
}
