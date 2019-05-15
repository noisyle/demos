package com.noisyle.demo.multidatasource.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.noisyle.demo.multidatasource.annotation.DB1;
import com.noisyle.demo.multidatasource.entity.User;

@Mapper
@DB1
public interface DB1UserRepository {
    List<User> findAll();
}
