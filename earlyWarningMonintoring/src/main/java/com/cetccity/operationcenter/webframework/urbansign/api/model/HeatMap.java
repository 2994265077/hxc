package com.cetccity.operationcenter.webframework.urbansign.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeatMap<T> {

    private String name;

    private String max;

    private String min;

    private T data;
}
