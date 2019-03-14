package com.noisyle.demo.multidatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

import com.noisyle.demo.multidatasource.datasource.MultipleDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "com.noisyle.demo.multidatasource.repository", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "db.db1")
    public DataSource dataSource1() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "db.db2")
    public DataSource dataSource2() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        MultipleDataSource dataSource = new MultipleDataSource();

        dataSource.setDefaultTargetDataSource(dataSource1());
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("db1", dataSource1());
        dsMap.put("db2", dataSource2());
        dataSource.setTargetDataSources(dsMap);

        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }
}
