package com.cetccity.operationcenter.webframework.hiddendanger.controller.map;

import com.cetccity.operationcenter.webframework.hiddendanger.api.model.PageInfo_LoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.api.map.PageInfoApi;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.PageInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
public class PageInfoController implements PageInfoApi {

    @Autowired
    PageInfoUtil pageInfoUtil;

    public PageInfo_LoadMap findPageInfoByDb(@PathVariable("tableNameUrl") String tableNameUrl, String pageNum, String pageSize, String street) throws IOException {
        return pageInfoUtil.getPageInfoDbUtil(tableNameUrl,pageNum,pageSize,street);
    }

    public PageInfo_LoadMap findPageInfoByDbOfURBAN_RISK(@PathVariable("tableNameUrl") String tableNameUrl,String pageNum, String pageSize,String street) throws IOException {
        return pageInfoUtil.getPageInfoDbUtil(tableNameUrl,pageNum,pageSize,street);
    }

}
