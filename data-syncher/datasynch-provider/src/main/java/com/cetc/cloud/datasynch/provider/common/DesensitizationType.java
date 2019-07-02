package com.cetc.cloud.datasynch.provider.common;

/**
 * Description：
 * Created by luolinjie on 2018/8/30.
 */
public interface DesensitizationType {


    //对于excel中的列，目标列所在列的下标减去3得到的值
    //    人名
    public static int PERSON_NAME = 1;
    //    企业名称
    public static int ENTERPRISE_NAME = 2;
    //    电话号码
    public static int TELEPHONE_NUM = 3;
    //    QQ号码
    public static int QQ_NUMBER = 4;
    //    身份证号
    public static int IDCARD_NUM = 5;
    //    详细地址
    public static int DETAIL_ADDRESS = 6;
    //    标准地址
    public static int STANDARD_ADDRESS = 7;
    //    经纬度
    public static int COORDINATE_VALUE = 8;
}
