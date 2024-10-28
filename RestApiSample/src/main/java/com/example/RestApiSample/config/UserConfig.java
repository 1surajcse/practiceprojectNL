package com.example.RestApiSample.config;/*
 * @author -Suraj Tiwari
 */

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.RestApiSample.user",entityManagerFactoryRef = "userEntityManagerFactoryBean", transactionManagerRef = "userTransactionManager")
public class UserConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix ="spring.user.datasource" )
    public DataSource userDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactoryBean(EntityManagerFactoryBuilder managerFactoryBuilder
                                                                            , @Qualifier("userDataSource") DataSource userdataSource){

       Map<String,Object> props=new HashMap<>();
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        return managerFactoryBuilder.dataSource(userdataSource)
                   // .properties(props)
                .persistenceUnit("user").
                packages("com.example.RestApiSample.user").build();
    }

    @Bean
    public PlatformTransactionManager userTransactionManager(@Qualifier("userEntityManagerFactoryBean")EntityManagerFactory userEntityManagerFactoryBean ){
        return new JpaTransactionManager(userEntityManagerFactoryBean);
    }


}
