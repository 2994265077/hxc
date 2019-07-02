package com.cetccity.operationcenter.webframework.web.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@UtilityClass
public class MD5Encoder {


    /**
     * 单次md5加密
     *
     * @param string
     * @return
     * @throws Exception
     */
    public String encode(String string) {
        byte[] hash = null;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
			        string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 5次md5加密
     *
     * @param pwd
     * @return
     */
    public String fiveMD5Encode(String pwd) {
        try {
            return encode(encode(encode(encode(encode(pwd)))));
        } catch (Exception e) {
        	log.error(e.toString());
        }
        return "";
    }

}
