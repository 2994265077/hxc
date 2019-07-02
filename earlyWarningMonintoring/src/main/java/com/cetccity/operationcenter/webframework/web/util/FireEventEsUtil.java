package com.cetccity.operationcenter.webframework.web.util;

import java.util.ArrayList;
import java.util.List;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHits2Model;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHitsModel;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticSearchModel;
import com.cetccity.operationcenter.webframework.web.model.incident.FireEventEs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class FireEventEsUtil {
    public static PageInfo<FireEventEs> fireEventEs(Integer page,Integer size,String state) {
        PageInfo<FireEventEs> pageInfo = new PageInfo<FireEventEs>();
        List<FireEventEs> fireEventEslist = new ArrayList<FireEventEs>();

        if (state == null) {
            String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/tb_fire_emergencies@31project_april/_doc/_search";
            String json = "{\n" +
                    "    \"from\":\"" + page + "\",\n" +
                    "    \"size\":\"" + size + "\"\n" +
                    "}";
            String source = UrlApiToSource.doJsonPost(urlStr, json);
            /**
             *json转为实体
             */
            ElasticSearchModel elasticSearchModel = JsonUtils.jsonToBean(source, ElasticSearchModel.class);
            String hits = elasticSearchModel.getHits().toString();
            ElasticHitsModel elasticHitsModel = JsonUtils.jsonToBean(hits, ElasticHitsModel.class);
            Integer total = Integer.valueOf(elasticHitsModel.getTotal());
            String hits2 = elasticHitsModel.getHits().toString();
            /**
             *json转为List
             */
            List<ElasticHits2Model> elasticHits2Modellist = JsonUtils.jsonToList(hits2,ElasticHits2Model.class);
            for (ElasticHits2Model elasticHits2Model : elasticHits2Modellist) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String str = objectMapper.writeValueAsString(elasticHits2Model.get_source());
                    FireEventEs fireEventEs = JsonUtils.jsonToBean(str, FireEventEs.class);
                    fireEventEslist.add(fireEventEs);
                } catch (JsonProcessingException e) {
                    log.error(e.toString());
                }
            }
            pageInfo.setList(fireEventEslist);
            pageInfo.setTotal(total);
            pageInfo.setStartRow(page + 1);
            pageInfo.setPageNum(page / size + 1);
            pageInfo.setPageSize(size);
            pageInfo.setPages(total % size == 0 ? total / size : total / size + 1);
        } else {
                String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/tb_fire_emergencies@31project_april/_doc/_search";
                String json = "{\n" +
                        "  \"from\":\""+page+"\",\n" +
                        "  \"size\":\""+size+"\",\n" +
                        "  \"query\" : {  \n" +
                        "    \"match_phrase\" : {  \n" +
                        "       \"disposal_status\" :\""+state+"\"  \n" +
                        "    }  \n" +
                        "  }  \n" +
                        "}";
                String source = UrlApiToSource.doJsonPost(urlStr, json);
                Integer total = Integer.valueOf(EsToEntityUtil.esToEntity(source).size());
                for (ElasticHits2Model elasticHits2Model:EsToEntityUtil.esToEntity(source)) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        String str = objectMapper.writeValueAsString(elasticHits2Model.get_source());
                        FireEventEs fireEventEs = JsonUtils.jsonToBean(str,FireEventEs.class);
                        fireEventEslist.add(fireEventEs);
                    } catch (JsonProcessingException e) {
                        log.error(e.toString());
                    }
                }
            pageInfo.setList(fireEventEslist);
            pageInfo.setTotal(total);
            pageInfo.setStartRow(page + 1);
            pageInfo.setPageNum(page / size + 1);
            pageInfo.setPageSize(size);
            pageInfo.setPages(total % size == 0 ? total / size : total / size + 1);
                }
        return pageInfo;
    }
}
