package com.cetccity.operationcenter.webframework.alarmcenter.api.model;

/**
 * Description： 全局常量定义
 * Created by luolinjie on 2018/10/9.
 */
public interface CommonInstance {
    /**
     * DataX 的安装路径
     */
    public static final String DataXInstallPath = "D:\\Software\\install\\Environment\\DataX\\datax\\bin";

    /**
     * 数据接入方式：
     * 0：数据库方式
     * 1：接口方式
     */
    public static final int TYPE_DB = 0;
    public static final int TYPE_INTERFACE = 1;

    /**
     * 任务开启状态：
     * 0：关闭
     * 1：开启
     */
    public static final int JOB_DISABLED = 0;
    public static final int JOB_ENABLED = 1;

    /**
     * 全局Http默认时间参数设定
     */
    int HTTP_SOCKET_TIMEOUT = 20000;//默认创建套接字超时时间20s
    int HTTP_CONNECT_TIMEOUT = 6000;//默认获取连接超时
    int HTTP_CONNECT_RESPONSE_TIMEOUT = 20000;//默认等待返回值超时时间20s

    /**
     * 数据库默认全局字段名称、值
     */
    String GLOBAL_COLNAME_INCRE_ID = "OBJECT_ID";
    String GLOBAL_COLNAME_CREATE_TIME = "YJJC_CREATE_TIME";
    String GLOBAL_COLNAME_UPDATE_TIME = "YJJC_UPDATE_TIME";
    String GLOBAL_COL_CREATE_TIME_DEFAULT_VAL = "SYSDATE";
    String GLOBAL_COL_UPDATE_TIME_DEFAULT_VAL = "SYSDATE";

    /**
     * 默认分页大小
     */
    int DefaultPageSize = 100;


    /**
     * 接口方式分页参数占位符名称
     */
    String PAGE_NUM_NAME = "$PAR_pageSize$";
    String PAGE_SIZE_NAME = "$PAR_pageNum$";


    /**
     * Excel默认数据起始行 --Excel中从0行开始记录
     */
    int DEFAULT_EXCEL_STARTWROW = 1;//默认需要Excel加表头

    String HTTP_RES_CODE = "code";
    String HTTP_RES_DATA = "data";

    /**
     * 是否做分页查询
     */
    int NO_PAGING = 0;
    int DO_PAGING = 1;

    /**
     * 当前请求是否成功
     */
    int SUCCESS = 1;
    int FAIL = 0;
    int ERROR = -1;

    /**
     * 默认起始页码
     */
    int DEFAULT_START_PAGE_NUM = 1;

    /**
     * 分页参数组织类型
     */
    String HTTP_PAGING_TYPE_NORMAL = "1";   //一般类型：pageNum=1&pageSize=100

    String HTTP_PAGING_TYPE_JSON_QAJJ = "2";//安监接口类型:page={"pagenum":"1","pagesize":"50" }
    String HTTP_PAGING_TYPE_JSON_QAJJ_key_pagenum = "pagenum";
    String HTTP_PAGING_TYPE_JSON_QAJJ_key_pagesize = "pagesize";

    String HTTP_PAGING_TYPE_COUNT = "3";    //城管案件：STARTPOSITION=0&MAXCOUNT=1000
    String HTTP_PAGING_TYPE_COUNT_key_chengguan = "STARTPOSITION";    //城管案件：STARTPOSITION=0&MAXCOUNT=1000

    /**
     * Outer job Names
     */
    String JOB_calc_trouble_sanxiao = "calc_trouble_sanxiao";
    String JOB_get_today_xinfang = "get_today_xinfang";
    String JOB_add_chengguanevent_attach = "add_chengguanevent_attach";
    String JOB_get_weather_alarm_info = "get_weather_alarm_info";
    String JOB_refresh_sanxiao_list = "refresh_sanxiao_list";
    String JOB_generate_water_AQI_info = "generate_water_AQI_info";
    String JOB_refresh_video = "refresh_video";

    /**
     * param 统一组织形式
     */
    String GLOBAL_PARAM_KEYNAME = "PARAM_KEY";
    String GLOBAL_PARAM_VALUENAME = "PARAM_VALUE";

    /**
     * 水质AQI类型
     */
    public static final int AQI_Pm10_24h = 1;
    public static final int AQI_Pm2_5_24h = 2;
    public static final int AQI_O3_1h = 3;
    public static final int AQI_O3_8h = 4;
    public static final int AQI_NO2_24h = 5;
    public static final int AQI_SO2_24h = 6;
    public static final int AQI_CO_24h = 7;
    public static final int AQI_NO2_1h = 8;
    public static final int AQI_SO2_1h = 9;
    public static final int AQI_CO_1h = 10;
}
