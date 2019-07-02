package com.cetc.cloud.datasynch.provider.config;//package com.cetc.cloud.datasynch.provider.config;
/**
 * @Description: //mybatis配置
 **/

import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.cetc.cloud.datasynch.provider.mapper")
public class MybatisConfig {
    /**
     * Sequence主键自增配置
     * @return 返回oracle自增类
     * @author zhenggc
     * @date 2019/1/2
     */
    @Bean
    public OracleKeyGenerator oracleKeyGenerator(){
        return new OracleKeyGenerator();
    }
}
