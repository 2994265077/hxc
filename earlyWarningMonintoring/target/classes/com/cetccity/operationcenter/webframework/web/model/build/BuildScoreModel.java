package com.cetccity.operationcenter.webframework.web.model.build;

import lombok.Data;

@Data
public class BuildScoreModel<T> {

    private  String buildName;

    private  String dangerScore;

    private  String dangerGrader;

    private  String colour;

    private  String colourLevel;

    private  String mainDanger;

    private  T table;
}
