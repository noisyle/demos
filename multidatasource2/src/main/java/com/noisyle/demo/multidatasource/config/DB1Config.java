package com.noisyle.demo.multidatasource.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.noisyle.demo.multidatasource.annotation.DB1;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "com.noisyle.demo.multidatasource.repository", annotationClass = DB1.class, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1Config {

    @Bean
    @ConfigurationProperties(prefix = "db.db1")
    @Primary
    public DataSource db1DataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    @Primary
    public SqlSessionFactoryBean db1SqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1DataSource());
        return sqlSessionFactoryBean;
    }
}
