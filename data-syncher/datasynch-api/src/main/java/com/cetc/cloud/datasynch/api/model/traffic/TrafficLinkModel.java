package com.cetc.cloud.datasynch.api.model.traffic;

import lombok.Data;

/**
 * Created by Administrator on 2019/6/21.
 */
@Data
public class TrafficLinkModel {

    private String text;
    private String href;

    public TrafficLinkModel(String text, String href) {
        this.text = text;
        this.href = href;
    }
}
