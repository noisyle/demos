package com.noisyle.demo.sqlinject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noisyle.demo.sqlinject.entity.User;
import com.noisyle.demo.sqlinject.repository.UserRepository;

@Service
public class TransactionService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> list() {
		return userRepository.list();
	}
	
	@Transactional
	public void add(User user) {
		userRepository.add(user);
		if(true){
			throw new RuntimeException("test");
		}
	}
}