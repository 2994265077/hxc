package com.cetccity.operationcenter.webframework.web.controller.association_relationship.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 关联关系 结构维护
 {
 "building":{
 "name":"建筑",
 "relationship":[organization,],
 "table":"jianzhuwu_001"
 },
 "organization":{
 "name":"组织(企业)",
 "relationship":[employee,],
 "table":"shangshizhuti_001"
 },
 "employee":{
 "name":"企业员工",
 "relationship":[housing,],
 "table":"st_shebaorenkou_001"
 },
 "housing":{
 "name":"住房",
 "relationship":[roomate],
 "table":"st_fangwurenkou_001"
 },
 "roomate":{
 "name":"室友",
 "relationship":[],
 "table":"st_fangwurenkou_001"
 }
 }
 TODO 从配置文件中读取关联关系
 */
public class AssociationRelationship {

    private static  final Logger logger = LoggerFactory.getLogger(AssociationRelationship.class);

    @JsonInclude
    protected LinkedHashMap<String,RootNode> associationRealtionsipMap;/*关系映射*/

    public AssociationRelationship(){

    }

    /**
     * 根据节点找到关系集合，每一个关系均为：关系四元组[源节点表名，源节点字段名，关联节点表名，关联节点字段名]
     * @param nodeType
     * @return
     */
    public HashMap<String,List<String>> queryAccosiation4s(String nodeType){
        HashMap<String,List<String>> accosiation4s = new HashMap<String, List<String>>();
        RootNode rootNode = queryNode(nodeType);
        List<Relationship> relationships = rootNode.getRelationships();
        for (Relationship relationship: relationships){
            List<String> accosiation4 = new ArrayList<String>();
            /*添加源节点表名*/
            accosiation4.add(rootNode.getTable());
            /*添加源节点字段名*/
            accosiation4.add(relationship.getRootCol());
            /*添加关联节点表名*/
            accosiation4.add(queryNode(relationship.getNode()).getTable());
            /*添加关联接点的字段名*/
            accosiation4.add(relationship.getRelationCol());

            accosiation4s.put(relationship.getNode(),accosiation4);
        }
        return accosiation4s;
    }

    /**
     * 根据rootNode的类型，获取rootNode源节点
     * @param nodeType
     * @return
     */
    public RootNode queryNode(String nodeType) {
        return associationRealtionsipMap.get(nodeType);
    }

    public LinkedHashMap<String, RootNode> getAssociationRealtionsipMap() {
        return associationRealtionsipMap;
    }

    public void setAssociationRealtionsipMap(LinkedHashMap<String, RootNode> associationRealtionsipMap) {
        this.associationRealtionsipMap = associationRealtionsipMap;
    }
}
