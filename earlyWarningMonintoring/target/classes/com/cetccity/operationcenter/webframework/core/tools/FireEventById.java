package com.cetccity.operationcenter.webframework.core.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.DisposalProcess;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticTipModel;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;

public class FireEventById {

    private static final Logger logger = LoggerFactory.getLogger(FireEventById.class);
    public static String getUuidById(String tableName,String id){

        DisposalProcess disposalProcess;
        String urlStr = "http://"+ CommonStaticInstance.elasticsearchIp+":"+CommonStaticInstance.elasticsearchPort+"/"+tableName+"@31project_april/_doc/"+id+"";
        String source = null;
        try {
            source = UrlApiToSource.doJsonGet(urlStr);
            /**
             *json转为实体
             */
            ElasticTipModel elasticTipModel = JsonUtils.jsonToBean(source, ElasticTipModel.class);

            String _source = elasticTipModel.get_source().toString();
            disposalProcess = JsonUtils.jsonToBean(_source, DisposalProcess.class);
        } catch (Exception e) {
            disposalProcess = null;
            logger.info("没有数据......");
        }
        return disposalProcess.getUuid();
    }
}
