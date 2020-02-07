package com.noisyle.demo.multidatasource.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noisyle.demo.multidatasource.entity.User;
import com.noisyle.demo.multidatasource.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    
    @Transactional
    public List<User> getList() {
        List<User> result = new LinkedList<User>();
        result.addAll(repo.getDb1List());
        result.addAll(repo.getDb2List());
        return result;
    }
}
