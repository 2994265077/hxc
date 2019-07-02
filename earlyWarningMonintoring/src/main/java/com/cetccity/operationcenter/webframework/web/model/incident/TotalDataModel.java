package com.cetccity.operationcenter.webframework.web.model.incident;

import lombok.Data;

@Data
public class TotalDataModel<T> {

    private long total;

    private T data;

}
