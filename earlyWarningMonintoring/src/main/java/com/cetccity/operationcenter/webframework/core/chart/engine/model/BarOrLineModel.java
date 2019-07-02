package com.cetccity.operationcenter.webframework.core.chart.engine.model;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.KeyValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.api.model
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/17 16:17
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/17 16:17
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarOrLineModel {
    private String name;

    private NameValueTypeModel xAxis;
    private NameValueTypeModel yAxis;
    private String type;    // bar line pie
    private String stack;    // bar line pie
    private List<NameValueTypeModel<Integer>> data = new LinkedList<>();

    public BarOrLineModel(String name, NameValueTypeModel... data){
        this.name = name;
        this.data = new ArrayList<NameValueTypeModel<Integer>>();
        for (int i = 0; i < data.length; ++i){
            this.data.add(data[i]);
        }
    }

    public BarOrLineModel(String name,List<NameValueTypeModel<Integer>> data){
        this.name = name;
        this.data = data;
    }

    public BarOrLineModel(String name, String type, String stack, List<NameValueTypeModel<Integer>> data){
        this.name = name;
        this.type = type;
        this.stack = stack;
        this.data = data;
    }

    @JsonProperty("xAxis")
    public NameValueTypeModel getxAxis() {
        return xAxis;
    }

    public void setxAxis(NameValueTypeModel xAxis) {
        this.xAxis = xAxis;
    }

    @JsonProperty("yAxis")
    public NameValueTypeModel getyAxis() {
        return yAxis;
    }

    public void setyAxis(NameValueTypeModel yAxis) {
        this.yAxis = yAxis;
    }
}
