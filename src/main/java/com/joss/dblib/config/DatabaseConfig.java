package com.joss.dblib.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.joss.dblib.properties.DatabaseProperties;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"${database.config.base}"})
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);
    private final DatabaseProperties properties;

    @Autowired
    public DatabaseConfig(DatabaseProperties properties) {
        this.properties = properties;
    }


    @Bean(name = {"dataSource"})
    public DataSource dataSource() {
        log.info("Starting dataSource()");
        HikariConfig hc = new HikariConfig();
        hc.setDriverClassName(this.properties.getDriverClassName());
        hc.setUsername(this.properties.getUsername()); //todo: extract from secret file, maybe?
        hc.setPassword(this.properties.getSecret()); //todo: extract from secret file, maybe?
        hc.setPoolName(this.properties.getPoolName());
        hc.setMinimumIdle(this.properties.getMinPoolSize());
        hc.setMaximumPoolSize(this.properties.getMaxPoolSize());
        hc.setMaxLifetime(this.properties.getMaxLifetime());
        hc.setValidationTimeout(this.properties.getValidationTimeout());
        hc.setSchema(this.properties.getSchema());

        return new HikariDataSource(hc);
    }

    @Bean(name= {"entityManagerFactory"})
    public EntityManagerFactory entityManagerFactory() {
        log.info("Starting entityManagerFactory()");
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan(this.properties.getScanPackages());
        factory.setDataSource(this.dataSource());
        factory.setJpaPropertyMap(properties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = {"transactionManager"})
    public PlatformTransactionManager transactionManager() {
        log.info("Starting transactionManager()");
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(this.entityManagerFactory());
        return jpaTransactionManager;
    }

    protected Map<String, Object> properties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        props.put("hibernate.c3p0.min_size", this.properties.getMinPoolSize());
        props.put("hibernate.c3p0.max_size", this.properties.getMaxPoolSize());
        props.put("hibernate.c3p0.timeout", this.properties.getMaxLifetime());
        props.put("hibernate.c3p0.max_statements", 100);
        props.put("hibernate.show_sql", this.properties.isShowSql());
        props.put("hibernate.format_sql", this.properties.isFormatSql());
        return props;
    }



}
