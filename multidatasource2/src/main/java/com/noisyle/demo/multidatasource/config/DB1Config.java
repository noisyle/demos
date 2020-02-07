package com.noisyle.demo.multidatasource.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.noisyle.demo.multidatasource.annotation.DB1;

@Configuration
@MapperScan(basePackages = "com.noisyle.demo.multidatasource.repository", annotationClass = DB1.class, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1Config {

    @Bean
    @ConfigurationProperties(prefix = "dataSource.db1")
    @Primary
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    @Primary
    public SqlSessionFactoryBean db1SqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource1());
        return sqlSessionFactoryBean;
    }
}
