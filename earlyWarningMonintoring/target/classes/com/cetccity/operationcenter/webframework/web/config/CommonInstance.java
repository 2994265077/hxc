package com.cetccity.operationcenter.webframework.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class CommonInstance {
	@Value("${myprops.ElasticsearchIp:}")
    public   String ElasticsearchIp;
    
	@Value("${myprops.ElasticsearchPort:}")
    public  String ElasticsearchPort;
    
	@Value("${myprops.InternetIp:}")
    public  String InternetIp;

	@Value("${myprops.InternetPort:}")
    public  String InternetPort;
    
	@Value("${myprops.ServerIp:}")
    public  String ServerIp;
	
	@Value("${myprops.ServerPort:}")
    public  String ServerPort;
    /**
     *物联网
     */
	@Value("${myprops.IotDeviceListUrl:}")
    public  String IotDeviceListUrl;
	@Value("${myprops.IotdataQueryUrl:}")
    public  String IotdataQueryUrl;
    /**
     * Primary Datasource
     */
	@Value("${myprops.PrimaryDatasourceIp:}")
    public  String PrimaryDatasourceIp;
	@Value("${myprops.PrimaryDatasourceUsername:}")
    public  String PrimaryDatasourceUsername;
	@Value("${myprops.PrimaryDatasourcePassword:}")
    public  String PrimaryDatasourcePassword;

    /**
     * ES别名
     */
	@Value("${myprops.Alias:}")
    public  String Alias;
	@Value("${myprops.AliasSearchURL:}")
    public  String AliasSearchURL;
	@Value("${myprops.AliasManageURL:}")
    public  String AliasManageURL;

    /**
     * 实时获取永兴元数据URL
     *
     * @return
     */
    public  String RealTimeURLTbWeijiInInfo;
    public  String RealTimeURLTbWeijOutInfo;
    public  String RealTimeURLTbWeijiOutVistitsDay;
    public  String RealTimeURLTbWeijiSdmInfo;

    @Value("${myprops.WeatherForecastURL:}")
    public  String WeatherForecastURL;
    
    @Value("${myprops.addressSearch:}")
    private String addressSearch;
    
    @Value("${myprops.comprehensiveSearch:}")
    private String comprehensiveSearch;

    public void setElasticsearchIp(String elasticsearchIp) {
    	CommonStaticInstance.elasticsearchIp = elasticsearchIp;
        ElasticsearchIp = elasticsearchIp;
    }

    public  void setElasticsearchPort(String elasticsearchPort) {
    	CommonStaticInstance.elasticsearchPort = elasticsearchPort;
        ElasticsearchPort = elasticsearchPort;
    }
}
