package com.ylan.mybatisflexdemo01.config;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import com.mybatisflex.spring.boot.SqlSessionFactoryBeanCustomizer;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by pepsi-wyl
 * @date 2023-07-30 16:36
 */

@Configuration
public class MybatisFlexConfig
        implements ConfigurationCustomizer, SqlSessionFactoryBeanCustomizer, MyBatisFlexCustomizer {

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        System.out.println("Mybatis Flex 配置");
    }

    @Override
    public void customize(FlexConfiguration flexConfiguration) {
        System.out.println("Mybatis Configuration 配置");
        flexConfiguration.setLogImpl(StdOutImpl.class);
    }

    @Override
    public void customize(SqlSessionFactoryBean sqlSessionFactoryBean) {
        System.out.println("SqlSessionFactoryBean 配置");
    }

}