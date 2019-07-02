package com.cetccity.operationcenter.webframework.rundisplay.controller.map;

import com.cetccity.operationcenter.webframework.hiddendanger.controller.map.PageInfoController;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.PageInfoUtil;
import com.cetccity.operationcenter.webframework.rundisplay.api.map.CityComponentPageInfoApi;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.PageInfo_LoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.PageInfoDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class CityComponentPageInfoController implements CityComponentPageInfoApi {

    @Autowired
    PageInfoController pageInfoController;

    @Autowired
    PageInfoUtil pageInfoUtil;

    public PageInfo_LoadMap findVideoPageInfoByDb(@PathVariable("tableNameUrl") String tableNameUrl, String pageNum, String pageSize, String area) throws IOException {
        return pageInfoUtil.getPageInfoDbUtil(tableNameUrl, pageNum, pageSize, area);
    }

    public PageInfo_LoadMap findPageInfoByDb(@PathVariable("tableNameUrl") String tableNameUrl, String pageNum, String pageSize, String area) throws IOException {
        return pageInfoController.findPageInfoByDb(tableNameUrl,pageNum,pageSize,area);
    }
}
