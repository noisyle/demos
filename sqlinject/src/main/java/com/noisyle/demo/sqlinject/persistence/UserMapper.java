package com.noisyle.demo.sqlinject.persistence;

import java.util.List;

import com.noisyle.demo.sqlinject.entity.User;

public interface UserMapper {
    public List<User> getUserByCondition(User condition);
    
    public List<User> getUserByConditionSafe(User condition);
    
    public List<User> list();
    
    public void add(User user);
}
