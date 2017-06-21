package com.noisyle.demo.sqlinject.persistence;

import java.util.List;

import com.noisyle.demo.sqlinject.entity.User;

public interface UserMapper {
    public List<User> listUsers(User condition);
    
    public List<User> listUsersSafe(User condition);
}
