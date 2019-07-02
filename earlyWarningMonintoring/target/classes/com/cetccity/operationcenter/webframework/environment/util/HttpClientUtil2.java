package com.cetccity.operationcenter.webframework.environment.util;

import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.alarmcenter.api.model.CommonInstance;
import com.cetccity.operationcenter.webframework.environment.api.model.PostModel;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PackageName:   com.cetc.cloud.datasynch.provider.core.util
 * projectName:   dataSyncher
 * Description:   Http连接工具--连接池版本
 * Creator:     by luolinjie
 * Create_Date: 2018/11/19 18:01
 * Updater:     by luolinjie
 * Update_Date: 2018/11/19
 * Update_Description: luolinjie 补充
 **/
@Slf4j
@UtilityClass
public class HttpClientUtil2 {
    static ConcurrentHashMap<String, PoolingHttpClientConnectionManager> cmHashMap =
            new ConcurrentHashMap<String, PoolingHttpClientConnectionManager>();
    static ConcurrentHashMap<String, CloseableHttpClient> clientHashMap =
            new ConcurrentHashMap<String, CloseableHttpClient>();

    private synchronized CloseableHttpClient getHttpClient(String url) {
        if (!url.contains("http://")) {
            url = "http://" + url;
        }
        String[] ip_port = getIpAndPortFromUrl(url);
        String ip = ip_port[0];
        int port = Integer.parseInt(ip_port[1]);
        if (clientHashMap.keySet().contains(ip + "-" + port)) {
            return clientHashMap.get(ip + "-" + port);
        } else {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(200);// 将最大连接数增加到200
            cm.setDefaultMaxPerRoute(20);// 将每个路由基础的连接增加到20
            HttpHost localhost = new HttpHost(ip, 80);
            cm.setMaxPerRoute(new HttpRoute(localhost), 10);//将目标主机的最大连接数增加到10
            CloseableHttpClient buildHttpClient = HttpClients.custom()
                    .setConnectionManager(cm)
                    .build();
            cmHashMap.put(ip + "-" + port, cm);
            clientHashMap.put(ip + "-" + port, buildHttpClient);
            return buildHttpClient;
        }
    }

