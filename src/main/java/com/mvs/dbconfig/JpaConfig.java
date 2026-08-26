package com.mvs.dbconfig;

import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfig {

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            JpaProperties jpaProperties) {

        HibernateJpaVendorAdapter vendorAdapter =
                new HibernateJpaVendorAdapter();

        Map<String, Object> properties =
                new HashMap<>(jpaProperties.getProperties());

        return new EntityManagerFactoryBuilder(
                vendorAdapter,
                dataSource -> properties,
                null
        );
    }
}