package com.cetccity.operationcenter.webframework.web.util;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/**根据url获取网页数据
 *  中电科（把Json格式的字符串入参String<Json>封装成ObjectModel）
 *  StringToObject
 *  UrlApiTest
 *  @return ObjectModel
 *  @throws null
 *  @author heliangming
 *  @version 1.0.0-SNAPSHOT
 * */
@Slf4j
@UtilityClass
public class UrlApiToSource {

    /**
     *
     * @param urlPath 路径
     * @return
     * @throws Exception Exception
     */
    public String doJsonGet(String urlPath) throws Exception{
        //链接URL
        URL url=new URL(urlPath);
        //返回结果集
        StringBuffer document = new StringBuffer();
        //创建链接
        URLConnection conn = url.openConnection();
        //读取返回结果集
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String line;
        while ((line = reader.readLine()) != null){
            document.append(line);
        }
        reader.close();
        JSONObject json =JSONObject.fromObject(document.toString());
        return json.toString();
    }

    public String httpGet(String url){
        //get请求返回结果
        String strResult=null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                strResult = EntityUtils.toString(response.getEntity());
            } else {
                log.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            log.error("get请求提交失败:" + url, e);
        }
        return strResult;
    }

    public String doJsonPost(String urlPath, String Json) {
        // HttpClient 6.0被抛弃了
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            // 往服务器里面发送数据
            if (Json != null) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes("UTF-8"));
                outwritestream.flush();
                outwritestream.close();
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

    public String doJsonPost(String urlPath, List<NameValueModel> httpURLConnection_list, String Json) {
        // HttpClient 6.0被抛弃了
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");

            for(NameValueModel nameValueModel:httpURLConnection_list)  {
                conn.setRequestProperty(nameValueModel.getName(), nameValueModel.getValue());
            }
            // 往服务器里面发送数据
            if (Json != null) {
                byte[] writebytes = Json.getBytes("UTF-8");
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes("UTF-8"));
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

    public String doJsonDelete(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("name", "robben");
        connection.setRequestProperty("content-type", "text/html");
        // 获取返回的数据
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line = null;
        StringBuffer content = new StringBuffer();
        while ((line = in.readLine()) != null) {
            // line 为返回值，这就可以判断是否成功
            content.append(line);
        }
        in.close();
        return content.toString();
    }

    public String doJsonDelete(String urlPath, List<String> list) throws IOException {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("name", "robben");
            connection.setRequestProperty("content-type", "text/html");
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "8859_1");
            // 将要传递的集合转换成JSON格式
            JSONArray jsonArray = JSONArray.fromObject(list);
            // 组织要传递的参数
            out.write("" + jsonArray);
            out.flush();
            out.close();
            // 获取返回的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = null;
            StringBuffer content = new StringBuffer();
            while ((line = in.readLine()) != null) {
                // line 为返回值，这就可以判断是否成功
                content.append(line);
            }
            in.close();
            return content.toString();
    }

    public String doJsonGetHttps(String url){
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL u=new URL(url);
            HttpsURLConnection huconn=(HttpsURLConnection) u.openConnection();
            //连接服务器
            huconn.connect();
            // 取得输入流，并使用Reader读取
            in = new BufferedReader(new InputStreamReader(huconn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
        finally{
            try{
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
