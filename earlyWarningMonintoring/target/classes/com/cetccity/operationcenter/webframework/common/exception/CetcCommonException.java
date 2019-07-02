/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CetcCommonException
 * Author:   YHY
 * Date:     2019/4/24 16:27
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.exception;

import lombok.Data;

import java.util.function.Supplier;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/24
 * @since 1.0.0
 */
@Data
public class CetcCommonException extends RuntimeException {
    private Integer code;

    public CetcCommonException() {
        super();
    }

    public CetcCommonException(String msg) {
        super(msg);
    }

    public CetcCommonException(int code,String msg) {
        super(msg);
        this.code = code;
    }

    public CetcCommonException(Throwable throwable) {
        super(throwable);
    }

    public CetcCommonException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public CetcCommonException(Supplier<Throwable> supplier) {
        super(supplier.get());
    }

    public CetcCommonException(String msg, Supplier<Throwable> supplier) {
        super(msg, supplier.get());
    }

    public static CetcCommonException defaultException() {
        return new CetcCommonException();
    }

    public static CetcCommonException defaultException(String msg) {
        return new CetcCommonException(msg);
    }

    public static CetcCommonException defaultException(int code, String msg) {
        return new CetcCommonException(code, msg);
    }

    public static CetcCommonException defaultException(Throwable throwable) {
        return new CetcCommonException(throwable);
    }

    public static CetcCommonException defaultException(String msg, Throwable throwable) {
        return new CetcCommonException(msg, throwable);
    }

    public static CetcCommonException defaultException(Supplier<Throwable> supplier) {
        return new CetcCommonException(supplier);
    }

    public static CetcCommonException defaultException(String msg, Supplier<Throwable> supplier) {
        return new CetcCommonException(msg, supplier);
    }

}