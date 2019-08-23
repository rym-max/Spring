package com.tongji.bwm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
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

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef ="entityManagerFactoryEU",
//        transactionManagerRef = "transactionManagerEU",
//        basePackages = {"com.tongji.bwm.repository"})
public class EUConfig {

//    @Autowired
//    @Qualifier("euDataSource")
//    private DataSource euDataSource;
//
//    @Autowired
//    private HibernateProperties hibernateProperties;
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Primary
//    @Bean(name = "entityManagerEU")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
//        return entityManagerFactoryEU(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactoryEU")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryEU(EntityManagerFactoryBuilder builder){
//
//        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
//                jpaProperties.getProperties(),new HibernateSettings()
//        );
//        return builder
//                .dataSource(euDataSource)
//                .properties(properties)
//                .packages("com.tongji.bwm.entity.eu")
//                .persistenceUnit("primaryPersistenceUnit")
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerEU")
//    public PlatformTransactionManager transactionManagerEU(EntityManagerFactoryBuilder builder){
//        return new JpaTransactionManager(entityManagerFactoryEU(builder).getObject());
//    }
//




}
