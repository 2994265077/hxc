package com.cetccity.operationcenter.webframework.web.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LocalhostIP {
    public static String getIp(){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
        	log.error(e.toString());
        }
        String ip=address.getHostAddress().toString(); //获取本机ip
        return ip;
    }
}
