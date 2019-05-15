package com.noisyle.demo.multidatasource.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.noisyle.demo.multidatasource.annotation.DB2;
import com.noisyle.demo.multidatasource.entity.User;

@Mapper
@DB2
public interface DB2UserRepository {
    List<User> findAll();
}
