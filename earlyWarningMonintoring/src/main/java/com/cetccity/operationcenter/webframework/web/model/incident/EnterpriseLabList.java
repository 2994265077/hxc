package com.cetccity.operationcenter.webframework.web.model.incident;

public class EnterpriseLabList {

    //预警类型
    private String type;
    //预警内容
    private String content;
    //预警时间
    private String time;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
