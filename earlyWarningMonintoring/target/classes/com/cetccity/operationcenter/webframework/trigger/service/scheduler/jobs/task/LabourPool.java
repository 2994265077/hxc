package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.task;

import com.cetccity.operationcenter.webframework.core.frame.model.CetcCloudResult;
import com.cetccity.operationcenter.webframework.urbansign.api.model.Tbl_pojo_futianApi;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.scheduled
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:52 2019-03-08
 * Updater:     heliangming
 * Update_Date：17:52 2019-03-08
 * 更新描述:    heliangming 补充
 **/
@Slf4j
@Component
public class LabourPool {

    public static List<Tbl_pojo_futianApi> list;
    //@Scheduled(cron="0 0/2 * * * ? ") //2分钟执行一次
    //@Scheduled(cron = "0 0 22 * * ? ")
    public void getLabourPool(){
        log.info("-----tbl_pojo_futian_list----");
        List<Tbl_pojo_futianApi> tbl_pojo_futian_list = new ArrayList<>();
        String path = "http://10.190.113.44:8080/cetc_futian/findFutain";
        String json = "{\n" +
                " \"api_key\":\"cetc\",\n" +
                " \"secret_key\":\"cetc2017092\"\n" +
                "}";
        String source = UrlApiToSource.doJsonPost(path,json);
        CetcCloudResult<List<Map<String,String>>> cetcCloudResult = JsonUtils.jsonToPojo(source,CetcCloudResult.class);
        List<Map<String,String>> list_map;
        if(cetcCloudResult.getCode()==200){
            list_map = cetcCloudResult.getData();
            for (Map map:list_map) {
                Tbl_pojo_futianApi tbl_pojo_futianApi = JsonUtils.mapToBean(Tbl_pojo_futianApi.class,map);
                tbl_pojo_futian_list.add(tbl_pojo_futianApi);
            }
        }
        list = tbl_pojo_futian_list;
    }

}
