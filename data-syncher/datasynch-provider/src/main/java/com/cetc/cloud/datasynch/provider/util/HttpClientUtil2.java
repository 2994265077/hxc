package com.cetc.cloud.datasynch.provider.util;

import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.Token;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.util.entity.GetModel;
import com.cetc.cloud.datasynch.provider.util.entity.PostModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Lookup;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.RFC6265CookieSpecProvider;
import org.apache.http.message.BasicHeader;
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
public class HttpClientUtil2 {
    static ConcurrentHashMap<String, PoolingHttpClientConnectionManager> cmHashMap =
            new ConcurrentHashMap<String, PoolingHttpClientConnectionManager>();
    static ConcurrentHashMap<String, CloseableHttpClient> clientHashMap =
            new ConcurrentHashMap<String, CloseableHttpClient>();

    private static synchronized CloseableHttpClient getHttpClient(String url){
        if (!url.contains("http://")) {
            url = "http://" + url;
        }
        String[] ip_port = getIpAndPortFromUrl(url);
        String ip = ip_port[0];
//        PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL("https://publicsuffix.org/list/effective_tld_names.dat"));
        RFC6265CookieSpecProvider cookieSpecProvider = new RFC6265CookieSpecProvider();

        Lookup<CookieSpecProvider> cookieSpecRegistry = RegistryBuilder.<CookieSpecProvider>create()
                .register(CookieSpecs.DEFAULT, cookieSpecProvider)
                .register(CookieSpecs.STANDARD, cookieSpecProvider)
                .register(CookieSpecs.STANDARD_STRICT, cookieSpecProvider)
                .build();
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
//                    .setDefaultCookieSpecRegistry(cookieSpecRegistry)
                    .setConnectionManager(cm)
                    .build();
            cmHashMap.put(ip + "-" + port, cm);
            clientHashMap.put(ip + "-" + port, buildHttpClient);
            return buildHttpClient;
        }

    }



    /**
     * Get方法：不带Token认证
     * 注：不能使用url+params集成到params
     *
     * @return
     * @throws URISyntaxException
     */
    public static JSONObject doGetWithGetModel(GetModel getModel) {
        log.info("\n>>Http getMethod:URL:" + toHttpParamStr(getModel.getUrl(), getModel.getParams()) + "\n");
        CloseableHttpClient httpClient = getHttpClient(getModel.getUrl());
        JSONObject result = new JSONObject();
        HttpGet httpGet = null;
        StatusLine status = null;
        try {
            URIBuilder builder = new URIBuilder(getModel.getUrl());
            JSONObject params = getModel.getParams();
            if (!MapUtils.isEmpty(params)) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.getString(key));
                }
            }
            httpGet = new HttpGet(builder.build());
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT)
//                    .setCookieSpec(CookieSpecs.DEFAULT)
                    .setRedirectsEnabled(true)
                    .build();
            if (getModel.getHeader() != null) {
                JSONObject header = getModel.getHeader();
                Set<String> keySet = header.keySet();
                for (String key : keySet) {
                    httpGet.addHeader(key, header.getString(key));
                }
            }
            httpGet.setConfig(config);
//            httpGet.setHeader("cookie", "ci_session=08685ca1ee9aa72a9038ddf080947b539bea0e67; Admin-Token=limujun; szstat_uid=432; szstat_username=%E6%9D%8E%E6%9C%A8%E4%BF%8A; szstat_color=1; szstat_name=limujun");
            HttpResponse response = httpClient.execute(httpGet);
            status = response.getStatusLine();                          //获取返回的状态码
            if (status.getStatusCode() != 200) {
                Header firstHeader = response.getFirstHeader("Set-Cookie");
                String value = firstHeader.getValue();
                String ci_session = value.split(";")[0];
                httpGet.removeHeaders("cookie");
                httpGet.setHeader("cookie", ci_session + "; Admin-Token=limujun; szstat_uid=432; szstat_username=%E6%9D%8E%E6%9C%A8%E4%BF%8A; szstat_color=1; szstat_name=limujun");
                response = httpClient.execute(httpGet);
                status = response.getStatusLine();
            }
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
            result.put("code", status.getStatusCode());
            result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
        } finally {
            httpGet.abort();//中止请求，连接被释放回连接池
        }
        return result;
    }
