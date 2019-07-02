package com.cetccity.operationcenter.webframework.urbansign.api.model;

public class PublicSecurityDetail<T> {

    private T safetyProduction;

    private T publicHealth;

    private T fireSafety;

    public T getSafetyProduction() {
        return safetyProduction;
    }

    public void setSafetyProduction(T safetyProduction) {
        this.safetyProduction = safetyProduction;
    }

    public T getPublicHealth() {
        return publicHealth;
    }

    public void setPublicHealth(T publicHealth) {
        this.publicHealth = publicHealth;
    }

    public T getFireSafety() {
        return fireSafety;
    }

    public void setFireSafety(T fireSafety) {
        this.fireSafety = fireSafety;
    }
}
