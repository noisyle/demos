package com.noisyle.demo.multidatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

import com.noisyle.demo.multidatasource.datasource.MultipleDataSource;
import com.noisyle.demo.multidatasource.datasource.MultipleDataSource.Target;

@Configuration
@MapperScan(basePackages = "com.noisyle.demo.multidatasource.repository", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "dataSource.db1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "dataSource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        MultipleDataSource dataSource = new MultipleDataSource();

        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put(Target.DB1, dataSource1());
        dsMap.put(Target.DB2, dataSource2());
        dataSource.setTargetDataSources(dsMap);
        dataSource.setDefaultTargetDataSource(dataSource1());

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
