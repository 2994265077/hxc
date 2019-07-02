/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: DefaultGuavaCacheManager
 * Author:   YHY
 * Date:     2019/4/19 14:08
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.utils;


import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.TokenModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.JwtServiceImpl;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 〈一句话功能简述〉<br> 
 * 〈默认不存储token的token管理器〉
 *
 * @author yhy
 * @create 2019/4/19
 * @since 1.0.0
 */
@Slf4j
public class DefaultNoCacheTokenManager implements TokenManager<String>{

    private static final String secret = "7afe7197-3f6b-473e-8173-103dd5ae1634";

    private  SecretKeySpec keySpec;

    @PostConstruct
    public void init() throws Exception{
        keySpec = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
    }

    @Override
    public TokenModel token(@NotNull String subject) {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Date expireData = Date.from(now.plusHours(4).atZone(zoneId).toInstant());
        return token(subject, expireData);
    }

    protected TokenModel token(@NotNull String subject, Date expireTime) {
        Assert.notNull(subject, "用户账号不能为空");
        String uuid = UUID.randomUUID().toString().replace("_", "");
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Date nowData = Date.from(now.atZone(zoneId).toInstant());
        return new TokenModel(Jwts.builder()
                .setIssuer("CETC")
                .setSubject(subject)
                .setAudience("CETC ")
                .setExpiration(expireTime)
                .setIssuedAt(nowData)
                .setId(uuid)
                .signWith(keySpec).compact(), nowData);
    }

    @Override
    public Claims deToken(String token)  {
        try {
            return  Jwts.parser().setSigningKey(keySpec).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
                log.error("token已过期", e);
            throw CetcCommonException.defaultException(AuthCode.USER_NO_LOGIN_ERROR, AuthCode.USER_NO_LOGIN_ERROR_MESSAGE);
        } catch (Throwable throwable) {
            log.error("解析token失败", throwable);
            throw CetcCommonException.defaultException(AuthCode.USER_NO_LOGIN_ERROR, AuthCode.USER_NO_LOGIN_ERROR_MESSAGE);
        }
    }

    @Override
    public TokenModel refresh(@NotNull String token) {
        Claims claims = deToken(token);
        Date expiration = claims.getExpiration();
        long issuedAtTime = claims.getIssuedAt().getTime();
        long currentTimeMillis = System.currentTimeMillis();
        long interval = currentTimeMillis - issuedAtTime;
        if (new Date().compareTo(expiration) < 0) {
            if (interval > 10000) {
                return token(claims.getSubject());
            }
            return new TokenModel(token, claims.getExpiration());
        }
        throw new RuntimeException("token已过期");
    }

    @Override
    public TokenModel evict(@NotNull String token) {
        Assert.notNull(token, "退出时token不能为空");
        Claims claims = deToken(token);
        Date expiration = claims.getExpiration();
        // 有效期内，返回一个过期token
        if (expiration.compareTo(new Date()) >= 0) {
            return token(claims.getSubject(), new Date());
        }
        // 非有效期内，返回原token
        return new TokenModel(token, claims.getIssuedAt());
    }

    @Override
    public boolean available(String token) {
        try {
            Claims claims = deToken(token);
            Date expiration = claims.getExpiration();
            if (expiration.compareTo(new Date()) >= 0) {
                return true;
            }
        } catch (Throwable e) {
        }
        return false;
    }
}