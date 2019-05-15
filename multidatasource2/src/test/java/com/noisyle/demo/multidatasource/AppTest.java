package com.noisyle.demo.multidatasource;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.noisyle.demo.multidatasource.entity.User;
import com.noisyle.demo.multidatasource.repository.DB1UserRepository;
import com.noisyle.demo.multidatasource.repository.DB2UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    private DB1UserRepository repo1;
    @Autowired
    private DB2UserRepository repo2;
    
    @Test
    public void test1() {
        List<User> list1 = repo1.findAll();
        Assert.assertNotNull(list1);
    }
    
    @Test
    public void test2() {
        List<User> list2 = repo2.findAll();
        Assert.assertNotNull(list2);
    }
}
