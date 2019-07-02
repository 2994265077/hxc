package com.cetccity.operationcenter.webframework.web.model.incident;

public class ElasticSearchModel<T> {
    private String took;
    private String timed_out;
    private T _shards;
    private T hits;

    public String getTook() {
        return took;
    }

    public void setTook(String took) {
        this.took = took;
    }

    public String getTimed_out() {
        return timed_out;
    }

    public void setTimed_out(String timed_out) {
        this.timed_out = timed_out;
    }

    public T get_shards() {
        return _shards;
    }

    public void set_shards(T _shards) {
        this._shards = _shards;
    }

    public T getHits() {
        return hits;
    }

    public void setHits(T hits) {
        this.hits = hits;
    }
}
