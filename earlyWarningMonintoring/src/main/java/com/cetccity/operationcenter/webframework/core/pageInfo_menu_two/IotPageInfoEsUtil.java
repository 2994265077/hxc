/*
package com.cetccity.operationcenter.webframework.core.pageInfo_menu_two;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

*/
/**
 * Description--hlm
 * Created by xuhui on 2018/7/13.
 *//*

@Component
public class IotPageInfoEsUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public MyPageInfoModel<List<LinkedHashMap>> getPageInfoIot(Integer page,Integer size,String sql_column,String event_code,String data_pre){
        MyPageInfoModel<List<LinkedHashMap>> pageInfo = new MyPageInfoModel<List<LinkedHashMap>>();
        List<LinkedHashMap> pagelist = new ArrayList<LinkedHashMap>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        String now = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        String year = now.split("-")[0];
        String month = now.split("-")[1];
        String day = now.split("-")[2];
        now=year+"-"+month+"-"+day+" 00:00:00";
        String sql_total = "select \"id\" from \"iot_event\" where 1=1 and \"event_code\"='"+event_code+"' and \"produce_time\">=to_date('"+data_pre+"','yyyy-mm-dd hh24:mi:ss') and \"produce_time\"<=to_date('"+now+"','yyyy-mm-dd hh24:mi:ss')";
        String sql_iot_device = "SELECT * FROM (SELECT rowres.*, ROWNUM RN FROM (select "+sql_column+" from \"iot_event\" where 1=1 and \"event_code\"='"+event_code+"' and \"produce_time\">=to_date('"+data_pre+"','yyyy-mm-dd hh24:mi:ss') and \"produce_time\"<=to_date('"+now+"','yyyy-mm-dd hh24:mi:ss') order by \"produce_time\" desc)rowres WHERE ROWNUM <="+size+"+"+page+")WHERE RN >"+page+"";
        List<LinkedHashMap> list_total = oracleOperateService.querySql(sql_total);
        List<LinkedHashMap> list_sql_iot_device = oracleOperateService.querySql(sql_iot_device);
        int total = list_total.size();
        for (LinkedHashMap<String, String> map:list_sql_iot_device) {
            LinkedHashMap<String, String> mapNew = new  LinkedHashMap<String, String>();
            mapNew.put("时间",map.get("produce_time"));
            mapNew.put("设备编码",map.get("device_code"));
            mapNew.put("监测名称","无");
            mapNew.put("设备类型","无");
            mapNew.put("事件名称",map.get("event_name"));
            mapNew.put("事件类型","无");
            mapNew.put("事件等级",map.get("event_level"));
            mapNew.put("状态","无");
            pagelist.add(mapNew);
        }
        pageInfo.setTotal(total);
        pageInfo.setPageNum(page/size+1);
        pageInfo.setList(pagelist);
        pageInfo.setPages(total%size==0?total/size:total/size+1);
        pageInfo.setPageSize(size);
        return pageInfo;
    }
}
*/