//    /**
//     * Get方法：不带Token认证
//     * 注：不能使用url+params集成到params
//     *
//     * @return
//     * @throws URISyntaxException
//     */
//    public static JSONObject doGetWithGetModel(GetModel getModel) {
//        log.info("\n>>Http getMethod:URL:" + toHttpParamStr(getModel.getUrl(), getModel.getParams()) + "\n");
//        CloseableHttpClient httpClient = getHttpClient(getModel.getUrl());
//        JSONObject result = new JSONObject();
//        HttpGet httpGet = null;
//        StatusLine status = null;
//        try {
//            URIBuilder builder = new URIBuilder(getModel.getUrl());
//            JSONObject params = getModel.getParams();
//            if (!MapUtils.isEmpty(params)) {
//                for (String key : params.keySet()) {
//                    builder.addParameter(key, params.getString(key));
//                }
//            }
//            httpGet = new HttpGet(builder.build());
//            RequestConfig config = RequestConfig.custom()
//                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
//                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
//                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT)
//                    .setCookieSpec(CookieSpecs.DEFAULT)
//                    .setRedirectsEnabled(true)
//                    .build();
//            if (getModel.getHeader() != null) {
//                JSONObject header = getModel.getHeader();
//                Set<String> keySet = header.keySet();
//                for (String key : keySet) {
//                    httpGet.addHeader(key, header.getString(key));
//                }
//            }
//            httpGet.setConfig(config);
//            HttpResponse response = httpClient.execute(httpGet);
//            status = response.getStatusLine();                          //获取返回的状态码
//            if (status.getStatusCode() == 401) {
//                Header firstHeader = response.getFirstHeader("Set-Cookie");
//                String name = firstHeader.getName();
//                String value = firstHeader.getValue();
//                String ci_session = value.split(";")[0];
//                httpGet.setHeader("cookie", ci_session + "; Admin-Token=limujun; szstat_uid=432; szstat_username=%E6%9D%8E%E6%9C%A8%E4%BF%8A; szstat_color=1; szstat_name=limujun");
//                response = httpClient.execute(httpGet);
//            }
//            HttpEntity entity = response.getEntity();                   //获取响应内容
//            status = response.getStatusLine();
//            if (status.getStatusCode() == 200) {
//                result.put("success", true);
//                result.put("data", EntityUtils.toString(entity, "UTF-8"));
//                result.put("code", 200);
//                result.put("msg", "请求成功");
//            } else {
//                result.put("success", false);
//                result.put("code", status.getStatusCode());
//                result.put("msg", "请求异常，异常信息:" + status.getReasonPhrase());
//            }
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("code", status.getStatusCode());
//            result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
//        } finally {
//            httpGet.abort();//中止请求，连接被释放回连接池
//        }
//        return result;
//    }

