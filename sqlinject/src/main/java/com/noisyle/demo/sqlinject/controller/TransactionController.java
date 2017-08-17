package com.noisyle.demo.sqlinject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.noisyle.demo.sqlinject.entity.User;
import com.noisyle.demo.sqlinject.service.TransactionService;

@Controller
public class TransactionController {

	private final static Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService service;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.addObject("users", service.list());
		model.setViewName("add.jsp");
		return model;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView login(ModelAndView model, User user) {
		logger.debug("参数: {}", user);
		try {
			service.add(user);
		} catch (Exception e) {
			logger.error("error: ", e);
		}
		model.addObject("users", service.list());
		model.setViewName("add.jsp");
		return model;
	}
}
