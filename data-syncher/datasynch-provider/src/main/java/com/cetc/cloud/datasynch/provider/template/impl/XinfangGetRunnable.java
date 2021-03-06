package com.cetc.cloud.datasynch.provider.template.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.DsOuterJobModel;
import com.cetc.cloud.datasynch.api.model.XinFangEventModel;
import com.cetc.cloud.datasynch.api.model.XinFangPeopleModel;
import com.cetc.cloud.datasynch.provider.config.SpringContextUtil;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import com.cetc.cloud.datasynch.provider.util.HttpClientUtil2;
import com.cetc.cloud.datasynch.provider.util.JsonExtractor;
import com.cetc.cloud.datasynch.provider.mapper.XinfangEventMapper;
import com.cetc.cloud.datasynch.provider.service.impl.OuterUrlsService;
import com.cetc.cloud.datasynch.provider.util.entity.GetModel;
import com.cetc.cloud.datasynch.provider.util.entity.PostModel;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description：创建定时 执行计算 实例
 * Created by luolinjie on 2018/10/10.
 */
@Slf4j
public class XinfangGetRunnable implements OuterJobRunnableTemplate {

    private OuterUrlsService outerUrlsService;
    private XinfangEventMapper xinfangEventMapper;

    public XinfangGetRunnable() {
        this.outerUrlsService = (OuterUrlsService) SpringContextUtil.getBean("outerUrlsService");
        this.xinfangEventMapper = (XinfangEventMapper) SpringContextUtil.getBean("xinfangEventMapper");
    }


