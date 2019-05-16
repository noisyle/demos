package com.noisyle.demo.optional.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noisyle.demo.optional.bean.User;
import com.noisyle.demo.optional.repository.MockUserRepository;

@Service
public class UserService {

    @Autowired
    private MockUserRepository userRepository;

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
