package com.tongji.bwm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(
        //exclude = {DataSourceAutoConfiguration.class
        )
@EnableTransactionManagement
@EnableAsync
@EnableJpaAuditing
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    }


    //重写configure方法，启动主控servlet
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return  application.sources(Application.class);
    }
}
