package com.cetc.cloud.datasynch.provider.filter;

import com.cetc.cloud.datasynch.provider.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */
@Slf4j
@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "ipFilter")
public class UrlFilter implements Filter {

    @Value("${ip-white-list}")
    private String ipWhiteList;

    private List<String> whiteLists;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //加载预置白名单列表
        if (CollectionUtils.isEmpty(whiteLists)) {
            whiteLists = IpUtils.getIps(ipWhiteList);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        //过滤ip,若用户在白名单内，则放行
        String ipAddress = IpUtils.getIpAddress((HttpServletRequest) servletRequest);
//        log.info("USER IP ADDRESS IS =>" + ipAddress);
        if(!StringUtils.isNotBlank(ipAddress)) {
            return;
        }

        if(whiteLists.contains(ipAddress)){
//            if (((HttpServletRequest) servletRequest).getContextPath().contains("/bp/push")){
//
//            }
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            servletResponse.getWriter().append("<h1 style=\"text-align:center;\">Access Denied!</h1>");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
