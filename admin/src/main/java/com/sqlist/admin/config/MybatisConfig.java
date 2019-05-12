package com.sqlist.admin.config;

import com.sqlist.admin.config.mybatis.MapWrapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.autoconfigure.ConfigurationCustomizer;

/**
 * @author SqList
 * @date 2019/5/13 1:01
 * @description
 *      返回Map的时候,将Map内的Key转换为驼峰的命名表达式  与 mybatis.configuration.map-underscore-to-camel-case=true 配合使用
 *      https://my.oschina.net/u/2278977/blog/1795969
 **/
@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setObjectWrapperFactory(new MapWrapperFactory());
            }
        };
    }

}