//    /**
//     * Get方法：带Token认证
//     *
//     * @param url
//     * @param params
//     * @param token
//     * @return
//     */
//    public static JSONObject doGetWithAuthoration(String url, JSONObject params, Token token) {
//
//        log.info("\n>>Http getMethod:URL:" + toHttpParamStr(url, params) + "\n");
//
//        CloseableHttpClient httpClient = getHttpClient(url);
//        JSONObject result = new JSONObject();
//        result.put("success", true);
//        result.put("data", null);
//        result.put("code", 200);
//        result.put("msg", null);
//        HttpGet httpGet = null;
//        StatusLine status = null;
//        try {
//            URIBuilder builder = new URIBuilder(url);
//            if (!MapUtils.isEmpty(params)) {
//                for (String key : params.keySet()) {
//                    builder.setParameter(key, params.getString(key));
//                }
//            }
//            httpGet = new HttpGet(builder.build());
//            RequestConfig config = RequestConfig.custom()
//                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
//                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
//                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
//            httpGet.setConfig(config);
//            httpGet.setHeader(token.getKey(), token.getValue());
//            HttpResponse response = httpClient.execute(httpGet);
//            status = response.getStatusLine();                          //获取返回的状态码
//            HttpEntity entity = response.getEntity();                   //获取响应内容
//            if (status.getStatusCode() == 200) {
//                result.put("success", true);
//                result.put("data", EntityUtils.toString(entity, "UTF-8"));
//                result.put("code", 200);
//                result.put("msg", "请求成功");
//            } else {
//                result.put("success", false);
//                result.put("code", status.getStatusCode());
//                result.put("msg", "请求异常，异常信息:" + status.getReasonPhrase());
//            }
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("code", 500);
//            result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
//        } finally {
//            httpGet.abort();//中止请求，连接被释放回连接池
//        }
//        return result;
//
//    }

    public static String toHttpParamStr(String url, JSONObject params) {
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

    public static JSONObject doPostWith_param_body_token(String url, JSONObject params, String bodyContent, String tokenStr) {
        log.info("\n>>Http getMethod:URL:" + toHttpParamStr(url, params) + "\n");
        CloseableHttpClient httpClient = getHttpClient(url);
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", null);
        result.put("code", 200);
        result.put("msg", null);
        HttpPost httpPost = null;
        StatusLine status = null;
        Token token = parseTokenStr2Token(tokenStr);
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!MapUtils.isEmpty(params)) {
                for (String key : params.keySet()) {
                    builder.setParameter(key, params.getString(key));
                }
            }
            httpPost = new HttpPost(builder.build());
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
            httpPost.setHeader(token.getKey(), token.getValue());
            httpPost.setConfig(config);
            if (null != bodyContent && !"".equals(bodyContent)) {
                HttpEntity entity = new StringEntity(bodyContent);
                httpPost.setEntity(entity);
            }
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


    public static JSONObject doPostWith_param_body(String url, JSONObject params, String bodyContent) {
        log.info("\n>>Http doPostWithBody:URL:" + toHttpParamStr(url, params) + "\n");
        CloseableHttpClient httpClient = getHttpClient(url);
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", null);
        result.put("code", 200);
        result.put("msg", null);
        HttpPost httpPost = null;
        StatusLine status = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!MapUtils.isEmpty(params)) {
                for (String key : params.keySet()) {
                    builder.setParameter(key, params.getString(key));
                }
            }
            httpPost = new HttpPost(builder.build());
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
            httpPost.setConfig(config);
            if (null != bodyContent && !"".equals(bodyContent)) {
                HttpEntity entity = new StringEntity(bodyContent);
                httpPost.setEntity(entity);
            }

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

    public static JSONObject doPostWith_param(String url, JSONObject params) {
        log.info("\n>>Http doPostWithBody:URL:" + toHttpParamStr(url, params) + "\n");
        CloseableHttpClient httpClient = getHttpClient(url);
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", null);
        result.put("code", 200);
        result.put("msg", null);
        HttpPost httpPost = null;
        StatusLine status = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!MapUtils.isEmpty(params)) {
                for (String key : params.keySet()) {
                    builder.setParameter(key, params.getString(key));
                }
            }
            httpPost = new HttpPost(builder.build());
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
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

    public static JSONObject doPostWithPostModel(PostModel postModel) {
        log.info("\n>>Http doPostWithPostModel:URL:" + toHttpParamStr(postModel.getUrl(), postModel.getParams()));
        if (null!=postModel.getBody()) {
            log.info("\n>>Http doPostWithPostModel:Body:" + postModel.getBody().toJSONString() + "\n");
        }
        if (null!=postModel.getHeader()) {
            log.info("\n>>Http doPostWithPostModel:Header:" + postModel.getHeader().toJSONString() + "\n");
        }
        CloseableHttpClient httpClient = getHttpClient(postModel.getUrl());
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", null);
        result.put("code", 200);
        result.put("msg", null);
        HttpPost httpPost = null;
        StatusLine status = null;
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
            JSONObject header = postModel.getHeader();
            if (header != null) {
                Set<String> keySet = header.keySet();
                for (String key : keySet) {
                    httpPost.addHeader(key, header.getString(key));
                }
            }
            String body = null;
            List<NameValuePair> pairs = null;
            JSONObject postModelBody = postModel.getBody();
            if (null != postModelBody) {
                if (postModelBody != null && !postModelBody.isEmpty()) {
                    pairs = new ArrayList(postModelBody.size());
                    Iterator i$ = postModelBody.entrySet().iterator();

                    while (i$.hasNext()) {
                        Map.Entry<String, String> entry = (Map.Entry) i$.next();
                        String value = entry.getValue();
                        if (StringUtils.isNotBlank(value)) {
                            pairs.add(new BasicNameValuePair(entry.getKey(), value));
                        }
                    }
                }
                HttpEntity entityBody = new UrlEncodedFormEntity(pairs, "UTF-8");
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

    public static JSONObject doPostFormWithPostModel(PostModel postModel) {
        log.info("\n>>Http doPostWithPostModel:URL:" + toHttpParamStr(postModel.getUrl(), postModel.getParams()) + "\n");
        CloseableHttpClient httpClient = getHttpClient(postModel.getUrl());
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", null);
        result.put("code", 200);
        result.put("msg", null);
        HttpPost httpPost = null;
        StatusLine status = null;
        try {
            httpPost = new HttpPost(postModel.getUrl());
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();

            if (null != postModel.getBody()) {
                JSONObject body = postModel.getBody();
                Iterator<String> iterator1 = body.keySet().iterator();
                while (iterator1.hasNext()) {
                    String next = iterator1.next();
                    formparams.add(new BasicNameValuePair(next, body.getString(next)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                httpPost.setEntity(entity);
            }


            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
            //添加Form表单的表头
            JSONObject headerParam = postModel.getHeader();
            Header header1 = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader(header1);
            if (headerParam != null) {
                Set<String> keySet = headerParam.keySet();
                for (String key : keySet) {
                    httpPost.addHeader(key, headerParam.getString(key));
                }
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

//    public static JSONObject doPostWithParam_Body_Token(String url, JSONObject params, String bodyContent, String tokenStr) {
//        log.info("\n>>Http getMethod:URL:" + toHttpParamStr(url, params) + "\n");
//        CloseableHttpClient httpClient = getHttpClient(url);
//        JSONObject result = new JSONObject();
//        result.put("success", true);
//        result.put("data", null);
//        result.put("code", 200);
//        result.put("msg", null);
//        HttpPost httpPost = null;
//        StatusLine status = null;
//        Token token = parseTokenStr2Token(tokenStr);
//        try {
//            URIBuilder builder = new URIBuilder(url);
//            if (!MapUtils.isEmpty(params)) {
//                for (String key : params.keySet()) {
//                    builder.setParameter(key, params.getString(key));
//                }
//            }
//            httpPost = new HttpPost(url);
//            RequestConfig config = RequestConfig.custom()
//                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
//                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
//                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
//            httpPost.setHeader(token.getKey(), token.getValue());
//            httpPost.setConfig(config);
//            if (null != bodyContent && !"".equals(bodyContent)) {
//                HttpEntity entity = new StringEntity(bodyContent);
//                httpPost.setEntity(entity);
//            }
//            HttpResponse response = httpClient.execute(httpPost);
//            status = response.getStatusLine();                          //获取返回的状态码
//            HttpEntity entity = response.getEntity();                   //获取响应内容
//            if (status.getStatusCode() == 200) {
//                result.put("success", true);
//                result.put("data", EntityUtils.toString(entity, "UTF-8"));
//                result.put("code", 200);
//                result.put("msg", "请求成功");
//            } else {
//                result.put("success", false);
//                result.put("code", status.getStatusCode());
//                result.put("msg", "请求异常，异常信息:" + status.getReasonPhrase());
//            }
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("code", 500);
//            result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
//        } finally {
//            httpPost.abort();//中止请求，连接被释放回连接池
//        }
//        return result;
//    }

    public static JSONObject doPostWithParam_Body_Header(String url, JSONObject params, String bodyContent, JSONObject header) {
        log.info("\n>>Http getMethod:URL:" + toHttpParamStr(url, params) + "\n");
        CloseableHttpClient httpClient = getHttpClient(url);
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("data", null);
        result.put("code", 200);
        result.put("msg", null);
        HttpPost httpPost = null;
        StatusLine status = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!MapUtils.isEmpty(params)) {
                for (String key : params.keySet()) {
                    builder.setParameter(key, params.getString(key));
                }
            }
            httpPost = new HttpPost(url);
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(CommonInstance.HTTP_SOCKET_TIMEOUT)
                    .setConnectTimeout(CommonInstance.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CommonInstance.HTTP_CONNECT_RESPONSE_TIMEOUT).build();
            Set<String> headerKeySet = header.keySet();
            if (CollectionUtils.isNotEmpty(headerKeySet)) {
                Iterator<String> iterator = headerKeySet.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    httpPost.addHeader(next, header.getString(next));
                }
            }
            httpPost.setConfig(config);
            if (null != bodyContent && !"".equals(bodyContent)) {
                HttpEntity entity = new StringEntity(bodyContent);
                httpPost.setEntity(entity);
            }
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


    public static JSONObject getParamObject(String httpParamExpression) {
        JSONObject params = new JSONObject();
        if (null != httpParamExpression) {
            String[] paramKeyValues = httpParamExpression.split("&");
            for (int i = 0; i < paramKeyValues.length; i++) {
                String[] split = paramKeyValues[i].split("=");
                if (split.length == 2) {
                    String key = split[0];
                    String value = split[1];
                    params.put(key, value);
                } else {
                    continue;
                }
            }
        }
        return params;
    }


    private static String[] getIpAndPortFromUrl(String URL) {
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


    public static String get(String url, JSONObject paramMap) throws IOException, URISyntaxException {
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
    }

    public static String put(String url, String params) throws IOException {
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

//    public static String post(String url, String params) throws IOException {
//        CloseableHttpClient httpClient = getHttpClient(url);
//        String result = "";
//        HttpPost httpPost = new HttpPost(url);
//
//        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
//        httpPost.setEntity(new StringEntity(params));
//        HttpResponse response = httpClient.execute(httpPost);
//        StatusLine status = response.getStatusLine();                   //获取返回的状态码
//        HttpEntity entity = response.getEntity();                       //获取响应内容
//        result = EntityUtils.toString(entity, "UTF-8");
//        if (!(status.getStatusCode() == HttpStatus.SC_OK)) {
//            log.error("post request error:\n" + result);
//        }
//        httpPost.abort();//中止请求，连接被释放回连接池
//        return result;
//    }

    public static String get(String url, Map<String, String> paramMap) throws IOException, URISyntaxException {
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


    public static String delete(String url) throws IOException {
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
    }

    public static JSONObject getHttpParams(String httpParamExpression) {
        JSONObject httpQueryParams = new JSONObject();
        if (null != httpParamExpression) {
            String[] paramKeyValues = httpParamExpression.split("&");

            for (int i = 0; i < paramKeyValues.length; i++) {
                String[] split = paramKeyValues[i].split("=");
                if (split.length == 2) {
                    String key = split[0];
                    String value = split[1];
                    httpQueryParams.put(key, value);
                } else {
                    continue;
                }
            }
        }
        return httpQueryParams;
    }

    public static JSONObject getUrlAndParamsFromURL(String URL) {
        JSONObject res = new JSONObject();
        JSONObject httpQueryParams = new JSONObject();
        String url = null;
        String paramStr = null;
        if (null != URL && URL.contains("?")) {
            String[] paramKeyValues = URL.split("\\?");
            if (paramKeyValues.length == 2) {
                url = paramKeyValues[0];
                paramStr = paramKeyValues[1];
            }
        }

        if (null != paramStr) {
            String[] paramKeyValues = paramStr.split("&");

            for (int i = 0; i < paramKeyValues.length; i++) {
                String[] split = paramKeyValues[i].split("=");
                if (split.length == 2) {
                    String key = split[0];
                    String value = split[1];
                    httpQueryParams.put(key, value);
                } else {
                    continue;
                }
            }
        }
        res.put(CommonInstance.HTTP_URL_KEY_NAME,url);
        res.put(CommonInstance.HTTP_QUERY_PARAMS_KEY_NAME,httpQueryParams);
        return res;
    }

    public static Token parseTokenStr2Token(String tokenStr) {
        if (tokenStr != null && !"".equals(tokenStr)) {
            if (tokenStr.contains(":")) {
                String[] split = tokenStr.split("\\:");
                Token token = new Token();
                token.setKey(split[0]);
                token.setValue(split[1]);
                return token;
            }
        }
        return null;
    }

    public static JSONObject parseTokenStr2JSONObj(String tokenStr) {
        JSONObject jsonToken = new JSONObject();
        if (tokenStr != null && !"".equals(tokenStr)) {
            if (tokenStr.contains(":")) {
                String[] split = tokenStr.split("\\:");
                jsonToken.put(split[0], split[1]);
                return jsonToken;
            }
        }
        return null;
    }

}
