package com.cetc.cloud.datasynch.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.DsOuterJobModel;
import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import com.cetc.cloud.datasynch.api.model.DsSynchJobLogInfoModel;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.util.HttpClientUtil2;
import com.cetc.cloud.datasynch.provider.util.JsonExtractor;
import com.cetc.cloud.datasynch.provider.util.entity.GetModel;
import com.cetc.cloud.datasynch.provider.util.entity.PostModel;
import com.ctrip.framework.apollo.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

/**
 * Http在线请求服务
 * Created by llj on 2018/10/11.
 */
@Service("httpOperateService")
@Slf4j
public class HttpOperateService {

    @Autowired
    SignParamService signParamService;

    /**
     * 通过HTTP协议请求对应的URL获取数据
     *
     * @param model
     * @param pageNum
     * @return
     */
    public List<HashMap> doHttpQueryList(DsScheduleModel model, int pageNum) {
        List<HashMap> listData = null;
        //获取URL
        String URL = model.getSource();
        if (StringUtils.isEmpty(URL)) {
            log.error("Param: Http.source cannot be null!");
            return null;
        }
        // 获取拼接后的URL参数
        JSONObject httpParams = getHttpParams(model, pageNum);

        //获取token
        String tokenStr = model.getHttpToken();
        String httpQueryMethod = model.getHttpQueryMethod();

        JSONObject httpResult = null;
        PostModel postModel = null;
        if (null != httpQueryMethod) {
            switch (httpQueryMethod.toUpperCase()) {
                case "GET":
                    GetModel getModel = new GetModel();
                    if (null != tokenStr && !"".equals(tokenStr)) {
                        JSONObject token = new JSONObject();
                        if (tokenStr.contains(":")) {
                            String[] split = tokenStr.split(":");
                            if (!"".equals(split[0]) && !"".equals(split[1])) {
                                token.put(split[0], split[1]);
                            }
                        }
                        getModel.setUrl(URL);
                        getModel.setHeader(token);
                        getModel.setParams(httpParams);
                    }
                    getModel.setUrl(URL);
                    getModel.setParams(httpParams);
                    httpResult = HttpClientUtil2.doGetWithGetModel(getModel);
                    break;
                case "POST":
                    postModel = new PostModel();
                    if (null != tokenStr && !"".equals(tokenStr)) {
                        JSONObject token = new JSONObject();
                        if (tokenStr.contains(":")) {
                            String[] split = tokenStr.split(":");
                            if (!"".equals(split[0]) && !"".equals(split[1])) {
                                token.put(split[0], split[1]);
                            }
                        }
                        postModel.setHeader(token);
                    }
                    postModel.setBody(httpParams);
                    postModel.setUrl(URL);
                    httpResult = HttpClientUtil2.doPostWithPostModel(postModel);
                    break;
                case "POST_FORM":
                    postModel = new PostModel();
                    if (null != tokenStr && !"".equals(tokenStr)) {
                        JSONObject token = new JSONObject();
                        if (tokenStr.contains(":")) {
                            String[] split = tokenStr.split(":");
                            if (!"".equals(split[0]) && !"".equals(split[1])) {
                                token.put(split[0], split[1]);
                            }
                        }
                        postModel.setHeader(token);
                    }
                    postModel.setBody(httpParams);
                    postModel.setUrl(model.getSource());
                    httpResult = HttpClientUtil2.doPostFormWithPostModel(postModel);
                    break;
                default: {
                    //do nothing
                }
            }
            //获取json解析规则
            String jsonExtractRule = model.getHttpJsonExtractRule();
            //解析，并生成结果数据集
            if (200 == (Integer) httpResult.get(CommonInstance.HTTP_RES_CODE)) {
                String data = (String) httpResult.get("data");
                listData = JsonExtractor.ExtractListData(data, jsonExtractRule);
            } else {
                log.error(httpResult.get(CommonInstance.HTTP_RES_MSG).toString());
            }

            return listData;

        } else {
            log.error("Param:httpQueryMethod cannot be null!");
            return null;
        }
    }


    /**
     * 获取当前页的行数
     *
     * @param model
     * @param logInfoModel
     * @return
     */
    public int getHttpCurrentPageTotalRows(DsScheduleModel model, DsSynchJobLogInfoModel logInfoModel) {
        List<HashMap> queryResult = doHttpQueryList(model, logInfoModel.getLastQueryPageNum());
        if (null != queryResult) {
            return queryResult.size();
        } else {
            return 0;
        }
    }

