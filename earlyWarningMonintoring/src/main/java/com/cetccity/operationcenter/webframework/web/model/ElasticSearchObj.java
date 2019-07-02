package com.cetccity.operationcenter.webframework.web.model;

public class ElasticSearchObj<T> {

    private String total;

    private T hits;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public T getHits() {
        return hits;
    }

    public void setHits(T hits) {
        this.hits = hits;
    }
}
