package com.cetccity.operationcenter.webframework.web.util.tip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticTipModel;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.JIANZHUWU_001;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;

public class TipUtilOfJIANZHUWU_001 {
	
    private static final Logger logger = LoggerFactory.getLogger(TipUtilOfJIANZHUWU_001.class);

    public static JIANZHUWU_001 loadMapTip(String tableName, String id) {
        JIANZHUWU_001 jIANZHUWU_001;
        String urlStr = "http://"+ CommonStaticInstance.elasticsearchIp+":"+ CommonStaticInstance.elasticsearchPort+"/"+tableName+"@31project_april/_doc/"+id+"";
        String source = null;
        try {
            source = UrlApiToSource.doJsonGet(urlStr);
            /**
             *json转为实体
             */
            ElasticTipModel elasticTipModel = JsonUtils.jsonToBean(source, ElasticTipModel.class);
            String _source = elasticTipModel.get_source().toString();
            jIANZHUWU_001 = JsonUtils.jsonToBean(_source, JIANZHUWU_001.class);
        } catch (Exception e) {
            logger.info("没有数据......");
            jIANZHUWU_001 = null;
        }
        return jIANZHUWU_001;
    }
}
