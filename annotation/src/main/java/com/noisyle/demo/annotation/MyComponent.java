package com.noisyle.demo.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
	private static final Logger logger = LoggerFactory.getLogger(MyComponent.class);
	
	@MyAnnotation
	public String run(MyEntity entity) {
		logger.info("run() : " + entity.toString());
		return "success";
	}
	
	@MyAnnotation(pk="code")
	public void runThrow(MyEntity entity) {
		logger.info("runThrow()");
		throw new RuntimeException();
	}
}
