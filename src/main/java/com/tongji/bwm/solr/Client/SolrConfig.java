package com.tongji.bwm.solr.Client;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Data
@Component
@ConfigurationProperties(prefix = "solrserver")
@Configuration
@EnableAsync
public class SolrConfig {

    private String defaultUrl;
    //创建异步任务
    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
//    private int keepAliveSeconds;

    @Bean
    public Executor solrTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
//        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("Solr请求线程");
        executor.initialize();
        return executor;
    }

}
