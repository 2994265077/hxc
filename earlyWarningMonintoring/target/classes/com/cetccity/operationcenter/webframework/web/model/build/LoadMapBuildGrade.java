package com.cetccity.operationcenter.webframework.web.model.build;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoadMapBuildGrade {

    @JsonProperty("BLDG_NO")
    private  String BLDG_NO;

    private  String score;

    private  int level;
}
