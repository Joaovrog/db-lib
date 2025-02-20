package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"${database.config.base}"})
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);
    //Todo: some database properties class?
    //Todo: database properties injection


    @Bean(name = {"dataSource"})
    public DataSource dataSource() {
        log.info("Starting dataSource()");
        HikariConfig hc = new HikariConfig();
        hc.setDriverClassName(null); //todo: field from database properties class
        hc.setJdbcUrl(null); //todo: field from database properties class
        hc.setUsername(null); //todo: field from database properties class
        hc.setPassword(null); //todo: field from database properties class
        hc.setPoolName(null); //todo: field from database properties class
        hc.setMinimumIdle(0); //todo: field from database properties class
        hc.setMaximumPoolSize(0); //todo: field from database properties class
        hc.setMaxLifetime(0); //todo: field from database properties class
        hc.setValidationTimeout(0L); //todo: field from database properties class
        hc.setSchema(null); //todo: field from database properties class

        return new HikariDataSource(hc);
    }

    @Bean(name= {"entityManagerFactory"})
    public EntityManagerFactory entityManagerFactory() {
        log.info("Starting entityManagerFactory()");
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan(""); //todo: field from database properties class
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
        props.put("hibernate.c3p0.min_size", null); //todo: field from database properties class
        props.put("hibernate.c3p0.max_size", null); //todo: field from database properties class
        props.put("hibernate.c3p0.timeout", null); //todo: field from database properties class
        props.put("hibernate.c3p0.max_statements", 100);
        props.put("hibernate.show_sql", null); //todo: field from database properties class
        props.put("hibernate.format_sql", null); //todo: field from database properties class
        return props;
    }



}
