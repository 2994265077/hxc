package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.BLK_SANXIAO_PLACEDetailsApi;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoTip;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BLK_SANXIAO_PLACEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BLK_SANXIAO_PLACEDetailsController implements BLK_SANXIAO_PLACEDetailsApi {

    @Autowired
    BLK_SANXIAO_PLACEService bLK_SANXIAO_PLACEService;

    public SanXiaoTip findBLK_SANXIAO_PLACETipOne(String id) throws Exception{
        SanXiaoTip sanXiaoTip = bLK_SANXIAO_PLACEService.findBLK_SANXIAO_PLACETip(id);
        return sanXiaoTip;
    }

    public SanXiaoTip findBLK_SANXIAO_PLACETipTwo(String id) throws Exception{
        SanXiaoTip sanXiaoTip = bLK_SANXIAO_PLACEService.findBLK_SANXIAO_PLACETip(id);
        return sanXiaoTip;
    }

    public SanXiaoTip findBLK_SANXIAO_PLACETipThree(String id) throws Exception{
        SanXiaoTip sanXiaoTip = bLK_SANXIAO_PLACEService.findBLK_SANXIAO_PLACETip(id);
        return sanXiaoTip;
    }

    public NameDataModel findBLK_SANXIAO_PLACEDetails(String id) throws Exception{
        NameDataModel nameDataModel = bLK_SANXIAO_PLACEService.findBLK_SANXIAO_PLACEDetails(id);
        return nameDataModel;
    }

    public NameDataModel findBLK_SANXIAO_PLACEScore(String id) throws Exception{
        NameDataModel nameDataModel = bLK_SANXIAO_PLACEService.findBLK_SANXIAO_PLACEScore(id);
        return nameDataModel;
    }
}
