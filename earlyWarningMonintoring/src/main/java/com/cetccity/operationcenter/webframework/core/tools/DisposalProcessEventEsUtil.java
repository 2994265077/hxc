package com.cetccity.operationcenter.webframework.core.tools;

import java.util.ArrayList;
import java.util.List;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.DisposalProcess;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHits2Model;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHitsModel;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticSearchModel;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DisposalProcessEventEsUtil {
    public static List<DisposalProcess> findDisposalProcessEventEs(String emergency_id){
        List<DisposalProcess> disposalProcesslist = new ArrayList<DisposalProcess>();
        String urlStr = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/tb_fire_emgc_disposal_process@31project_april/_doc/_search";
        String json = "{\n" +
                "  \"_source\":[\"id\",\"disposal_record\",\"disposal_time\",\"disposal_status\"],\n" +
                "    \"query\" : {\n" +
                "        \"constant_score\" : {\n" +
                "            \"filter\" : {\n" +
                "                \"match_phrase\" : {\n" +
                "                    \"emergency_id\" : \""+emergency_id+"\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String source = UrlApiToSource.doJsonPost(urlStr,json);
        /**
         *json转为实体
         */
        ElasticSearchModel elasticSearchModel = JsonUtils.jsonToBean(source,ElasticSearchModel.class);
        String hits = elasticSearchModel.getHits().toString();
        ElasticHitsModel elasticHitsModel = JsonUtils.jsonToBean(hits,ElasticHitsModel.class);
        String hits2 = elasticHitsModel.getHits().toString();
        /**
         *json转为List
         */
        List<ElasticHits2Model> elasticHits2Modellist = JsonUtils.jsonToList(hits2,ElasticHits2Model.class);
        for (ElasticHits2Model elasticHits2Model:elasticHits2Modellist) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String str = objectMapper.writeValueAsString(elasticHits2Model.get_source());
                DisposalProcess disposalProcess = JsonUtils.jsonToBean(str,DisposalProcess.class);
                disposalProcesslist.add(disposalProcess);
            } catch (JsonProcessingException e) {
            	log.error(e.toString());
            }
        }
        return disposalProcesslist;
    }


    //查看已结束的消防事件
    public static List<String> findFinishedDisposalProcessEventEs() {
        List<String> list = new ArrayList<String>();
        String urlStr = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/tb_fire_emgc_disposal_process@31project_april/_doc/_search";
        String json = "{\n" +
                "\t\"from\":\"0\",\n" +
                "\t\"size\":\"9999\",\n" +
                "    \"_source\":[\"emergency_id\"],\n" +
                "    \"query\" : {\n" +
                "        \"constant_score\" : {\n" +
                "            \"filter\" : {\n" +
                "                \"match_phrase\" : {\n" +
                "                    \"disposal_status\" : \"1\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String source = UrlApiToSource.doJsonPost(urlStr,json);
        /**
         *json转为实体
         */
        ElasticSearchModel elasticSearchModel = JsonUtils.jsonToBean(source,ElasticSearchModel.class);
        String hits = elasticSearchModel.getHits().toString();
        ElasticHitsModel elasticHitsModel = JsonUtils.jsonToBean(hits,ElasticHitsModel.class);
        String hits2 = elasticHitsModel.getHits().toString();
        /**
         *json转为List
         */
        List<ElasticHits2Model> elasticHits2Modellist = JsonUtils.jsonToList(hits2,ElasticHits2Model.class);
        for (ElasticHits2Model elasticHits2Model:elasticHits2Modellist) {

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String str = objectMapper.writeValueAsString(elasticHits2Model.get_source());
                DisposalProcess disposalProcess = JsonUtils.jsonToBean(str,DisposalProcess.class);
                list.add(disposalProcess.getEmergencyId());
            } catch (JsonProcessingException e) {
            	log.error(e.toString());
            }
        }
        return list;
    }
}
