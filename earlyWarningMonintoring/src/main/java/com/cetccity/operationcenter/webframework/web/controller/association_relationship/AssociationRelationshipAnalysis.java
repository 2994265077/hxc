package com.cetccity.operationcenter.webframework.web.controller.association_relationship;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.web.controller.association_relationship.model.AssociationRelationship;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/association_relationship_analysis")
@Api(tags = "建筑物关联分析")
public class AssociationRelationshipAnalysis{

    private static final Logger logger = LoggerFactory.getLogger(AssociationRelationshipAnalysis.class);
    static private String associationRelationshipJson="{\"associationRealtionsipMap\":{\"responsible_person\":{\"name\":\"责任人\",\"table\":\"jianzhuwu_001\",\"basic_cols\":[\"yjlxr\",\"lxfs\",\"jzmc\"],\"relationships\":[]},\"building\":{\"name\":\"建筑\",\"table\":\"jianzhuwu_001\",\"basic_cols\":[\"jzmc\",\"jzdz\",\"jzqk\"],\"relationships\":[{\"node\":\"organization\",\"rootCol\":\"jzdz\",\"rootColLable\":\"建筑地址\",\"relationCol\":\"jycs\",\"relationColLable\":\"经营场所\"},{\"node\":\"hydrant\",\"rootCol\":\"jzdz\",\"rootColLable\":\"建筑地址\",\"relationCol\":\"szdz\",\"relationColLable\":\"所在地址\"},{\"node\":\"hidden_events\",\"rootCol\":\"jzdz\",\"rootColLable\":\"建筑地址\",\"relationCol\":\"address\",\"relationColLable\":\"隐患部位（地址）\"},{\"node\":\"responsible_person\",\"rootCol\":\"id\",\"rootColLable\":\"主键编号\",\"relationCol\":\"id\",\"relationColLable\":\"主键编号\"}]},\"hydrant\":{\"name\":\"消火栓\",\"table\":\"xiaohuoshuan_001\",\"basic_cols\":[\"xfsmc\",\"szdz\",\"gsyl\",\"gdzj\"],\"relationships\":[]},\"organization\":{\"name\":\"组织\",\"table\":\"shangshizhuti_001\",\"basic_cols\":[\"qymc\",\"lsqymc\",\"fddbr\"],\"relationships\":[{\"node\":\"employee\",\"rootCol\":\"zzjgdm\",\"rootColLable\":\"组织机构代码\",\"relationCol\":\"unit_org_code\",\"relationColLable\":\"组织机构代码\"}]},\"employee\":{\"name\":\"企业员工\",\"table\":\"shebaorenkou_001\",\"basic_cols\":[\"name\",\"birthday\",\"politic_status_code\",\"mobile_phone\"],\"relationships\":[{\"node\":\"housing\",\"rootCol\":\"id_no\",\"rootColLable\":\"身份证号码\",\"relationCol\":\"cardno\",\"relationColLable\":\"身份证号码\"}]},\"housing\":{\"name\":\"住房\",\"table\":\"fangwurenkou_001\",\"basic_cols\":[\"homeaddress\",\"registrar\",\"politicsid\"],\"relationships\":[{\"node\":\"roomate\",\"rootCol\":\"housecode\",\"rootColLable\":\"房屋编码\",\"relationCol\":\"housecode\",\"relationColLable\":\"房屋编码\"}]},\"roomate\":{\"name\":\"室友\",\"table\":\"fangwurenkou_001\",\"basic_cols\":[\"name\",\"homeaddress\"],\"relationships\":[]},\"hidden_events\":{\"name\":\"隐患事件\",\"table\":\"tb_fire_danger\",\"basic_cols\":[\"danger_type\",\"address\"],\"relationships\":[]}}}";


    AssociationRelationship associationRelationship;/*关联关系类，可以获取关联关系四元组*/

    public AssociationRelationshipAnalysis() {/*将关联关系json传入，初始化关联关系类*/
        try {
            associationRelationship = ESOperate.objectMapper.readValue(associationRelationshipJson,AssociationRelationship.class);
        } catch (IOException e) {
            logger.error("AssociationRelationshipAnalysis jackson error!\n",e);
        }
    }

