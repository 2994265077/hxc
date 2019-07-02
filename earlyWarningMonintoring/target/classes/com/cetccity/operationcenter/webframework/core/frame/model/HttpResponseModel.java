package com.cetccity.operationcenter.webframework.core.frame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.cetccity.operationcenter.framework.http
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 10:44
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 10:44
 * @Update_Description: huangzezhou 补充
 **/
@Data
@Builder
@AllArgsConstructor
public class HttpResponseModel<T> {

    private Integer code; //状态码
    private String message; //描述信息
    private T data;      //数据

    public HttpResponseModel(){
        this.code = SysCode.SYS_SUCCESS_CODE;
        this.message = SysCode.map.get(code);
        this.data = null;
    }


    public HttpResponseModel(Integer code){
        this.code = code;
        this.message = SysCode.map.get(code);
        this.data = null;
    }

    public HttpResponseModel(Exception e){
        this.code = SysCode.UNKNOWN_ERROR_CODE;
        this.message = SysCode.map.get(code);
        this.message += e.getMessage();
        this.data = null;
    }

    public HttpResponseModel(Integer code, T data){
        this.code = code;
        this.message = SysCode.map.get(code);
        this.data = data;
    }
    
    public HttpResponseModel(T data){
        this.code = SysCode.SYS_SUCCESS_CODE;
        this.message = SysCode.map.get(code);
        this.data = data;
    }

    public static <T> HttpResponseModel<T> defaultSuccess(T t) {
        HttpResponseModel<T> bean = new HttpResponseModel<>();
        bean.setCode(SysCode.SYS_SUCCESS_CODE);
        bean.setMessage("请求成功");
        bean.setData(t);
        return bean;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
