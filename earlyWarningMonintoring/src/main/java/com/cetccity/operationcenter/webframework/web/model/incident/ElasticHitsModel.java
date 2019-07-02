package com.cetccity.operationcenter.webframework.web.model.incident;

public class ElasticHitsModel<T> {
    private String total;
    private String max_score;
    private T hits;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMax_score() {
        return max_score;
    }

    public void setMax_score(String max_score) {
        this.max_score = max_score;
    }

    public T getHits() {
        return hits;
    }

    public void setHits(T hits) {
        this.hits = hits;
    }
}