    @ApiOperation(value = "获取关联关系的配置", notes = "获取关联关系的配置")
    @RequestMapping(value = "/get_association_json",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAssociationRelationshipJson(){
        return associationRelationshipJson;
    }

    /* 属性查询接口*/
    @ApiOperation(value = "属性查询接口", notes = "属性查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "该数据的含义，例如建筑物等，需要是配置文件中的type，type对应表", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "主键id", paramType = "query")
    })
    @RequestMapping(value = "/query_info",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap queryInfo(String type, String id) {
        try {
            String tbName = associationRelationship.queryNode(type).getTable();
            LinkedHashMap result =  ESOperate.queryObjectByField(ESOperate.getIndexName(ESOperate.dbName,tbName), "id", id).get(0);

            ESOperate.removeNoMeanField(result);
            /*处理字段名，替换为中文字段*/
            ESOperate.columnToComment(result,tbName);
            return result;
        } catch (IOException e) {
            logger.error("queryInfo[type=" + type + ",id=" + id + "]", e);
        } catch (IndexOutOfBoundsException e){
            logger.error("Search result is null!", e);
        }
        return null;
    }

    /* 关联关系查询接口,暂不支持翻页参数*/

    /**
     * 查询关联关系
     *
     * @param type 根节点类型
     * @param id 根节点的编号
     * @return
     */
    @ApiOperation(value = "查询关联关系", notes = "查询关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "该数据的含义，例如建筑物等，需要是配置文件中的type，type对应表", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "主键id", paramType = "query")
    })
    @RequestMapping(value = "/query_association",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap queryAssociation(String type, String id)  {
        if (type==null || id==null) return null;
        LinkedHashMap result = new LinkedHashMap();/*最终返回结果*/
        LinkedHashMap data = new LinkedHashMap();/*用于存放数据*/
        LinkedHashMap name = new LinkedHashMap();/*用于存放名称*/
        /*关联关系四元组
        * {
        *   关系类别1：[源表，源字段，目标表，目标字段],[...]
        *   关系类别2：[...],[,,,]
        * }
        * */
        HashMap<String, List<String>> association4s = associationRelationship.queryAccosiation4s(type);
        Set<String> set = association4s.keySet();
        /*每一种类别分别查询数据*/
        for(String associationType: set){
            List<String> list = association4s.get(associationType);
            String rootNodeTable = list.get(0);
            String rootNodeCol = list.get(1);
            String relationTable = list.get(2);
            String relationCol = list.get(3);

            /*查询源节点的值*/
            try {
                List<Object> queryValue =  ESOperate.queryFieldByField(ESOperate.getIndexName(ESOperate.dbName,rootNodeTable),rootNodeCol,"id",id,10);
                String rootValue = (String)queryValue.get(0);
                String scroll_id = (String) queryValue.get(queryValue.size()-1);
                /*查询所有属性*/
                List<LinkedHashMap> relationValues = ESOperate.queryObjectByField(ESOperate.getIndexName(ESOperate.dbName,relationTable),relationCol,rootValue);
                logger.debug(relationValues.toString());
                /*只保留id和名称*/
                /*由于数据库的名称不统一，均为*mc，因为这里进行统一格式化为name*/
                /*由于有多个*mc的字段例如，企业名称和隶属企业名称，这时候采用前面一个为准*/
                String mcKey=null;
                String mcValue=null;
                for (LinkedHashMap map: relationValues){
                    mcKey=null;
                    mcValue=null;
                    Set<String> keySet = map.keySet();
                    Iterator<Map.Entry> iterator = map.entrySet().iterator();

                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    List<String> basic_cols = associationRelationship.queryNode(associationType).getBasic_cols();
                    boolean isFirst = true;
                    linkedHashMap.put("id",map.get("id"));
                    for (String col: basic_cols){
                        if (isFirst){
                            linkedHashMap.put("name",map.get(col));
                            isFirst=false;
                        }else {
                            String tbName = relationTable;
                            String commentStr = ESOperate.dataDictionary(ESOperate.dbName,tbName,col);
                            if (commentStr!=null) {/*找到中文描述，则使用中文字段*/
                                if(!ESOperate.isNull(map.get(col)))
                                    linkedHashMap.put(commentStr, map.get(col));
                                else
                                    linkedHashMap.put(commentStr, ESOperate.NULL_STR);
                            }else{/*否则使用原字段名*/
                                if(!ESOperate.isNull(map.get(col)))
                                    linkedHashMap.put(col, map.get(col));
                                else
                                    linkedHashMap.put(col, ESOperate.NULL_STR);
                            }
                        }
                    }
                    map.clear();
                    map.putAll(linkedHashMap);
                }

                /*获取该类别的中文名*/
                String nameStr = associationRelationship.queryNode(associationType).getName();
                name.put(associationType,nameStr);
                data.put(associationType,relationValues);

                /*添加游标*/
//                result.put(key+"_scroll_id",scroll_id);
            } catch (Exception e) {
                logger.error("queryAssociation error! type="+type+",id="+id+"\n",e);
            }
        }
        result.put("name",name);
        result.put("data",data);
        return result;
    }


    /*TODO 翻页查询,用scroll_id只能往后翻页，不能往前翻页
    * 实现：每次返回给前端都是第一页的scroll_id，而前端只需要scroll_id+当前页即可，不需要传任何参数。
    * 不合理，scroll_id无法显示清楚，会有代价
    * */
    public LinkedHashMap queryPage(String nodeType, String id, String associationType, int currentPage, int pageSize) {
        /*先进行关联查询，然后再翻页*/
//        ESOperate.scrollSearch(scroll_id);
        return null;
    }

    /*TODO 建筑物的关联关系查询：事件、物、组织*/
    public List queryBuildingAssociateRelationship(String id){

        return null;
    }
    /*TODO 组织的关联关系查询：企业员工*/
    public List queryOrganizationAssociateRelationship(){
        return null;
    }
    /*TODO 企业员工的关联关系查询: 住房*/
    public List queryEmpployeeAssociateRelationship(){
        return null;
    }
    /*TODO 住房关联关系查询：住户*/
    public List queryHousingAssociateRelationship(){
        return null;
    }


//    public static void main(String[] args){
//        /*查询id为295的建筑的关联关系*/
//        new AssociationRelationshipAnalysis().queryAssociation("building","295");
//    }

}
