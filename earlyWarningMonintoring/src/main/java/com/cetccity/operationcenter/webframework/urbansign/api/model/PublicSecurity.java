package com.cetccity.operationcenter.webframework.urbansign.api.model;

public class PublicSecurity<T> {

    private T majorSafetyAccident;

    private T injury;

    private T death;

    public T getMajorSafetyAccident() {
        return majorSafetyAccident;
    }

    public void setMajorSafetyAccident(T majorSafetyAccident) {
        this.majorSafetyAccident = majorSafetyAccident;
    }

    public T getInjury() {
        return injury;
    }

    public void setInjury(T injury) {
        this.injury = injury;
    }

    public T getDeath() {
        return death;
    }

    public void setDeath(T death) {
        this.death = death;
    }
}
