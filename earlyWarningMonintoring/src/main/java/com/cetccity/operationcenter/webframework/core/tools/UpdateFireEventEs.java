package com.cetccity.operationcenter.webframework.core.tools;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.FireEvent;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UpdateFireEventEs {
    public static String updateFireEvent(FireEvent fireEvent){
        String urlPath = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/tb_fire_emergencies@31project_april/_doc/"+fireEvent.getId()+"/_update ";
        Map map = new HashMap();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(fireEvent.getEmergencyTime()!=null) { map.put("emergency_time", fireEvent.getEmergencyTime()); }
        if(fireEvent.getOnSiteLiaison()!=null){map.put("on_site_liaison",fireEvent.getOnSiteLiaison());}
        if(fireEvent.getCreateTime()!=null){ map.put("create_time",time.format(fireEvent.getCreateTime()));}
        if(fireEvent.getJd84()!=null){ map.put("jd84",fireEvent.getJd84());}
        if(fireEvent.getEmergencyAddress()!=null){ map.put("emergency_address",fireEvent.getEmergencyAddress());}
        if(fireEvent.getUuid()!=null){ map.put("uuid",fireEvent.getUuid());}
        if(fireEvent.getWd()!=null){ map.put("wd",fireEvent.getWd());}
        if(fireEvent.getEmergencyDesc()!=null){ map.put("emergency_desc",fireEvent.getEmergencyDesc());}
        if(fireEvent.getDisposalStatus()!=null){ map.put("disposal_status",fireEvent.getDisposalStatus());}
        if(fireEvent.getEmergencyName()!=null){ map.put("emergency_name",fireEvent.getEmergencyName());}
        if(fireEvent.getUpdateStatus()!=null){ map.put("update_status",fireEvent.getUpdateStatus());}
        if(fireEvent.getUpdateTime()!=null){ map.put("update_time",time.format(fireEvent.getUpdateTime()));}
        if(fireEvent.getReserved3()!=null){ map.put("reserved3",fireEvent.getReserved3());}
        if(fireEvent.getEmergencyCategory()!=null){ map.put("emergency_category",fireEvent.getEmergencyCategory());}
        if(fireEvent.getReserved2()!=null){ map.put("reserved2",fireEvent.getReserved2());}
        if(fireEvent.getJd()!=null){ map.put("jd",fireEvent.getJd());}
        if(fireEvent.getReserved1()!=null){ map.put("reserved1",fireEvent.getReserved1());}
        if(fireEvent.getId()!=null){ map.put("id",fireEvent.getId());}
        if(fireEvent.getEmergencyGrade()!=null){ map.put("emergency_grade",fireEvent.getEmergencyGrade());}
        if(fireEvent.getWd84()!=null){ map.put("wd84",fireEvent.getWd84());}
        if(fireEvent.getReportingUnit()!=null){ map.put("reporting_unit",fireEvent.getReportingUnit());}
        if(fireEvent.getReserved5()!=null){ map.put("reserved5",fireEvent.getReserved5());}
        if(fireEvent.getReserved4()!=null){ map.put("reserved4",fireEvent.getReserved4());}

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMap = null;
        try {
            jsonMap = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
        	log.error(e.toString());
        }

        String json = "{  \n" +
                "  \"doc\":" +
                jsonMap +
                " " +
                "}  ";
        String source = UrlApiToSource.doJsonPost(urlPath,json);
        return source;
    }
}