    @Override
    public void run() {
        Thread.currentThread().setName("XINFANG_TODAY");
        try {
            insertXinfangDataToday();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertXinfangDataToday() throws SQLException {
        log.info("Started Scheduled Job:insertXinfangDataToday()");
        //1.获取Http请求信息
        DsOuterJobModel xinfangModel = outerUrlsService.getModelByTableName("WEEKLY_XINFANG_TODAY");
        //2.获取关联token请求信息
        DsOuterJobModel tokenModel = outerUrlsService.getModelByObjectId(xinfangModel.getToken_link_id());
        //3.获取最新token值
        String tokenString = getXinfangTokenStr(tokenModel);
        //在线请求
        if (tokenString != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formatedDate = format.format(new Date());
            JSONObject params = new JSONObject();
            params.put("access_token", tokenString);
            params.put("date", formatedDate);
            JSONObject token = HttpClientUtil2.parseTokenStr2JSONObj(xinfangModel.getHeaders());
            GetModel getModel = new GetModel();
            getModel.setHeader(token);
            getModel.setUrl(xinfangModel.getUrl());
            getModel.setParams(params);
            JSONObject httpQueryRes = HttpClientUtil2.doGetWithGetModel(getModel);
            if (200 == httpQueryRes.getIntValue("code")) {
                String dataString = httpQueryRes.getString("data");
                JSONArray jsonRes = JSON.parseArray(dataString);
                JSONArray jsonRes1 = new JSONArray();
                Set<String> visitCodeSet = xinfangEventMapper.getVisitCodeList();
                Iterator<Object> iterator = jsonRes.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    String VISITNO = next.getString("VISITNO");
                    if (!visitCodeSet.contains(VISITNO)) {
                        jsonRes1.add(next);
                        visitCodeSet.add(VISITNO);
                    }
                }
                insertXinfangJSONData(jsonRes1);
            }
        }
    }

    public static String getXinfangTokenStr(DsOuterJobModel tokenModel) {

        PostModel postModel = new PostModel();
        postModel.setUrl(tokenModel.getUrl());
        postModel.setParams(HttpClientUtil2.getHttpParams(tokenModel.getParams()));
        String body = tokenModel.getBody();
        JSONObject httpParamsBody = HttpClientUtil2.getHttpParams(body);
        postModel.setBody(httpParamsBody);
        JSONObject header = HttpClientUtil2.parseTokenStr2JSONObj(tokenModel.getHeaders());
        postModel.setHeader(header);
        JSONObject jsonObject = HttpClientUtil2.doPostFormWithPostModel(postModel);
        if (jsonObject.getInteger("code") == 200) {
            String tokenJson = jsonObject.getString("data");

            JSONObject tokenJsonObj = JSON.parseObject(tokenJson);
            JsonExtractor.extractTokenStr(tokenJsonObj, "access_token");
            String tokenString = tokenJsonObj.getString("access_token");
            System.out.println("====>> get token:" + tokenString);
            return tokenString;
        } else {
            return null;
        }
    }

    private void insertXinfangJSONData(JSONArray dataArr) {
//        int count = jsonRes.getIntValue("count");
        //获取信访visitCode集合，用于比对重复值
        for (int i = 0; i < dataArr.size(); i++) {
            JSONObject obj = dataArr.getJSONObject(i);
            XinFangEventModel xinFangEventModel = new XinFangEventModel();
            xinFangEventModel.setVisitNo(obj.getString("VisitNo"));
            xinFangEventModel.setVisitTime(obj.getString("VisitTime"));
            xinFangEventModel.setVisitPersonnelNum(obj.getIntValue("VisitPersonnelNum"));
            xinFangEventModel.setContradiction(obj.getString("Contradiction"));
            xinFangEventModel.setMattersName(obj.getString("MattersName"));
            xinFangEventModel.setVisitMattersRemark(obj.getString("VisitMattersRemark"));
            xinFangEventModel.setEventAddress(obj.getString("EventAddress"));
            xinFangEventModel.setPerformanceName(obj.getString("PerformanceName"));
            xinFangEventModel.setDepartmentName(obj.getString("DepartmentName"));
            xinFangEventModel.setVisitPlace(obj.getString("VisitPlace"));
            xinFangEventModel.setOutAttitude(obj.getString("OutAttitude"));
            xinFangEventModel.setIsIncludedStatistics(String.valueOf(obj.getBoolean("IsIncludedStatistics")));
            xinFangEventModel.setVisitAddressName(obj.getString("VisitAddressName"));
            xinFangEventModel.setSource(obj.getString("Source"));
            xinFangEventModel.setVisitType(obj.getString("VisitType"));
            int i1 = xinfangEventMapper.addEvent(xinFangEventModel);
            if (i1 > 0) {
                log.info("added XinfangEvent:" + xinFangEventModel.toString());
            }
            JSONArray idCardInfos = obj.getJSONArray("IDCardInfos");
            for (int j = 0; j < idCardInfos.size(); j++) {
                JSONObject personObj = idCardInfos.getJSONObject(j);
                XinFangPeopleModel personModel = new XinFangPeopleModel();
                personModel.setVisitNo(obj.getString("VisitNo"));
                personModel.setName(personObj.getString("Name"));
                personModel.setSex(personObj.getString("Sex"));
                personModel.setDateofBirth(personObj.getString("DateOfBirth"));
                personModel.setAddress(personObj.getString("Address"));
                personModel.setCertKind(personObj.getString("CertKind"));
                personModel.setCertNo(personObj.getString("CertNo"));
                personModel.setIssuing(personObj.getString("Issuing"));
                personModel.setCensusRegister(personObj.getString("CensusRegister"));
                personModel.setNation(personObj.getString("Nation"));
                personModel.setCertificateValidity(personObj.getString("CertificateValidity"));
                personModel.setContactNumber(personObj.getString("ContactNumber"));
                personModel.setCertAddress(personObj.getString("CertAddress"));
                personModel.setIsMain(personObj.getIntValue("IsMain"));
                int i2 = xinfangEventMapper.addPerson(personModel);
                if (i2 > 0) {
                    log.info("added XinfangPeople:" + personModel.toString());
                }
            }
        }

    }
}


