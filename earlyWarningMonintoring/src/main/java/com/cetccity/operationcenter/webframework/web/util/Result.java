package com.cetccity.operationcenter.webframework.web.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @author ZHUTONGYU
 * Description:公共结果集
 * 2019年3月28日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T datas;
    private Integer resp_code;
    private String resp_msg;

    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> succeed(T model) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(),"操作成功");
    }

    public static <T> Result<T> succeedWith(T datas, Integer code,String msg) {
        return new Result<T>(datas, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return failedWith(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model,String msg) {
        return failedWith(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T datas, Integer code, String msg) {
        return new Result<T>( datas, code, msg);
    }

}
