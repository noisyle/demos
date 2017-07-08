package com.noisyle.demo.sqlinject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noisyle.demo.sqlinject.entity.User;
import com.noisyle.demo.sqlinject.repository.UserRepository;

@Controller
public class LoginController {

	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/login")
	public ModelAndView login(ModelAndView model, User condition) {
		logger.debug("参数: {}", condition);
		model.addObject("user", userRepository.getUserByCondition(condition));
		model.setViewName("login.jsp");
		return model;
	}
	
	@RequestMapping("/login_safe")
	public ModelAndView loginSafe(ModelAndView model, User condition) {
		logger.debug("参数: {}", condition);
		model.addObject("user", userRepository.getUserByConditionSafe(condition));
		model.setViewName("login_safe.jsp");
		return model;
	}
}
