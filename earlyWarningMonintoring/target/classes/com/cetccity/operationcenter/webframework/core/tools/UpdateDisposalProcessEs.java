package com.cetccity.operationcenter.webframework.core.tools;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.DisposalProcess;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UpdateDisposalProcessEs {
    public static void updateDisposalProcess(DisposalProcess disposalProcess) {
        String urlPath = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/tb_fire_emgc_disposal_process@31project_april/_doc/"+disposalProcess.getId()+"/_update ";
        Map map = new HashMap();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(disposalProcess.getCreateTime()!=null){ map.put("create_time",time.format(disposalProcess.getCreateTime()));}
        if(disposalProcess.getUuid()!=null){ map.put("uuid",disposalProcess.getUuid());}
        if(disposalProcess.getDisposalTime()!=null){ map.put("disposal_time",disposalProcess.getDisposalTime());}
        if(disposalProcess.getEmergencyId()!=null){ map.put("emergency_id",disposalProcess.getEmergencyId());}
        if(disposalProcess.getUpdateStatus()!=null){ map.put("update_status",disposalProcess.getUpdateStatus());}
        if(disposalProcess.getUpdateTime()!=null){ map.put("update_time",time.format(disposalProcess.getUpdateTime()));}
        if(disposalProcess.getDisposalRecord()!=null){ map.put("disposal_record",disposalProcess.getDisposalRecord());}
        if(disposalProcess.getDisposalStatus()!=null){ map.put("disposal_status",disposalProcess.getDisposalStatus());}
        if(disposalProcess.getReserved3()!=null){ map.put("reserved3",disposalProcess.getReserved3());}
        if(disposalProcess.getReserved2()!=null){ map.put("reserved2",disposalProcess.getReserved2());}
        if(disposalProcess.getReserved1()!=null){ map.put("reserved1",disposalProcess.getReserved1());}
        if(disposalProcess.getId()!=null){ map.put("id",disposalProcess.getId());}
        if(disposalProcess.getReserved5()!=null){ map.put("reserved5",disposalProcess.getReserved5());}
        if(disposalProcess.getReserved4()!=null){ map.put("reserved4",disposalProcess.getReserved4());}

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
        UrlApiToSource.doJsonPost(urlPath,json);
    }
}
