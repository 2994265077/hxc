package com.cetccity.operationcenter.webframework.web.model.incident;

public class EarlyWarningCenter<T> {

    private T center;

    private T list;

    private T disposalStatus;

    public T getCenter() {
        return center;
    }

    public void setCenter(T center) {
        this.center = center;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public T getDisposalStatus() {
        return disposalStatus;
    }

    public void setDisposalStatus(T disposalStatus) {
        this.disposalStatus = disposalStatus;
    }
}
