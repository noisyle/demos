package com.noisyle.demo.springdemo;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.noisyle.demo.springdemo.entity.TaskExecute;
import com.noisyle.demo.springdemo.service.TaskService;

public class TaskTest {
	final static private Logger logger = LoggerFactory.getLogger(TaskTest.class);
	
	@Test
	public void testTask() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
		TaskService service = ctx.getBean(TaskService.class);
		List<TaskExecute> executes = service.findAllExecuteImmediately();
		for(TaskExecute e: executes){
			logger.info("{}:\t{}", e.getAutotask().getName(), e.getExecutetime());
		}
	}
}
