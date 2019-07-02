/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ExceptionHandleConfig
 * Author:   YHY
 * Date:     2019/4/24 16:41
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.config;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br> 
 * 〈全局异常处理器〉
 *
 * @author yhy
 * @create 2019/4/24
 * @since 1.0.0
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandleConfig {

    public static final String DEFAULT_ERROR_MESSAGE = "系统繁忙，请稍后再试";

    /**
     * 全局异常捕捉处理
     * @param cetcCommonException 异常处理
     * @return
     */
    @ExceptionHandler(value = { CetcCommonException.class } )
    @ResponseBody
    public HttpResponseModel handleCetcCommonException(CetcCommonException cetcCommonException) {
        log.error("", cetcCommonException);
        return HttpResponseModel.builder().code(SysCode.UNKNOWN_ERROR_CODE).message(cetcCommonException.getMessage()).build();
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    public HttpResponseModel handleNull(NullPointerException nullPointerException) {
        log.error("", nullPointerException);
        String message = nullPointerException.getMessage();
        message = StringUtils.isNotBlank(message) ? message: DEFAULT_ERROR_MESSAGE;
        return HttpResponseModel.builder().code(SysCode.UNKNOWN_ERROR_CODE).message(message).build();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseBody
    public HttpResponseModel handleThrowable(Throwable throwable) {
        log.error("", throwable);
        return HttpResponseModel.builder().code(SysCode.UNKNOWN_ERROR_CODE).message(DEFAULT_ERROR_MESSAGE).build();
    }



}