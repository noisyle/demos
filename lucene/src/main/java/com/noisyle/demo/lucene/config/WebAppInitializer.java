package com.noisyle.demo.lucene.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		MultipartConfigElement multipartConfig = new MultipartConfigElement("/tmp", 25 * 1024 * 1024,
				125 * 1024 * 1024, 1 * 1024 * 1024);
		registration.setMultipartConfig(multipartConfig);
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {characterEncodingFilter(), hiddenHttpMethodFilter() };
	}
	
	private Filter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	private Filter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

}
