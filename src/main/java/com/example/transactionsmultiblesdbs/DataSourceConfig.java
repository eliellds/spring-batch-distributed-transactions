package com.example.transactionsmultiblesdbs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springDs() {
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "app.datasource")
    @Bean
    public DataSource appDS() {
        return DataSourceBuilder.create().build();
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManagerApp(@Qualifier("appDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
