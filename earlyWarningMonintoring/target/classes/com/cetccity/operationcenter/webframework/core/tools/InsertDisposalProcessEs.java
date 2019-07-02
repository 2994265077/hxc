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
public class InsertDisposalProcessEs {
    public static String insertObject(DisposalProcess disposalProcess) {
        String url = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/tb_fire_emgc_disposal_process@31project_april/_doc/"+disposalProcess.getId()+"";
        Map map = new HashMap();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        map.put("create_time",time.format(disposalProcess.getCreateTime()));
        map.put("uuid",disposalProcess.getUuid());
        map.put("disposal_time",disposalProcess.getDisposalTime());
        map.put("emergency_id",disposalProcess.getEmergencyId());
        map.put("update_status",disposalProcess.getUpdateStatus());
        map.put("update_time",time.format(disposalProcess.getUpdateTime()));
        map.put("disposal_record",disposalProcess.getDisposalRecord());
        map.put("disposal_status",disposalProcess.getDisposalStatus());
        map.put("reserved3",disposalProcess.getReserved3());
        map.put("reserved2",disposalProcess.getReserved2());
        map.put("reserved1",disposalProcess.getReserved1());
        map.put("id",disposalProcess.getId());
        map.put("reserved5",disposalProcess.getReserved5());
        map.put("reserved4",disposalProcess.getReserved4());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
        	log.error(e.toString());
        }
        String source = UrlApiToSource.doJsonPost(url,json);
        return source;

    }
}
