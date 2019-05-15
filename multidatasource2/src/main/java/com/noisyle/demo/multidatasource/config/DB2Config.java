package com.noisyle.demo.multidatasource.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.noisyle.demo.multidatasource.annotation.DB2;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "com.noisyle.demo.multidatasource.repository", annotationClass = DB2.class, sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DB2Config {

    @Bean
    @ConfigurationProperties(prefix = "db.db2")
    public DataSource db2DataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean db2SqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2DataSource());
        return sqlSessionFactoryBean;
    }
}
