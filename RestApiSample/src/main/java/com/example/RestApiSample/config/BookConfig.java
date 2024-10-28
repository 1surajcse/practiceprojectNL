package com.example.RestApiSample.config;/*
 * @author -Suraj Tiwari
 */

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
@EnableJpaRepositories(basePackages = "com.example.RestApiSample.book",entityManagerFactoryRef = "bookEntityManagerFactoryBean", transactionManagerRef = "bookTransactionManager")

public class BookConfig {

    @Bean
    @ConfigurationProperties(prefix ="spring.book.datasource" )
    public DataSource bookDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactoryBean(EntityManagerFactoryBuilder managerFactoryBuilder
            , @Qualifier("bookDataSource") DataSource bookDataSource){
        Map<String,Object> props=new HashMap<>();
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        return managerFactoryBuilder
                .dataSource(bookDataSource)
               // .properties(props)
                .packages("com.example.RestApiSample.book")
                .persistenceUnit("book")
               .build();
    }

    @Bean
    PlatformTransactionManager bookTransactionManager(@Qualifier("bookEntityManagerFactoryBean") EntityManagerFactory bookEntityManagerFactoryBean ){
        return new JpaTransactionManager(bookEntityManagerFactoryBean);
    }
}
