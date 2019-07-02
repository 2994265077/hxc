package com.cetccity.operationcenter.webframework.environment.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.environment.api.model.PostModel;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_PATROLMapper;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_PATROL;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverDetailService;
import com.cetccity.operationcenter.webframework.environment.util.HttpClientUtil2;
import com.cetccity.operationcenter.webframework.environment.util.SignParamService;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:33 2019-05-27
 * Updater:     heliangming
 * Update_Date：17:33 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@Service
@Slf4j
public class CleanRiverDetailServiceImpl implements CleanRiverDetailService {

    @Autowired
    QJHH_PATROLMapper qJHH_PATROLMapper;

    public PageInfo findPatrolRecord(String id, Integer pageNum, Integer pageSize){

        /*String url = "http://10.190.113.92:8280/api";
        String content = "{\"id\":\"ff8080816ab0e24b016ab16abd890002\"}";
        String appkey ="UDlkgJuMh8L52kDG";
        String secretKey = "4L$yX0M%Il%PQ7ZcAZo0RztQpoAcS3sD";
        String code = "T04";
        JSONObject t04 = SignParamService.getSignedParam(appkey, content, code, secretKey);
        PostModel postModel = new PostModel();
        postModel.setBody(null);
        JSONObject header = new JSONObject();
        header.put("Content-Type","application/json");
        postModel.setHeader(header);
        postModel.setParams(t04);
        postModel.setUrl(url);
        JSONObject result = HttpClientUtil2.doPostWithPostModel(postModel);
        Map maps = (Map) JSON.parse((String) result.get("data"));
        maps.get("patrol");*/

        PageHelper.startPage(pageNum, pageSize);
        QJHH_PATROL qJHH_PATROL = new QJHH_PATROL();
        qJHH_PATROL.setFACILITY_ID(id);
        List<QJHH_PATROL> qJHH_PATROL_list = qJHH_PATROLMapper.getList(qJHH_PATROL);
        qJHH_PATROL_list.stream().forEach(u->{
            String phone = StringUtils.join(JSON.parseArray(u.getPHOTO()));
            phone = phone.substring(1,phone.length()-1);
            phone = phone.replaceAll("\"","");
            u.setPHOTO(phone);
        });
        PageInfo<QJHH_PATROL> pageInfo = new PageInfo(qJHH_PATROL_list);
        return pageInfo;
    }
}
