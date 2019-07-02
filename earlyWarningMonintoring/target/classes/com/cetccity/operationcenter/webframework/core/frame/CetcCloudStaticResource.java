package com.cetccity.operationcenter.webframework.core.frame;
/**********************************************************************
 *
 * Copyright (c) 2017 CETC Company                                       
 * 中电科新型智慧城市研究院有限公司版权所有                                           
 *
 * PROPRIETARY RIGHTS of CETC Company are involved in the                
 * subject matter of this material. All manufacturing, reproduction, use, 
 * and sales rights pertaining to this subject matter are governed by the 
 * license agreement. The recipient of this software implicitly accepts   
 * the terms of the license.                                              
 * 本软件文档资料是中电科新型智慧城市研究院有限公司的资产，任何人士阅读和                   
 * 使用本资料必须获得相应的书面授权，承担保密责任和接受相应的法律约束。     
 *
 *************************************************************************/

/**
 * 工程包名:   com.cetc.cloud.framework.api.model
 * 项目名称:   framework-plugin
 * 创建描述:   静态资源属性配置，暂由interfaces支持，后续由数据字典替换
 * Creator:     zhangliang
 * Create_Date: 2017/10/18
 * Updater:     zhangliang
 * Update_Date：2017/10/18
 * 更新描述:    zhangliang 补充
 **/
public interface CetcCloudStaticResource {

    /** SYSTEM COMMON *************************************************************************************************/
    int VOS_INT_OK                        = 0;                             // INT型，正常返回状态码
    int VOS_INT_NOK                       = -1;                            // INT型，错误返回状态码

    String VOS_STR_SUCCESS                = "SUCCESS";                     // INT型，正常返回状态码
    String VOS_STR_FAIL                   = "FAIL";                        // INT型，错误返回状态码
    String VOS_STR_NONE                   = "NONE";                        // String,空对象描述 \ 空签名
    String VOS_STR_UN_REGISTER            = "UNREGISTER";                   // String,服务未注册
    String VOS_STR_ERROR                  = "ERROR";                        // String，错误码，字符

    int VOS_INT_FALL_BACK                 = 10001;                         // INT型, 熔断错误码
    String VOS_STR_FALL_BACK              = "FALL BACK";                   // String, Fall back
    int VOS_INT_NONE_OBJ_REQUEST          = 10004;                         // String,非法对象
    String VOS_STR_NONE_OBJ_REQUEST       = "ILLEGAL OBJECT";              // String,非法对象
    int VOS_INT_INPUT_DB_ERROR            = 10005;                         // INT, 录入DB持久化失败
    String VOS_STR_INPUT_DB_ERROR         = "PERSISTENCE DB FAILED ";      // String, 录入DB持久化失败
    int VOS_INT_NONE_REMOTE_RESPONSE      = 10006;                          // Int,错误码，远程服务无响应
    String VOS_STR_NONE_REMOTE_RESPONSE   = "REMOTE SERVER NO RESPONSE";    // String,网络连接，远程服务无响应

    /** Process[Id generator] code description ************************************************************************/
    int VOS_INT_ID_GENERATOR_NO_RESPONSE  = 10101;                                // INT, id-generator 服务失效
    String VOS_STR_ID_GENERATOR_NO_RESPONSE = "ID_GENERATOR service no response"; // String, id-generator 服务失效

    /** Process[Msg frame] code description ***************************************************************************/
    int VOS_INT_MSG_FRAME_NO_RESPONSE     = 10201;                           // INT, msg-frame 服务失效
    String VOS_STR_MSG_FRAME_NO_RESPONSE  = "MSG_FRAME service no response"; // String, msg-frame 服务失效

    /** Process[Register frame] code description **********************************************************************/
    int VOS_INT_REGISER_FRAME_NO_RESPONSE    = 10301;                                 // INT, register-frame 服务失效
    String VOS_STR_REGISER_FRAME_NO_RESPONSE = "REGISTER_FRAME service no response"; // String, register-frame 服务失效

    /** Process[Redis frame] code description *************************************************************************/
    int VOS_INT_REDIS_FRAME_RESPONSE       = 10401;                             // INT, redis-frame 服务失效
    String VOS_STR_REDIS_FRAME_NO_RESPONSE = "REDIS_FRAME service no response"; // String, redis-frame 服务失效

    /** Process[Neuron frame] code description ************************************************************************/
    int VOS_INT_NEURON_FRAME_RESPONSE      = 10601;                               // INT, neuron-frame 服务失效
    String VOS_STR_NEURON_FRAME_NO_RESPONSE = "NEURON_FRAME service no response"; // String, neuron-frame 服务失效

    /** Process[Keygen frame] code description ************************************************************************/
    int VOS_INT_KEYGEN_FRAME_RESPONSE      = 10701;                               // INT, keygen-frame 服务失效
    String VOS_STR_KEYGEN_FRAME_NO_RESPONSE = "KEYGEN_FRAME service no response"; // String, keygen-frame 服务失效

    /** NET CONNECT ***************************************************************************************************/
    String VOS_STR_PING_PONG              = "PONG";                         // String,网络连接，ping PONG响应

    /** PAGE SPLIT ****************************************************************************************************/
    int DEFAULT_PAGE_TOTAL_NUM            = 10;                             // 系统全局默认分页容量
    int DEFAULT_PAGE_START_POSITION       = 1;                              // 系统全局默认起始页码

    /** SEARCH INDEX **************************************************************************************************/
    String INDEX_CETC_SEARCH_STR          = "";                             // CETC Cloud 搜索索引值
    String INDEX_CETC_SEARCH_TYPE_ALL_STR = "cetc.cloud.index.type.total";  // CETC Cloud 搜索索引类型

    String ROOT_PATH_BUILD_IMAGE = "http://10.190.55.58:8081/img/";
    String ROOT_PATH_BJ_IMAGE = "http://10.190.55.58:8081/img/";
    String ROOT_PATH_SANXIAO_IMAGE = "http://10.190.62.49:8080/fileServer/attachments/";

}
