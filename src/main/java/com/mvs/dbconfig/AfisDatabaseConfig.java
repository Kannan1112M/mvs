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
        basePackages = "com.mvs.afis.repository",
        entityManagerFactoryRef = "afisEntityManagerFactory",
        transactionManagerRef = "afisTransactionManager"
)
public class AfisDatabaseConfig {

    @Bean
    @ConfigurationProperties("app.datasource.afis")
    public DataSourceProperties afisDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource afisDataSource() {
        return afisDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean afisEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("afisDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.mvs.afis.entity")
                .persistenceUnit("afis")
                .build();
    }

    @Bean
    public PlatformTransactionManager afisTransactionManager(
            @Qualifier("afisEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}