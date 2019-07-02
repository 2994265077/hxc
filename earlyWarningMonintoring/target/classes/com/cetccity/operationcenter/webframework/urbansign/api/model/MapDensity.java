package com.cetccity.operationcenter.webframework.urbansign.api.model;

import lombok.Data;

@Data
public class MapDensity {

    private String id;

    private String streetName;

    private Double area;

    private Double density;

    private String value;

    private int level;

    private String color;

    private String max;

    private String min;
}
