package com.cetccity.operationcenter.webframework.web.model.incident;

public class LoadMapEs<T> {

    private String id;

    private T location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getLocation() {
        return location;
    }

    public void setLocation(T location) {
        this.location = location;
    }
}
