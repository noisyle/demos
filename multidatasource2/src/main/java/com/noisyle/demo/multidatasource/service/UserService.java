package com.noisyle.demo.multidatasource.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noisyle.demo.multidatasource.entity.User;
import com.noisyle.demo.multidatasource.repository.DB1UserRepository;
import com.noisyle.demo.multidatasource.repository.DB2UserRepository;

@Service
public class UserService {
    @Autowired
    private DB1UserRepository repo1;
    @Autowired
    private DB2UserRepository repo2;
    
    @Transactional
    public List<User> getList() {
        List<User> result = new LinkedList<User>();
        result.addAll(repo1.findAll());
        result.addAll(repo2.findAll());
        return result;
    }
}
