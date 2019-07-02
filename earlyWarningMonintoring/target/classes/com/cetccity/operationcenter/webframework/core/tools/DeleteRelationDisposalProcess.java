package com.cetccity.operationcenter.webframework.core.tools;

import java.io.IOException;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHits2Model;
import com.cetccity.operationcenter.webframework.web.util.EsToEntityUtil;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DeleteRelationDisposalProcess {

    public static void deleteByUuid(String emergency_id){
        String urlStr = "http://"+ CommonStaticInstance.elasticsearchIp+":"+ CommonStaticInstance.elasticsearchPort+"/tb_fire_emgc_disposal_process@31project_april/_doc/_search";
        String urlStr2 = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/tb_fire_emgc_disposal_process@31project_april/_doc/";

        String json = "{  \n" +
                "  \"_source\":[\"emergency_id\"],\n" +
                "  \"query\" : {  \n" +
                "    \"match_phrase\" : {  \n" +
                "       \"emergency_id\" :\""+emergency_id+"\"  \n" +
                "    }  \n" +
                "  }  \n" +
                "}  ";
        String source = UrlApiToSource.doJsonPost(urlStr,json);
        for (ElasticHits2Model elasticHits2Model: EsToEntityUtil.esToEntity(source)) {
            String id = elasticHits2Model.get_id();
            try {
                System.out.println("delete_url----"+urlStr2+id);
                UrlApiToSource.doJsonDelete(urlStr2+id);
            } catch (IOException e) {
            	log.error(e.toString());
            }
        }
    }
}
