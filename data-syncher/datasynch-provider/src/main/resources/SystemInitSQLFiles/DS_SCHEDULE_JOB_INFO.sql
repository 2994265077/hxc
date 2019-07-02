/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.1.0

Source Server         : ZHFTYJJCPT_local
Source Server Version : 110200
Source Host           : 10.192.19.108:1521
Source Schema         : ZHFTYJJCPT

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2018-11-12 15:03:28
*/


-- ----------------------------
-- Table structure for DS_SCHEDULE_JOB_INFO
-- ----------------------------
DROP TABLE "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO";
CREATE TABLE "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO" (
"ID" NUMBER NULL ,
"CONN_TYPE" NUMBER NULL ,
"SOURCE" VARCHAR2(1024 BYTE) NULL ,
"ORDER_BY_COL_NAME" VARCHAR2(255 BYTE) NULL ,
"HTTP_PARAM_EXPRESSION" VARCHAR2(1024 BYTE) NULL ,
"HTTP_TOKEN" VARCHAR2(1024 BYTE) NULL ,
"HTTP_PARAM_PAGESIZE" VARCHAR2(255 BYTE) NULL ,
"HTTP_PARAM_PAGENUM" VARCHAR2(255 BYTE) NULL ,
"HTTP_JSON_EXTRACTRULE" VARCHAR2(255 BYTE) NULL ,
"TARGET_TABLE_NAME" VARCHAR2(255 BYTE) NULL ,
"PAGE_SIZE" NUMBER NULL ,
"CRON_EXPRESSION" VARCHAR2(255 BYTE) NULL ,
"IS_ENABLED" NUMBER DEFAULT 0  NULL ,
"CREATE_TIME" DATE DEFAULT sysdate  NULL ,
"UPDATE_TIME" DATE DEFAULT sysdate  NULL ,
"IS_PAGING_QUERY" NUMBER NULL ,
"HTTP_PAGING_TYPE" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO" IS '数据同步定时任务管理表';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."ID" IS '自增主键';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."CONN_TYPE" IS '连接方式(0：数据库方式 1：接口方式)';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."SOURCE" IS '源(请求URL路径/前置机view视图的名称)';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."ORDER_BY_COL_NAME" IS '源-排序字段名称';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."HTTP_PARAM_EXPRESSION" IS '入参表达式';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."HTTP_TOKEN" IS 'token表达式';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."HTTP_PARAM_PAGESIZE" IS 'pageSize映射名称';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."HTTP_PARAM_PAGENUM" IS 'pageNum映射名称';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."HTTP_JSON_EXTRACTRULE" IS '入参表达式+pageSize、pageNum映射规则';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."TARGET_TABLE_NAME" IS '入库表名';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."PAGE_SIZE" IS '分页大小';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."CRON_EXPRESSION" IS '定时表达式';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."IS_ENABLED" IS '是否开启（0：关闭 1：开启）';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."IS_PAGING_QUERY" IS '是否做分页查询';
COMMENT ON COLUMN "ZHFTYJJCPT"."DS_SCHEDULE_JOB_INFO"."HTTP_PAGING_TYPE" IS '分页参数组织形式（1:normal;2:json;3:position）';
