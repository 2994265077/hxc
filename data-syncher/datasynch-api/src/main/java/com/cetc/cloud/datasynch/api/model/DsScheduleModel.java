package com.cetc.cloud.datasynch.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务表: DS_SCHEDULE_JOB_INFO
 * Created by llj on 2018/10/8.
 */
@Data
@NoArgsConstructor
public class DsScheduleModel implements Serializable {
    private int id;                         //job唯一主键
    private int connType;                   //连接方式（0：前置机方式 1：接口方式）
    private String source;                  //源（请求URL路径@token/前置机view视图的名称）
    private int srcDs;                      //源数据源 ： 0：readOnly配置（数据库默认值）  1：third数据源配置
    private int isPagingQuery;              //是否是分页查询(如果不做分页,对于DB类：全量请求入库,没有pagesize)
    private String orderByColumnName;       //（数据库请求独有参数）源-排序字段名称
    private String httpParamExpression;     //（HTTP请求独有参数）入参表达式
    private String httpToken;               //（HTTP请求独有参数）token表达式
    private String httpParamPageSize;       //（HTTP请求独有参数）pageSize映射参数名
    private String httpParamPageNum;        //（HTTP请求独有参数）pageNum映射参数名
    private int httpPagingType;             //（HTTP请求独有参数）分页参数组织类型（1:pageNum+pageSize; 2:startPosition+MaxCount）
    private String httpJsonExtractRule;     //（HTTP请求独有参数）json解析规则
    private String httpQueryMethod;         //（HTTP请求独有参数）请求方式 GET/POST
    private int httpSignType;               //（HTTP请求独有参数）http签名方式(0:不签名 1:清洁护河方式签名 2:其他类型)
    private String targetTableName;         //入库表名
    private int needsTruncateTargetTb;      //是否要清空表  0：不清空 1：清空
    private int pageSize;                   //页大小
    private String cronExpression;          //定时表达式
    private int isEnabled;                  //是否开启（0：关闭 1：开启）
    private Date createTime;                //创建时间
    private Date updateTime;                //更新时间

    @Override
    public String toString() {
        return "DsScheduleModel{" +
                "id=" + id +
                ", connType=" + connType +
                ", source='" + source + '\'' +
                ", isPagingQuery=" + isPagingQuery +
                ", orderByColumnName='" + orderByColumnName + '\'' +
                ", httpParamExpression='" + httpParamExpression + '\'' +
                ", httpToken='" + httpToken + '\'' +
                ", httpParamPageSize='" + httpParamPageSize + '\'' +
                ", httpParamPageNum='" + httpParamPageNum + '\'' +
                ", httpJsonExtractRule='" + httpJsonExtractRule + '\'' +
                ", targetTableName='" + targetTableName + '\'' +
                ", needsTruncateTargetTb='" + needsTruncateTargetTb + '\'' +
                ", pageSize=" + pageSize +
                ", cronExpression='" + cronExpression + '\'' +
                ", isEnabled=" + isEnabled +
                ", srcDs=" + srcDs +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
