package com.noisyle.demo.sqlinject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackages = "com.noisyle.demo.sqlinject.controller")
public class WebConfig extends WebMvcConfigurationSupport {
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.jsp");
	}

}
