package com.cetccity.operationcenter.webframework.web.model.incident.weiji;

public class TypeModel<T> {

    private Integer total;

    private T street_data;

    private T rank_data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getStreet_data() {
        return street_data;
    }

    public void setStreet_data(T street_data) {
        this.street_data = street_data;
    }

    public T getRank_data() {
        return rank_data;
    }

    public void setRank_data(T rank_data) {
        this.rank_data = rank_data;
    }
}
