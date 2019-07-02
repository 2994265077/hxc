package com.cetccity.operationcenter.webframework.web.util;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class IotApiUrl {

    public static String doJsonGet(String urlPath) throws Exception{
        //链接URL
        URL url=new URL(urlPath);
        //返回结果集
        StringBuffer documentBuffer = new StringBuffer();
        //创建链接
        URLConnection conn = url.openConnection();
        //读取返回结果集
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String line = null;
        while ((line = reader.readLine()) != null){
            documentBuffer.append(line);
        }
        reader.close();
        JSONObject json =JSONObject.fromObject(documentBuffer.toString());
        return json.toString();
    }

    public static String doJsonPost(String urlPath,  String Json) {
        // HttpClient 6.0被抛弃了
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置请求的方式 响应时长 读取时长
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(2000);

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");

            conn.setRequestProperty("token","c94c0cea596342ae9d39fe06cc0660f9");
            conn.setRequestProperty("sys_id","112");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            // 往服务器里面发送数据
            if (Json != null) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                //   Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode());
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        }
        return result;
    }


}
