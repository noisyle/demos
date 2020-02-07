package com.noisyle.demo.multidatasource.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.noisyle.demo.multidatasource.annotation.DataSource;
import com.noisyle.demo.multidatasource.datasource.MultipleDataSource.Target;
import com.noisyle.demo.multidatasource.entity.User;

@Mapper
public interface UserRepository {
    List<User> getDb1List();

    @DataSource(Target.DB2)
    List<User> getDb2List();
}
