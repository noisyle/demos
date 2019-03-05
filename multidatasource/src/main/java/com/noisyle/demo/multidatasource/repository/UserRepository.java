package com.noisyle.demo.multidatasource.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.noisyle.demo.multidatasource.annotation.DataSource;
import com.noisyle.demo.multidatasource.entity.User;

@Mapper
public interface UserRepository {
    @DataSource("db1")
    List<User> getDb1List();
    
    @DataSource("db2")
    List<User> getDb2List();
}
