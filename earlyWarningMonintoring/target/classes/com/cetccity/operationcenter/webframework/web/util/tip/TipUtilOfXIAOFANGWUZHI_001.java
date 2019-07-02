package com.cetccity.operationcenter.webframework.web.util.tip;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticTipModel;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.XIAOFANGWUZHI_001;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TipUtilOfXIAOFANGWUZHI_001 {

    private static final Logger logger = LoggerFactory.getLogger(TipUtilOfXIAOFANGWUZHI_001.class);

    public static XIAOFANGWUZHI_001 loadMapTip(String tableName, String id) {
        XIAOFANGWUZHI_001 xIAOFANGWUZHI_001;
        String urlStr = "http://"+ CommonStaticInstance.elasticsearchIp +":"+ CommonStaticInstance.elasticsearchPort+"/"+tableName+"@31project_april/_doc/"+id+"";
        String source = null;
        try {
            source = UrlApiToSource.doJsonGet(urlStr);
            /**
             *json转为实体
             */
            ElasticTipModel elasticTipModel = JsonUtils.jsonToBean(source, ElasticTipModel.class);
            String _source = elasticTipModel.get_source().toString();
            xIAOFANGWUZHI_001 = JsonUtils.jsonToBean(_source, XIAOFANGWUZHI_001.class);
        } catch (Exception e) {
            logger.info("没有数据......");
            xIAOFANGWUZHI_001 = null;
        }
        return xIAOFANGWUZHI_001;
    }
}
