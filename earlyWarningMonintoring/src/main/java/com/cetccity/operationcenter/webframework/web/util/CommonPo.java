package com.cetccity.operationcenter.webframework.web.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommonPo implements Serializable {

    private static final long serialVersionUID = 3658849063387356397L;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String createUserId;

    private String updateUserId;

    public void setCommonField(String userId){
        Date now = new Date();
        setCreateDate(now);
        setCreateUserId(userId);
        setUpdateDate(now);
        setUpdateUserId(userId);
    }
}
