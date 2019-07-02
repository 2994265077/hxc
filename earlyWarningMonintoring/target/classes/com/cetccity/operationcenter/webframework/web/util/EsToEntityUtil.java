package com.cetccity.operationcenter.webframework.web.util;

import com.cetccity.operationcenter.webframework.web.model.incident.*;
import java.util.List;

public class EsToEntityUtil {
    public static List<ElasticHits2Model> esToEntity(String source){
        /**
        *json转为实体
        */
        ElasticSearchModel elasticSearchModel = JsonUtils.jsonToBean(source, ElasticSearchModel.class);
        String hits = elasticSearchModel.getHits().toString();
        ElasticHitsModel elasticHitsModel = JsonUtils.jsonToBean(hits, ElasticHitsModel.class);
        String hits2 = elasticHitsModel.getHits().toString();
        /**
         *json转为List
         */
        List<ElasticHits2Model> elasticHits2Modellist = JsonUtils.jsonToList(hits2,ElasticHits2Model.class);

        return elasticHits2Modellist;
    }
}
