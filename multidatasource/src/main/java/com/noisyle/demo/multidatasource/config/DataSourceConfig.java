package com.noisyle.demo.multidatasource.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "com.noisyle.demo.multidatasource.repository", sqlSessionFactoryRef = DataSourceConfig.PREFIX + "SqlSessionFactory")
public class DataSourceConfig {
    
    final static public String PREFIX = "db1";
    
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Bean(PREFIX + "DataSource")
    @ConfigurationProperties(prefix = "db." + PREFIX)
    @Primary
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean(PREFIX + "SqlSessionFactory")
    @ConfigurationProperties(prefix = "mybatis")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }

}
