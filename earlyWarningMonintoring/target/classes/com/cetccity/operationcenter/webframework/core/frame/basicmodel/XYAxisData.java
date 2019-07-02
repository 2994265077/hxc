package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.Data;
import java.util.Map;

@Data
public class XYAxisData<T> {

    private Map<String,String> xAxis;

    private Map<String,String> yAxis;

    private T data;
}
