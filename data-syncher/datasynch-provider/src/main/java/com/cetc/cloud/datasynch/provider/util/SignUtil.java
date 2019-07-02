package com.cetc.cloud.datasynch.provider.util;

import org.apache.commons.collections4.CollectionUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by Administrator on 2019/5/29.
 */
public class SignUtil {
    final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    /**
     * 对入参进行签名
     * @param params
     * @return
     */
    public static String getSign(String params) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteToString(md.digest(params.getBytes()));
    }

    /**
     * 将传入的参数，转换为 清洁护河 要求的特殊类型
     * @param map
     * @return
     */
    public static String getParamString(Map<String, String> map) {
        if (!map.isEmpty()) {
            List<String> keys = new ArrayList<String>(map.keySet());
            Collections.sort(keys);
            StringBuilder paramString = new StringBuilder();
            for (String key : keys) {
                paramString.append(key);
                paramString.append(map.get(key));
            }
            return paramString.toString();
        }
        return null;
    }


    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString().toUpperCase();
    }

    // return Hexadecimal
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }


}