    /**
     * 根据Model中的参数，拼接Http请求参数
     * 该方法的思路是：1.通过分页类型计算，参数值  2.直接替换占位符pageNum pageSize
     * <p>
     * 占位符定义： pageNum：  $1$
     * pageSize： $2$
     *
     * @param model
     * @param pageNum
     * @return
     */
    public JSONObject getHttpParams(DsScheduleModel model, int pageNum) {
        String httpParamExpression = model.getHttpParamExpression();
        int pageSize = model.getPageSize();
        //如果需要分页，则需要替换http参数内占位符
        if (CommonInstance.DO_PAGING == model.getIsPagingQuery()) {
            if (null != httpParamExpression) {
                switch (model.getHttpPagingType()) {
                    case CommonInstance.HTTP_PAGING_TYPE_PageNum_PageSize:
                        httpParamExpression = httpParamExpression.replaceFirst(CommonInstance.PAGE_NUM_replaceholder, pageNum + "");
                        httpParamExpression = httpParamExpression.replaceFirst(CommonInstance.PAGE_SIZE_replaceholder, pageSize + "");
                        break;
                    case CommonInstance.HTTP_PAGING_TYPE_StartPosition_MaxCount:
                        int startPosition = (pageNum - 1) * model.getPageSize();
                        httpParamExpression = httpParamExpression.replaceFirst(CommonInstance.PAGE_NUM_replaceholder, startPosition + "");
                        httpParamExpression = httpParamExpression.replaceFirst(CommonInstance.PAGE_SIZE_replaceholder, pageSize + "");
                        break;
                    default:
                        //do nothing
                        break;
                }
            }
        } else if (CommonInstance.NO_PAGING == model.getIsPagingQuery()) {
            //do nothing
        }
        int signType = model.getHttpSignType();
        //如果需要加签，对HTTP请求参数进行加签操作
        if (CommonInstance.NO_SIGN == signType) {
            return parseParamStr2JsonObj(httpParamExpression);
        } else {
            JSONObject paramsObj = parseParamStr2JsonObj(httpParamExpression);
            JSONObject signedParam = null;
            switch (signType) {
                case CommonInstance.SIGN_TYPE_TIMESTAMP_SECRETKEY:
                    signedParam = signParamService.getSignedParam(paramsObj.getString("appkey"), paramsObj.getString("content"),
                            paramsObj.getString("code"), paramsObj.getString("secretKey"));
                    break;
                default:
                    //do nothing
                    break;
            }
            return signedParam;
        }
    }


    public JSONObject queryDataByOuterUrlModel(DsOuterJobModel outerModel) {
        JSONObject httpResult;
        String URL = outerModel.getUrl();
        String params = outerModel.getParams();
        // 获取param
        JSONObject httpParams = parseParamStr2JsonObj(params);
        // 获取header
        String tokenStr = outerModel.getHeaders();
        GetModel getModel = new GetModel();
        if (null != tokenStr && !"".equals(tokenStr)) {
            if (null != tokenStr && !"".equals(tokenStr)) {
                JSONObject token = new JSONObject();
                if (tokenStr.contains(":")) {
                    String[] split = tokenStr.split(":");
                    if (!"".equals(split[0]) && !"".equals(split[1])) {
                        token.put(split[0], split[1]);
                    }
                }
                getModel.setHeader(token);
            }
        }
        getModel.setUrl(URL);
        getModel.setParams(httpParams);
        httpResult = HttpClientUtil2.doGetWithGetModel(getModel);
        return httpResult;
    }


    public JSONObject queryDataByOuterUrlModelAddParam(DsOuterJobModel outerModel, JSONObject systemIdParam, JSONObject tokenParam) {
        JSONObject httpResult;
        String URL = outerModel.getUrl();
        String params = outerModel.getParams();
        // 获取param
        JSONObject httpQueryParams = parseParamStr2JsonObj(params);
        //添加param
        httpQueryParams.put(tokenParam.getString(CommonInstance.GLOBAL_PARAM_KEYNAME), tokenParam.getString(CommonInstance.GLOBAL_PARAM_VALUENAME));
        httpQueryParams.put(systemIdParam.getString(CommonInstance.GLOBAL_PARAM_KEYNAME), systemIdParam.getString(CommonInstance.GLOBAL_PARAM_VALUENAME));
        // 获取header
        String tokenStr = outerModel.getHeaders();

        GetModel getModel = new GetModel();
        if (null != tokenStr && !"".equals(tokenStr)) {
            if (null != tokenStr && !"".equals(tokenStr)) {
                JSONObject token = new JSONObject();
                if (tokenStr.contains(":")) {
                    String[] split = tokenStr.split(":");
                    if (!"".equals(split[0]) && !"".equals(split[1])) {
                        token.put(split[0], split[1]);
                    }
                }
                getModel.setHeader(token);
            }
        }
        getModel.setUrl(URL);
        getModel.setParams(httpQueryParams);
        httpResult = HttpClientUtil2.doGetWithGetModel(getModel);
        return httpResult;
    }

    private JSONObject parseParamStr2JsonObj(String httpParamExpression) {
        JSONObject res = new JSONObject();
        if (null != httpParamExpression) {
            String[] paramKeyValues = httpParamExpression.split("&");
            for (int i = 0; i < paramKeyValues.length; i++) {
                String[] split = paramKeyValues[i].split("=");
                if (split.length == 2) {
                    String key = split[0];
                    String value = split[1];
                    if (key != null && !key.equals("")) {
                        res.put(key, value);
                    }
                }
            }
        }
        return res;
    }
}
