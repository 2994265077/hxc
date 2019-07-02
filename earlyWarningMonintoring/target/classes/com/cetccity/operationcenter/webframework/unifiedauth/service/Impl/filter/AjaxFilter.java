package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cetccity.operationcenter.webframework.backstage.interfaces.controller.SysServiceController;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.filter
 * @Project: 31project-Apr4
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/8 15:35
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/8 15:35
 * @Update_Description: huangzezhou 补充
 **/
@Component
@WebFilter
public class AjaxFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        System.out.println("拦截到了" + request.getRequestURL());
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Token, Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since, token");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        //此行代码确保请求可以继续执行至Controller
        filterChain.doFilter(request, response);
    }
}