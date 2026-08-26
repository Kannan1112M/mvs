package com.mvs.dbconfig;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.mvs.reg.repository",
        entityManagerFactoryRef = "regEntityManagerFactory",
        transactionManagerRef = "regTransactionManager"
)
public class RegDatabaseConfig {

    @Bean
    @ConfigurationProperties("app.datasource.reg")
    public DataSourceProperties voterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource regDataSource() {
        return voterDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean regEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("regDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.mvs.reg.entity")
                .persistenceUnit("reg")
                .build();
    }

    @Bean
    public PlatformTransactionManager regTransactionManager(
            @Qualifier("regEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}