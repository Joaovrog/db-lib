package com.joss.dblib.annotation;

import com.joss.dblib.config.DatabaseConfig;
import com.joss.dblib.properties.DatabaseProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({DatabaseConfig.class, DatabaseProperties.class})
public @interface EnableLibDatabase {
}
