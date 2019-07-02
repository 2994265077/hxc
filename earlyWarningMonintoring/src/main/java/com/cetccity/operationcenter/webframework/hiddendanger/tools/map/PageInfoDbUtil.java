package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.PageInfo_LoadMap;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class PageInfoDbUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public PageInfo_LoadMap getPageInfoDb(String tableName, Integer page, Integer size,String sql_column,String column_comment,String street) {
        String sql_total = "select ROWID from \""+tableName+"\" where REGION_CODE = '440304' and STREET_CODE = '"+street+"'";
        String sql_list = "SELECT * FROM (SELECT rowres.*, ROWNUM RN FROM (select "+sql_column+" from \""+tableName+"\" where STREET_CODE = '"+street+"' and REGION_CODE = '440304' order by ROWID)rowres WHERE ROWNUM <="+size+"+"+page+")WHERE RN >"+page+"";
        List<LinkedHashMap> list_total = oracleOperateService.querySql(sql_total);
        List<LinkedHashMap> list_sql_return = oracleOperateService.querySql(sql_list);
        PageInfo_LoadMap pageInfo_LoadMap = getPageInfo_LoadMapUtil(page,size,list_total.size(),sql_column,column_comment,list_sql_return);
        return pageInfo_LoadMap;
    }

    //重载
    public PageInfo_LoadMap getPageInfoDb(String tableName, Integer page, Integer size,String sql_column,String column_comment) {
        String sql_total = "select ROWID from \""+tableName+"\" where REGION_CODE = '440304'";
        String sql_list = "SELECT * FROM (SELECT rowres.*, ROWNUM RN FROM (select "+sql_column+" from \""+tableName+"\" where REGION_CODE = '440304' order by ROWID)rowres WHERE ROWNUM <="+size+"+"+page+")WHERE RN >"+page+"";
        List<LinkedHashMap> list_total = oracleOperateService.querySql(sql_total);
        List<LinkedHashMap> list_sql_return = oracleOperateService.querySql(sql_list);
        PageInfo_LoadMap pageInfo_LoadMap = getPageInfo_LoadMapUtil(page,size,list_total.size(),sql_column,column_comment,list_sql_return);
        return pageInfo_LoadMap;
    }

    public PageInfo_LoadMap getPageInfoDbHasType(String tableName,Integer page,Integer size,String sql_column,String column_comment,String col,String columnEntity,String street){
        String sql_total = "select ROWID from \""+tableName+"\" where \""+col+"\"="+columnEntity+" and REGION_CODE = '440304' and STREET_CODE = '"+street+"'";
        String sql_list = "SELECT * FROM (SELECT rowres.*, ROWNUM RN FROM (select "+sql_column+" from \""+tableName+"\" where \""+col+"\"="+columnEntity+" and STREET_CODE = '"+street+"'  and REGION_CODE = '440304' order by ROWID)rowres WHERE ROWNUM <="+size+"+"+page+")WHERE RN >"+page+"";
        List<LinkedHashMap> list_total = oracleOperateService.querySql(sql_total);
        List<LinkedHashMap> list_sql_return = oracleOperateService.querySql(sql_list);
        PageInfo_LoadMap pageInfo_LoadMap = getPageInfo_LoadMapUtil(page,size,list_total.size(),sql_column,column_comment,list_sql_return);
        return pageInfo_LoadMap;
    }

    //重载
    public PageInfo_LoadMap getPageInfoDbHasType(String tableName,Integer page,Integer size,String sql_column,String column_comment,String col,String columnEntity){
        String sql_total = "select ROWID from \""+tableName+"\" where \""+col+"\"="+columnEntity+" and REGION_CODE = '440304'";
        String sql_list = "SELECT * FROM (SELECT rowres.*, ROWNUM RN FROM (select "+sql_column+" from \""+tableName+"\" where \""+col+"\"="+columnEntity+" and REGION_CODE = '440304' order by ROWID)rowres WHERE ROWNUM <="+size+"+"+page+")WHERE RN >"+page+"";
        List<LinkedHashMap> list_total = oracleOperateService.querySql(sql_total);
        List<LinkedHashMap> list_sql_return = oracleOperateService.querySql(sql_list);
        PageInfo_LoadMap pageInfo_LoadMap = getPageInfo_LoadMapUtil(page,size,list_total.size(),sql_column,column_comment,list_sql_return);
        return pageInfo_LoadMap;
    }

    PageInfo_LoadMap getPageInfo_LoadMapUtil(Integer page, Integer size,int total,String sql_column,String column_comment,List<LinkedHashMap> list_sql_return){
        PageInfo_LoadMap pageInfo_LoadMap = new PageInfo_LoadMap();
        MyPageInfoModel<List<LinkedHashMap>> pageInfo = new MyPageInfoModel<List<LinkedHashMap>>();
        List<LinkedHashMap> pagelist = new ArrayList<LinkedHashMap>();
        String column[] = sql_column.replace("\"","").split(",");
        String comment[] = column_comment.split(",");
        List list = new ArrayList();
        for (LinkedHashMap<String, String> map:list_sql_return) {
            LinkedHashMap<String, String> mapNew = new LinkedHashMap<String, String>();
            list.add(map.get("ID"));
            for (int i = 1;i<column.length;i++) {
                mapNew.put(comment[i], LoadMyUtil.checkMyVariable(map.get(column[i])));
            }
            pagelist.add(mapNew);
        }
        pageInfo.setTotal(total);
        pageInfo.setPageNum(page/size+1);
        pageInfo.setList(pagelist);
        pageInfo.setPages(total%size==0?total/size:total/size+1);
        pageInfo.setPageSize(size);
        pageInfo_LoadMap.setID(list);
        pageInfo_LoadMap.setPageInfo(pageInfo);
        return pageInfo_LoadMap;
    }
}
