package com.tongji.bwm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef ="entityManagerFactoryGE",
        transactionManagerRef = "transactionManagerGE",
        basePackages = {"com.tongji.bwm.repository.*"})
@EntityScan(
        basePackages = {"com.tongji.bwm.entity.*"}
)
public class GEConfig {

    @Autowired
    @Qualifier("geDataSource")
    private DataSource geDataSource;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "entityManagerGE")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactoryGE(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryGE")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryGE(EntityManagerFactoryBuilder builder){

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(),new HibernateSettings()
        );

        return builder
                .dataSource(geDataSource)
                .properties(properties)
                .packages("com.tongji.bwm.entity")
                .persistenceUnit("gePersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerGE")
    public PlatformTransactionManager transactionManagerGE(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(entityManagerFactoryGE(builder).getObject());
    }
}
