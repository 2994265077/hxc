package com.cetccity.operationcenter.webframework.web.model.build;

import lombok.Data;

@Data
public class ScoreTotleAttribute<T> {

    private  String scoreTotle;
    private  T basic;
    private  T buildSecurity;
    private  T buildPurpose;
    private  T buildSurroundingEnvironment;
}
