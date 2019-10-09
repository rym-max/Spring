package com.tongji.bwm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DataSourcesConfig {

    @Bean(name="euDataSource")
    @Qualifier("euDataSource")
    @Primary
    @ConfigurationProperties(prefix = "eusource.datasource")
    public DataSource euDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name="geDataSource")
    @ConfigurationProperties(prefix = "gesource.datasource")
    public DataSource geDataSource(){
        //这个设置
        return DataSourceBuilder.create().build();
    }


}
