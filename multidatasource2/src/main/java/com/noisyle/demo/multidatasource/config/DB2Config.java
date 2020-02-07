package com.noisyle.demo.multidatasource.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.noisyle.demo.multidatasource.annotation.DB2;

@Configuration
@MapperScan(basePackages = "com.noisyle.demo.multidatasource.repository", annotationClass = DB2.class, sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DB2Config {

    @Bean
    @ConfigurationProperties(prefix = "dataSource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean db2SqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource2());
        return sqlSessionFactoryBean;
    }
}
