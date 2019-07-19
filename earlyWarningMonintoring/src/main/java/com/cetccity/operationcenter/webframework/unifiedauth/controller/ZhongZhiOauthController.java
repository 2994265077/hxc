package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import cn.hutool.core.codec.Base64;
import com.cetccity.operationcenter.webframework.unifiedauth.api.ZhongZhiOauthApi;
import com.cetccity.operationcenter.webframework.web.util.MD5Encoder;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.cetccity.operationcenter.webframework.web.util.apollo.ApolloPropertiesLoadUtils;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.unifiedauth.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:06 2019-07-01
 * Updater:     heliangming
 * Update_Date：16:06 2019-07-01
 * 更新描述:    heliangming 补充
 **/
@Slf4j
@RestController
public class ZhongZhiOauthController implements ZhongZhiOauthApi{

    private static String state;

    private static String access_token;

    public void ask(HttpServletResponse response) throws Exception{
        String client_id = "bc58384170d441b8bd248c1154531956";
        state = UUID.randomUUID().toString().replaceAll("-","");
        String ZhongzhiUrl = "http://10.190.62.49:8080/ucenter/oauth2/authorize?client_id="
                +client_id+"&response_type=code&state="+state+"&redirect_uri="+URLEncoder.encode("http://"+ ApolloPropertiesLoadUtils.readProperties("application","redirect.server.ip")+":8081/oauth2ew/zhongzhi/login", "utf-8");
        response.sendRedirect(ZhongzhiUrl);
    }

    public void login(String code, String state, HttpServletResponse response)throws Exception{
        if(state.equals(state)) {
            String client_id = "bc58384170d441b8bd248c1154531956";
            String client_secret = "b8b4857f25274bd7ad607f811fc8ae74";
            //md5加密
            String client_secret2Md5 = MD5Encoder.encode(client_id + state + client_secret + code);
            String fangXingZhongzhiUrl = "http://10.190.62.49:8080/ucenter/oauth2/accessToken?client_id="
                    + client_id + "&client_secret=" + client_secret2Md5 +
                    "&grant_type=authorization_code&code=" + code + "&redirect_uri=" + URLEncoder.encode("sss","utf-8");
            String source = UrlApiToSource.doJsonGet(fangXingZhongzhiUrl);
            Map map = (Map) JSON.parse(source);
            //获取到access_token
            access_token = (String) map.get("access_token");
            String getUserUrl = "http://10.190.62.49:8080/ucenter/openapi/user/getCurrentLoginUser?access_token=" + access_token + "";
            String userObject = UrlApiToSource.doJsonGet(getUserUrl);
            Map userMap = (Map) JSON.parse(userObject);
            String userName = Base64.encode((String) userMap.get("userid"));
            //web/SingleSignOn为我们前端的页面
            String urlLast = "http://"+ApolloPropertiesLoadUtils.readProperties("application","redirect.server.web.ip")+"/web/SingleSignOn?url=/unifiedauth/authentication/authClient/"+userName+"/"+Base64.encode("zz_app")+"/"+Base64.encode(MD5Encoder.encode( "Ft@admin123"));
            response.sendRedirect(urlLast);
        }else {
            return;
        }
    }

}
