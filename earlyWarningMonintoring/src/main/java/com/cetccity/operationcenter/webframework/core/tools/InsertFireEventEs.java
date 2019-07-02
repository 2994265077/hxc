package com.cetccity.operationcenter.webframework.core.tools;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.FireEvent;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/*@service("insertFireEventEs")*/
@Slf4j
public class InsertFireEventEs {
    public static String insertObject(FireEvent fireEvent){
        String url = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/tb_fire_emergencies@31project_april/_doc/"+fireEvent.getId()+"";
        Map map = new LinkedHashMap();
        Map mapLocation = new HashMap();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        mapLocation.put("lon",fireEvent.getJd84());
        mapLocation.put("lat",fireEvent.getWd84());
        map.put("emergency_time",fireEvent.getEmergencyTime());
        map.put("on_site_liaison",fireEvent.getOnSiteLiaison());
        map.put("create_time",time.format(fireEvent.getCreateTime()));
        map.put("jd84",fireEvent.getJd84());
        map.put("emergency_address",fireEvent.getEmergencyAddress());
        map.put("uuid",fireEvent.getUuid());
        map.put("wd",fireEvent.getWd());
        map.put("emergency_desc",fireEvent.getEmergencyDesc());
        map.put("disposal_status",fireEvent.getDisposalStatus());
        map.put("emergency_name",fireEvent.getEmergencyName());
        map.put("update_status",fireEvent.getUpdateStatus());
        map.put("update_time",time.format(fireEvent.getUpdateTime()));
        map.put("reserved3",fireEvent.getReserved3());
        map.put("emergency_category",fireEvent.getEmergencyCategory());
        map.put("reserved2",fireEvent.getReserved2());
        map.put("jd",fireEvent.getJd());
        map.put("reserved1",fireEvent.getReserved1());
        map.put("id",fireEvent.getId());
        map.put("emergency_grade",fireEvent.getEmergencyGrade());
        map.put("wd84",fireEvent.getWd84());
        map.put("location",mapLocation);
        map.put("reporting_unit",fireEvent.getReportingUnit());
        map.put("reserved5",fireEvent.getReserved5());
        map.put("reserved4",fireEvent.getReserved4());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
        	log.error(e.toString());
        }

        /*String json = "{\n" +
                "    \"emergency_time\":\""+fireEvent.getEmergencyTime()+"\",\n" +
                "    \"on_site_liaison\":\""+fireEvent.getOnSiteLiaison()+"\",\n" +
                "    \"create_time\":\""+fireEvent.getCreateTime()+"\",\n" +
                "    \"jd84\":\""+fireEvent.getJd84()+"\",\n" +
                "    \"emergency_address\":\""+fireEvent.getEmergencyAddress()+"\",\n" +
                "    \"uuid\":\""+fireEvent.getUuid()+"\",\n" +
                "    \"wd\":\""+fireEvent.getWd()+"\",\n" +
                "    \"emergency_desc\":\""+fireEvent.getEmergencyDesc()+"\",\n" +
                "    \"emergency_name\":\""+fireEvent.getEmergencyName()+"\",\n" +
                "    \"update_status\":\""+fireEvent.getUpdateStatus()+"\",\n" +
                "    \"update_time\":\""+fireEvent.getUpdateTime()+"\",\n" +
                "    \"reserved3\":\""+fireEvent.getReserved3()+"\",\n" +
                "    \"emergency_category\":\""+fireEvent.getEmergencyCategory()+"\",\n" +
                "    \"reserved2\":\""+fireEvent.getReserved2()+"\",\n" +
                "    \"jd\":\""+fireEvent.getJd()+"\",\n" +
                "    \"reserved1\":\""+fireEvent.getReserved1()+"\",\n" +
                "    \"id\":\""+fireEvent.getId()+"\",\n" +
                "    \"emergency_grade\":\""+fireEvent.getEmergencyGrade()+"\",\n" +
                "    \"wd84\":\""+fireEvent.getWd84()+"\",\n" +
                "    \"reporting_unit\":\""+fireEvent.getReportingUnit()+"\",\n" +
                "    \"reserved5\":\""+fireEvent.getReserved5()+"\",\n" +
                "    \"reserved4\":\""+fireEvent.getReserved4()+"\"\n" +
                "}";*/
        String source = UrlApiToSource.doJsonPost(url,json);
        return source;
    }
}
