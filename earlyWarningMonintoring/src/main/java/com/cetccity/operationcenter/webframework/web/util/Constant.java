package com.cetccity.operationcenter.webframework.web.util;

/**
 * 自定义常量类
 * 项目名称：MXOTEP - 培训教学服务管理与资源平台  
 * 类名称：Constant   
 * 类描述：   
 * 创建人：何良明   
 * 创建时间：2016-3-31 上午09:16:22   
 * 修改人：何良明   
 * 修改时间：2016-3-31 上午09:16:22   
 * 修改备注：   
 * @version
 */
public final class Constant {
	/** 日志信息 */
	public final static String LOGTYPE_LOGIN = "0"; // 登录日志
	public final static String LOGTYPE_ADD = "1"; // 添加操作日志
	public final static String LOGTYPE_UPDATE = "2"; // 修改操作日志
	public final static String LOGTYPE_DEL = "3"; // 删除操作日志
	
	/** 账号状态 **/
	public final static String LOGIN_STATUS_YES = "1"; //启用
	public final static String LOGIN_STATUS_NO = "2"; //停用
	
	/** 公告状态 **/
	public final static String IS_NOTICE_RELEASE_OFF = "0"; //未发布
	public final static String IS_NOTICE_RELEASE_ON = "1"; //已发布
	public final static String IS_NOTICE_RELEASE_OUT = "2"; //已过期
	
	/** 阿里云短信API参数 **/
    public static final String AccessId ="LTAI2hvSDUA7cqTg";
    public static final String AccessKey = "BzJqaPRHHfm4S1NQnXLP0xfJEH2sey";
    public static final String MNSEndpoint = "https://1471077634123020.mns.cn-hangzhou.aliyuncs.com/";
    public static final String Topic ="sms.topic-cn-hangzhou";
    public static final String SignName = "中电科研究院";
    public static final String SMSTemplateCode = "SMS_77340044";
    
    /** RSA非对称加密算法的公钥、私钥 **/
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgmSc90f0KD5c16E2imiyGTta5Jo0OHCq3qDcddsddXkrf0lHXi3VAH-eV89sgguPzBUBihVAnesbyw6MqSSFHfS590hJyhwmHE9wrLaz2IYbpsUKNUjx2_pVXMtvyvJVlCyofgotDFgEf7Ief8I4qhFIYVO4IgBke_u7_Cd7t_wIDAQAB";
    public static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKCZJz3R_QoPlzXoTaKaLIZO1rkmjQ4cKreoNx12x11eSt_SUdeLdUAf55Xz2yCC4_MFQGKFUCd6xvLDoypJIUd9Ln3SEnKHCYcT3CstrPYhhumxQo1SPHb-lVcy2_K8lWULKh-Ci0MWAR_sh5_wjiqEUhhU7giAGR7-7v8J3u3_AgMBAAECgYBAtTTQfdl8wkdk2xLc303tV7rAoz3WX5b-pPKAFqnBfwqgIDgFpxU5HyHKk5TJBrQd6RHGIHzpS1j528XUdd7IMTiYkuFaCBVMaoGS7do3nSeBkifP8rPh0H_RSBtA6RVZroKTY5TB-sdCKResC2b5UUe_XnuLLQZTn_iVtZLUEQJBANyM-OoHq4LYT2qb90CdpEACk5p7-oioUTdiLNnjHP8_sCZh95OVK7UZd4Z6VGj_KLNvcFaP5hZs8dBs5vN3dOsCQQC6aU-XHGTGbHeNMbltssJni8Fk2ArXqDrotWM8t12yGxC0WCTFfFROENAL_Tq_RjY5MNQaQqmrLNvIEj0tm7Y9AkA31_IGv95riP3Czq7yS3gVRRG-ofmztHqr_U3B9cGX17ZwYYngcnrYphQflsXcuA7EMElPgawOrZMk5Q4tQ2f1AkA6TFUn3SlnJOT-s15agF1sJLkG4MoDSAmkXZX7lx0mZnCC9k4JtShogbmPBlMpHrw2hp7O16pYbl8N4yZe7SPBAkEA2ZclS8ASv33WLhgRNLC5lJqfivSjogwSpM1h6n1vDS0h_sPCxY47FWdhZxj963LC-Fble8_h7WSACNeLJkEuVQ";
    
    /**
     * 公共文件
     */
    public static final String MAP_CONFIG = "mapconfig.xml";
    
    public static final String NEARLY_SEARCH_MAP_CONFIG = "nearlySearchMapConfig.xml";
    
    public static final String RUN_DISPLAY_MAP_CONFIG = "runDisplayMapConfig.xml";
    
    public static final String SEARCH_TAG_MENU = "searchTagMenu.xml";
    
    /**
     * 初始化密码
     */
    public static final String INIT_PWD = MD5Encoder.encode("123456");
    
    /**
     * 一级组织的ID为00000000-0000-0000-0000-000000000000
     * 数据来源于福田OA
     */
    public static final String DEPT_PARENT_ID = "{00000000-0000-0000-0000-000000000000}";
    
    /**
     * 1：后台菜单， 2：前台菜单
     */
    public static final Integer FRONT_MENU_TYPE=2;
    
    public static final Integer BACK_MENU_TYPE=1;

    public static final Integer TABLEAU_MENU_TYPE=3;

}
