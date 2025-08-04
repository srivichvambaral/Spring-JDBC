package com.expressmovies.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;
import java.io.InputStream;

@Configuration
public class DBConfig {
    @Bean
    public DataSource dataSource() throws Exception {
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
        props.load(input);

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(props.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(props.getProperty("spring.datasource.url"));
        ds.setUsername(props.getProperty("spring.datasource.username"));
        ds.setPassword(props.getProperty("spring.datasource.password"));
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        return new JdbcTemplate(dataSource());
    }
}
