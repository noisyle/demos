package com.noisyle.demo.jwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import com.noisyle.demo.jwt.auth.User;

@Repository
@ConfigurationProperties(prefix = "mock")
public class UserRepository {
    private List<User> users;
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }
}
