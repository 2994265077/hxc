package com.cetccity.operationcenter.webframework.web.model.build;

public class BuildScoreRestful<T> {

    private  Integer total;

    private  T buildScore;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getBuildScore() {
        return buildScore;
    }

    public void setBuildScore(T buildScore) {
        this.buildScore = buildScore;
    }
}
