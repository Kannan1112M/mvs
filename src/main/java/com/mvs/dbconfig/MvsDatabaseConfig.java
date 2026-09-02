package com.mvs.dbconfig;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.mvs.user.repository",
        entityManagerFactoryRef = "mvsEntityManagerFactory",
        transactionManagerRef = "mvsTransactionManager"
)
public class MvsDatabaseConfig {

    @Bean
    @ConfigurationProperties("app.datasource.mvs")
    public DataSourceProperties mvsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mvsDataSource() {
        return mvsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mvsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mvsDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto", "none");

        return builder
                .dataSource(dataSource)
                .packages("com.mvs.user.entity")
                .persistenceUnit("mvsdatabase")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager mvsTransactionManager(
            @Qualifier("mvsEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}