    public String toHttpParamStr(String url, JSONObject params) {
        String paramStr = "";
        if (null == params) {
            String res = url;
            return res;
        }
        if (params.size() != 0) {
            Set<String> keySet = params.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                paramStr += key + "=" + params.getString(key) + "&";
            }
        }
        String res = "";
        if (paramStr.length() != 0) {
            res = url + "?" + paramStr;
        } else {
            res = url;
        }
        if (res.endsWith("&")) {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public JSONObject doPostWithPostModel(PostModel postModel) {
        log.debug("\n>>Http doPostWithPostModel:URL:" + toHttpParamStr(postModel.getUrl(), postModel.getParams()) + "\n");
        CloseableHttpClient httpClient = getHttpClient(postModel.getUrl());
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", null);
        result.put("code", 200);
        result.put("msg", null);
        HttpPost httpPost = null;
        StatusLine status;
        try {
            URIBuilder builder = new URIBuilder(postModel.getUrl());
            if (!MapUtils.isEmpty(postModel.getParams())) {
                for (String key : postModel.getParams().keySet()) {
                    builder.setParameter(key, postModel.getParams().getString(key));
                }
            }
            httpPost = new HttpPost(builder.build());
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
            if (postModel.getHeader() != null) {
                JSONObject header = postModel.getHeader();
                Set<String> keySet = header.keySet();
                for (String key : keySet) {
                    httpPost.setHeader(key, header.getString(key));
                }
            }
            String body;
            if (null != postModel.getBody()) {
                body = postModel.getBody().toJSONString();
                HttpEntity entityBody = new StringEntity(body, "UTF-8");
                httpPost.setEntity(entityBody);
            }
            httpPost.setConfig(config);
            HttpResponse response = httpClient.execute(httpPost);
            status = response.getStatusLine();                          //获取返回的状态码
            HttpEntity entity = response.getEntity();                   //获取响应内容
            if (status.getStatusCode() == 200) {
                result.put("success", true);
                result.put("data", EntityUtils.toString(entity, "UTF-8"));
                result.put("code", 200);
                result.put("msg", "请求成功");
            } else {
                result.put("success", false);
                result.put("code", status.getStatusCode());
                result.put("msg", "请求异常，异常信息:" + status.getReasonPhrase());
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("code", 500);
            result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
        } finally {
            httpPost.abort();//中止请求，连接被释放回连接池
        }
        return result;
    }

    private String[] getIpAndPortFromUrl(String URL) {
        if (URL != null && !"".equals(URL)) {
            String[] ip_port = new String[2];
            String[] split = URL.split("//");
            String s1 = split[1].split("/")[0];
            if (s1.contains(":")) {
                ip_port[0] = s1.split(":")[0];
                ip_port[1] = s1.split(":")[1];
            } else {
                ip_port[0] = s1;
                ip_port[1] = "80";
            }
            return ip_port;
        } else {
            return null;
        }
    }


    /*public String get(String url, JSONObject paramMap) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = getHttpClient(url);
        String result = null;
        URIBuilder builder = new URIBuilder(url);
        if (!MapUtils.isEmpty(paramMap)) {
            for (String key : paramMap.keySet()) {
                builder.setParameter(key, paramMap.getString(key));
            }
        }
        HttpGet httpGet = new HttpGet(builder.build());
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
        httpGet.setConfig(config);
        HttpResponse response = httpClient.execute(httpGet);
        StatusLine status = response.getStatusLine();                   //获取返回的状态码
        HttpEntity entity = response.getEntity();                       //获取响应内容
        result = EntityUtils.toString(entity, "UTF-8");
        if (!(status.getStatusCode() == HttpStatus.SC_OK)) {
            log.error("put request error:\n" + result);
        }
        httpGet.abort();//中止请求，连接被释放回连接池
        return result;
    }*/

    /*public String put(String url, String params) throws IOException {
        CloseableHttpClient httpClient = getHttpClient(url);
        String result = null;
        HttpPut httpPut = new HttpPut(url);

        httpPut.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPut.setEntity(new StringEntity(params));
        HttpResponse response = httpClient.execute(httpPut);
        StatusLine status = response.getStatusLine();                   //获取返回的状态码
        HttpEntity entity = response.getEntity();                       //获取响应内容
        result = EntityUtils.toString(entity, "UTF-8");
        if (!(status.getStatusCode() == HttpStatus.SC_OK)) {
            log.error("put request error:\n" + result);
        }
        httpPut.abort();//中止请求，连接被释放回连接池
        return result;
    }

    public String get(String url, Map<String, String> paramMap) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = getHttpClient(url);
        String result = null;
        URIBuilder builder = new URIBuilder(url);
        if (!MapUtils.isEmpty(paramMap)) {
            for (String key : paramMap.keySet()) {
                builder.setParameter(key, paramMap.get(key));
            }
        }
        HttpGet httpGet = new HttpGet(builder.build());
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
        httpGet.setConfig(config);
        HttpResponse response = httpClient.execute(httpGet);
        StatusLine status = response.getStatusLine();                   //获取返回的状态码
        HttpEntity entity = response.getEntity();                       //获取响应内容
        result = EntityUtils.toString(entity, "UTF-8");
        if (!(status.getStatusCode() == HttpStatus.SC_OK)) {
            log.error("put request error:\n" + result);
        }
        httpGet.abort();//中止请求，连接被释放回连接池
        return result;
    }

    public String delete(String url) throws IOException {
        CloseableHttpClient httpClient = getHttpClient(url);
        String result = null;
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Content-Type", "application/json;charset=UTF-8");
        HttpResponse response = httpClient.execute(httpDelete);
        StatusLine status = response.getStatusLine();                   //获取返回的状态码
        HttpEntity entity = response.getEntity();                       //获取响应内容
        result = EntityUtils.toString(entity, "UTF-8");
        if (!(status.getStatusCode() == HttpStatus.SC_OK)) {
            log.error("delete request error:\n" + result);
        }
        httpDelete.abort();//中止请求，连接被释放回连接池
        return result;
    }*/

}
