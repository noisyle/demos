package com.noisyle.demo.multidatasource;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;

import com.noisyle.demo.multidatasource.entity.User;
import com.noisyle.demo.multidatasource.repository.UserRepository;
import com.noisyle.demo.multidatasource.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MyTestConfiguration.class)
public class AppTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserRepository repo;
    @Autowired
    private UserService service;
    
    @Test
    public void test1() {
        List<User> list = repo.getDb1List();
        Assert.assertNotNull(list);
        logger.debug("result: {}", list);
    }
    
    @Test
    public void test2() {
        List<User> list = repo.getDb2List();
        Assert.assertNotNull(list);
        logger.debug("result: {}", list);
    }
    
    @Test
    public void test3() {
        List<User> list = service.getList();
        logger.debug("result: {}", list);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 2);
        Assert.assertNotEquals(list.get(0).getName(), list.get(1).getName());
    }
}

@TestConfiguration
class MyTestConfiguration {
    // 加载测试数据
    @Bean
    public DataSourceInitializer dataSource1Initializer(@Qualifier("dataSource1") DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("db1-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("db1-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public DataSourceInitializer dataSource2Initializer(@Qualifier("dataSource2") DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("db2-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("db2-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
