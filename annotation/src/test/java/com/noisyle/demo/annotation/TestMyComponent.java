package com.noisyle.demo.annotation;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyComponent {
	private static ApplicationContext context;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("spring-context.xml");
	}

	@Test
	public void testRun() {
		MyComponent component = (MyComponent) context.getBean(MyComponent.class);
		component.run(new MyEntity(1, "a"));
	}

	@Test
	public void testRunThrow() {
		MyComponent component = (MyComponent) context.getBean(MyComponent.class);
		try {
			component.runThrow(new MyEntity(2, "b"));
		} catch (Exception e) {
		}
	}
	
}
