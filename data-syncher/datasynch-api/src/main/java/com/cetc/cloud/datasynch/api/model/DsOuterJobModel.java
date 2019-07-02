package com.cetc.cloud.datasynch.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * PackageName:   com.cetc.cloud.datasynch.api.model
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/12/14 17:19
 * Updater:     by luolinjie
 * Update_Date: 2018/12/14
 * Update_Description: luolinjie 补充
 **/
@Data
@NoArgsConstructor
@ToString
public class DsOuterJobModel implements Serializable {

    private String job_class_name;
    private String table_name;
    private String url;
    private String body;
    private String params;
    private String headers;
    private int object_id;
    private String create_time;
    private String update_time;
    private String method;
    private int token_link_id;
    private String result_extract_rule;
    private String token_key_name;
    private String cron_expression;

}
