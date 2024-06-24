package com.mruruc.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;


@Configuration(value ="DbConfig Bean")
@PropertySource("classpath:datasource-config.properties")
public class DbConfig {
    @Value(value = "${jdbc.driverClassName}")
    private String driverClassName;
     @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Value("${connection.pool.max.size}")
    private int maxPoolSize;
    @Value("${connection.pool.name}")
    private String poolName;


    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(driverClassName);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);

        hikariDataSource.setMaximumPoolSize(maxPoolSize);
        hikariDataSource.setPoolName(poolName);
        hikariDataSource.setAutoCommit(true);

        return hikariDataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }

}
