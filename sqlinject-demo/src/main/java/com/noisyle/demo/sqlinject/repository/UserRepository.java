package com.noisyle.demo.sqlinject.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noisyle.demo.sqlinject.entity.User;
import com.noisyle.demo.sqlinject.persistence.UserMapper;

@Repository
public class UserRepository {
	
	private final static Logger logger = LoggerFactory.getLogger(UserRepository.class);
	
	@Autowired
    private UserMapper userMapper;
	
	public User getUserByCondition(User condition) {
		User user = null;
		List<User> userList = userMapper.listUsers(condition);
		if(userList!=null && !userList.isEmpty()) {
			user = userList.get(0);
		}
		logger.debug("查询结果: {}", user);
		return user;
	}
	
	public User getUserByConditionSafe(User condition) {
		User user = null;
		List<User> userList = userMapper.listUsersSafe(condition);
		if(userList!=null && !userList.isEmpty()) {
			user = userList.get(0);
		}
		logger.debug("查询结果: {}", user);
		return user;
	}

}