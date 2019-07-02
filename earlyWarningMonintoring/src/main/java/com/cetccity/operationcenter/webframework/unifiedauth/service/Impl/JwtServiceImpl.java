package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service
 * @Project: unified-auth
 * @Description: //jwt服务，处理jwt相关逻辑
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/1 14:33
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/1 14:33
 * @Update_Description: huangzezhou 补充
 **/
@Service
public class JwtServiceImpl {

//    private static final String serverSecret = UUID.randomUUID().toString();
    private static final String serverSecret = "7afe7197-3f6b-473e-8173-103dd5ae1634";//测试数据

    public boolean validateToken(String token) throws UnsupportedEncodingException {
        SecretKeySpec keySpec = new SecretKeySpec(serverSecret.getBytes("UTF-8"), "HmacSHA256");
        Jwts.parser().setSigningKey(keySpec).parseClaimsJws(token);
        return true;
    }

    /**
     * 解码token (Jws: JSON Web Token)
     * @return
     * @throws NoSuchAlgorithmException
     */
    public Jws<Claims> decodeJws(String token) throws UnsupportedEncodingException {
        SecretKeySpec keySpec = new SecretKeySpec(serverSecret.getBytes("UTF-8"), "HmacSHA256");
        return Jwts.parser().setSigningKey(keySpec).parseClaimsJws(token);
    }

    public String account(String token) throws UnsupportedEncodingException {

        SecretKeySpec keySpec = new SecretKeySpec(serverSecret.getBytes("UTF-8"), "HmacSHA256");
        return String.valueOf(Jwts.parser().setSigningKey(keySpec).parseClaimsJws(token).getBody().get("sub"));

    }

    /**
     * token的产生逻辑
     */
    public TokenModel token(String account) throws UnsupportedEncodingException, InterruptedException {
        SecretKeySpec keySpec = new SecretKeySpec(serverSecret.getBytes("UTF-8"), "HmacSHA256");
        //有效期设置为第二天的凌晨2点
        Calendar c = Calendar.getInstance();
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        c.add(Calendar.DATE, 1);// 日期+1，避免出现不合理日期
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 2, 0);
        return new TokenModel(Jwts.builder()
                .setIssuer("CETC")  //签发人
                .setSubject(account)    //客体-被校验的用户
                .setAudience("CETC") //接收token方
                .setExpiration(c.getTime()) //有效期至该时间
                .setIssuedAt(now.getTime())
                .setId(String.valueOf(UUID.randomUUID()))
                .signWith(keySpec).compact(), c.getTime());
    }

}
