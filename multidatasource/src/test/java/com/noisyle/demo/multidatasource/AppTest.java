package com.noisyle.demo.multidatasource;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.noisyle.demo.multidatasource.entity.User;
import com.noisyle.demo.multidatasource.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    private UserRepository repo;
    
    @Test
    public void test() {
        List<User> list = repo.getList();
        Assert.assertNotNull(list);
    }
}
